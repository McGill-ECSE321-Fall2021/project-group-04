package ca.mcgill.ecse321.library.service;

public class HelpersBooking {
	
	
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
		if(price == null || price == "") {
			error += "price needs to be specified ";
		}
		
		Boolean hasError = error != null || error != "";
		
		if(hasError) throw new IllegalArgumentException(error);
		else return true;
		
	}
	
}
