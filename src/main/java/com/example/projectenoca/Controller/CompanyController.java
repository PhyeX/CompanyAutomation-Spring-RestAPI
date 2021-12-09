package com.example.projectenoca.Controller;

import com.example.projectenoca.Exceptions.CompanyNotFoundException;
import com.example.projectenoca.Model.Company;
import com.example.projectenoca.Model.Employee;
import com.example.projectenoca.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/company")
    public ResponseEntity<String> save(@RequestBody Company company) {

        try {
            companyService.flush();
            companyService.save(company);
            String companyName = company.getName();
            return new ResponseEntity<>(companyName + " has been added successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            log.info(e.getMessage());
            return new ResponseEntity<>("Unsuccessful", HttpStatus.OK);
        }
    }

    @GetMapping("/company/all")
    public List<Company> getAll() {
        return companyService.findAll();
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable int id) {
        return new ResponseEntity<>(companyService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/company/update/{id}")
    Company update(@RequestBody Company newCompany, @PathVariable int id) {
        return companyService.updateById(id,newCompany);
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable int id) {
        try {

            Company company = companyService.findById(id);
            String nameOfCompany = company.getName();
            companyService.deleteById(id);
            return new ResponseEntity<String>(nameOfCompany + " has deleted.", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>(new CompanyNotFoundException(id).getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
