package data;

import data.entities.*;

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
        } catch (Exception ex) {
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

    public static List<revenuesPerItem> getItemsRevenues() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from revenuesPerItem i");
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static List<expensesPerItem> getItemsExpenses() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from expensesPerItem i");
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static List<profitPerItem> getItemsProfits() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from profitPerItem i");
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static String getTotalRevenues() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.value from totalRevenues t");
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static String getTotalExpenses() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.value from totalExpenses t");
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static String getTotalProfits() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.value from totalProfit t");
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }


    public static List<averageExpensesPerItem> getItemAvgAmountEachPurchase() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from averageExpensesPerItem i");
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static String getTotalAvgAmountEachPurchase() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.value from totalAverageExpenses t");
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static String getItemHighestProfit() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.value from itemHighestProfit t");
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static String getTotalRevenuesLastHour() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.value from totalRevenuesLastHour t");
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static String getTotalExpensesLastHour() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.value from totalExpensesLastHour t");
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static String getTotalProfitsLastHour() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("select t.value from totalProfitLastHour t");
            return query.getSingleResult().toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    public static List<countryHighestSalePerItem> getCountryHighestSalesPerItem() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

        try {
            Query query = em.createQuery("from countryHighestSalePerItem i");
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }


}
