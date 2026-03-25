package com.bridgelabz.employeepayrollapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Employee Payroll Spring Boot Application.
 * This class bootstraps the entire Spring context and starts the embedded server.
 *
 * @author Manas
 * @version 1.0
 */
@SpringBootApplication
public class EmployeePayrollAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeePayrollAppApplication.class, args);
        System.out.println("Employee Payroll App Started Successfully!");
    }
}
