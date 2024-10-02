package com.colak.springtutorial.commandline;

import com.hazelcast.function.SupplierEx;

import javax.sql.DataSource;
import java.sql.Connection;

public class NewConnectionProvider implements SupplierEx<Connection> {

    @Override
    public Connection getEx() throws Exception {
        DataSource dataSource = DataSourceProvider.getDataSource();
        return dataSource.getConnection();
    }
}
