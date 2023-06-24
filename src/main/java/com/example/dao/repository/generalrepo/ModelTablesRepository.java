package com.example.dao.repository.generalrepo;

import com.example.dao.poolconnection.PoolConnection;
import com.example.dao.poolconnection.PoolConnectionSingleton;
import com.example.dao.repository.model.BootsRepository;
import com.example.dao.repository.model.SandalsRepository;
import com.example.dao.repository.model.ShoesRepository;
import com.example.dao.repository.model.SlippersRepository;
import com.example.model.Category;
import com.example.model.FootwearAbstract;
import com.example.model.Sandals;
import com.example.model.types.BootsType;
import com.example.model.types.TypeFootwear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ModelTablesRepository {

    private final static Logger LOG = LoggerFactory.getLogger(ModelTablesRepository.class);

    private PoolConnection poolConnection;

    public ModelTablesRepository() {

        try {
            poolConnection = PoolConnectionSingleton.getInstance();
        } catch (SQLException e) {
            LOG.warn("cannot get connection instance");
        }

    }


    public int getCountOfCategory(Category category) {

        String query = "SELECT category FROM %s WHERE category = '" + category.getCategory() + "'";

        ExecutorService executor = Executors.newFixedThreadPool(4);

        int count = 0;

        try (Connection connection = poolConnection.getConnection()) {

            Statement statement = connection.createStatement();

            count += executor.submit(new AllItemsCategory(String.format(query, "shoes"), statement)).get();
            count += executor.submit(new AllItemsCategory(String.format(query, "slippers"), statement)).get();
            count += executor.submit(new AllItemsCategory(String.format(query, "sandals"), statement)).get();
            count += executor.submit(new AllItemsCategory(String.format(query, "boots"), statement)).get();


            connection.commit();
        } catch (SQLException e) {
            LOG.info("query: " + query);
            LOG.warn("cannot set a connection");
        } catch (ExecutionException | InterruptedException e) {
            LOG.warn("cannot get a future thread");
        }
        return count;
    }

    private static final class AllItemsCategory implements Callable<Integer> {

        private int count = 0;
        private String query;

        private Statement statement;

        public AllItemsCategory(String query, Statement statement) {
            this.query = query;
            this.statement = statement;
        }
        @Override
        public Integer call() throws Exception {

            ResultSet resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                count++;
            }

            return count;
        }
    }


    public List<FootwearAbstract> getAllItems() {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        String query = "SELECT * FROM %s";

        List<FootwearAbstract> items = new ArrayList<>();

        try (Connection connection = poolConnection.getConnection()) {

            Statement statement = connection.createStatement();

            items.addAll(executorService.submit(new AllItems(query, "boots", statement)).get());
            items.addAll(executorService.submit(new AllItems(query, "sandals", statement)).get());
            items.addAll(executorService.submit(new AllItems(query, "shoes", statement)).get());
            items.addAll(executorService.submit(new AllItems(query, "slippers", statement)).get());

            connection.commit();
        } catch (ExecutionException | SQLException | InterruptedException e) {
            LOG.info("query: " + query);
            LOG.info("sql wrong");
        }

        return items;
    }

    private static final class AllItems implements Callable<List<FootwearAbstract>> {

        private String query;

        private Statement statement;

        private String tableName;

        public AllItems(String query, String tableName, Statement statement) {
            this.query = String.format(query, tableName);
            this.statement = statement;
            this.tableName = tableName;
        }
        @Override
        public List<FootwearAbstract> call() throws Exception {

            ResultSet resultSet = statement.executeQuery(query);

            List<FootwearAbstract> items = new ArrayList<>();

            while (resultSet.next()) {

                switch (tableName) {

                    case "boots" -> items.add(BootsRepository.mapFootwear(resultSet));
                    case "sandals" -> items.add(SandalsRepository.mapFootwear(resultSet));
                    case "shoes" -> items.add(ShoesRepository.mapFootwear(resultSet));
                    case "slippers" -> items.add(SlippersRepository.mapFootwear(resultSet));

                }
            }


            return items;
        }


    }
}
