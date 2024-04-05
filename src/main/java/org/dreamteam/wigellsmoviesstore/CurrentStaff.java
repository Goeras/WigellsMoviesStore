package org.dreamteam.wigellsmoviesstore;

import org.dreamteam.wigellsmoviesstore.DAO.StaffDAO;
import org.dreamteam.wigellsmoviesstore.Entitys.Staff;

public class CurrentStaff {
    private static CurrentStaff instance;
    private Staff currentStaff;

    private StaffDAO staffDAO = new StaffDAO();

    private CurrentStaff(){}

    public static synchronized CurrentStaff getInstance(){
        if(instance == null){
            instance = new CurrentStaff();
        }
        return instance;
    }

    public Staff getCurrentStaff(){
        return currentStaff;
    }
    public void setCurrentStaff(Staff staff){
        this.currentStaff = staff;
    }

    public void updateCurrentStaff(){
        this.currentStaff = staffDAO.getStaffById(currentStaff.getId());
    }
}
