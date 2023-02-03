import feature.database.Database;
import feature.database.requests.DatabaseQueryService;
import feature.database.template.*;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().findMaxProjectClient();
        List<MaxSalaryWorker> maxSalaryWorker = new DatabaseQueryService().findMaxSalaryWorker();
        List<LongestProject> longestProject = new DatabaseQueryService().findLongestProject();
        List<ProjectPrice> projectPrice = new DatabaseQueryService().findProjectPrice();
        List<YoungestEldestWorker> youngestEldestWorker = new DatabaseQueryService().findYoungestEldestWorker();
    }
}