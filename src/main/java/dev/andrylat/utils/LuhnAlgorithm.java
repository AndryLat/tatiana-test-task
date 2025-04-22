package dev.andrylat.utils;

public final class LuhnAlgorithm {
	
	public static int calculateCheckSum(String number) {
		
		if(!CheckDigitsAlgorithm.checkDigitsInString(number)) {
			return -1;
		}
		
		int evenDigitsSum = 0;
		int oddDigitsSum = 0;
		
		char[] digits = number.toCharArray();
		for(int i = 0; i < number.length() -1 ; i++) {
			
			int temp = digits[i] - 48;
			
			if(i%2==0) {
				int digitalRoot = ((temp * 2) / 10) + ((temp * 2) % 10);
				evenDigitsSum += digitalRoot;
			}
			else {
				oddDigitsSum += temp;
			}
		}
		
		int checkSum = 10 - ((evenDigitsSum + oddDigitsSum) % 10);
		return checkSum;
	}
	
}
