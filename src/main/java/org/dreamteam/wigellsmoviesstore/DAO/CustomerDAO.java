package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Address;
import org.dreamteam.wigellsmoviesstore.Entitys.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CustomerDAO {

    private SessionFactory sessionFactory;

    public CustomerDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createCustomer(Customer customer){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.persist(customer);
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

    public Customer readCustomer(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            return session.get(Customer.class, id);
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

    public List<Customer> readAllCustomers(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Customer> customer = session.createQuery("FROM Customer", Customer.class).list();
        session.close();
        return customer;
    }


    public void updateCustomer(Customer customer){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.saveOrUpdate(customer);
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

    public void deleteCustomer(Customer customer){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.remove(customer);
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
}
