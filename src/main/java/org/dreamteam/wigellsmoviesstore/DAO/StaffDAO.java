package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Staff;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class StaffDAO {

    private SessionFactory sessionFactory;

    public StaffDAO(){
        this.sessionFactory = DatabaseSessionFactory.getSessionFactory();
    }

    public void createStaff(Staff staff){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        try {
            s.persist(staff);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public Staff getStaffById(int id){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        Staff staff = s.get(Staff.class, id);
        s.close();

        return staff;
    }

    public List<Staff> getAllStaffs(){
        Session s = sessionFactory.openSession();
        s.beginTransaction();

        List<Staff> staffs = s.createQuery("FROM Staff", Staff.class).list();
        s.close();
        return staffs;
    }

    public void updateStaff(Staff staff){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.saveOrUpdate(staff);
            s.getTransaction().commit();
        }
        catch(HibernateException he){
            he.printStackTrace();
        }
        finally{
            s.close();
        }
    }

    public void deleteStaff(Staff staff){
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        try{
            s.remove(staff);
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
