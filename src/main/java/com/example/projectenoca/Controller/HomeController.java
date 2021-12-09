package com.example.projectenoca.Controller;

import com.example.projectenoca.Exceptions.CompanyNotFoundException;
import com.example.projectenoca.Model.Company;
import com.example.projectenoca.Repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final CompanyRepository companyRepository;

    @GetMapping("/")
    public String goIndex() {
        return "redirect:/index";
    }


    @RequestMapping("/index")
    public String index( Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        return "index";
    }

    @RequestMapping("/company/{companyId}/employees")
    public String getEmployees(@PathVariable int companyId , Model model) {

        Company company = companyRepository.
                findById(companyId).
                orElseThrow( ()  -> new CompanyNotFoundException(companyId) );

        model.addAttribute("employees", company.getEmployees());
        model.addAttribute("name",company.getName());
        model.addAttribute("id",companyId);
        return "index-employee";
    }
}

