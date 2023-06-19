package com.example.dao.poolconnection;

import java.sql.SQLException;

public class PoolConnectionSingleton {

    private static PoolConnection poolConnection;

    private PoolConnectionSingleton() {
    }

    public static PoolConnection getInstance() throws SQLException {

        if (poolConnection == null) {
            poolConnection = new PoolConnection();
        }

        return poolConnection;
    }
}
