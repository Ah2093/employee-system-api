package com.company.employee.services;

import com.company.employee.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployee();

    EmployeeDto findEmployeeById(long id);

    Boolean deleteEmployee(long id);

    EmployeeDto updateEmployee(long id, EmployeeDto employeeDto);
}
