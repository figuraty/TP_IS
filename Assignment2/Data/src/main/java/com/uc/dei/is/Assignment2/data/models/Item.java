package com.uc.dei.is.Assignment2.data.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Item")
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date insertionDate;

    @Column(name = "price", nullable = false)
    private float price;

    @ManyToOne
    @JoinColumn(name="userID")
    private User user;

    public Item() {
        super();
    }

    public Item(String name, String category, String country, String picture, Date insertionDate, float price, User user) {
        super();
        this.name = name;
        this.category = category;
        this.country = country;
        this.picture = picture;
        this.insertionDate = insertionDate;
        this.price = price;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Date insertionDate) {
        this.insertionDate = insertionDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", country='" + country + '\'' +
                ", picture='" + picture + '\'' +
                ", insertionDate=" + insertionDate +
                ", price=" + price +
                ", user=" + user.getId() +
                '}';
    }
}
