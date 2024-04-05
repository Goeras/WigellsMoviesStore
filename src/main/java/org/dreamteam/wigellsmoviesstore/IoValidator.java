package org.dreamteam.wigellsmoviesstore;


import javafx.scene.control.Alert;

import java.util.regex.Pattern;

public class IoValidator {
    public static boolean validateInteger(String input){
        boolean isValidInt = false;

        try {
            int tempInt = Integer.parseInt(input);
            isValidInt = true;
            return isValidInt;

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return isValidInt;
    }
    public static boolean validateMovieYear(String input){
        final int FIRST_MOVIE_EVER_YEAR = 1901;
        final int CURRENT_YEAR = 2024;

        boolean isValid = false;
        boolean isValidInt = validateInteger(input);
        if (isValidInt == false){
            isValid = false;
            return isValid;
        } else {
            isValid = true;
        }

        int tempYear = Integer.parseInt(input);

        if(tempYear < FIRST_MOVIE_EVER_YEAR || tempYear > CURRENT_YEAR){
            isValid = false;
        }
        return isValid;
    }
    public static boolean validateStringNotEmpty(String input) {

        boolean entryMade = false;

        try {
            String tempString = input;
            if (!tempString.isEmpty()) {
                entryMade = true;
            } else {
                entryMade = false;
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return entryMade;
    }
    // email-validering som accepterar t.ex "user.name@domain.co.in" men inte t.ex ".user.name@.com"
    public static boolean validateEmail(String input) {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile(regexPattern)
                .matcher(input)
                .matches();
    }
    public static boolean validatePostNum(String input){

        boolean isValid = false;

        if(input.length() == 5){
            if(Character.isDigit(input.charAt(0))&&Character.isDigit(input.charAt(1))&&Character.isDigit(input.charAt(2))&&
                    Character.isDigit(input.charAt(3))&&Character.isDigit(input.charAt(4))){
                isValid = true;
                return isValid;
            }
        }
        return isValid;
    }
    public static boolean stringOneEqualsStringTwo(String stringOne, String stringTwo){
        if(stringOne.equals(stringTwo)){
            return true;
        }
        return false;
    }

    public static void displayAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);

        alert.showAndWait();
    }

    public static void displayConfirmation(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);

        alert.showAndWait();
    }


}


