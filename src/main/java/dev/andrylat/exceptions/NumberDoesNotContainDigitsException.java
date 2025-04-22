package dev.andrylat.exceptions;

public class NumberDoesNotContainDigitsException extends Exception {
	
	@Override
	public String toString() {
		return "-> Number should contain only digits";
	}

}
