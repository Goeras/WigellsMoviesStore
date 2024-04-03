package org.dreamteam.wigellsmoviesstore;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;

// Konverterar div inputs och returnera dem. static för åtkomst över hela programmet.
public class IoConverter {

    public static String doubleToString(double input){
        return Double.toString(input);
    }

    public static double stringToDouble(String input){
        return Double.parseDouble(input);
    }

    public static String integerToString(int input){
        return Integer.toString(input);
    }

    public static int stringToInteger(String input){
        return Integer.parseInt(input);
    }

    public static SimpleStringProperty stringToSimpleStringProperty(String input){
        return new SimpleStringProperty(input);
    }

    public static SimpleIntegerProperty integerToSimpleIntegerProperty(int input){
        return new SimpleIntegerProperty(input);
    }

    public static SimpleDoubleProperty doubleToSimpleDoubleProperty(double input){
        return new SimpleDoubleProperty(input);
    }

    public static String specialFeaturesToString(List<String> specialFeatures) {
        if (specialFeatures == null || specialFeatures.isEmpty()) {
            return "";
        }
        return String.join(",", specialFeatures);
    }

}
