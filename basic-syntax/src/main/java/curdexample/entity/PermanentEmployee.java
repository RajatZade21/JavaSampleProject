package curdexample.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "permanent_employee")
public class PermanentEmployee extends Employee{

    private BigDecimal salary;

    @ElementCollection
    @CollectionTable(
            name = "employee_benefits",
            joinColumns = @JoinColumn(name = "employee_id")
    )
    @Column(name = "benefit")
    private List<String> benefits = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "employee_trainings",
            joinColumns = @JoinColumn(name = "employee_id")
    )
    @Column(name = "training")
    private List<String> trainings = new ArrayList<>();
}
