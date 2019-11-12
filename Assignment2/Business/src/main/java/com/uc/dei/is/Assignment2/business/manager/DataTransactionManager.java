package com.uc.dei.is.Assignment2.business.manager;


import com.uc.dei.is.Assignment2.data.models.Item;
import com.uc.dei.is.Assignment2.data.models.User;


import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DataTransactionManager {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("IS_Assignment2");

    public static int addUser(String name, String email, String password, String country){

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
                return 1;
            }
            return 0;
        } catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
            return 0;
        } finally {
            em.close();
        }
    }

    public static int updateUser(String userEmail, String name, String email, String password, String country){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            String[] passEncrypted = EncryptionManager.encprypt(password);
            if (passEncrypted!= null && passEncrypted.length == 2) {
                Query query = em.createQuery("from User u where u.email = :email")
                        .setParameter("email", userEmail);
                User user = (User) query.getSingleResult();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(passEncrypted[1]);
                user.setCountry(country);
                user.setDk(passEncrypted[0]);
                em.merge(user);
                et.commit();
                return 1;
            }
            return 0;
        } catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
            return 0;
        } finally {
            em.close();
        }
    }

    public static User getUser(String userEmail){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from User u where u.email = :email")
                    .setParameter("email", userEmail);
            return (User) query.getSingleResult();
        } catch (NoResultException ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static void deleteUser(String userEmail){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Query query = em.createQuery("from User u where u.email = :email")
                    .setParameter("email", userEmail);
            User user = (User) query.getSingleResult();
            em.remove(user);
            et.commit();
        } catch (NoResultException ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }

    public static List<Item> getAllItems(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            return em.createQuery("from Item i").getResultList();
        } catch (NoResultException ex){
            ex.printStackTrace();
            return null;
        }finally {
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

    public static void updateItem(int itemId, String name, String category, String country, String picture, Date initialInsertionDate, Float price){

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
            item.setInsertionDate(initialInsertionDate);
            item.setPrice(price);
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

    public static Item getItem(int itemID){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from Item i where i.id = :itemID")
                    .setParameter("itemID", itemID);
            return (Item) query.getSingleResult();
        } catch (NoResultException ex){
            ex.printStackTrace();
            return null;
        }finally {
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

    public static List<Item> getItemsByFilter(Filter filter) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        Query query = em.createQuery(createFilteredQuery(filter));
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

        return query.getResultList();
    }

    public static List<Item> getItemsByFilterOrdered(Filter filter, String sortingParameter, String sortingOrder) {

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        Query query = null;
        if (filter != null) {
            query = em.createQuery(sortingQuery(sortingParameter, sortingOrder, createFilteredQuery(filter)));
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
        }
        else
            query = em.createQuery(sortingQuery(sortingParameter, sortingOrder, "from Item i"));
        return query.getResultList();
    }

    private static String createFilteredQuery(Filter filter){
        boolean first = false;
        String queryString = "from Item i";

        if (filter.getName() != null) {
            first = true;
            queryString += " where i.name like '%" + filter.getName() + "%'";

        }
        if (filter.getCategory() != null) {
            if (!first) {
                first = true;
                queryString += " where i.category = :category";
            }
            else
                queryString += " and i.category = :category";
        }
        if (filter.getCountry() != null) {
            if (!first) {
                first = true;
                queryString += " where i.country = :country";
            }
            else
                queryString += " and i.country = :country";
        }
        if (filter.getIntialInsertionDate() != null) {
            if (!first) {
                first = true;
                queryString += " where i.insertionDate >= :intialInsertionDate";
            }
            else
                queryString += " and i.insertionDate >= :intialInsertionDate";
        }
        if (filter.getFinalInsertionDate() != null) {
            if (!first) {
                first = true;
                queryString += " where i.insertionDate <= :finalInsertionDate";
            }
            else
                queryString += " and i.insertionDate <= :finalInsertionDate";
        }
        if (filter.getIntitialPriceRange() > 0) {
            if (!first) {
                first = true;
                queryString += " where i.price >= :intitialPriceRange";
            }
            else
                queryString += " and i.price >= :intitialPriceRange";
        }
        if (filter.getFinalPriceRange() > 0) {
            if (!first) {
                first = true;
                queryString += " where i.price <= :finalPriceRange";
            }
            else
                queryString += " and i.price <= :finalPriceRange";
        }
        return queryString;
    }

    private static String sortingQuery(String sortingParameter, String sortingOrder, String queryString){
        return queryString += " order by i." + sortingParameter + " " + sortingOrder;
    }

    public static List<Item> getLastThreeItems() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try{
            Query query = em.createQuery("from Item order by itemID desc").setMaxResults(3);
            return (List<Item>) query.getResultList();
        } catch (NoResultException ex){
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

}