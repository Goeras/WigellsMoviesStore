package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Actor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ActorDAO {

    private SessionFactory sessionFactory;

    public ActorDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createActor(Actor actor){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        try {
            s.persist(actor);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public Actor getActorById(int id){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        Actor actor = s.get(Actor.class, id);
        s.close();

        return actor;
    }

    public List<Actor> getAllActors(){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Actor> actors = s.createQuery("FROM Actor", Actor.class).list();
        s.close();
        return actors;
    }

    public void updateActor(Actor actor){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.saveOrUpdate(actor);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public void deleteActor(Actor actor){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.remove(actor);
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
