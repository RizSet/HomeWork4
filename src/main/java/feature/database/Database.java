package feature.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final Database INSTANCE = new Database();
    private Connection connection;

    private Database() {
        try {
            String connectionUrl = "jdbc:h2:./home_work6";
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String sql){
        try (Statement st = connection.createStatement();){
            return st.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                String connectionUrl = "jdbc:h2:./home_work6";
                connection = DriverManager.getConnection(connectionUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }return connection;
    }

    public static Database getInstance() {
        return INSTANCE;
    }
}
