package streamapis;

import common.layer.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DummyDataService;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;


public class StreamOnMap {
    public static Logger logger = LoggerFactory.getLogger(StreamOnMap.class);
    public static void main(String[] args){
        DummyDataService service = new DummyDataService();
        List<Employee> list = service.getEmployeeList();

        //Highest Salary Per Department
        Map<String,Employee> highestSalaryMap = list.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.maxBy(
                                                Comparator.comparingDouble(Employee::getSalary)
                                        ),
                                        Optional::get)
                        )
                );

        //Second Salary Per Department
        Map<String,Employee> secondHighestSalaryMap = list.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        employees -> employees.stream()
                                                .sorted(Comparator.comparingDouble(Employee::getSalary))
                                                .skip(1)
                                                .findFirst()
                                                .orElse(null)
                                )
                        )
                );

        //grouping by - department
        //return as map
        Map<String,List<Employee>> map0 = list.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        //grouping by - departments
        //sorted - no of employees
        //return as map
        Map<String,Long> map = list.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()))
                .entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        for(Map.Entry<String,Long> entry : map.entrySet()){
            logger.debug(entry.getKey() + " " + entry.getValue());
        }

        //get average salary for each department
        //Collectors.groupingBy
        //Collectors.averagingDouble
        Map<String,Double> map1 = list.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingDouble(Employee::getSalary)));


        //get the highest salary for each department
        //Collectors.collectingAndThen
        //Collectors.maxBy
        Map<String,Employee> map2 = list.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.collectingAndThen(maxBy(Comparator.comparingDouble(Employee::getSalary)),Optional::get)));

        //get sum of salary for all department
        //summingDouble
        Map<String,Double> map3 = list.stream().collect(Collectors.groupingBy(Employee::getDepartment,summingDouble(Employee::getSalary)));
        for(Map.Entry<String,Double> entry : map3.entrySet()){
            logger.debug(entry.getKey() + " : " + entry.getValue());
        }

        //get repeated numbers from this array
        List<Integer> numbers = Arrays.asList(1,4,9,2,4,7,8,23,1,9);
        numbers.stream().collect(Collectors.groupingBy(Function.identity(),counting())).entrySet().stream().filter(x -> x.getValue() > 1).map(Map.Entry::getKey).collect(toList()).forEach(System.out::println);

        //
        String name = "RajatZade";
        Map<Character,Long> map4 = name.chars().mapToObj(x -> (char)x).collect(groupingBy(Function.identity(),counting()));

        Predicate<Integer> b = (x) -> x % 2 == 0;
        Map<Boolean,List<Integer>> oddEven = numbers.stream().collect(partitioningBy(b));
        logger.debug(oddEven.get(true).toString());

        Map<String, Double> combineCSIT = list.stream()
                .collect(Collectors.groupingBy(
                        t -> !t.getDepartment().equals("IT") ? t.getDepartment() : "CS",
                        Collectors.summingDouble(Employee::getSalary)
                ));

        System.out.println(combineCSIT);

        list.stream()
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    LinkedHashMap::new,
                    Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(
                        Map.Entry.<String, Long>comparingByKey()
                                .thenComparing(Map.Entry.comparingByValue())
                 )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1,e2) -> e1,
                        LinkedHashMap::new
                ));


        Map<String, Employee> result = list.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)),
                                Optional::get
                        )
                ));
    }


}
