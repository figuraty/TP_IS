package com.uc.dei.is.Assignment2.data.manager;

import com.uc.dei.is.Assignment2.data.models.Item;
import com.uc.dei.is.Assignment2.data.models.User;

import javax.persistence.*;
import java.util.Set;

public class teste {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("IS_Assignment2");

    public static void main(String[] args) {

//        addUser("Francisco", "abilio@gmail.com", "teste", "Portugal");
//        updateUser(1, "Abilio", "abilio@gmail.com", "cenas", "Portugal");
//        getUser("abilio@gmail.com");
//        deleteUser(1);
//        addItem("abilio@gmail.com", "lapis", "utensilios", "Portugal", "ref");
        updateItem(1, "Caneta", "utensilios", "Portugal", "ref");

        ENTITY_MANAGER_FACTORY.close();
    }

    public static void addUser(String name, String email, String password, String country){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            String[] passEncrypted = EncryptionManager.encprypt(password);
            if (passEncrypted!= null && passEncrypted.length == 2) {
                User user = new User(name, email, passEncrypted[1], country);
                user.setDk(passEncrypted[0]);
                em.persist(user);
                et.commit();
            }
        } catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void updateUser(int userId, String name, String email, String password, String country){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            String[] passEncrypted = EncryptionManager.encprypt(password);
            if (passEncrypted!= null && passEncrypted.length == 2) {
                User user = em.find(User.class, userId);
                user.setName(name);
                user.setEmail(email);
                user.setPassword(passEncrypted[1]);
                user.setCountry(country);
                user.setDk(passEncrypted[0]);
                em.merge(user);
                et.commit();
            }
        } catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void deleteUser(int userId){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            User user = em.find(User.class, userId);
            for (Item item : user.getItems()) {
                em.remove(item);
            }
            em.remove(user);
            et.commit();
        } catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void addItem(String email, String name, String category, String country, String picture){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            String query = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> tq = em.createQuery(query, User.class)
                    .setParameter("email", email);
            User user = tq.getSingleResult();
            Item item = new Item(name, category, country, picture, user);
            em.persist(item);
            et.commit();
        } catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void updateItem(int itemId, String name, String category, String country, String picture){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Item item = em.find(Item.class, itemId);
            item.setName(name);
            item.setCategory(category);
            item.setCountry(country);
            item.setPicture(picture);
            em.merge(item);
            et.commit();
        } catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

//    public static void deleteItem(int itemId){
//
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction et = null;
//
//        try {
//            et = em.getTransaction();
//            et.begin();
//            Item item = em.find(Item.class, itemId);
//            item.setName(name);
//            item.setCategory(category);
//            item.setCountry(country);
//            item.setPicture(picture);
//            em.merge(item);
//            et.commit();
//        } catch (Exception ex){
//            if(et != null)
//                et.rollback();
//            ex.printStackTrace();
//        } finally {
//            em.close();
//        }
//    }





    public static void getUser(String userEmail){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            String query = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> tq = em.createQuery(query, User.class)
                    .setParameter("email", userEmail);
            User user = tq.getSingleResult();
            Set<Item> items = user.getItems();
            System.out.println(EncryptionManager.decprypt(user.getDk(), user.getPassword()));
            System.out.println(items.size());
        } catch (NoResultException ex){
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }
}
