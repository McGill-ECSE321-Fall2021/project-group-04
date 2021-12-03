package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import org.springframework.stereotype.Service;

@Service
public class MobileItemServices {

    /**
     * @param barCode
     * @param title
     * @param author
     * @param dateOfRelease
     * @param price
     * @return
     * @author alymo
     * Checks if input elements are null
     */
    public static boolean checkItemInfo(String barCode, String title, String author, String dateOfRelease, String price) {

        String error = "";

        if (barCode == null || barCode == "") {
            error += "barcode needs to be specified ";
        }
        if (title == null || title == "") {
            error += "title needs to be specified ";
        }
        if (author == null || author == "") {
            error += "author needs to be specified ";
        }
        if (dateOfRelease == null || dateOfRelease == "") {
            error += "dateOfRelease needs to be specified ";
        }
        try {
            Date date = Date.valueOf(dateOfRelease);
        } catch (Exception e) {
            error += "date format is not correct ";
        }
        if (price == null || price == "") {
            error += "price needs to be specified ";
        }
        try {
            int p = Integer.valueOf(price);
        } catch (Exception e) {
            error += "price is not a number ";
        }

        Boolean hasError = error != "";

        if (hasError) throw new IllegalArgumentException(error);
        else return true;

    }

}
