package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * EmployeePayrollService is the concrete implementation of IEmployeePayrollService.
 *
 * Section 2 - UC3: The service layer now properly stores, retrieves, updates,
 * and deletes Employee Payroll Data in an in-memory List.
 *
 * An AtomicInteger is used for thread-safe ID generation.
 * A Logger is added to trace all operations for debugging purposes.
 *
 * Note: Data is stored in memory (List). On application restart, all data is lost.
 * Persistent DB storage will be introduced in a future module.
 *
 * @author Manas
 * @version 1.0
 */
@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    // Logger for tracing service layer operations
    private static final Logger log = Logger.getLogger(EmployeePayrollService.class.getName());

    // Thread-safe auto-incrementing ID generator for employee records
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    // In-memory list storing all employee payroll records during the session
    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

    /** Fetches all employee records from the in-memory list and logs the operation */
    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        log.info("Fetching all employee payroll records. Total: " + employeePayrollList.size());
        return employeePayrollList;
    }

    /** Searches and returns a single employee record by ID, returns null if not found */
    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        log.info("Fetching employee with ID: " + empId);
        return employeePayrollList.stream()
                .filter(e -> e.employeeId == empId)
                .findFirst()
                .orElse(null);
    }

    /** Creates a new employee record from DTO, assigns a unique ID, and stores it in the list */
    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO payrollDTO) {
        int newId = idCounter.incrementAndGet();
        EmployeePayrollData data = new EmployeePayrollData(newId, payrollDTO.name, payrollDTO.salary);
        employeePayrollList.add(data);
        log.info("Created new employee: " + data);
        return data;
    }

    /** Finds employee by ID, updates their name and salary from DTO, and returns updated record */
    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO payrollDTO) {
        log.info("Updating employee with ID: " + empId);
        for (EmployeePayrollData emp : employeePayrollList) {
            if (emp.employeeId == empId) {
                emp.name = payrollDTO.name;
                emp.salary = payrollDTO.salary;
                log.info("Updated employee: " + emp);
                return emp;
            }
        }
        log.warning("Employee with ID " + empId + " not found for update.");
        return null;
    }

    /** Removes employee record matching the given ID from the in-memory list */
    @Override
    public void deleteEmployeePayrollData(int empId) {
        boolean removed = employeePayrollList.removeIf(e -> e.employeeId == empId);
        if (removed) {
            log.info("Deleted employee with ID: " + empId);
        } else {
            log.warning("Employee with ID " + empId + " not found for deletion.");
        }
    }
}
