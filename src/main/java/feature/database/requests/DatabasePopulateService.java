package feature.database.requests;

import feature.database.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {
    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection();
             Statement st = connection.createStatement()) {
            String initDatabaseFileName = "./sql/populate_db.sql";
            String sql = String.join("\n",
                    Files.readAllLines(Paths.get(initDatabaseFileName))
            );
            st.executeUpdate(sql);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
