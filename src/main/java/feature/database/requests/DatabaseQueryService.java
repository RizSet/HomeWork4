package feature.database.requests;

import feature.database.Database;
import feature.database.template.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    Database database = Database.getInstance();

    private String getSql(String FileName) {
        try {
            return String.join("\n", Files.readAllLines(Paths.get(FileName)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> maxSalaryWorkerList = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(getSql("./sql/find_max_salary_worker.sql"))) {
                while (rs.next()) {
                    MaxSalaryWorker maxSalaryWorker = new MaxSalaryWorker();
                    maxSalaryWorker.setName(rs.getString("name"));
                    maxSalaryWorker.setSalary(rs.getInt("salary"));
                    maxSalaryWorkerList.add(maxSalaryWorker);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maxSalaryWorkerList;
    }

    public List<MaxProjectCountClient> findMaxProjectClient() {
        List<MaxProjectCountClient> maxProjectCountClientList = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(getSql("./sql/find_max_projects_client.sql"))) {
                while (rs.next()) {
                    MaxProjectCountClient maxProjectCountClient = new MaxProjectCountClient();
                    maxProjectCountClient.setName(rs.getString("name"));
                    maxProjectCountClient.setProjectCount(rs.getInt("project_count"));
                    maxProjectCountClientList.add(maxProjectCountClient);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return maxProjectCountClientList;
    }

    public List<LongestProject> findLongestProject() {
        List<LongestProject> longestProjectList = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(getSql("./sql/find_longest_project.sql"))) {
                while (rs.next()) {
                    LongestProject longestProject = new LongestProject();
                    longestProject.setName(rs.getString("name"));
                    longestProject.setMonthCount(rs.getInt("month_count"));
                    longestProjectList.add(longestProject);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return longestProjectList;
    }

    public List<YoungestEldestWorker> findYoungestEldestWorker() {
        List<YoungestEldestWorker> youngestEldestWorkerList = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(getSql("./sql/find_youngest_eldest_workers.sql"))) {
                while (rs.next()) {
                    YoungestEldestWorker youngestEldestWorker = new YoungestEldestWorker();
                    youngestEldestWorker.setType(rs.getString("type"));
                    youngestEldestWorker.setName(rs.getString("name"));
                    youngestEldestWorker.setBirthday(rs.getString("birthday"));
                    youngestEldestWorkerList.add(youngestEldestWorker);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return youngestEldestWorkerList;
    }

    public List<ProjectPrice> findProjectPrice() {
        List<ProjectPrice> projectPriceList = new ArrayList<>();
        try (Statement st = database.getConnection().createStatement()) {
            try (ResultSet rs = st.executeQuery(getSql("./sql/print_project_prices.sql"))) {
                while (rs.next()) {
                    ProjectPrice projectPrice = new ProjectPrice();
                    projectPrice.setName(rs.getString("name"));
                    projectPrice.setPrice(rs.getInt("price"));
                    projectPriceList.add(projectPrice);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projectPriceList;
    }
}
