package streamapis;

import common.layer.models.Department;
import common.layer.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.DummyDataService;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

        //get name of all empployee
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
    }
}
