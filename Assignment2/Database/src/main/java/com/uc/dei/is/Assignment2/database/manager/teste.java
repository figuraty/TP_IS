package com.uc.dei.is.Assignment2.database.manager;

import com.uc.dei.is.Assignment2.database.models.Item;
import com.uc.dei.is.Assignment2.database.models.User;

import javax.persistence.*;
import java.util.Set;

public class teste {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("IS_Assignment2");

    public static void main(String[] args) {

//        addUser("adsads", "abilio@gmail.com", "merda", "Portugal");
//        addItem("adsads", "abilio@gmail.com", "merda", "Portugal", new User());
        getUser(1);

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

    public static void addItem(String name, String category, String country, String picture, User user){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();

            String query = "SELECT u FROM User u WHERE u.id = :userID";
            TypedQuery<User> tq = em.createQuery(query, User.class);
            tq.setParameter("userID", 1);
            User user2 = tq.getSingleResult();

            Item item = new Item(name, category, country, picture, user2);
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



    public static void getUser(int id){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT u FROM User u WHERE u.id = :userID";

        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("userID", id);
        User user = null;

        try {
            user = tq.getSingleResult();
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
