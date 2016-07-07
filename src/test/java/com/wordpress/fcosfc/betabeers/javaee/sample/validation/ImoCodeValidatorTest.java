package com.wordpress.fcosfc.betabeers.javaee.sample.validation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit test for the IMO Code Validator
 * 
 * Test unitario para el validador de códigos IMO de buques
 * 
 * @author Paco Saucedo
 */
public class ImoCodeValidatorTest {
    
    private static final int VALID_IMO_NUMBER = 9043952;
    private static final int WRONG_IMO_NUMBER = 9043954;
    private static final int TOO_SMALL_IMO_NUMBER = 123;
    private static final int TOO_LONG_IMO_NUMBER = 904395478;
    
    @Test
    public void testIsValid() {
        ImoCodeValidator validator = new ImoCodeValidator();
        assertTrue(validator.isValid(VALID_IMO_NUMBER, null));
    }
    
    @Test
    public void testIsNotValid() {
        ImoCodeValidator validator = new ImoCodeValidator();
        assertFalse(validator.isValid(WRONG_IMO_NUMBER, null));
    }
    
    @Test
    public void testNullIsNotValid() {
        ImoCodeValidator validator = new ImoCodeValidator();
        assertFalse(validator.isValid(null, null));
    }
    
    @Test
    public void testTooSmallNumbersAreNotValid() {
        ImoCodeValidator validator = new ImoCodeValidator();
        assertFalse(validator.isValid(TOO_SMALL_IMO_NUMBER, null));
    }
    
    @Test
    public void testTooLongNumbersAreNotValid() {
        ImoCodeValidator validator = new ImoCodeValidator();
        assertFalse(validator.isValid(TOO_LONG_IMO_NUMBER, null));
    }
    
}
