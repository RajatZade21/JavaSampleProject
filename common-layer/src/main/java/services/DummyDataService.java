package services;

import common.layer.models.Department;
import common.layer.models.Employee;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyDataService {

    public List<Employee> getEmployeeList() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Employee> list = new ArrayList<>();
        try{
            list.add(Employee.builder().id(101).name("Rajat").department("IT").salary(220000.0).dateOfJoining(sdf.parse("2017-10-01")).build());
            list.add(Employee.builder().id(106).name("Mayuri").department("CS").salary(200000.0).dateOfJoining(sdf.parse("2018-12-25")).build());
            list.add(Employee.builder().id(104).name("Panda").department("IT").salary(300000.0).dateOfJoining(sdf.parse("2016-01-26")).build());
            list.add(Employee.builder().id(108).name("Aarya").department("ME").salary(350000.0).dateOfJoining(sdf.parse("2024-01-24")).build());
            list.add(Employee.builder().id(120).name("Neeti").department("IT").salary(2000000.0).dateOfJoining(sdf.parse("2021-06-15")).build());
            list.add(Employee.builder().id(123).name("Suchita").department("ME").salary(100000.0).dateOfJoining(sdf.parse("2022-02-02")).build());
            list.add(Employee.builder().id(111).name("Usha").department("IT").salary(20000.0).dateOfJoining(sdf.parse("2021-07-06")).build());
            list.add(Employee.builder().id(115).name("Ruchu").department("CS").salary(4500000.0).dateOfJoining(sdf.parse("2017-05-11")).build());
            list.add(Employee.builder().id(107).name("Om").department("IT").salary(150000.0).dateOfJoining(sdf.parse("2019-11-09")).build());
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return list;
    }

    public List<Department> getDepartment(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Department> list = new ArrayList<>();
        try{
            List<Employee> list1 = new ArrayList<>();
            list1.add(Employee.builder().id(101).name("Rajat").department("IT").dateOfJoining(sdf.parse("2017-10-01")).build());
            list1.add(Employee.builder().id(106).name("Mayuri").department("IT").dateOfJoining(sdf.parse("2018-12-25")).build());
            list1.add(Employee.builder().id(104).name("Panda").department("IT").dateOfJoining(sdf.parse("2016-01-26")).build());
            list.add(Department.builder().departmentId(1).name("IT").employeeList(list1).build());

            List<Employee> list2 = new ArrayList<>();
            list2.add(Employee.builder().id(108).name("Aarya").department("ME").dateOfJoining(sdf.parse("2024-01-24")).build());
            list2.add(Employee.builder().id(120).name("Neeti").department("ME").dateOfJoining(sdf.parse("2021-06-15")).build());
            list2.add(Employee.builder().id(123).name("Suchita").department("ME").dateOfJoining(sdf.parse("2022-02-02")).build());
            list.add(Department.builder().departmentId(2).name("ME").employeeList(list2).build());

            List<Employee> list3 = new ArrayList<>();
            list3.add(Employee.builder().id(111).name("Usha").department("CS").dateOfJoining(sdf.parse("2021-07-06")).build());
            list3.add(Employee.builder().id(115).name("Ruchu").department("CS").dateOfJoining(sdf.parse("2017-05-11")).build());
            list3.add(Employee.builder().id(107).name("Om").department("CS").dateOfJoining(sdf.parse("2019-11-09")).build());
            list.add(Department.builder().departmentId(3).name("CS").employeeList(list3).build());
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        return list;
    }
}
