package com.uc.dei.is.Assignment2.database.manager;

import com.uc.dei.is.Assignment2.database.models.Item;
import com.uc.dei.is.Assignment2.database.models.User;

import javax.persistence.*;
import java.util.Set;

public class teste {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("IS_Assignment2");

    public static void main(String[] args) {

//        addUser("Francisco", "abilio@gmail.com", "teste", "Portugal");
//        addItem("abilio@gmail.com", "lapis", "utensilios", "Portugal", "ref");
//        updateUser("abilio@gmail.com", "Francisco", "abilio@gmail.com", "cenas", "Portugal");
//        getUser("abilio@gmail.com");
//        deleteUser("abilio@gmail.com");

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

    public static void updateUser(String userEmail, String name, String email, String password, String country){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            String[] passEncrypted = EncryptionManager.encprypt(password);
            if (passEncrypted!= null && passEncrypted.length == 2) {
                String query = "SELECT u FROM User u WHERE u.email = :email";
                TypedQuery<User> tq = em.createQuery(query, User.class)
                        .setParameter("email", userEmail);
                User user = tq.getSingleResult();
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

    public static void deleteUser(String email){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            String query = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> tq = em.createQuery(query, User.class)
                    .setParameter("email", email);
            User user = tq.getSingleResult();
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

//    public static void updateItem(String email, String name, String category, String country, String picture){
//
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction et = null;
//
//        try {
//            et = em.getTransaction();
//            et.begin();
//            String query = "SELECT u FROM User u WHERE u.email = :email";
//            TypedQuery<User> tq = em.createQuery(query, User.class)
//                    .setParameter("email", email);
//            User user = tq.getSingleResult();
//            Item item = new Item(name, category, country, picture, user);
//            em.persist(item);
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
