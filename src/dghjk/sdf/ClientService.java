package sdf;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    private final PreparedStatement createClient;
    private final PreparedStatement getByIdClient;
    private final PreparedStatement setNameClient;
    private final PreparedStatement deleteByIdClient;
    private final PreparedStatement allClients;
    private final PreparedStatement selectMaxIdSt;

    public ClientService(Connection connection) {
        try {
            createClient = connection.prepareStatement("INSERT INTO client (name) VALUES (?)");

            getByIdClient = connection.prepareStatement("SELECT name FROM client WHERE id = ?");

            setNameClient = connection.prepareStatement("UPDATE client SET name = ? WHERE id = ?");

            deleteByIdClient = connection.prepareStatement("DELETE FROM client WHERE id = ?");

            allClients = connection.prepareStatement("SELECT id, name FROM client");

            selectMaxIdSt = connection.prepareStatement("SELECT max(id) AS maxId FROM client");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long create(String name){
        long id;
        try{
            createClient.setString(1, name);
            createClient.executeUpdate();

            ResultSet rs = selectMaxIdSt.executeQuery();
            rs.next();
            id = rs.getLong("maxId");
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public String getById(long id) {
        String name;
        try {
            getByIdClient.setLong(1, id);
            ResultSet rs = getByIdClient.executeQuery();
            rs.next();
            name = rs.getString("name");
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return name;
    }

    public void setName(long id, String name) {
        try {
            setNameClient.setString(1, name);
            setNameClient.setLong(2, id);
            setNameClient.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteById(long id) {
        try {
            deleteByIdClient.setLong(1, id);
            deleteByIdClient.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Client> listAll() {
        ArrayList<Client> allClintsList = new ArrayList<>();

        try(ResultSet rs = allClients.executeQuery()) {
            while (rs.next()) {
                allClintsList.add(new Client(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allClintsList;
    }
}
