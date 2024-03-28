package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Store;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StoreDAO {

    private SessionFactory sessionFactory;

    public StoreDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createStore(Store store){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        try {
            s.persist(store);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public Store getStoreById(int id){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        Store store = s.get(Store.class, id);
        s.close();

        return store;
    }

    public List<Store> getAllStores(){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Store> stores = s.createQuery("FROM Store", Store.class).list();
        s.close();
        return stores;
    }

    public void updateStore(Store store){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.saveOrUpdate(store);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public void deleteStore(Store store){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.remove(store);
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
