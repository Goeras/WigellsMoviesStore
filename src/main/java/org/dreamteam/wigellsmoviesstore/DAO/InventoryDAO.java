package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Inventory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class InventoryDAO {
    private SessionFactory sessionFactory;

    public InventoryDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createInventory(Inventory inventory){
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            session.persist(inventory);
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

    public Inventory readInventory(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            return session.get(Inventory.class, id);
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

    public List<Inventory> readAllInventories(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Inventory> inventory = session.createQuery("FROM Inventory", Inventory.class).list();
        session.close();
        return inventory;
    }


    public void updateInventory(Inventory inventory){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.saveOrUpdate(inventory);
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

    public void deleteInventory(Inventory inventory){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        try
        {
            session.remove(inventory);
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
