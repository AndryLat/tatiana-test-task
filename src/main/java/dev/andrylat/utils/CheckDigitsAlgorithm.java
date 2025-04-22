package dev.andrylat.utils;

public class CheckDigitsAlgorithm {
	
	public static boolean checkDigitsInString(String number) {
		if(number == null)
			return false;
		
		long count = number.chars()
				.filter(Character::isDigit)
				.count();
				
			return count == number.length();
	}
}
