package lab02.validator;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import lab02.repository.Repository;
import lab02.exceptions.PatientException;

public class ConsultationValidation {
	public static void nameValidate(String name) throws PatientException {
		if (name.length() == 0) {
			throw new PatientException("One of the required fields is empty!");
		}
		if (name.matches("[a-zA-Z ]+")==false){
			throw new PatientException("the name is not only made of letters");
		}
		if( name.length() > 4==false){
			throw new PatientException("the name length is less than 5 ");

		}
		/*
		Pattern pattern = Pattern.compile("^[a-zA-Z -]+$");
		Matcher matcher = pattern.matcher(name);
		if (!matcher.find()) {
			throw new PatientException("The \"name\" field has an invalid format!");
		}
		*/
	}

	public static void ssnValidate(String ssn) throws PatientException {
		if (ssn.length() != 13) {
			System.out.println("ssn="+ ssn+" si lungime="+ssn.length());
			throw new PatientException("SSN has the length != 13");
		}
		if(ssn.matches("[0-9]+") == false){
			throw new PatientException("SSN doesn't have only numbers");
		}


	}

	public static void addressValidate(String address) throws PatientException {
		if (address.length() == 0) {
			throw new PatientException("One of the required fields is empty!");
		}
		if (address.matches("[a-zA-Z 0-9]+") == false){
			throw new PatientException("the address is not only made of letters and numbers");
		}
		if(address.length() > 4==false){
			throw new PatientException("the address has length smaller than 5");

		}
	}
}
