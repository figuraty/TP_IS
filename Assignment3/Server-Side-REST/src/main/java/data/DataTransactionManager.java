package data;

import data.entities.DBEntity;
import data.entities.ItemTransactions;

import javax.persistence.*;
import java.util.List;

public class DataTransactionManager {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("IS_Assignment3");

    static public void addCountry(String countryName) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            DBEntity country = new DBEntity(countryName, "Country");
            em.persist(country);
        } catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static List<DBEntity> listCountries(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("from DBEntity d WHERE d.type = :type")
                    .setParameter("type", "Country")
                    .getResultList();
        } catch (NoResultException ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static void addItem(String name){

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            DBEntity item = new DBEntity(name, "Item");
            em.persist(item);
        } catch (Exception ex){
            em.getTransaction().rollback();
            ex.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static List<DBEntity> listItems(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("from DBEntity d WHERE d.type = :type")
                    .setParameter("type", "Item")
                    .getResultList();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static List<ItemTransactions> getItemsRevenues() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from ItemTransactions i");
            return query.getResultList();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static List<ItemTransactions> getItemsExpenses() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from ItemTransactions i");
            return query.getResultList();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static List<ItemTransactions> getItemsProfits() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from ItemTransactions i");
            return query.getResultList();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static String getTotalRevenues() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.totalRevenues from TotalTransactions t");
            return query.getSingleResult().toString();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static String getTotalExpenses() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.totalExpenses from TotalTransactions t");
            return query.getSingleResult().toString();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static String getTotalProfits() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.totalProfit from TotalTransactions t");
            return query.getSingleResult().toString();
        } catch (Exception ex){
            ex.printStackTrace();
            return "";
        }finally {
            em.close();
        }
    }


    public static List<ItemTransactions> getItemAvgAmountEachPurchase(){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from ItemTransactions i");
            return query.getResultList();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static String getTotalAvgAmountEachPurchase() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.averagePurchaseAmount from TotalTransactions t");
            return query.getSingleResult().toString();
        } catch (Exception ex){
            ex.printStackTrace();
            return "";
        }finally {
            em.close();
        }
    }

    public static String getItemHighestProfit() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.itemHighestProfit from TotalTransactions t");
            return query.getSingleResult().toString();
        } catch (Exception ex){
            ex.printStackTrace();
            return "";
        }finally {
            em.close();
        }
    }

    public static String getTotalRevenuesLastHour() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.lastHourRevenues from TotalTransactions t");
            return query.getSingleResult().toString();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static String getTotalExpensesLastHour() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.lastHourExpenses from TotalTransactions t");
            return query.getSingleResult().toString();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static String getTotalProfitsLastHour() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.lastHourProfits from TotalTransactions t");
            return query.getSingleResult().toString();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }

    public static List<ItemTransactions> getCountryHighestSalesPerItem() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from ItemTransactions i");
            return query.getResultList();
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }finally {
            em.close();
        }
    }


}