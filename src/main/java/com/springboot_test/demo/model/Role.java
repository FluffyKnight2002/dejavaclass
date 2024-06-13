package com.springboot_test.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private List<User> users = new ArrayList<>();

}
