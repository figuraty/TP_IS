package data;

import javax.persistence.*;
import java.util.List;

public class DataTransactionManager {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("IS_Assignment3");

    public String addItem(String name) {
        return null;
    }

    public String getItemRevenue(String name) {
        return null;
    }

    public String getItemExpenses(String name) {
        return null;
    }

    public String getItemProfit() {
        return null;
    }

    public String getTotalRevenues() {
        return null;
    }

    public String getTotalExpenses() {
        return null;
    }

    public String getTotalProfit() {
        return null;
    }

    public String getAvgAmountSpentEachPurchase() {
        return null;
    }

    public String getItemHighestProfit() {
        return null;
    }

    public String getTotalExpensesLastHour() {
        return null;
    }

    public String getTotalProfitsLastHour() {
        return null;
    }

    public String getCountryHighestSalesPerItem() {
        return null;
    }

    //http://localhost:8080/play-REST-server/webapi/project3webservices/addcountry?country=olaolao
    static public void addCountry(String countryName) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
//            et = em.getTransaction();
//            et.begin();
            Country country = new Country(countryName);
            em.persist(country);
//            et.commit();
        } catch (Exception ex){
            if(et != null)
                et.rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static List<Item> listItems(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("from Item i").getResultList();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static List<Country> listCountries(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            System.out.println(em.createQuery("from Country c").getResultList());
            return em.createQuery("from Country c").getResultList();
        } catch (NoResultException ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static void addItem(String name, int price){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            Item item = new Item(name, price);
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

//    public static void updateItem(int itemId, String name, String category, String country, String picture, Date initialInsertionDate, Float price){
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
//            item.setInsertionDate(initialInsertionDate);
//            item.setPrice(price);
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
//
//    public static Item getItem(int itemID){
//
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//
//        try {
//            Query query = em.createQuery("from Item i where i.id = :itemID")
//                    .setParameter("itemID", itemID);
//            return (Item) query.getSingleResult();
//        } catch (NoResultException ex){
//            ex.printStackTrace();
//            return null;
//        }finally {
//            em.close();
//        }
//    }
//
//    public static void deleteItem(int itemId){
//
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//        EntityTransaction et = null;
//
//        try {
//            et = em.getTransaction();
//            et.begin();
//            Item item = em.find(Item.class, itemId);
//            em.remove(item);
//            et.commit();
//        } catch (Exception ex){
//            if(et != null)
//                et.rollback();
//            ex.printStackTrace();
//        } finally {
//            em.close();
//        }
//    }
//
//    public static List<Item> getItemsByFilter(Filter filter) {
//
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//
//        Query query = em.createQuery(createFilteredQuery(filter));
//        if (filter.getCategory() != null)
//            query.setParameter("category", filter.getCategory());
//        if (filter.getCountry() != null)
//            query.setParameter("country", filter.getCountry());
//        if (filter.getIntialInsertionDate() != null)
//            query.setParameter("intialInsertionDate", filter.getIntialInsertionDate());
//        if (filter.getFinalInsertionDate() != null)
//            query.setParameter("finalInsertionDate", filter.getFinalInsertionDate());
//        if (filter.getIntitialPriceRange() > 0)
//            query.setParameter("intitialPriceRange", filter.getIntitialPriceRange());
//        if (filter.getFinalPriceRange() > 0)
//            query.setParameter("finalPriceRange", filter.getFinalPriceRange());
//
//        return query.getResultList();
//    }
//
//    public static List<Item> getItemsByFilterOrdered(Filter filter, String sortingParameter, String sortingOrder) {
//
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//        Query query = null;
//        if (filter != null) {
//            query = em.createQuery(sortingQuery(sortingParameter, sortingOrder, createFilteredQuery(filter)));
//            if (filter.getCategory() != null)
//                query.setParameter("category", filter.getCategory());
//            if (filter.getCountry() != null)
//                query.setParameter("country", filter.getCountry());
//            if (filter.getIntialInsertionDate() != null)
//                query.setParameter("intialInsertionDate", filter.getIntialInsertionDate());
//            if (filter.getFinalInsertionDate() != null)
//                query.setParameter("finalInsertionDate", filter.getFinalInsertionDate());
//            if (filter.getIntitialPriceRange() > 0)
//                query.setParameter("intitialPriceRange", filter.getIntitialPriceRange());
//            if (filter.getFinalPriceRange() > 0)
//                query.setParameter("finalPriceRange", filter.getFinalPriceRange());
//        }
//        else
//            query = em.createQuery(sortingQuery(sortingParameter, sortingOrder, "from Item i"));
//        return query.getResultList();
//    }
//
//    private static String createFilteredQuery(Filter filter){
//        boolean first = false;
//        String queryString = "from Item i";
//
//        if (filter.getName() != null) {
//            first = true;
//            queryString += " where i.name like '%" + filter.getName() + "%'";
//
//        }
//        if (filter.getCategory() != null) {
//            if (!first) {
//                first = true;
//                queryString += " where i.category = :category";
//            }
//            else
//                queryString += " and i.category = :category";
//        }
//        if (filter.getCountry() != null) {
//            if (!first) {
//                first = true;
//                queryString += " where i.country = :country";
//            }
//            else
//                queryString += " and i.country = :country";
//        }
//        if (filter.getIntialInsertionDate() != null) {
//            if (!first) {
//                first = true;
//                queryString += " where i.insertionDate >= :intialInsertionDate";
//            }
//            else
//                queryString += " and i.insertionDate >= :intialInsertionDate";
//        }
//        if (filter.getFinalInsertionDate() != null) {
//            if (!first) {
//                first = true;
//                queryString += " where i.insertionDate <= :finalInsertionDate";
//            }
//            else
//                queryString += " and i.insertionDate <= :finalInsertionDate";
//        }
//        if (filter.getIntitialPriceRange() > 0) {
//            if (!first) {
//                first = true;
//                queryString += " where i.price >= :intitialPriceRange";
//            }
//            else
//                queryString += " and i.price >= :intitialPriceRange";
//        }
//        if (filter.getFinalPriceRange() > 0) {
//            if (!first) {
//                first = true;
//                queryString += " where i.price <= :finalPriceRange";
//            }
//            else
//                queryString += " and i.price <= :finalPriceRange";
//        }
//        return queryString;
//    }
//
//    private static String sortingQuery(String sortingParameter, String sortingOrder, String queryString){
//        return queryString += " order by i." + sortingParameter + " " + sortingOrder;
//    }
//
//    public static List<Item> getLastThreeItems() {
//        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
//
//        try{
//            Query query = em.createQuery("from Item order by itemID desc").setMaxResults(3);
//            return (List<Item>) query.getResultList();
//        } catch (NoResultException ex){
//            ex.printStackTrace();
//            return null;
//        } finally {
//            em.close();
//        }
//    }

}