package dev.andrylat.cardHandlers;

import dev.andrylat.cardProperties.PaymentSystems;
import dev.andrylat.cards.Card;
import dev.andrylat.exceptions.NumberDoesNotContainDigitsException;
import dev.andrylat.exceptions.WrongNumberLengthException;
import dev.andrylat.exceptions.WrongCheckSumException;
import dev.andrylat.exceptions.WrongPaymentSystemException;
import dev.andrylat.interfaces.Validatable;
import dev.andrylat.utils.CheckDigitsAlgorithm;
import dev.andrylat.utils.LuhnAlgorithm;
import dev.andrylat.utils.PaymentSystemFinder;

public class CardHandler implements Validatable{
	
	private static final int CARD_NUMBER_LENGTH = 16;
	private Card card;
	
	public CardHandler(Card card) {
		if(card != null)
			this.card = card;
	}
	
	@Override
	public boolean checkCardNumberLength() throws WrongNumberLengthException {
		boolean result = card.getNumber().length() == CARD_NUMBER_LENGTH;
		if(result) {
			return result;
		}
		else {
			throw new WrongNumberLengthException();
		}
	}

	@Override
	public boolean checkCardDigits() throws NumberDoesNotContainDigitsException {
		boolean result = CheckDigitsAlgorithm.checkDigitsInString(card.getNumber());
		if(result) {
			return result;
		}
		else {
			throw new NumberDoesNotContainDigitsException();
		}
	}

	@Override
	public boolean checkPaymentSystem() throws WrongPaymentSystemException {
		boolean result = !(PaymentSystemFinder.findPaymentSystem(card.getNumber()).equals(PaymentSystems.UNDEFINED));
		if(result) {
			return result;
		}
		else {
			throw new WrongPaymentSystemException();
		}
		
	}

	@Override
	public boolean checkControlSum() throws WrongCheckSumException {
		String sub_cardNumber = card.getNumber().substring(card.getNumber().length() - 1);
		int last_number = Integer.valueOf(sub_cardNumber);
		
		boolean result = last_number == LuhnAlgorithm.calculateCheckSum(card.getNumber());
		if(result) {
			return result;
		}
		else {
			throw new WrongCheckSumException();
		}
	}
	
	@Override
	public boolean approveCard() throws NumberDoesNotContainDigitsException, WrongNumberLengthException, WrongPaymentSystemException, WrongCheckSumException {
		return checkCardDigits() && checkCardNumberLength() && checkPaymentSystem() && checkControlSum();
	}
	
	public int getCardNumberLength() {
		return CARD_NUMBER_LENGTH;
	}
	


}
