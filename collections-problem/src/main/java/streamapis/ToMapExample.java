package streamapis;

import common.layer.models.Employee;
import services.DummyDataService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ToMapExample {

    public static void main(String[] args){
        DummyDataService service = new DummyDataService();
        List<Employee> list = service.getEmployeeList();

        Map<String, String> result = list.stream()
                .collect(Collectors.toMap(
                        Employee::getDepartment,
                        Employee::getName,
                        (existing, newValue) -> existing + "," + newValue,
                        LinkedHashMap::new
                ));

        /*
        * Collectors.toMap(
            KEY,
            VALUE,
            (OLD_VALUE, NEW_VALUE) -> WHAT_TO_DO
          )
        * */

        // SUM
        //(existing, newValue) -> existing + newValue

        // MAX
        //(existing, newValue) -> Math.max(existing, newValue)

        // MIN
        //(existing, newValue) -> Math.min(existing, newValue)

        // KEEP FIRST
        //(existing, newValue) -> existing

        // KEEP LAST
        //(existing, newValue) -> newValue
    }
}
