package com.example.projectenoca.Service;

import com.example.projectenoca.Exceptions.EmployeeNotFoundException;
import com.example.projectenoca.Model.Employee;
import com.example.projectenoca.Repository.CompanyRepository;
import com.example.projectenoca.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public Employee findById( int id ){
       return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void save( Employee employee ){
        employeeRepository.save(employee);
    }

    public void delete( Employee employee ){
        employeeRepository.delete(employee);
    }

    public Employee updateById( int id ,Employee newEmployee ){

        return employeeRepository.findById(id).map( employee -> {
            employee.setName(newEmployee.getName());
            employee.setSalary(newEmployee.getSalary());
            employee.setSurname(newEmployee.getSurname());
            return employeeRepository.save(employee);
        })
                .orElseGet( () -> {
                    newEmployee.setId(id);
                    return employeeRepository.save(newEmployee);
                });
    }



}
