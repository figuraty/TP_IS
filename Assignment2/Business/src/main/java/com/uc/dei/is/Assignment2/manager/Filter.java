package com.uc.dei.is.Assignment2.manager;

import java.util.Date;

public class Filter {
    private String name;
    private String category;
    private String country;
    private Date intialInsertionDate;
    private Date finalInsertionDate;
    private float intitialPriceRange;
    private float finalPriceRange;

    public Filter(String name, String category, String country, Date intialInsertionDate, Date finalInsertionDate, float intitialPriceRange, float finalPriceRange) {
        this.name = name;
        this.category = category;
        this.country = country;
        this.intialInsertionDate = intialInsertionDate;
        this.finalInsertionDate = finalInsertionDate;
        this.intitialPriceRange = intitialPriceRange;
        this.finalPriceRange = finalPriceRange;
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

    public Date getIntialInsertionDate() {
        return intialInsertionDate;
    }

    public void setIntialInsertionDate(Date intialInsertionDate) {
        this.intialInsertionDate = intialInsertionDate;
    }

    public Date getFinalInsertionDate() {
        return finalInsertionDate;
    }

    public void setFinalInsertionDate(Date finalInsertionDate) {
        this.finalInsertionDate = finalInsertionDate;
    }

    public float getIntitialPriceRange() {
        return intitialPriceRange;
    }

    public void setIntitialPriceRange(float intitialPriceRange) {
        this.intitialPriceRange = intitialPriceRange;
    }

    public float getFinalPriceRange() {
        return finalPriceRange;
    }

    public void setFinalPriceRange(float finalPriceRange) {
        this.finalPriceRange = finalPriceRange;
    }
}
