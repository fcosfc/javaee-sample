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
    
    @Test
    public void testIsValid() {
        ImoCodeValidator validator = new ImoCodeValidator();
        assertTrue(validator.isValid(9043952, null));
    }
    
    @Test
    public void testIsNotValid() {
        ImoCodeValidator validator = new ImoCodeValidator();
        assertFalse(validator.isValid(9043954, null));
    }
    
    @Test
    public void testNullIsNotValid() {
        ImoCodeValidator validator = new ImoCodeValidator();
        assertFalse(validator.isValid(null, null));
    }
    
    @Test
    public void testTooSmallOrTooLongNumbersAreNotValid() {
        ImoCodeValidator validator = new ImoCodeValidator();
        assertFalse(validator.isValid(123, null));
        assertFalse(validator.isValid(904395478, null));
    }
    
}
