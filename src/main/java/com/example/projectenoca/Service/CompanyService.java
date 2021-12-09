package com.example.projectenoca.Service;

import com.example.projectenoca.Exceptions.CompanyNotFoundException;
import com.example.projectenoca.Model.Company;
import com.example.projectenoca.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public void flush(){
    companyRepository.flush();
    }
    public void save( Company company ){
        companyRepository.save(company);
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Company findById(int id){

        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public Company updateById(int id ,Company newCompany){

        return companyRepository.findById(id).map( company -> {
            company.setName(newCompany.getName());
            company.setLocation(newCompany.getLocation());
            company.setType(newCompany.getType());
            return companyRepository.save(company);
        })
                .orElseGet(() -> {
                    newCompany.setId(id);
                    return companyRepository.save(newCompany);
                });
    }

    public void deleteById(int id){

       companyRepository.deleteById(id);
    }

}
