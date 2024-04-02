package org.dreamteam.wigellsmoviesstore;

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
        final int FIRST_MOVIE_EVER_YEAR = 1888;
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
}
