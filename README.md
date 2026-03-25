# Employee Payroll App - Spring Boot

## Overview
A Spring Boot REST API application for managing Employee Payroll data.
Built as part of BridgeLabz Full Stack Training.

## Tech Stack
- Java 11
- Spring Boot 2.4.0
- Spring Web (REST)
- Spring Data JPA
- MySQL Driver
- Spring Boot DevTools
- Bean Validation API
- Maven

## Project Structure
`
EmployeePayrollApp/
+-- src/main/java/com/bridgelabz/employeepayrollapp/
¦   +-- EmployeePayrollAppApplication.java
¦   +-- controller/
¦   ¦   +-- EmployeePayrollController.java
¦   +-- dto/
¦   ¦   +-- EmployeePayrollDTO.java
¦   +-- model/
¦   ¦   +-- EmployeePayrollData.java
¦   +-- service/
¦       +-- IEmployeePayrollService.java
¦       +-- EmployeePayrollService.java
+-- src/main/resources/
    +-- application.properties
`

## GitFlow Branch Strategy
- **master** - Production ready code (README only for now)
- **develop** - Integration branch
- **UC1-SpringBootSetup** - Spring project setup and REST Controller
- **UC2-DTOAndModel** - Introducing DTO and Model
- **UC3-ServicesLayer** - Introducing Services Layer
- **UC4-ServicesLayerStorage** - Services Layer storing data in memory

## API Endpoints
| Method | URL | Description |
|--------|-----|-------------|
| GET | /employeepayrollservice/ | Get all employees |
| GET | /employeepayrollservice/get/{id} | Get employee by ID |
| POST | /employeepayrollservice/create | Create employee |
| PUT | /employeepayrollservice/update/{id} | Update employee |
| DELETE | /employeepayrollservice/delete/{id} | Delete employee |

## CURL Test Commands
`ash
curl localhost:8080/employeepayrollservice/ -w "\n"
curl localhost:8080/employeepayrollservice/get/1 -w "\n"
curl -X POST -H "Content-Type: application/json" -d '{"name":"Lisa","salary":2000}' http://localhost:8080/employeepayrollservice/create -w "\n"
curl -X PUT -H "Content-Type: application/json" -d '{"name":"Lisa","salary":3000}' http://localhost:8080/employeepayrollservice/update/1 -w "\n"
curl -X DELETE localhost:8080/employeepayrollservice/delete/1 -w "\n"
`

## Setup Instructions
1. Clone the repository
2. Make sure MySQL is running on localhost:3306
3. Create database: CREATE DATABASE payroll_service;
4. Update pplication.properties with your MySQL credentials
5. Run: mvn spring-boot:run

## Author
Manas Pratap Singh - BridgeLabz Full Stack Training
