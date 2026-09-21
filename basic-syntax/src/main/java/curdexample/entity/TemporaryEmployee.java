package curdexample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "temporary_employee")
public class TemporaryEmployee extends Employee {
    private BigDecimal salary;
}
