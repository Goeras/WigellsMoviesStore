package org.dreamteam.wigellsmoviesstore;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.locationtech.jts.geom.Geometry;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

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

    public static short stringToShort(String input) { return Short.parseShort(input);}

    public static byte stringToByte(String input) { return Byte.parseByte(input);}

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
            e.printStackTrace();
            return null;
        }
    }

    public static String geometryToString(Geometry geometry) {
        return ("Position: " + geometry.toText());
    }

}
