package com.example.projectenoca;

import com.example.projectenoca.Model.Company;
import com.example.projectenoca.Model.Employee;
import com.example.projectenoca.Repository.CompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(CompanyRepository companyRepository) {

        return args -> {
                if(companyRepository.findAll().size() == 0) {
                    Company company = new Company("Enoca","Software","Karabük");
                    Employee bilbo = new Employee("Bilbo Baggins", "burglar", 5000,"bilbo@gmail.com");
                    Employee frado = new Employee("Frodo Baggins", "thief", 100,"frado@gmail.com");
                    company.setEmployees(Arrays.asList(bilbo, frado));
                    log.info("Preloading " + companyRepository.save(company));
                }

        };
    }
}
