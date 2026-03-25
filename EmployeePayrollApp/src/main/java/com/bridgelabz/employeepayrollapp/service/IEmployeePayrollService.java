package com.bridgelabz.employeepayrollapp.service;

import com.bridgelabz.employeepayrollapp.dto.EmployeePayrollDTO;
import com.bridgelabz.employeepayrollapp.model.EmployeePayrollData;
import java.util.List;

/**
 * IEmployeePayrollService defines the contract for all business operations
 * related to Employee Payroll management.
 *
 * Any class implementing this interface must provide concrete implementations
 * for fetching, creating, updating, and deleting employee payroll records.
 *
 * @author Manas
 * @version 1.0
 */
public interface IEmployeePayrollService {

    /** Retrieves all employee payroll records */
    List<EmployeePayrollData> getEmployeePayrollData();

    /** Retrieves a single employee payroll record by employee ID */
    EmployeePayrollData getEmployeePayrollDataById(int empId);

    /** Creates and stores a new employee payroll record from DTO */
    EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO payrollDTO);

    /** Updates an existing employee payroll record identified by empId */
    EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO payrollDTO);

    /** Deletes an employee payroll record by employee ID */
    void deleteEmployeePayrollData(int empId);
}
