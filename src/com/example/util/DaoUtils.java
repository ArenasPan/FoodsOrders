package com.example.util;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Pan on 16/12/3.
 */
public class DaoUtils {

    private static DataSource source;
    private DaoUtils(){

    }

    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileReader(DaoUtils.class.getClassLoader().getResource("dbcp.property").getPath()));
            BasicDataSourceFactory factory = new BasicDataSourceFactory();
            source = factory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static DataSource getSource(){
        return source;
    }

    public static Connection getConnection(){
        try {
            return source.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
