package org.dreamteam.wigellsmoviesstore;
import org.dreamteam.wigellsmoviesstore.DAO.StoreDAO;
import org.dreamteam.wigellsmoviesstore.Entitys.Store;
public class CurrentStore {

    private static CurrentStore instance;
    private Store currentStore;

    private StoreDAO storeDAO = new StoreDAO();

    private CurrentStore(){}

    public static synchronized CurrentStore getInstance(){
        if(instance == null){
            instance = new CurrentStore();
        }
        return instance;
    }

    public Store getCurrentStore(){
        return currentStore;
    }
    public void setCurrentStore(Store store){
        this.currentStore = store;
    }

    public void updateCurrentStore(){
        this.currentStore = storeDAO.getStoreById(currentStore.getId());
    }
}
