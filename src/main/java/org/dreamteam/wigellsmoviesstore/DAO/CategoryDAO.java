
package org.dreamteam.wigellsmoviesstore.DAO;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private SessionFactory sessionFactory;

    public CategoryDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createCategory(Category category) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(category);
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

    public Category readCategory(int id){
        Session session = sessionFactory.openSession();

        Category category = new Category();
        try{
            session.beginTransaction();
            category = session.get(Category.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
        }
        session.close();
        return category;
    }
    public List<Category> readAllCategories(){
        Session session = sessionFactory.openSession();
        List<Category> categories = new ArrayList<>();
        try{
            session.beginTransaction();
            Query<Category> query = session.createQuery("FROM Category ", Category.class);
            categories = query.list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return categories;
    }

    public void updateCategory(Category category){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.saveOrUpdate(category);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void deleteAddress(Category category){
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.remove(category);
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
}
