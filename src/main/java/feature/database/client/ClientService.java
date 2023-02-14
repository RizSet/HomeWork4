package feature.database.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private PreparedStatement createClient;
    private PreparedStatement getByIdClient;
    private PreparedStatement setNameClient;
    private PreparedStatement deleteByIdClient;
    private PreparedStatement allClients;
    private PreparedStatement selectMaxIdSt;

    public ClientService(Connection connection) {
        try {
            createClient = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");
            getByIdClient = connection.prepareStatement("SELECT name FROM client WHERE id = ?");
            setNameClient = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
            deleteByIdClient = connection.prepareStatement("DELETE FROM client WHERE id = ?");
            allClients = connection.prepareStatement("SELECT id, name FROM client");
            selectMaxIdSt = connection.prepareStatement("SELECT max(id) AS maxId FROM client");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long create(String name) {
        long id = 0;
        try {
            createClient.setString(1, name);
            createClient.executeUpdate();
            try (ResultSet rs = selectMaxIdSt.executeQuery()) {
                rs.next();
                id = rs.getLong("maxId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getById(long id) {
        String name = null;
        try {
            getByIdClient.setLong(1, id);
            try (ResultSet rs = getByIdClient.executeQuery()) {
                rs.next();
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    public void setName(long id, String name) {
        try {
            setNameClient.setString(1, name);
            setNameClient.setLong(2, id);
            setNameClient.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteById(long id) {
        try {
            deleteByIdClient.setLong(1, id);
            deleteByIdClient.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listAll() {
        ArrayList<Client> clientsList = new ArrayList<>();

        try (ResultSet rs = allClients.executeQuery()) {
            while (rs.next()) {
                Client client = new Client(rs.getLong("id"), rs.getString("name"));
                clientsList.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientsList;
    }
}
