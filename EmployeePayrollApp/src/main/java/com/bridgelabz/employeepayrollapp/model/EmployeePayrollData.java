package com.bridgelabz.employeepayrollapp.model;

/**
 * EmployeePayrollData is the Model class representing the Employee entity
 * within the application.
 *
 * This class holds employee data that will eventually be persisted to
 * the database via JPA. For now, it is used as an in-memory model.
 *
 * Fields: employeeId, name, salary
 *
 * @author Manas
 * @version 1.0
 */
public class EmployeePayrollData {

    /** Unique identifier for each employee */
    public int employeeId;

    /** Full name of the employee */
    public String name;

    /** Monthly salary of the employee */
    public double salary;

    /** Default no-argument constructor */
    public EmployeePayrollData() {
    }

    /** Parameterized constructor to initialize all fields */
    public EmployeePayrollData(int employeeId, String name, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.salary = salary;
    }

    /** Returns a readable string representation of the model */
    @Override
    public String toString() {
        return "EmployeePayrollData{employeeId=" + employeeId + ", name='" + name + "', salary=" + salary + "}";
    }
}
