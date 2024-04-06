package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Payment;
import org.dreamteam.wigellsmoviesstore.Entitys.Rental;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;

public class RentalDAO {
    private SessionFactory sessionFactory;

    public RentalDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createRental(Rental rental){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.persist(rental);
            session.getTransaction().commit();
        }
        catch(Exception E){
            if(session.getTransaction() != null && session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
        }
        finally{
            session.close();
        }
    }

    public Rental readRental(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            return session.get(Rental.class, id);
        }
        catch(Exception E)
        {
            E.printStackTrace();
            return null;
        }
        finally
        {
            session.close();
        }
    }

    public List<Rental> readAllRentals(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Rental> rental = session.createQuery("FROM Rental", Rental.class).list();
        session.close();
        return rental;
    }


    public void updateRental(Rental rental){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.saveOrUpdate(rental);
            session.getTransaction().commit();
        }
        catch (Exception E)
        {
            E.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void deleteRental(Rental rental){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.remove(rental);
            session.getTransaction().commit();
        }
        catch(HibernateException E)
        {
            E.printStackTrace();
        }
        finally {
            session.close();
        }
    }
    public List<Rental> getInventoryByFilmAndStore(){
        Session session = DatabaseSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        String queryString = "FROM Rental WHERE returnDate IS null";
        System.out.println(queryString);
        Query<Rental> query = session.createQuery(queryString, Rental.class);
        List<Rental> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }
}
