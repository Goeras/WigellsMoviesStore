package org.dreamteam.wigellsmoviesstore.Managers;

import javafx.collections.ObservableList;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Customer;
import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.dreamteam.wigellsmoviesstore.Entitys.Rental;
import org.dreamteam.wigellsmoviesstore.IoConverter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class PosManager {
    DAOmanager daOmanager = new DAOmanager();

    public void createNewRental(){
    }
    public String calculateReturnDate(int duration){
        LocalDate localDate = LocalDate.now();
        LocalDate returnDate = LocalDate.now();
        returnDate = localDate.plusDays(duration);
        return returnDate.toString();
    }
    public ObservableList addFilmToCart(ObservableList<Film> filmList, String id){
        int filmid = IoConverter.stringToInteger(id);
        Film film = daOmanager.getFilmDAO().getFilmById(filmid);
        filmList.add(film);
        return filmList;
    }
    public String[] searchFilm(String id){
        int filmId = IoConverter.stringToInteger(id);
        Film film = daOmanager.getFilmDAO().getFilmById(filmId);
        String[] strings = {Integer.toString(film.getFilmId()), film.getTitle()};
        return strings;
    }

}
