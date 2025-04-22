package dev.andrylat.app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dev.andrylat.cardHandlers.CardHandler;
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
import dev.andrylat.utils.StringConverter;



public class AppTest 
{
    private static Validatable cardHandler;
    private static Validatable cardHandler_negativeCase;
    private static final String validCardNumberExample = "5457623898234113";
    private static final String invalidCardNumberExample = "1234xzyq1111";
    
    @BeforeAll
    public static void setup() {
    	Card validCard = new Card();
    	validCard.setNumber(validCardNumberExample);
    	cardHandler = new CardHandler(validCard);
    	
    	Card invalidCard = new Card();
    	invalidCard.setNumber(invalidCardNumberExample);
    	cardHandler_negativeCase = new CardHandler(invalidCard);
    }
    
    @Test
    public void isCardNumberLengthValid_True() {
    	boolean result = false;
    	
		try {
			result = cardHandler.checkCardNumberLength();
			
			Assertions.assertEquals(result, true);
		} catch (WrongNumberLengthException e) {
			Assertions.fail();
		}    	
    }

    @Test
    public void isCardNumberContainOnlyDigits_True() {
    	boolean result = false;
    	
    	try {
			result = cardHandler.checkCardDigits();
			
			Assertions.assertEquals(result, true);
		} catch (NumberDoesNotContainDigitsException e) {
			Assertions.fail();
		}    	
    }
    
    @Test
    public void isCardPaymentSystemValid_True() {
    	boolean result = false;
    	
    	try {
			result = cardHandler.checkPaymentSystem();
			
			Assertions.assertEquals(result, true);
		} catch (WrongPaymentSystemException e) {
			Assertions.fail();
		}    	
    }
    
    @Test
    public void isCardControlSumValid_True() {
    	boolean result = false;
    	
    	try {
			result = cardHandler.checkControlSum();
			
			Assertions.assertEquals(result, true);
		} catch (WrongCheckSumException e) {
			Assertions.fail();
		}    	
    }
    
    @Test
    public void isCardValid_true() {
    	boolean result = false;
    	
    	try {
    		result = cardHandler.approveCard();
    		
    		Assertions.assertEquals(result, true);
    	} catch (Exception e) {
    		Assertions.fail();
    	}
    }
    
    @Test
    public void testCheckDigitsAlgorithm_validInput_true() {
    	boolean result = false;
    	final String validNumberExample = "1123581321";
    	
    	result = CheckDigitsAlgorithm.checkDigitsInString(validNumberExample);
    	
    	Assertions.assertEquals(result, true);
    }
    
    @Test
    public void testCheckDigitsAlgorithm_invalidInput_false() {
    	boolean result = false;
    	final String invalidNumberExample = "112t5e1321";
    	
    	result = CheckDigitsAlgorithm.checkDigitsInString(invalidNumberExample);
    	
    	Assertions.assertEquals(result, false);
    }
    
    @Test
    public void testCheckDigitsAlgorithm_emptyInput_false() {
    	boolean result = false;
    	final String emptyNumberExample = null;
    	
    	result = CheckDigitsAlgorithm.checkDigitsInString(emptyNumberExample);
    	
    	Assertions.assertEquals(result, false);
    }
    
    @Test
    public void testLuhnAlgorithm_resultEquals3() {
    	final String validCardNumber = "5457623898234113";
    	int expectedNumber = 3;
    	
    	int calculatedNumber = LuhnAlgorithm.calculateCheckSum(validCardNumber);
    	
    	Assertions.assertEquals(calculatedNumber, expectedNumber);    	
    }
    
    @Test
    public void testLuhnAlgorithm_negativeCaseWhenNumberDoesntContaintOnlyDigits() {
    	final String invalidCardNumber = "5457s23898234o13";
    	int expectedNumber = -1;
    	
    	int calculatedNumber = LuhnAlgorithm.calculateCheckSum(invalidCardNumber);
    	
    	Assertions.assertEquals(calculatedNumber, expectedNumber);    	
    }
    
    @Test
    public void testPaymentSystemFinding_expectMastercard() {
    	final String validCardNumber = "5457623898234113";
    	
    	PaymentSystems mastercard = PaymentSystemFinder.findPaymentSystem(validCardNumber);
    	
    	Assertions.assertEquals(mastercard, PaymentSystems.MASTERCARD);    	
    }
    
    @Test
    public void testPaymentSystemFinding_expectVisa() {
    	final String validCardNumber = "4457623898234113";
    	
    	PaymentSystems visa = PaymentSystemFinder.findPaymentSystem(validCardNumber);
    	
    	Assertions.assertEquals(visa, PaymentSystems.VISA);    	
    }
    
    @Test
    public void testPaymentSystemFinding_expectUndefined() {
    	final String invalidCardNumber = "0001623898234113";
    	
    	PaymentSystems undefined = PaymentSystemFinder.findPaymentSystem(invalidCardNumber);
    	
    	Assertions.assertEquals(undefined, PaymentSystems.UNDEFINED);    	
    }
    
    @Test
    public void testPaymentSystemFinding_expectUndefinedWhenInputIsValid() {
    	final String invalidCardNumber = "asd2wd234";
    	
    	PaymentSystems undefined = PaymentSystemFinder.findPaymentSystem(invalidCardNumber);
    	
    	Assertions.assertEquals(undefined, PaymentSystems.UNDEFINED);    	
    }
    
    @Test
    public void testPaymentSystemFinding_expectUndefinedWhenInputIsNull() {
    	final String invalidCardNumber = null;
    	
    	PaymentSystems undefined = PaymentSystemFinder.findPaymentSystem(invalidCardNumber);
    	
    	Assertions.assertEquals(undefined, PaymentSystems.UNDEFINED);    	
    }
    
    @Test
    public void testPaymentSystemFinding_expectUndefinedWhenInputLessThan4() {
    	final String invalidCardNumber = "123";
    	
    	PaymentSystems undefined = PaymentSystemFinder.findPaymentSystem(invalidCardNumber);
    	
    	Assertions.assertEquals(undefined, PaymentSystems.UNDEFINED);    	
    }
    
    @Test
    public void testStringConverter_true() {
    	final String inputString = " 1234 567 8 ";
    	final String standartOfString = "12345678";
    	
    	final String expectedString = StringConverter.convertString(inputString);
    	
    	Assertions.assertEquals(expectedString, standartOfString);
    }
    
    @Test
    void testWrongNumberLengthExceptionWithCardHandler() {

    	Assertions.assertThrows(WrongNumberLengthException.class, () -> {
    		cardHandler_negativeCase.checkCardNumberLength();
    	});
    }
    
    @Test
    void testNumberDoesNotContainDigitsExceptionWithCardHandler() {

    	Assertions.assertThrows(NumberDoesNotContainDigitsException.class, () -> {
    		cardHandler_negativeCase.checkCardDigits();
    	});
    }
    
    @Test
    void testWrongPaymentSystemExceptionWithCardHandler() {

    	Assertions.assertThrows(WrongPaymentSystemException.class, () -> {
    		cardHandler_negativeCase.checkPaymentSystem();
    	});
    }
    
    @Test
    void testWrongCheckSumExceptionWithCardHandler() {

    	Assertions.assertThrows(WrongCheckSumException.class, () -> {
    		cardHandler_negativeCase.checkControlSum();
    	});
    }
}
