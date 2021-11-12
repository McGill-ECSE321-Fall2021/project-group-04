package ca.mcgill.ecse321.library.service;

import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class ImmobileItemServices {

    /**
     * @param date
     * @param numberOfPages
     * @param title
     * @return
     * @author alymo
     * Checks if input elements are null
     */
    public static boolean checkItemInfo(String date, String numberOfPages, String title) {

        String error = "";

        if (title == null || title == "") {
            error += "title needs to be specified ";
        }
        if (date == null || date == "") {
            error += "date needs to be specified ";
        }
        try {
            Date d = Date.valueOf(date);
        } catch (Exception e) {
            error += "date format is not correct";
        }
        if (numberOfPages == null || numberOfPages == "") {
            error += "number of pages needs to be specified ";
        }
        try {
            int pages = Integer.valueOf(numberOfPages);
        } catch (Exception e) {
            error += "number of pages is not a number";
        }

        Boolean hasError = error != "";

        if (hasError) throw new IllegalArgumentException(error);
        else return true;

    }

}
