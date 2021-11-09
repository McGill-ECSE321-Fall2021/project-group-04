package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class LibraryItemService {
	
	
	/**
	 * @author alymo
	 * Checks if input elements are null
	 * @param barCode
	 * @param title
	 * @param author
	 * @param dateOfRelease
	 * @param price
	 * @return
	 */
	public static boolean checkMobileItemInfo(String barCode, String title, String author,
			String dateOfRelease, String price) {
		
		String error = "";
		
		if(barCode == null || barCode == "") {
			error += "barcode needs to be specified ";
		}		
		if(title == null || title == "") {
			error += "title needs to be specified ";
		}		
		if(author == null || author == "") {
			error += "author needs to be specified ";
		}		
		if(dateOfRelease == null || dateOfRelease == "") {
			error += "dateOfRelease needs to be specified ";
		}
		try {
			Date date = Date.valueOf(dateOfRelease);
		}
		catch(Exception e) {
			error += "date format is not correct";
		}
		if(price == null || price == "") {
			error += "price needs to be specified ";
		}
		try {
			int p = Integer.valueOf(price);
		}
		catch(Exception e) {
			error += "price is not a number";
		}
		
		Boolean hasError = error != null || error != "";
		
		if(hasError) throw new IllegalArgumentException(error);
		else return true;
		
	}
	
	/**
	 * @author alymo
	 * Checks if input elements are null
	 * @param date
	 * @param numberOfPages
	 * @param title
	 * @return
	 */
	public static boolean checkImmobileItemInfo(String date, String numberOfPages, String title) {
		
		String error = "";
		
		if(title == null || title == "") {
			error += "title needs to be specified ";
		}	
		if(date == null || date == "") {
			error += "date needs to be specified ";
		}		
		try {
			Date d = Date.valueOf(date);
		}
		catch(Exception e) {
			error += "date format is not correct";
		}
		if(numberOfPages == null || numberOfPages == "") {
			error += "number of pages needs to be specified ";
		}		
		try {
			int pages = Integer.valueOf(numberOfPages);
		}
		catch(Exception e) {
			error += "number of pages is not a number";
		}
	
		Boolean hasError = error != null || error != "";
		
		if(hasError) throw new IllegalArgumentException(error);
		else return true;
		
	}
	
	/**
	 * Converts an iterable element to a list
	 * @param <T>
	 * @param iterable
	 * @return
	 */
	public static <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;

	}

}
