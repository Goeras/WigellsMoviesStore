package org.dreamteam.wigellsmoviesstore.Managers;

import javafx.collections.ObservableList;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Staff;

import java.util.ArrayList;
import java.util.List;

public class StaffManager {


    public List<Staff> filterByName(ObservableList observableList, String name){
        List<Staff> staffList = new ArrayList<>();
        for(Object o : observableList){
            if(o instanceof Staff) {
                Staff s = (Staff) o;
                if (s.getFirstName().equalsIgnoreCase(name) || s.getLastName().equalsIgnoreCase(name)) {
                    staffList.add(s);
                }
            }
        }
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

    public String isActiveStringFromBoolean(Boolean bool){
        if(!bool){
            return "Ej Aktiv";
        }
        else{
            return "Aktiv";
        }
    }

}
