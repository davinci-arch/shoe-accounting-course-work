package com.example.dao.repository.model;

import com.example.dao.poolconnection.PoolConnection;
import com.example.dao.poolconnection.PoolConnectionSingleton;
import com.example.dao.repository.ConvertorEnum;
import com.example.model.Boots;
import com.example.model.Category;
import com.example.model.FootwearAbstract;
import com.example.model.Sandals;
import com.example.model.types.BootsType;
import com.example.model.types.TypeFootwear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BootsRepository implements Repository {

    private static final Logger LOG = LoggerFactory.getLogger(BootsRepository.class);

    private PoolConnection pool;

    public BootsRepository() {

        try {
            pool = PoolConnectionSingleton.getInstance();
        } catch (SQLException e) {
            LOG.debug("get pool connection");
        }

    }

    @Override
    public Optional<FootwearAbstract> getFootwearById(Long id) {

        String query = "SELECT * FROM boots WHERE id_footwear = " + id;

        Optional<FootwearAbstract> footwear = Optional.empty();

        try (Connection connection = pool.getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                footwear = Optional.ofNullable(mapFootwear(resultSet));
            }

            LOG.info("execute: " + query);

            connection.commit();
        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");
        }

        return footwear;
    }

    public static FootwearAbstract mapFootwear(ResultSet resultSet) throws SQLException {
        return new Boots(resultSet.getInt("id_footwear"),
                ConvertorEnum.getCategory(resultSet.getString("Category")),
                ConvertorEnum.getType(resultSet.getString("type"), BootsType.values()),
                resultSet.getString("model"),
                resultSet.getString("brand"),
                BigDecimal.valueOf(Long.parseLong(resultSet.getString("price"))),
                ConvertorEnum.getSeason(resultSet.getString("seasons")),
                ConvertorEnum.getFastener(resultSet.getString("fastener")),
                resultSet.getString("color"),
                resultSet.getString("material"),
                resultSet.getDouble("weight"),
                resultSet.getInt("size"));
    }


    @Override
    public List<FootwearAbstract> getFootwearByCategory(Category category) {
        String query = "SELECT * FROM boots WHERE category = '" + category.getCategory() + "'";

        List<FootwearAbstract> footwear = new ArrayList<>();

        try (Connection connection = pool.getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                footwear.add(mapFootwear(resultSet));
            }

            LOG.info("execute: " + query);

            connection.commit();
        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");
        }

        return footwear;
    }

    @Override
    public List<FootwearAbstract> getFootwearByType(TypeFootwear type) {
        String query = "SELECT * FROM boots WHERE type = '" + type.getType() + "'";

        List<FootwearAbstract> footwear = new ArrayList<>();

        try (Connection connection = pool.getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                footwear.add(mapFootwear(resultSet));
            }

            LOG.info("execute: " + query);

            connection.commit();
        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");
        }

        return footwear;
    }

    @Override
    public void save(FootwearAbstract footwear) {
        String query = "INSERT INTO boots(category, type, model, brand, price," +
                " fastener, color, material, weight, size, seasons) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = pool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, footwear.getCategory().getCategory());
            preparedStatement.setString(2, footwear.getType().getType());
            preparedStatement.setString(3, footwear.getModel());
            preparedStatement.setString(4, footwear.getBrand());
            preparedStatement.setString(5, footwear.getPrice().toString());

            if (footwear instanceof Boots) {
                preparedStatement.setString(6, ((Boots) footwear).getFaster().getTypeFastener());
                preparedStatement.setString(7, ((Boots) footwear).getColor());
                preparedStatement.setString(8, ((Boots) footwear).getMaterial());
                preparedStatement.setDouble(9, ((Boots) footwear).getWeight());
                preparedStatement.setInt(10, ((Boots) footwear).getSize());
                preparedStatement.setString(11, footwear.getSeason().getSeasonName());

            }

            preparedStatement.execute();

            connection.commit();
        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");

        }
    }

    @Override
    public void remove(int id) {

        String query = "delete from boots where id_footwear = ?";

        try (Connection connection = pool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");
        }
    }

    @Override
    public void update(FootwearAbstract footwear) {
        String query = "update sandals " +
                "set category=?, type=?, model=?, brand=?, price=?, fastener=?, color=?, material=?, weight=?, size=?, seasons=?" +
                " where id_footwear = ?";

        try (Connection connection = pool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, footwear.getCategory().getCategory());
            preparedStatement.setString(2, footwear.getType().getType());
            preparedStatement.setString(3, footwear.getModel());
            preparedStatement.setString(4, footwear.getBrand());
            preparedStatement.setString(5, footwear.getPrice().toString());

            preparedStatement.setString(6, ((Boots) footwear).getFaster().getTypeFastener());
            preparedStatement.setString(7, ((Boots) footwear).getColor());
            preparedStatement.setString(8, ((Boots) footwear).getMaterial());
            preparedStatement.setDouble(9, ((Boots) footwear).getWeight());
            preparedStatement.setInt(10, ((Boots) footwear).getSize());
            preparedStatement.setString(11, footwear.getSeason().getSeasonName());
            preparedStatement.setInt(12, footwear.getId());

            preparedStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");
        }
    }
}
