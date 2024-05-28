package common.layer.models;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee{
    String name;
    Integer id;
    String department;
    Date dateOfJoining;
    Double salary;
    public Employee(String name,Integer id){
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "Employee{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", dateOfJoining=" + sdf.format(dateOfJoining) +
                ", department='" + department + '\'' +
                ", salary='" + salary + '\'' +
                '}';
    }
}