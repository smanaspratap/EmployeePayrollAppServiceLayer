package com.bridgelabz.employeepayrollapp.controller;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.dto.ResponseDTO;
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
 * Section 2 - UC3: All responses are now wrapped in a generic ResponseDTO
 * to provide consistent, structured API responses to the client.
 *
 * The Controller delegates all business logic to the Service Layer
 * injected via @Autowired.
 *
 * Base URL: /employeepayrollservice
 *
 * @author Manas
 * @version 1.0
 */
@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    // Service layer injected via Spring Dependency Injection
    @Autowired
    private IEmployeePayrollService employeePayrollService;

    /** Retrieves all stored employee payroll records wrapped in ResponseDTO */
    @GetMapping("/")
    public ResponseEntity<ResponseDTO<List<EmployeePayrollData>>> getEmployeePayrollData() {
        List<EmployeePayrollData> empList = employeePayrollService.getEmployeePayrollData();
        ResponseDTO<List<EmployeePayrollData>> response =
                new ResponseDTO<>("All Employee Payroll Data fetched successfully!", empList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /** Retrieves a single employee payroll record by ID wrapped in ResponseDTO */
    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO<EmployeePayrollData>> getEmployeePayrollDataById(
            @PathVariable int empId) {
        EmployeePayrollData data = employeePayrollService.getEmployeePayrollDataById(empId);
        ResponseDTO<EmployeePayrollData> response =
                new ResponseDTO<>("Employee data fetched for ID: " + empId, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /** Creates a new employee record and returns the saved data wrapped in ResponseDTO */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<EmployeePayrollData>> addEmployeePayrollData(
            @Valid @RequestBody EmployeePayrollDTO payrollDTO) {
        EmployeePayrollData data = employeePayrollService.createEmployeePayrollData(payrollDTO);
        ResponseDTO<EmployeePayrollData> response =
                new ResponseDTO<>("Employee Payroll Data added successfully!", data);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /** Updates an existing employee record and returns the updated data in ResponseDTO */
    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO<EmployeePayrollData>> updateEmployeePayrollData(
            @PathVariable int empId,
            @Valid @RequestBody EmployeePayrollDTO payrollDTO) {
        EmployeePayrollData updatedData = employeePayrollService.updateEmployeePayrollData(empId, payrollDTO);
        if (updatedData != null) {
            ResponseDTO<EmployeePayrollData> response =
                    new ResponseDTO<>("Employee Payroll Data updated successfully!", updatedData);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        ResponseDTO<EmployeePayrollData> response =
                new ResponseDTO<>("Employee with ID " + empId + " not found!", null);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /** Deletes an employee record by ID and confirms deletion via ResponseDTO */
    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO<String>> deleteEmployeePayrollData(@PathVariable int empId) {
        employeePayrollService.deleteEmployeePayrollData(empId);
        ResponseDTO<String> response =
                new ResponseDTO<>("Employee with ID " + empId + " deleted successfully!", "Deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
