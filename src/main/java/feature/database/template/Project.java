package feature.database.template;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    int client;
    String startDate;
    String finishDate;
}
