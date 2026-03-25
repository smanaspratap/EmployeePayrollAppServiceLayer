package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import com.bridgelabz.employeepayrollapp.service.IEmployeePayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * EmployeePayrollController is a REST Controller responsible for handling
 * all incoming HTTP requests for the Employee Payroll Service.
 *
 * Section 2 - UC2: The Controller no longer manages the Model directly.
 * All business logic and model management is delegated to the Service Layer
 * via Dependency Injection using the @Autowired annotation.
 *
 * This achieves a clean separation of concerns:
 * Controller -> handles HTTP, Service -> handles business logic.
 *
 * Base URL: /employeepayrollservice
 *
 * @author Manas
 * @version 1.0
 */
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    // Dependency Injection of the Service Layer into the Controller
    @Autowired
    private IEmployeePayrollService employeePayrollService;

    /** Retrieves all employee payroll records from the service layer */
    @GetMapping("/")
    public ResponseEntity<List<EmployeePayrollData>> getEmployeePayrollData() {
        return new ResponseEntity<>(employeePayrollService.getEmployeePayrollData(), HttpStatus.OK);
    }

    /** Retrieves a single employee payroll record by employee ID */
    @GetMapping("/get/{empId}")
    public ResponseEntity<EmployeePayrollData> getEmployeePayrollDataById(@PathVariable int empId) {
        return new ResponseEntity<>(employeePayrollService.getEmployeePayrollDataById(empId), HttpStatus.OK);
    }

    /** Accepts validated DTO and delegates employee creation to the service layer */
    @PostMapping("/create")
    public ResponseEntity<EmployeePayrollData> addEmployeePayrollData(
            @Valid @RequestBody EmployeePayrollDTO payrollDTO) {
        return new ResponseEntity<>(
                employeePayrollService.createEmployeePayrollData(payrollDTO), HttpStatus.CREATED);
    }

    /** Accepts validated DTO and delegates employee update to the service layer */
    @PutMapping("/update/{empId}")
    public ResponseEntity<EmployeePayrollData> updateEmployeePayrollData(
            @PathVariable int empId,
            @Valid @RequestBody EmployeePayrollDTO payrollDTO) {
        EmployeePayrollData updatedData = employeePayrollService.updateEmployeePayrollData(empId, payrollDTO);
        if (updatedData != null) {
            return new ResponseEntity<>(updatedData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** Delegates employee deletion to the service layer by employee ID */
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable int empId) {
        employeePayrollService.deleteEmployeePayrollData(empId);
        return new ResponseEntity<>(
                "Employee with ID " + empId + " deleted successfully!", HttpStatus.OK);
    }
}
