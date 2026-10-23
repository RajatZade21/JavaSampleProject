package common.layer.models;

import lombok.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee{
    String name;
    Integer id;
    String department;
    Date dateOfJoining;

    /*
        use BigDecimal over long when we need precise decimal arithmetic,
        especially for financial calculations such as payments, interest, taxes, and currency conversions.
            */
    //BigDecimal salary;
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