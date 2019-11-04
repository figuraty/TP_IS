package com.uc.dei.is.Assignment2.database.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "dk", nullable = false)
    private String dk;

    @OneToMany(mappedBy="user")
    @Column(name = "items")
    private Set<Item> items;

    public User() {
    }

    public User(String name, String email, String password, String country) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.items = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDk() {
        return dk;
    }

    public void setDk(String dk) {
        this.dk = dk;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
