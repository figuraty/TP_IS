package com.uc.dei.is.Assignment2.database;

import javax.persistence.*;

public class teste {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("User");

    public static void main(String[] args) {

        addUser("Abilio", "abilio@gmail.com", "merda", "Portugal");
        getUser(1);

        ENTITY_MANAGER_FACTORY.close();

    }

    public static void addUser(String name, String email, String password, String country){
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            User user = new User(name, email, password, country);
            em.persist(user);
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
        String query = "SELECT u FROM com.uc.dei.is.database.User u WHERE u.id = :userID";

        TypedQuery<User> tq = em.createQuery(query, User.class);
        tq.setParameter("userID", id);
        User user = null;

        try {
            user = tq.getSingleResult();
            System.out.println(user);
        } catch (NoResultException ex){
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }
}
