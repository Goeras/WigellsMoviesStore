package org.dreamteam.wigellsmoviesstore.Managers;

import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Actor;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.dreamteam.wigellsmoviesstore.Entitys.Language;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.IoValidator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FilmManager {
  DAOmanager daoManager = new DAOmanager();

  public void addFilm(String title, String description, String releaseYear, String rentalDuration, String length, String replacementCost, String rentalRate, String rating, String specialFeaturesString, Language language, Language originalLanguage, List<Category> categoryList)  {
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


  if(validTitle && validLanguage && validRentalDuration && validRentalRate && validReplacementCost){
      daoManager.getFilmDAO().createFilm(film);
    IoValidator.displayConfirmation("Film Skapad", film.getTitle() + ": Tillagd i databasen");
    }
  }
}
