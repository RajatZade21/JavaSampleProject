package curdexample.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // HOD is one employee
    @OneToOne
    @JoinColumn(name = "hod_id")
    private Employee hod;

    // Department has many employees
    @OneToMany
    @JoinColumn(name = "department_id")
    private List<Employee> employees = new ArrayList<>();
}
