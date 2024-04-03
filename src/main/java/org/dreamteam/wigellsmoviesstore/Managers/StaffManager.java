package org.dreamteam.wigellsmoviesstore.Managers;

import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffManager {


    public List<Staff> filterByName(String name){
        List<Staff> staffList = new ArrayList<>();

        return staffList;
    }

    public Staff searchByName(int id){
        Staff staff = null;
        for(Staff s : CurrentStore.getInstance().getCurrentStore().getStaffList()){
            if(s.getId() == id){
                staff = s;
            }
        }
        return staff;
    }

}
