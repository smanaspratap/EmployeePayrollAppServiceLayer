package com.bridgelabz.employeepayrollapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeePayrollController is a REST Controller that handles all HTTP requests
 * for the Employee Payroll Service.
 *
 * Section 1 - UC2: Demonstrates basic REST API endpoints (GET, POST, PUT, DELETE)
 * without using DTO or Service Layer. All data is handled inline.
 *
 * Base URL: /employeepayrollservice
 *
 * @author Manas
 * @version 1.0
 */
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    // Temporary in-memory storage for testing REST endpoints
    private List<String> employeeList = new ArrayList<>();

    /** Returns a welcome message for the Employee Payroll Service */
    @GetMapping("/")
    public String getServiceMessage() {
        return "Welcome to Employee Payroll Service!";
    }

    /** Returns an employee by their ID (index-based for now) */
    @GetMapping("/get/{id}")
    public String getEmployeeById(@PathVariable int id) {
        return "Fetching Employee with ID: " + id;
    }

    /** Creates a new employee record from request body */
    @PostMapping("/create")
    public String createEmployee(@RequestBody String employeeData) {
        employeeList.add(employeeData);
        return "Employee Created: " + employeeData;
    }

    /** Updates an existing employee record by ID */
    @PutMapping("/update")
    public String updateEmployee(@RequestBody String employeeData) {
        return "Employee Updated: " + employeeData;
    }

    /** Deletes an employee record by their ID */
    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return "Employee with ID " + id + " deleted successfully!";
    }
}
