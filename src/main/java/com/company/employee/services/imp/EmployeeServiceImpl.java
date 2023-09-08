package com.company.employee.services.imp;

import com.company.employee.entity.Employee;
import com.company.employee.model.EmployeeDto;
import com.company.employee.repos.EmployeeRepo;
import com.company.employee.services.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepo employeeRepo ;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto,employee);
        employeeRepo.save(employee);
        return employeeDto ;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {

        List<Employee>employees = employeeRepo.findAll();
        return employees
                .stream()
                .map(employee ->new EmployeeDto(
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getEmail())
                )
                .toList();
    }

    @Override
    public EmployeeDto findEmployeeById(long id) {
        EmployeeDto dto =null;
        Employee employee =employeeRepo.findById(id).get();
        BeanUtils.copyProperties(employee,dto);
        return dto ;
    }

    @Override
    public Boolean deleteEmployee(long id) {
        employeeRepo.delete(employeeRepo.findById(id).get());
        return true ;
    }

    @Override
    public EmployeeDto updateEmployee(long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepo.findById(id).get();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employeeRepo.save(employee);
        return employeeDto;
    }
}

