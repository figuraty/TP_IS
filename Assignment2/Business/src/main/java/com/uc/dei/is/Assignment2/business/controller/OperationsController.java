package com.uc.dei.is.Assignment2.business.controller;

import com.uc.dei.is.Assignment2.business.manager.DataTransactionManager;
import com.uc.dei.is.Assignment2.business.manager.EncryptionManager;
import com.uc.dei.is.Assignment2.business.manager.Filter;
import com.uc.dei.is.Assignment2.data.models.Item;
import com.uc.dei.is.Assignment2.data.models.User;

import java.util.Date;
import java.util.List;

public class OperationsController {

    public static int login(String email, String password){
        User user = DataTransactionManager.getUser(email);
        if (user != null) {
            if (EncryptionManager.decprypt(user.getDk(), user.getPassword()).equals(password))
                return user.getId();
        }
        return 0;
    }

    public static int home(String name, String email, String password, String country){
        return DataTransactionManager.addUser(name, email, password, country);
    }

    public static String getUserName(String userEmail){
        return DataTransactionManager.getUser(userEmail).getName();
    }

    public static User getUser(String userEmail){
        return DataTransactionManager.getUser(userEmail);
    }

    public static Item getItem(int itemID){
        return DataTransactionManager.getItem(itemID);
    }

    public static int register(String name, String email,String password,String country){
        return DataTransactionManager.addUser(name, email, password, country);
    }

    public static int updateUser(String userEmail, String name, String email, String password, String country){
        return DataTransactionManager.updateUser(userEmail, name, email, password, country);
    }

    public static void updateItem(int itemID, String name, String category, String country, String picture, Date initialInsertionDate, Float price){
        DataTransactionManager.updateItem(itemID, name, category, country, picture, initialInsertionDate, price);
    }

    public static List<Item> getLastThreeItems(){
        return DataTransactionManager.getLastThreeItems();
    }

    public static List<Item> getItemsByCategory(String category){
        return DataTransactionManager.getItemsByFilter(new Filter(null, category, null, false, null, null, 0, 0));
    }

    public static List<Item> getAllItems(){
        return DataTransactionManager.getAllItems();
    }

    public static List<Item> getItemsByFilter(Filter filter){
        return DataTransactionManager.getItemsByFilter(filter);
    }

    public static List<Item> getItemsByFilterOrdered(Filter filter, String sortingParameter, String sortingOrder){
        return DataTransactionManager.getItemsByFilterOrdered(filter, sortingParameter, sortingOrder);
    }

    public static void deleteUser(String email){
        DataTransactionManager.deleteUser(email);
    }

    public static void deleteItem(int itemID){
        DataTransactionManager.deleteItem(itemID);
    }
}
