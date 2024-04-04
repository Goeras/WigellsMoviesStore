package org.dreamteam.wigellsmoviesstore.Managers;

import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.dreamteam.wigellsmoviesstore.IoConverter;

import java.util.ArrayList;
import java.util.List;

public class FilmManager {
  DAOmanager daoManager = new DAOmanager();

  public void addFilm(List<String> infoList, List<Category>categoryList) {
    Film film = new Film();
    film.setTitle(infoList.get(0));
    film.setDescription(infoList.get(1));
    film.setReleaseYear(IoConverter.stringToShort(infoList.get(2)));
    film.setLanguage(daoManager.getLanguageDAO().readLanguage(IoConverter.stringToInteger(infoList.get(3))));
    film.setOriginalLanguage(daoManager.getLanguageDAO().readLanguage(IoConverter.stringToInteger(infoList.get(4))));
    film.setRentalDuration(IoConverter.stringToByte(infoList.get(5)));
    film.setLength(IoConverter.stringToShort(infoList.get(6)));
    film.setReplacementCost(IoConverter.stringToDouble(infoList.get(7)));
    film.setRentalRate(IoConverter.stringToDouble(infoList.get(8)));
    film.setSpecialFeatures(infoList.get(9));
    film.setCategoryList(categoryList);


  }
}
