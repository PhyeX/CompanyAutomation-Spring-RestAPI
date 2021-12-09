package com.example.projectenoca.Model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String type;

    @NotNull
    private String location;

    @OneToMany(cascade = CascadeType.ALL )
    @JoinColumn(name = "project_id")
    private List<Employee> employees;

    public Company(String name,String type, String location) {
        this.name = name;
        this.type = type;
        this.location = location;
    }
}

