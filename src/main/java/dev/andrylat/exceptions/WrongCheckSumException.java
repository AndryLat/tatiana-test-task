package dev.andrylat.exceptions;

public class WrongCheckSumException extends Exception {
	
	public WrongCheckSumException() {
		super("Invalid card number. The check sum is not correct ");
	}
	
	@Override 
	public String toString() {
		return "-> Invalid card number. The check sum is not correct";
	}
}
