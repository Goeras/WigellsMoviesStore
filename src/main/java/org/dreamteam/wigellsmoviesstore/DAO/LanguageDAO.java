
package org.dreamteam.wigellsmoviesstore.DAO;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.dreamteam.wigellsmoviesstore.Entitys.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LanguageDAO {
    private SessionFactory sessionFactory;

    public LanguageDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createLanguage(Language language) {
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.persist(language);
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

    public Language readLanguage(int id){
        Session session = sessionFactory.openSession();

        Language language = new Language();
        try{
            session.beginTransaction();
            language = session.get(Language.class, id);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
        }
        session.close();
        return language;
    }
    public List<Language> readAllLanguages(){
        Session session = sessionFactory.openSession();
        List<Language> languages = new ArrayList<>();
        try{
            session.beginTransaction();
            Query<Language> query = session.createQuery("FROM Language ", Language.class);
            languages = query.list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return languages;
    }

    public void updateLanguage(Language language){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.saveOrUpdate(language);
            session.getTransaction().commit();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void deleteAddress(Language language){
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            session.remove(language);
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
