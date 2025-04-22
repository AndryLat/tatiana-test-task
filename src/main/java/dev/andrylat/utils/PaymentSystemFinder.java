package dev.andrylat.utils;

import java.util.HashMap;
import java.util.Map;

import dev.andrylat.cardProperties.PaymentSystems;

public class PaymentSystemFinder {
	
	public static Map<String, PaymentSystems> paymentSystems = new HashMap<>();
	
	static {
		paymentSystems.put("4", PaymentSystems.VISA);
		paymentSystems.put("51", PaymentSystems.MASTERCARD);
		paymentSystems.put("52", PaymentSystems.MASTERCARD);
		paymentSystems.put("53", PaymentSystems.MASTERCARD);
		paymentSystems.put("54", PaymentSystems.MASTERCARD);
		paymentSystems.put("55", PaymentSystems.MASTERCARD);
		paymentSystems.put("36", PaymentSystems.DINERS_CLUB);
		paymentSystems.put("38", PaymentSystems.DINERS_CLUB);
		paymentSystems.put("6011", PaymentSystems.DISCOVER);
		paymentSystems.put("65", PaymentSystems.DISCOVER);
		paymentSystems.put("65", PaymentSystems.DISCOVER);
		paymentSystems.put("35", PaymentSystems.JCB);
		paymentSystems.put("34", PaymentSystems.AMERICAN_EXPRESS);
		paymentSystems.put("37", PaymentSystems.AMERICAN_EXPRESS);		
	}
	
	public static PaymentSystems findPaymentSystem(String cardNumber) {
		if(cardNumber == null || cardNumber.length() < 4) {
			return PaymentSystems.UNDEFINED;
		}
		String prefix = cardNumber.substring(0, 4);
		if(!CheckDigitsAlgorithm.checkDigitsInString(prefix)) {
			return PaymentSystems.UNDEFINED;
		}
		
		StringBuilder sb = new StringBuilder();		
		for(char c: prefix.toCharArray()) {
			sb.append(c);			
			if(paymentSystems.containsKey(sb.toString())) {
				return paymentSystems.get(sb.toString());
			}
		}		
		return PaymentSystems.UNDEFINED;
	}
			
}
