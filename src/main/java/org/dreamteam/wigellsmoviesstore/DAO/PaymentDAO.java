
package org.dreamteam.wigellsmoviesstore.DAO;
import org.dreamteam.wigellsmoviesstore.Entitys.Inventory;
import org.dreamteam.wigellsmoviesstore.Entitys.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    private SessionFactory sessionFactory;

    public PaymentDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createPayment(Payment payment) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(payment);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
        }
        finally{
            session.close();
        }
    }

    public Payment readPayment(int id){
        Session session = sessionFactory.openSession();

        Payment payment = new Payment();
        try{
            session.beginTransaction();
            payment = session.get(Payment.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
        }
        session.close();
        return payment;
    }
    public List<Payment> readAllPayments(){
        Session session = sessionFactory.openSession();
        List<Payment> payments = new ArrayList<>();
        try{
            session.beginTransaction();
            Query<Payment> query = session.createQuery("FROM Payment ", Payment.class);
            payments = query.list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return payments;
    }

    public void updatePayment(Payment payment){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.saveOrUpdate(payment);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void deletePayment(Payment payment){
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.remove(payment);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    public List<Payment> getInventoryByFilmAndStore(Timestamp fromDate, Timestamp toDate){
        Session session = DatabaseSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        String queryString = "FROM Payment WHERE paymentDate <:toDate AND paymentDate > :fromDate";
        System.out.println(queryString);
        Query<Payment> query = session.createQuery(queryString, Payment.class);
        query.setParameter("toDate", toDate);
        query.setParameter("fromDate", fromDate);
        List<Payment> resultList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return resultList;
    }

}
