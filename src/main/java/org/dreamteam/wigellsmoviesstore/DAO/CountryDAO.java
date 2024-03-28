package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Country;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class CountryDAO {
    private SessionFactory sessionFactory;

    public CountryDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createCountry(Country country){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        try {
            s.persist(country);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public Country getCountryById(int id){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        Country country = s.get(Country.class, id);
        s.close();

        return country;
    }

    public List<Country> getAllCountries(){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Country> countries = s.createQuery("FROM Country", Country.class).list();
        s.close();
        return countries;
    }

    public void updateCountry(Country country){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.saveOrUpdate(country);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public void deleteCountry(Country country){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.remove(country);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

}
