package dev.andrylat.exceptions;

public class WrongNumberLengthException extends Exception {
	
	@Override
	public String toString() {
		return "-> Length should be 16 symbols";
	}

}
