package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Address;
import org.dreamteam.wigellsmoviesstore.Entitys.City;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AddressDAO {

    private SessionFactory sessionFactory;

    public AddressDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createAddress(Address address){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.persist(address);
            session.getTransaction().commit();
        }
        catch(Exception E){
            if(session.getTransaction() != null && session.getTransaction().isActive()){
                session.getTransaction().rollback();
                E.printStackTrace();
            }
        }
        finally{
            session.close();
        }
    }

    public Address readAddress(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            return session.get(Address.class, id);
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

    public Address getAddressByName(String address){
        Session session = DatabaseSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        String queryString = "FROM Address WHERE address = :addressName";
        Query<Address> query = session.createQuery(queryString, Address.class);
        query.setParameter("addressName", address);

        Address foundAddress = query.uniqueResult();

        session.getTransaction().commit();
        session.close();

        return foundAddress;
    }

    public List<Address> readAllAddresses(){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Address> address = s.createQuery("FROM Address", Address.class).list();
        s.close();
        return address;
    }


    public void updateAddress(Address address){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.saveOrUpdate(address);
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

    public void deleteAddress(Address address){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.remove(address);
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
