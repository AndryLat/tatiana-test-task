package dev.andrylat.interfaces;

import dev.andrylat.exceptions.NumberDoesNotContainDigitsException;
import dev.andrylat.exceptions.WrongNumberLengthException;
import dev.andrylat.exceptions.WrongCheckSumException;
import dev.andrylat.exceptions.WrongPaymentSystemException;

public interface Validatable {
	boolean checkCardNumberLength() throws WrongNumberLengthException;
	boolean checkCardDigits() throws NumberDoesNotContainDigitsException;
	boolean checkPaymentSystem() throws WrongPaymentSystemException;
	boolean checkControlSum() throws WrongCheckSumException;
	boolean approveCard()throws NumberDoesNotContainDigitsException, WrongNumberLengthException, WrongPaymentSystemException, WrongCheckSumException;
}
