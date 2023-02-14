package feature.database.client;

import feature.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private String createClient;
    private String getByIdClient;
    private String setNameClient;
    private String deleteByIdClient;
    private String allClients;
    private String selectMaxIdSt;


    public ClientService() {
        createClient = "INSERT INTO client (name) VALUES (?)";
        getByIdClient = "SELECT name FROM client WHERE id = ?";
        setNameClient = "UPDATE client SET name = ? WHERE id = ?";
        deleteByIdClient = "DELETE FROM client WHERE id = ?";
        allClients = "SELECT id, name FROM client";
        selectMaxIdSt = "SELECT max(id) AS maxId FROM client";
    }

    public long create(String name) {
        long id = 0;
        try (Connection connection = Database.getInstance().getConnection()) {
            try (PreparedStatement pstCreateClient = connection.prepareStatement(createClient)) {
                pstCreateClient.setString(1, name);
                pstCreateClient.executeUpdate();
                try (PreparedStatement pstSelectMaxIdSt = connection.prepareStatement(selectMaxIdSt)) {
                    try (ResultSet rs = pstSelectMaxIdSt.executeQuery()) {
                        rs.next();
                        id = rs.getLong("maxId");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getById(long id) {
        String name = null;
        try (Connection connection = Database.getInstance().getConnection()) {
            try (PreparedStatement pstGetByIdClient = connection.prepareStatement(getByIdClient)) {
                pstGetByIdClient.setLong(1, id);
                try (ResultSet rs = pstGetByIdClient.executeQuery()) {
                    rs.next();
                    name = rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public void setName(long id, String name) {
        try (Connection connection = Database.getInstance().getConnection()) {
            try (PreparedStatement pstSetNameClient = connection.prepareStatement(setNameClient)) {
                pstSetNameClient.setString(1, name);
                pstSetNameClient.setLong(2, id);
                pstSetNameClient.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        try (Connection connection = Database.getInstance().getConnection()) {
            try (PreparedStatement pstDeleteByIdClient = connection.prepareStatement(deleteByIdClient)) {
                pstDeleteByIdClient.setLong(1, id);
                pstDeleteByIdClient.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listAll() {
        ArrayList<Client> clientsList = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {
            try (PreparedStatement pstAllClients = connection.prepareStatement(allClients)) {
                try (ResultSet rs = pstAllClients.executeQuery()) {
                    while (rs.next()) {
                        Client client = new Client(rs.getLong("id"), rs.getString("name"));
                        clientsList.add(client);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientsList;
    }
}
