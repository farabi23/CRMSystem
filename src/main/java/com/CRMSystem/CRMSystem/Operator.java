package com.CRMSystem.CRMSystem;

import jakarta.persistence.*;

@Entity
@Table(name = "Operator")
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "surname", length = 250)
    private String surname;

    @Column(name = "department", length = 250)
    private String department;

    public Operator() {

    }

    public Operator(Long id, String name, String surname, String department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
