package streamapis;

import common.layer.models.Department;
import common.layer.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DummyDataService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamOnList {
    private static Logger logger = LoggerFactory.getLogger(StreamOnList.class);

    public static void main(String[] args){
        DummyDataService service = new DummyDataService();
        List<Employee> employeeList = service.getEmployeeList();
        List<Department> departmentList = service.getDepartment();

        //Get Employee,Salary > 20000
        List<Employee> list2 = employeeList.stream().filter(x -> x.getSalary() > 200000).collect(Collectors.toList());
        logger.info("Get Employee,Salary > 20000");
        logger.debug(list2.toString());

        //sorting with 2 attributes
        //Comparator.comparing
        //thenComparing
        employeeList.sort(Comparator.comparing(Employee::getDepartment).thenComparing(Employee::getSalary));

        //Get Employee who joined 1st
        //Compare date using compareTo()
        //for desceding
        Optional<Employee> employee1 = employeeList.stream().sorted((x, y) -> x.getDateOfJoining().compareTo(y.getDateOfJoining())).findFirst();
        logger.debug(employee1.get().toString());

        //find 3rd employee
        //skip
        //findFirst
        Optional<Employee> employee2 = employeeList.stream().sorted((x, y) -> x.getDateOfJoining().compareTo(y.getDateOfJoining())).skip(2).findFirst();
        logger.debug(employee2.get().toString());

        //get name of all employee
        //map
        List<String> listOfName = employeeList.stream().map(x -> x.getName()).collect(Collectors.toList());

        //Comparator.comparing
        List<Employee> sortedList = employeeList.stream().sorted(Comparator.comparing(x -> x.getSalary())).collect(toList());

        //example for reverse sorting
        //y compareTo x
        List<Employee> reverseSortedList = employeeList.stream().sorted((x,y) -> y.getSalary().compareTo(x.getSalary())).toList();

        //get list of employees from department
        //flatmap
        List<Employee> list3 = departmentList.stream().flatMap(x -> x.getEmployeeList().stream()).collect(toList());

        //get max salary
        //max
        OptionalDouble salary = employeeList.stream().mapToDouble(Employee::getSalary).max();

        //get total salary of employees
        //reduce
        OptionalDouble totalSalary = employeeList.stream().mapToDouble(Employee::getSalary).reduce(Double::sum);
        logger.debug(String.valueOf(totalSalary.getAsDouble()));

        List oddList = Arrays.asList(1,2,3,4,5,6,7,8,9).stream().filter(x -> x%2==1).collect(toList());

        //get unique elements from list
        //distinct
        List list4 = Arrays.asList(1,2,3,4,2,1,7,3,4).stream().distinct().collect(toList());


        String str = " Hi, I am Rajat and I am a programmer. ";
        List<String> list = Arrays.asList(str.split("\\W"));
        List<String> list5 = list.stream().collect(groupingBy(Function.identity(),counting())).entrySet().stream().filter(x -> x.getValue() > 1 && !x.getKey().isBlank()).map(Map.Entry::getKey).collect(toList());
        logger.debug(list5.toString());

        Integer[] arr = new Integer[]{1,3,8,2,7,6,9};
        Arrays.asList(arr).stream().sorted((x,y) -> y.compareTo(x)).forEach(System.out::println);

        //Check if list is null then return list of names
        //Optional.ofNullable().map().orElse()
        List<String> names = Optional.ofNullable(employeeList).map(x -> x.stream().map(Employee::getName).collect(Collectors.toList())).orElse(Collections.EMPTY_LIST);

        //startsWith
        Arrays.asList("Rajat","Mayuri","Zade","Ram").stream().filter(x -> x.startsWith("Ra")).forEach(System.out::print);

        Arrays.asList("Rajat","Mayuri","Zade","Ram").stream().map(x -> x.toUpperCase()).collect(toList());


        List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);

        boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
        boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
        boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);


        IntStream.range(10, 20).forEach(System.out::println);
    }
}
