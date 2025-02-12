package com.CRMSystem.CRMSystem;

import jakarta.persistence.*;

@Entity
@Table(name="Courses")
public class Courses {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "description", length = 450)
    private String description;

    @Column(name = "price")
    private int price;

    public Courses() {

    }



    public Courses(Long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
