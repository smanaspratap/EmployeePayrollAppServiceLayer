package com.bridgelabz.employeepayrollapp.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * EmployeePayrollDTO (Data Transfer Object) is used to carry employee data
 * between the client and the server over REST API calls.
 *
 * This DTO contains validation annotations to ensure data integrity
 * before it reaches the Service or Model layer.
 *
 * Fields: name, salary
 *
 * @author Manas
 * @version 1.0
 */
public class EmployeePayrollDTO {

    /** Employee name - must not be empty */
    @NotEmpty(message = "Employee name cannot be empty!")
    public String name;

    /** Employee salary - must be at least 500 */
    @Min(value = 500, message = "Minimum wage must be 500!")
    @NotNull(message = "Employee salary cannot be null!")
    public double salary;

    /** Returns a readable string representation of the DTO */
    @Override
    public String toString() {
        return "EmployeePayrollDTO{name='" + name + "', salary=" + salary + "}";
    }
}
