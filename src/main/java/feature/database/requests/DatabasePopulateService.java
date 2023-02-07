package feature.database.requests;

import feature.database.Database;
import feature.database.template.Project;
import feature.database.template.ProjectWorker;
import feature.database.template.Worker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static void main(String[] args) {
        String[] sqlRequests;
        String[] queries;
        Worker[] workers = new Worker[]{
                new Worker("Dima", "1993-09-30", "Trainee", 500),
                new Worker("Den", "1995-02-20", "Trainee", 400),
                new Worker("Sem", "1994-03-20", "Junior", 1500),
                new Worker("Max", "1996-06-30", "Junior", 1100),
                new Worker("Alex", "1992-08-25", "Middle", 3000),
                new Worker("Nik", "1990-08-30", "Senior", 5000),
                new Worker("Riz", "1991-05-01", "Senior", 5000),
                new Worker("Set", "1992-06-30", "Middle", 2500),
                new Worker("Ostin", "1999-06-30", "Middle", 2000),
                new Worker("Luck", "1998-06-30", "Trainee", 500)
        };
        String[] clients = new String[]{"Liza", "Sik", "Nolan", "Ady", "Lean"};
        Project[] projects = new Project[]{
                new Project(1 , "2020-05-13", "2024-06-12"),
                new Project( 1 , "2022-05-13", "2023-06-12"),
                new Project( 1 , "2022-05-13", "2022-07-12"),
                new Project( 2 , "2020-05-13", "2025-06-12"),
                new Project( 3 , "2019-05-13", "2023-06-12"),
                new Project( 3 , "2021-05-13", "2025-06-12"),
                new Project( 4 , "2022-05-13", "2025-06-12"),
                new Project( 4 , "2018-05-13", "2023-06-12"),
                new Project( 5 , "2019-05-13", "2022-06-12"),
                new Project( 5 , "2020-05-13", "2025-06-12")
        };
        ProjectWorker[] projectWorkers = new ProjectWorker[]{
                new ProjectWorker( 1 , 2),
                new ProjectWorker( 1 , 1),
                new ProjectWorker( 1 , 3),
                new ProjectWorker( 1 , 10),
                new ProjectWorker( 1 , 9),
                new ProjectWorker( 2 , 5),
                new ProjectWorker( 2 , 4),
                new ProjectWorker( 2 , 7),
                new ProjectWorker( 3 , 8),
                new ProjectWorker( 4 , 9),
                new ProjectWorker( 5 , 6),
                new ProjectWorker( 6 , 3),
                new ProjectWorker( 7 , 4),
                new ProjectWorker( 8 , 5),
                new ProjectWorker( 9 , 6),
                new ProjectWorker( 10 , 7)
        };

        try {
            String initDatabaseFileName = "./sql/populate_db.sql";
            sqlRequests = String.join("\n", Files.readAllLines(Paths.get(initDatabaseFileName))).split(";");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (Connection connection = Database.getInstance().getConnection();
             PreparedStatement pst1 = connection.prepareStatement(sqlRequests[0]);
             PreparedStatement pst2 = connection.prepareStatement(sqlRequests[1]);
             PreparedStatement pst3 = connection.prepareStatement(sqlRequests[2]);
             PreparedStatement pst4 = connection.prepareStatement(sqlRequests[3])) {

            for (Worker worker: workers) {
                pst1.setString(1, worker.getName());
                pst1.setString(2, worker.getDate());
                pst1.setString(3, worker.getLevel());
                pst1.setInt(4, worker.getSalary());
                pst1.addBatch();
            }
            pst1.executeBatch();

            for (String client: clients) {
                pst2.setString(1, client);
                pst2.addBatch();
            }
            pst2.executeBatch();

            for (Project project: projects) {
                pst3.setInt(1, project.getClient());
                pst3.setString(2, project.getStartDate());
                pst3.setString(3, project.getFinishDate());
                pst3.addBatch();
            }
            pst3.executeBatch();

            for (ProjectWorker projectWorker: projectWorkers) {
                pst4.setInt(1, projectWorker.getProject());
                pst4.setInt(2, projectWorker.getWorker());
                pst4.addBatch();
            }
            pst4.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
