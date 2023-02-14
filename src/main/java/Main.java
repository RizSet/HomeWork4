import feature.database.Database;
import feature.database.client.Client;
import feature.database.client.ClientService;
import feature.database.migration.DatabaseInitService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        new DatabaseInitService().initDb();

        try (Connection connection = Database.getInstance().getConnection()) {
            ClientService clientService = new ClientService(connection);
            long id = clientService.create("Lera");
            System.out.println(id);
            System.out.println(clientService.getById(id));
            clientService.setName(id, "Anna");
            System.out.println(clientService.getById(id));
            clientService.deleteById(id - 1);
            List<Client> clients = clientService.listAll();
            for (Client client : clients) {
                System.out.println("client = " + client.getId() + " " + client.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}