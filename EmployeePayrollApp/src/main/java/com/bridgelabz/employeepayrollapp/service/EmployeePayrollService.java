package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * EmployeePayrollService is the concrete implementation of IEmployeePayrollService.
 *
 * Section 2 - UC2: This service layer takes over Model management responsibility
 * from the Controller layer. The Controller now delegates all business logic
 * to this service class.
 *
 * The @Service annotation marks this class as a Spring-managed bean,
 * allowing it to be injected into the Controller via @Autowired.
 *
 * Note: Data is NOT yet being stored persistently. The list is reset
 * each time the application restarts. Persistence via DB is a future use case.
 *
 * @author Manas
 * @version 1.0
 */
@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    // In-memory list acting as temporary data store (UC2 - no DB yet)
    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

    /** Fetches and returns the entire list of employee payroll records */
    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeePayrollList;
    }

    /** Finds and returns a single employee record by their ID */
    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        return employeePayrollList.stream()
                .filter(e -> e.employeeId == empId)
                .findFirst()
                .orElse(null);
    }

    /** Builds a new EmployeePayrollData object from DTO and adds it to the list */
    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO payrollDTO) {
        EmployeePayrollData data = new EmployeePayrollData(
                employeePayrollList.size() + 1,
                payrollDTO.name,
                payrollDTO.salary
        );
        employeePayrollList.add(data);
        return data;
    }

    /** Finds an employee by ID and updates their name and salary from the DTO */
    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO payrollDTO) {
        for (EmployeePayrollData emp : employeePayrollList) {
            if (emp.employeeId == empId) {
                emp.name = payrollDTO.name;
                emp.salary = payrollDTO.salary;
                return emp;
            }
        }
        return null;
    }

    /** Removes the employee record matching the given ID from the list */
    @Override
    public void deleteEmployeePayrollData(int empId) {
        employeePayrollList.removeIf(e -> e.employeeId == empId);
    }
}
