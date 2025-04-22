package dev.andrylat.utils;

public class StringConverter {
	
	public static String convertString(String s) {
		String[] piecies= s.split(" ");
		StringBuilder sb = new StringBuilder();
		for(String str: piecies) {
			if(!str.isBlank())
			sb.append(str);
		}
		return sb.toString();
	}
}
