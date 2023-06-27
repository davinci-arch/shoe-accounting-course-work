package com.example.dao.repository.model;

import com.example.dao.poolconnection.PoolConnection;
import com.example.dao.poolconnection.PoolConnectionSingleton;
import com.example.dao.repository.ConvertorEnum;
import com.example.model.*;
import com.example.model.types.SlippersType;
import com.example.model.types.TypeFootwear;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SlippersRepository implements Repository {

    private static final Logger LOG = LoggerFactory.getLogger(SlippersRepository.class);

    private PoolConnection pool;

    public SlippersRepository() {

        try {
            pool = PoolConnectionSingleton.getInstance();
        } catch (SQLException e) {
            LOG.debug("get pool connection");
        }

    }

    @Override
    public Optional<FootwearAbstract> getFootwearById(Long id) {

        String query = "SELECT * FROM slippers WHERE id_footwear = " + id;

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
        return new Slippers(resultSet.getInt("id_footwear"),
                ConvertorEnum.getCategory(resultSet.getString("category")),
                ConvertorEnum.getType(resultSet.getString("type"), SlippersType.values()),
                resultSet.getString("model"),
                resultSet.getString("brand"),
                BigDecimal.valueOf(Long.parseLong(resultSet.getString("price"))),
                ConvertorEnum.getSeason(resultSet.getString("seasons")),
                resultSet.getString("color"),
                resultSet.getString("appointment"),
                resultSet.getInt("size")
        );
    }


    @Override
    public List<FootwearAbstract> getFootwearByCategory(Category category) {
        String query = "SELECT * FROM slippers WHERE category = '" + category.getCategory() + "'";

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
        String query = "SELECT * FROM slippers WHERE type = '" + type.getType() + "'";

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
        String query = "INSERT INTO slippers(category, type, model, brand, price," +
                " color, appointment, size, seasons) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection connection = pool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, footwear.getCategory().getCategory());
            preparedStatement.setString(2, footwear.getType().getType());
            preparedStatement.setString(3, footwear.getModel());
            preparedStatement.setString(4, footwear.getBrand());
            preparedStatement.setString(5, footwear.getPrice().toString());


            preparedStatement.setString(6, ((Slippers) footwear).getColor());
            preparedStatement.setString(7, ((Slippers) footwear).getAppointment());
            preparedStatement.setInt(8, ((Slippers) footwear).getSize());
            preparedStatement.setString(9, footwear.getSeason().getSeasonName());


            preparedStatement.execute();

            connection.commit();
        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");

        }
    }

    @Override
    public void remove(int id) {

        String query = "delete from slippers where id_footwear = ?";

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
        String query = "update slippers " +
                "set category=?, type=?, model=?, brand=?, price=?, color=?, appointment=?, size=?, seasons=?" +
                " where id_footwear = ?";

        try (Connection connection = pool.getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, footwear.getCategory().getCategory());
            preparedStatement.setString(2, footwear.getType().getType());
            preparedStatement.setString(3, footwear.getModel());
            preparedStatement.setString(4, footwear.getBrand());
            preparedStatement.setString(5, footwear.getPrice().toString());

            preparedStatement.setString(6, ((Slippers) footwear).getColor());
            preparedStatement.setString(7, ((Slippers) footwear).getAppointment());
            preparedStatement.setInt(8, ((Slippers) footwear).getSize());
            preparedStatement.setString(9, footwear.getSeason().getSeasonName());
            preparedStatement.setInt(10, footwear.getId());

            preparedStatement.execute();

            connection.commit();

        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");
        }
    }

    @Override
    public FootwearAbstract getLast() {

        String query = "select * from slippers order by id_footwear desc limit 1";

        FootwearAbstract footwearAbstract = null;

        try (Connection connection = pool.getConnection()) {

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                footwearAbstract = mapFootwear(resultSet);

            }

        } catch (SQLException e) {
            LOG.warn("cannot get a connection from pool");
        }

        return footwearAbstract;
    }
}
