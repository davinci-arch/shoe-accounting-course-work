package com.example.dao.repository.model;

import com.example.dao.poolconnection.PoolConnection;
import com.example.dao.poolconnection.PoolConnectionSingleton;
import com.example.dao.repository.ConvertorEnum;
import com.example.model.*;
import com.example.model.types.SandalsType;
import com.example.model.types.TypeFootwear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SandalsRepository implements Repository {

    private static final Logger LOG = LoggerFactory.getLogger(SandalsRepository.class);

    private PoolConnection pool;

    public SandalsRepository() {

        try {
            pool = PoolConnectionSingleton.getInstance();
        } catch (SQLException e) {
            LOG.debug("get pool connection");
        }

    }

    @Override
    public Optional<FootwearAbstract> getFootwearById(Long id) {

        String query = "SELECT * FROM sandals WHERE id_footwear = " + id;

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
        return new Sandals(resultSet.getInt("id_footwear"),
                ConvertorEnum.getCategory(resultSet.getString("Category")),
                ConvertorEnum.getType(resultSet.getString("type"), SandalsType.values()),
                resultSet.getString("model"),
                resultSet.getString("brand"),
                BigDecimal.valueOf(Long.parseLong(resultSet.getString("price"))),
                ConvertorEnum.getSeason(resultSet.getString("seasons")),
                ConvertorEnum.getFastener(resultSet.getString("fastener")),
                resultSet.getString("color"),
                resultSet.getString("appointment"),
                resultSet.getInt("size"));
    }


    @Override
    public List<FootwearAbstract> getFootwearByCategory(Category category) {
        String query = "SELECT * FROM sandals WHERE category = '" + category.getCategory() + "'";

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
        String query = "SELECT * FROM sandals WHERE type = '" + type.getType() + "'";

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
        String query = "INSERT INTO sandals(category, type, model, brand, price," +
                " fastener, color, appointment, size, seasons) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = pool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, footwear.getCategory().getCategory());
            preparedStatement.setString(2, footwear.getType().getType());
            preparedStatement.setString(3, footwear.getModel());
            preparedStatement.setString(4, footwear.getBrand());
            preparedStatement.setString(5, footwear.getPrice().toString());

            if (footwear instanceof Sandals) {

                preparedStatement.setString(8, ((Sandals) footwear).getFastener().getTypeFastener());
                preparedStatement.setString(8, ((Sandals) footwear).getColor());
                preparedStatement.setString(8, ((Sandals) footwear).getAppointment());
                preparedStatement.setInt(7, ((Sandals) footwear).getSize());
                preparedStatement.setString(6, footwear.getSeason().getSeasonName());

            }

            preparedStatement.execute();

            connection.commit();
        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");

        }
    }

    @Override
    public void remove(int id) {

        String query = "delete from sandals where id_footwear = ?";

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
                "set category=?, type=?, model=?, brand=?, price=?, fastener=?, color=?, appointment=?, size=?, seasons=?" +
                " where id_footwear = ?";

        try (Connection connection = pool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, footwear.getCategory().getCategory());
            preparedStatement.setString(2, footwear.getType().getType());
            preparedStatement.setString(3, footwear.getModel());
            preparedStatement.setString(4, footwear.getBrand());
            preparedStatement.setString(5, footwear.getPrice().toString());

            preparedStatement.setString(6, ((Sandals) footwear).getFastener().getTypeFastener());
            preparedStatement.setString(7, ((Sandals) footwear).getColor());
            preparedStatement.setString(8, ((Sandals) footwear).getAppointment());
            preparedStatement.setInt(9, ((Sandals) footwear).getSize());
            preparedStatement.setString(10, footwear.getSeason().getSeasonName());
            preparedStatement.setInt(11, footwear.getId());

            preparedStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");
        }
    }
}
