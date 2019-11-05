package com.uc.dei.is.Assignment2.manager;


import com.uc.dei.is.Assignment2.data.models.Item;
import com.uc.dei.is.Assignment2.data.models.User;


import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

public class DataTransactionManager {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("IS_Assignment2");

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

    public static void addItem(String email, String name, String category, String country, String picture, float price){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Query query = em.createQuery("from User u where u.email = :email")
                    .setParameter("email", email);
            User user = (User) query.getSingleResult();
            Item item = new Item(name, category, country, picture, Calendar.getInstance().getTime(), price, user);
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

    public static void deleteItem(int itemId){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Item item = em.find(Item.class, itemId);
            em.remove(item);
            et.commit();
        } catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void getItemsByFilter(Filter filter) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        et = em.getTransaction();
        et.begin();

        Query query = em.createQuery(createFilteredQuery(filter));
        if (filter.getName() != null)
            query.setParameter("name", filter.getName());
        if (filter.getCategory() != null)
            query.setParameter("category", filter.getCategory());
        if (filter.getCountry() != null)
            query.setParameter("country", filter.getCountry());
        if (filter.getIntialInsertionDate() != null)
            query.setParameter("intialInsertionDate", filter.getIntialInsertionDate());
        if (filter.getFinalInsertionDate() != null)
            query.setParameter("finalInsertionDate", filter.getFinalInsertionDate());
        if (filter.getIntitialPriceRange() > 0)
            query.setParameter("intitialPriceRange", filter.getIntitialPriceRange());
        if (filter.getFinalPriceRange() > 0)
            query.setParameter("finalPriceRange", filter.getFinalPriceRange());

        List<Item> items = query.getResultList();

        System.out.println(items.size());

        for(Item item : items)
            System.out.println(item.toString());
    }

    public static void getItemsOrderedByFilter(Filter filter, String sortingParameter, String sortingOrder) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        et = em.getTransaction();
        et.begin();

        Query query = em.createQuery(sortingQuery(sortingParameter, sortingOrder, createFilteredQuery(filter)));
        if (filter.getName() != null)
            query.setParameter("name", filter.getName());
        if (filter.getCategory() != null)
            query.setParameter("category", filter.getCategory());
        if (filter.getCountry() != null)
            query.setParameter("country", filter.getCountry());
        if (filter.getIntialInsertionDate() != null)
            query.setParameter("intialInsertionDate", filter.getIntialInsertionDate());
        if (filter.getFinalInsertionDate() != null)
            query.setParameter("finalInsertionDate", filter.getFinalInsertionDate());
        if (filter.getIntitialPriceRange() > 0)
            query.setParameter("intitialPriceRange", filter.getIntitialPriceRange());
        if (filter.getFinalPriceRange() > 0)
            query.setParameter("finalPriceRange", filter.getFinalPriceRange());

        List<Item> items = query.getResultList();

        System.out.println(items.size());

        for(Item item : items)
            System.out.println(item.toString());
    }

    private static String createFilteredQuery(Filter filter){
        boolean first = false;
        String queryString = "from Item i where ";

        if (filter.getName() != null) {
            first = true;
            queryString += "i.name = :name";

        }
        if (filter.getCategory() != null) {
            if (!first) {
                first = true;
                queryString += "i.category = :category";
            }
            else
                queryString += " and i.category = :category";
        }
        if (filter.getCountry() != null) {
            if (!first) {
                first = true;
                queryString += "i.country = :country";
            }
            else
                queryString += " and i.country = :country";
        }
        if (filter.getIntialInsertionDate() != null) {
            if (!first) {
                first = true;
                queryString += "i.insertionDate >= :intialInsertionDate";
            }
            else
                queryString += " and i.insertionDate >= :intialInsertionDate";
        }
        if (filter.getFinalInsertionDate() != null) {
            if (!first) {
                first = true;
                queryString += "i.insertionDate <= :finalInsertionDate";
            }
            else
                queryString += " and i.insertionDate <= :finalInsertionDate";
        }
        if (filter.getIntitialPriceRange() > 0) {
            if (!first) {
                first = true;
                queryString += "i.price >= :intitialPriceRange";
            }
            else
                queryString += " and i.price >= :intitialPriceRange";
        }
        if (filter.getFinalPriceRange() > 0) {
            if (!first) {
                first = true;
                queryString += "i.price <= :finalPriceRange";
            }
            else
                queryString += " and i.price <= :finalPriceRange";
        }
        return queryString;
    }

    private static String sortingQuery(String sortingParameter, String sortingOrder, String queryString){
        return queryString += " order by i." + sortingParameter + " " + sortingOrder;
    }

    public static void getUser(String userEmail){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from User u where u.email = :email")
                    .setParameter("email", userEmail);
            User user = (User) query.getSingleResult();
            System.out.println(EncryptionManager.decprypt(user.getDk(), user.getPassword()));
        } catch (NoResultException ex){
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }
}