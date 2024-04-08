package org.dreamteam.wigellsmoviesstore.Managers;

import org.dreamteam.wigellsmoviesstore.CurrentFilm;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.*;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.IoValidator;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilmManager {
  private DAOmanager daoManager = new DAOmanager();

  public void addFilm(String title, String description, String releaseYear, String rentalDuration, String length, String replacementCost, String rentalRate, String rating, String specialFeaturesString, Language language, Language originalLanguage, List<Category> categoryList, List<Actor> actorList)  {
  Film film = new Film();


  //needs value, NOT NULL
    boolean validTitle = IoValidator.validateStringNotEmpty(title);
    boolean validLanguage = language != null;
    boolean validRentalDuration = !rentalDuration.isEmpty() && IoValidator.validateInteger(rentalDuration);
    boolean validRentalRate = !rentalRate.isEmpty() && IoValidator.validateInteger(rentalRate);
    boolean validReplacementCost = !replacementCost.isEmpty() && IoValidator.validateInteger(replacementCost);
    film.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

    if(!validTitle) {
      IoValidator.displayAlert("Saknar Titel", "Vänligen fyll i filmtitel");
      return;} else {film.setTitle(title);}

    if(!validLanguage){
      IoValidator.displayAlert("Saknar Språk", "Vänligen fyll i språk");
      return;} else {film.setLanguage(language);}

    if(!validRentalDuration) {
      IoValidator.displayAlert("Saknar Hyrperiod", "Vänligen fyll i hyrperiod");
      return;} else {film.setRentalDuration(IoConverter.stringToByte(rentalDuration));}

    if(!validRentalRate) {
      IoValidator.displayAlert("Saknar Hyrkostnad", "Vänligen fyll i hyrkostnad");
      return;} else {film.setRentalRate(IoConverter.stringToDouble(rentalRate));}

    if(!validReplacementCost) {
      IoValidator.displayAlert("Saknar Ersättningskostnad", "Vänligen fyll i ersättningskostnad");
      return;} else {
      film.setReplacementCost(IoConverter.stringToDouble(replacementCost));}

  //default NULL in database
    if(IoValidator.validateStringNotEmpty(description)) {
    film.setDescription(description);
  }

  if(!releaseYear.isEmpty() && IoValidator.validateMovieYear(releaseYear)) {
    film.setReleaseYear(IoConverter.stringToShort(releaseYear));
  }

  if(originalLanguage != null) {
    film.setOriginalLanguage(originalLanguage);
  }

  if(IoValidator.validateStringNotEmpty(length) && IoValidator.validateInteger(length)) {
    film.setLength(IoConverter.stringToShort(length));
  }

  if(rating != null && IoValidator.validateStringNotEmpty(rating)) {
    film.setRating(rating);
  }

  if(IoValidator.validateStringNotEmpty(specialFeaturesString)) {
    film.setSpecialFeatures(specialFeaturesString);
  }
  film.setActors(actorList);
  film.setCategoryList(categoryList);


  if(validTitle && validLanguage && validRentalDuration && validRentalRate && validReplacementCost){
      daoManager.getFilmDAO().createFilm(film);
    IoValidator.displayConfirmation("Film Skapad", film.getTitle() + ": Tillagd i databasen");
    }
  }

  public void updateFilm(String title, String description, short releaseYear, Language language, Language originalLanguage, byte rentalDuration, double rentalRate, short length, double replacementCost, String rating, String specialFeatures, List<Category> categoryList, List<Actor> actorList){
    Film updateFilm = CurrentFilm.getInstance().getCurrentFilm();
//    Language updateLanguage = updateFilm.getLanguage();
//    Language updateOriginalLanguage =  updateFilm.getOriginalLanguage();

//    if(!updateLanguage.getName().equals(language.getName())){
//      Language newLanguage = new Language();
//      newLanguage.setName(language.getName());
//      newLanguage.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
//
//      daoManager.getLanguageDAO().createLanguage(newLanguage);
//    }

//    if(!updateOriginalLanguage.getName().equals(originalLanguage.getName())){
//      Language newOriginalLanguage = new Language();
//      newOriginalLanguage.setName(language.getName());
//      newOriginalLanguage.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
//
//      daoManager.getLanguageDAO().createLanguage(newOriginalLanguage);
//    }

    updateFilm.setTitle(title);
    updateFilm.setDescription(description);
    updateFilm.setReleaseYear(releaseYear);
    updateFilm.setLanguage(language);
    updateFilm.setOriginalLanguage(originalLanguage);
    updateFilm.setRentalDuration(rentalDuration);
    updateFilm.setRentalRate(rentalRate);
    updateFilm.setLength(length);
    updateFilm.setReplacementCost(replacementCost);
    updateFilm.setRating(rating);
    updateFilm.setSpecialFeatures(specialFeatures);
    updateFilm.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
    updateFilm.setCategoryList(categoryList);
    updateFilm.setActors(actorList);

    daoManager.getFilmDAO().updateFilm(updateFilm);
  }

  public String[] getFilmInfo(String filmId){
    int filmIdNbr = IoConverter.stringToInteger(filmId);
    String[] info = new String[13];
    Film film = daoManager.getFilmDAO().getFilmById(filmIdNbr);

    info[0] = film.getTitle();
    info[1] = Double.toString(film.getRentalRate());
    info[2] = Double.toString(film.getReplacementCost());
    info[3] = film.getCategoryList().toString();
    info[4] = film.getActors().toString();
    info[5] = Short.toString(film.getReleaseYear());
    info[6] = film.getLanguage().getName();
    info[7] = Short.toString(film.getLength());

    return info;
  }
  public Film getFilm(String filmId){
    int filmID = IoConverter.stringToInteger(filmId);
    Film film = daoManager.getFilmDAO().getFilmById(filmID);
    return film;
  }
}
