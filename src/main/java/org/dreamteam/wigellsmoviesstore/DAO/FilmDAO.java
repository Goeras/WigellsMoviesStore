package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class FilmDAO {
    private SessionFactory sessionFactory;

    public FilmDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createFilm(Film film){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        try {
            s.persist(film);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public Film getFilmById(int id){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        Film film = s.get(Film.class, id);
        s.close();

        return film;
    }

    public List<Film> getAllFilms(){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Film> films = s.createQuery("FROM Film", Film.class).list();
        s.close();
        return films;
    }

    public void updateFilm(Film film){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.saveOrUpdate(film);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public void deleteFilm(Film film){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.remove(film);
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
