package org.dreamteam.wigellsmoviesstore;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.locationtech.jts.geom.Geometry;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

import java.util.List;

// Konverterar div inputs och returnera dem. static för åtkomst över hela programmet.
public class IoConverter {

    public static String doubleToString(double input) {
        return Double.toString(input);
    }

    public static double stringToDouble(String input) {
        return Double.parseDouble(input);
    }

    public static String integerToString(int input) {
        return Integer.toString(input);
    }

    public static int stringToInteger(String input) {
        if (input != null) {
            return Integer.parseInt(input);
        }
        throw new NumberFormatException("Input is empty or null");
    }

    public static short stringToShort(String input) {
        if (input != null) {
            return Short.parseShort(input);
        }
        throw new NumberFormatException("Input is empty or null");
    }


    public static byte stringToByte(String input) { return Byte.parseByte(input);}

    public static SimpleStringProperty stringToSimpleStringProperty(String input){
        if(input!=null){
        return new SimpleStringProperty(input);}
        else{
            return new SimpleStringProperty(" ");
        }
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

    public static SimpleStringProperty categoriesToStringProperty(List<Category> categories){
        if (categories != null && !categories.isEmpty()) {
            StringBuilder categorySB = new StringBuilder();
            for (Category category : categories) {
                categorySB.append(category.getName()).append(", ");
            }
            categorySB.delete(categorySB.length() - 2, categorySB.length());
            return new SimpleStringProperty(categorySB.toString());
        } else {
            return new SimpleStringProperty("");
        }

    }

    public static Image convertBlobToImage(Blob blob) {
        try (InputStream inputStream = blob.getBinaryStream()) {
            return new Image(inputStream);
        } catch (Exception e) {
            System.out.println("NullPointerException: Ingen bild finns lagrad i databasen");
            return null;
        }
    }

    public static String geometryToString(Geometry geometry) {
        return ("Position: " + geometry.toText());
    }
    public static SimpleStringProperty dateToSimpleStringProperty(Date date){
        if(date!=null){
            return new SimpleStringProperty(date.toString());
        }
        else {
            return new SimpleStringProperty("");
        }
    }

}
