package dev.andrylat.exceptions;

public class WrongPaymentSystemException extends Exception {
	
	@Override
	public String toString() {
		return "-> Payment System cant't be determine";
	}

}
