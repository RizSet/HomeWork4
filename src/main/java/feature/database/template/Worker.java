package feature.database.template;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Worker {
    String name;
    String date;
    String level;
    int salary;
}
