package feature.database.requests;

import feature.database.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DatabasePopulateService {
    public static void main(String[] args) {
        try {
            String initDatabaseFileName = "./sql/populate_db.sql";
            String sql = String.join("\n",
                    Files.readAllLines(Paths.get(initDatabaseFileName))
            );
            Database.getInstance().executeUpdate(sql);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
