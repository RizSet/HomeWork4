package sdf;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        Connection connectionDb = ConnectionDb.getInstanse().getConnection();
        ClientService clientService = new ClientService(connectionDb);
    }
}
