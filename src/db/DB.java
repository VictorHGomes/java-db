package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    public static Connection getConnection(){
        if (conn == null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e){
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        if (conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        try (InputStream fs = DB.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties props = new Properties();
            if (fs == null) {
                throw new FileNotFoundException("Arquivo db.properties não encontrado no classpath");
            }
            props.load(fs);
            return props;
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

}
