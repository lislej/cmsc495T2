package org.amortizer.loancalcservice.utils;

import java.util.regex.Pattern;

public class RegexValidator {

	public static boolean isValid(String email, String emailRegex) {
		//"^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		
		return (email == null) ? false : pat.matcher(email).matches() ; 
		
	}
}
