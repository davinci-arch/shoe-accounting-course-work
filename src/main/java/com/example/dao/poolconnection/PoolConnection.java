package com.example.dao.poolconnection;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


import static com.example.configuration.ConfigurationDatabase.*;

public class PoolConnection extends MysqlDataSource {

    private final static Logger LOG = LoggerFactory.getLogger(PoolConnection.class);

    private BlockingQueue<Connection> poolConnection;


    public PoolConnection() throws SQLException {
        LOG.debug("create connections");

        poolConnection = new LinkedBlockingQueue<>();

        setUrl(URL);
        setUser(USERNAME);
        setPassword(PASSWORD);

        for (int i = 0; i < POOL_CONNECTION_AMOUNT; i++) {

            java.sql.Connection con = super.getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(java.sql.Connection.TRANSACTION_READ_COMMITTED);

            poolConnection.add(new Connection(con, poolConnection));

        }

    }

    @Override
    public java.sql.Connection getConnection() throws SQLException {
        LOG.debug("get connection object");
        return poolConnection.poll();
    }
}
