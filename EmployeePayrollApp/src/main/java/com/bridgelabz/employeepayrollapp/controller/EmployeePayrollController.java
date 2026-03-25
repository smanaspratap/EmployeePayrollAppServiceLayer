package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeePayrollController is a REST Controller that handles all HTTP requests
 * for the Employee Payroll Service.
 *
 * Section 2 - UC1: Updated to use EmployeePayrollDTO for incoming requests
 * and EmployeePayrollData (Model) for outgoing responses.
 *
 * The Controller is responsible ONLY for routing requests.
 * Model creation is temporarily inside the controller (to be moved to Service in UC2).
 *
 * Base URL: /employeepayrollservice
 *
 * @author Manas
 * @version 1.0
 */
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    // Temporary in-memory list acting as data store (will move to service layer)
    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

    /** Returns all employee payroll records */
    @GetMapping("/")
    public ResponseEntity<List<EmployeePayrollData>> getEmployeePayrollData() {
        return new ResponseEntity<>(employeePayrollList, HttpStatus.OK);
    }

    /** Returns a single employee record by ID */
    @GetMapping("/get/{empId}")
    public ResponseEntity<EmployeePayrollData> getEmployeePayrollDataById(@PathVariable int empId) {
        EmployeePayrollData data = employeePayrollList.stream()
                .filter(e -> e.employeeId == empId)
                .findFirst()
                .orElse(null);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    /** Creates a new employee record using validated DTO input */
    @PostMapping("/create")
    public ResponseEntity<EmployeePayrollData> addEmployeePayrollData(
            @Valid @RequestBody EmployeePayrollDTO payrollDTO) {
        EmployeePayrollData data = new EmployeePayrollData(
                employeePayrollList.size() + 1,
                payrollDTO.name,
                payrollDTO.salary
        );
        employeePayrollList.add(data);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    /** Updates an existing employee record by ID using DTO input */
    @PutMapping("/update/{empId}")
    public ResponseEntity<EmployeePayrollData> updateEmployeePayrollData(
            @PathVariable int empId,
            @Valid @RequestBody EmployeePayrollDTO payrollDTO) {
        for (EmployeePayrollData emp : employeePayrollList) {
            if (emp.employeeId == empId) {
                emp.name = payrollDTO.name;
                emp.salary = payrollDTO.salary;
                return new ResponseEntity<>(emp, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** Deletes an employee record by ID */
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable int empId) {
        employeePayrollList.removeIf(e -> e.employeeId == empId);
        return new ResponseEntity<>("Employee with ID " + empId + " deleted successfully!", HttpStatus.OK);
    }
}
