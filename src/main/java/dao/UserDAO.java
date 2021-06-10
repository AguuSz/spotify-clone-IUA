package main.java.dao;

import main.java.conf.DataSourceFactory;
import main.java.models.User;

import javax.sql.DataSource;

public class UserDAO {
    DataSource dataSource = DataSourceFactory.getMySQLDataSource();

}
