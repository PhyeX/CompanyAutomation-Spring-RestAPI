package com.example.projectenoca.Controller;

import com.example.projectenoca.Model.Company;
import com.example.projectenoca.Model.Employee;
import com.example.projectenoca.Service.CompanyService;
import com.example.projectenoca.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/project")
@CrossOrigin
@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {

        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/company/{companyId}/employee")
    public ResponseEntity<List<Employee>> getEmployees(@PathVariable int companyId) {

        return new ResponseEntity<>( companyService
                .findById(companyId)
                .getEmployees() , HttpStatus.OK);
    }

    @PostMapping("/company/{companyId}/employee/save")
    public ResponseEntity<String> save(@RequestBody Employee employee, @PathVariable int  companyId ) {

        try {
            Company company = companyService.findById(companyId);
            company.getEmployees().add(employee);
            employeeService.save(employee);
            companyService.save(company);
            String nameOfEmployee = employee.getName();
            return new ResponseEntity<>(nameOfEmployee + " has added to "+company.getName(), HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/employee/{id}")
    public Employee update( @PathVariable int id ,@RequestBody Employee newEmployee ) {

        return employeeService.updateById(id,newEmployee);
    }


    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> delete(@PathVariable int id ) {
        try {

            Employee employee = employeeService.findById(id);
            String nameOfEmployee = employee.getName();
            employeeService.delete(employee);

            return new ResponseEntity<>(nameOfEmployee + " has deleted.", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
        }
    }



}