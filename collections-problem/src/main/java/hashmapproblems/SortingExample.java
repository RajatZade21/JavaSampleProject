package hashmapproblems;

import common.layer.models.Employee;
import services.DummyDataService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortingExample {
    public static void main(String[] args){
        DummyDataService service = new DummyDataService();
        HashMap<String,List<Employee>> map = service.getDepartmentEmployee();

        // Sort the map by the size of the value lists (descending order)
        Map<String,List<Employee>> map2 = map.entrySet().stream().sorted(Comparator.comparingInt(x -> x.getValue().size())).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        System.out.println(map);
    }
}
