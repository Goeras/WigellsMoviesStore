package org.dreamteam.wigellsmoviesstore;
import org.dreamteam.wigellsmoviesstore.Entitys.Store;
public class CurrentStore {

    private static CurrentStore instance;
    private Store currentStore;

    private CurrentStore(){}

    public static synchronized CurrentStore getInstance(){
        if(instance == null){
            instance = new CurrentStore();
        }
        return instance;
    }

    public Store getCurrentUser(){
        return currentStore;
    }
    public void setCurrentCustomer(Store store){
        this.currentStore = store;
    }
}
