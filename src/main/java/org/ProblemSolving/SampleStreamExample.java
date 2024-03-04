package org.ProblemSolving;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

class Employee{
    String name;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Double getSalary() {
        return salary;
    }

    public Integer getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    String gender;
    Double salary;
    Integer age;
    String department;
    public Employee(Integer age,String name,String gender,Double salary,String department) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.salary = salary;
        this.department = department;
    }
}

class Department{
    List<Employee> employeeList;
}
public class SampleStreamExample {

    public static void main(String[] args){

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(20,"aaa", "Male",10000.0,"Developer"));
        employeeList.add(new Employee(30,"bbb", "Male",20000.0,"Tester"));
        employeeList.add(new Employee(35,"bbb", "FeMale",25000.0,"BAnalyst"));
        employeeList.add(new Employee(40,"ccc", "Male",30000.0,"Manager"));
        employeeList.add(new Employee(50,"ddd", "FeMale",40000.0,"QA team"));
        employeeList.add(new Employee(60,"eee", "Male",50000.0,"Developer"));
        List<Department> departmentList = new ArrayList<>();

        //example for filter
        List<Employee> filteredList = employeeList.stream().filter(x -> x.salary > 20000).collect(Collectors.toList());

        //example for map
        List<String> listofName = employeeList.stream().map(x -> x.name).collect(Collectors.toList());

        //example for sorting
        List<Employee> sortedList = employeeList.stream().sorted(Comparator.comparing(x -> x.salary)).collect(Collectors.toList());

        //example for sorting using comparator
        List<Employee> sortedList2 = employeeList.stream().sorted(Comparator.comparing(x -> x.salary)).collect(Collectors.toList());

        //example for reverse sorting
        List<Employee> reverseSortedList = employeeList.stream().sorted((x,y) -> y.salary.compareTo(x.salary)).toList();

        //example for flatmap -> return list from values
        List<Employee> employeeList2 = departmentList.stream().flatMap(x -> x.employeeList.stream()).collect(Collectors.toList());

        //example for flatmap -> return max salary
        OptionalDouble maxSalary = departmentList.stream().flatMap(x -> x.employeeList.stream()).mapToDouble(y -> y.salary).max();

        //example for reduce -> for joining element
        String finalString = Arrays.asList("Rajat","Zade").stream().reduce("0",(x, y)-> x + y);

        //sample for findFirst -> get 2nd largest number from list
        Optional<Integer> secondLargest = Arrays.asList(1,4,9,2,0,7,8,23).stream().sorted((x,y) -> y.compareTo(x)).skip(1).findFirst();
        System.out.println("second Largest - " + secondLargest);

        //get name of highest salary
        Optional<String> employeeName = employeeList.stream().sorted((x,y) ->  y.salary.compareTo(x.salary)).map(z -> z.name).findFirst();
        System.out.println(employeeName.get());

        //get average salary after group by department
        Map<String, Double> averageSalary = employeeList.stream().collect(groupingBy(Employee::getDepartment, averagingDouble(Employee::getSalary)));
        for(Map.Entry<String,Double> entry:averageSalary.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        //get highest salary after group by department
        Map<String, Employee> highestSalary = employeeList.stream().collect(groupingBy(Employee::getDepartment,collectingAndThen(maxBy(Comparator.comparingDouble(x -> x.getSalary())),Optional::get)));

        //get number of male and female
        Map<String,Long> genderCount = employeeList.stream().collect(groupingBy(Employee::getGender,counting()));
        for(Map.Entry<String,Long> entry:genderCount.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }


    }

}
