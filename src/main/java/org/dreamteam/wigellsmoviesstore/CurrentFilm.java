package org.dreamteam.wigellsmoviesstore;

import org.dreamteam.wigellsmoviesstore.Entitys.Film;


public class CurrentFilm {

    private static CurrentFilm instance;
    private Film currentFilm;

    private CurrentFilm(){}

    public static synchronized CurrentFilm getInstance(){
        if(instance == null){
            instance = new CurrentFilm();
        }
        return instance;
    }

    public Film getCurrentFilm(){
        return currentFilm;
    }
    public void setCurrentFilm(Film film){
        this.currentFilm = film;
    }
}

}
