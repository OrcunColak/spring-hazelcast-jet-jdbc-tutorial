package com.colak.springtutorial.commandline;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceProvider implements ApplicationContextAware {

    private static DataSource dataSource;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        dataSource = applicationContext.getBean(DataSource.class);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
