package dev.andrylat.appMain;

import java.util.Scanner;

import dev.andrylat.cardHandlers.CardHandler;
import dev.andrylat.cards.Card;
import dev.andrylat.exceptions.NumberDoesNotContainDigitsException;
import dev.andrylat.exceptions.WrongNumberLengthException;
import dev.andrylat.exceptions.WrongCheckSumException;
import dev.andrylat.exceptions.WrongPaymentSystemException;
import dev.andrylat.interfaces.Validatable;
import dev.andrylat.utils.PaymentSystemFinder;
import static dev.andrylat.utils.StringConverter.convertString;;

public class CreditCardValidationMain {
	
	//valid credit card number example 5457 6238 9823 4113

	public static void main(String[] args) {
		System.out.println("> Hello. Enter a card number for validation:");
		Scanner sc = new Scanner(System.in);
		String cardNumber = convertString(sc.nextLine());
		Card card = new Card();
		card.setNumber(cardNumber);
		Validatable cardValidator = new CardHandler(card);
		try {
			cardValidator.approveCard(); 
			System.out.println("> Card is valid. Payment System is " 
			    +  PaymentSystemFinder.findPaymentSystem(card.getNumber()));
			
		} catch (NumberDoesNotContainDigitsException | WrongNumberLengthException | WrongPaymentSystemException
				| WrongCheckSumException e) {
			System.out.println("> Card number is invalid");
			System.out.println("> Errors: ");
			System.out.println(e);
		}
		sc.close();
	}

}
