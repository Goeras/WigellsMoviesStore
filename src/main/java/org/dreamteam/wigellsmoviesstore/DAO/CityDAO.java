package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.City;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class CityDAO {
    private SessionFactory sessionFactory;

    public CityDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createCity(City city){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        try {
            s.persist(city);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public City getCityById(int id){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        City city = s.get(City.class, id);
        s.close();

        return city;
    }

    public List<City> getAllCities(){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<City> cities = s.createQuery("FROM City", City.class).list();
        s.close();
        return cities;
    }

    public void updateCity(City city){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.saveOrUpdate(city);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public void deleteCity(City city){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.remove(city);
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
