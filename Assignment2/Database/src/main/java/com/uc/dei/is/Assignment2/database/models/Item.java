package com.uc.dei.is.Assignment2.database.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Item")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itemID", unique = true)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "picture", nullable = false)
    private String picture;

    @ManyToOne
    @JoinColumn(name="userID", nullable=false)
    private User user;

    public Item() {
    }

    public Item(String name, String category, String country, String picture, User user) {
        this.name = name;
        this.category = category;
        this.country = country;
        this.picture = picture;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
