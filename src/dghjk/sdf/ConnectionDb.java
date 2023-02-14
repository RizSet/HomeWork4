package sdf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    private static final ConnectionDb INSTANSE = new ConnectionDb();

    private Connection connection;

    private ConnectionDb() {
        try {
            String connectionUrl = "jdbc:h2:./MegaSoft";
            connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ConnectionDb getInstanse() {
        return INSTANSE;
    }


    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                String connectionUrl = "jdbc:h2:./MegaSoft";
                connection = DriverManager.getConnection(connectionUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
