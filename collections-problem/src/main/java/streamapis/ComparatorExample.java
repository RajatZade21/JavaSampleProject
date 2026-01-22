package streamapis;

import common.layer.models.Employee;
import services.DummyDataService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorExample {
    public static void main(String[] args){
        DummyDataService service = new DummyDataService();
        List<Employee> employeeList = service.getEmployeeList();

        //Creating comparator
        Comparator<Employee> comparator = (x,y) -> {
            //return Double.compare(x.getSalary(),y.getSalary());   //can use compare methods from wrapper classes
            return x.getName().compareTo(x.getName());
        };

        Collections.sort(employeeList,comparator);
        System.out.println(employeeList);
        Collections.sort(employeeList,comparator.reversed());
        System.out.println(employeeList);

        //finding students with max and min salaries
        Employee employeeMaxSalary = employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        Employee employeeMinSalary = employeeList.stream().min(Comparator.comparingDouble(Employee::getSalary)).get();

        List list = Arrays.asList(1,2,8,7,9,6,4,12,3);
        list.sort(Comparator.naturalOrder());
        System.out.println(list);
        list.sort(Comparator.reverseOrder());
        System.out.println(list);
    }
}
