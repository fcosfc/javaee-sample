package com.wordpress.fcosfc.betabeers.javaee.sample.validation;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

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
    private static final int TOO_BIG_IMO_NUMBER = 904395478;
    
    private ImoCodeValidator validator;
    
    @Before
    public void createValidator() {
        validator = new ImoCodeValidator();
    }
    
    @Test
    public void forAValidIMO_returnTrue() {
        assertTrue(validator.isValid(VALID_IMO_NUMBER, null));
    }
    
    @Test
    public void forAWrongIMO_returnFalse() {
        assertFalse(validator.isValid(WRONG_IMO_NUMBER, null));
    }
    
    @Test
    public void whenNull_returnFalse() {
        assertFalse(validator.isValid(null, null));
    }
    
    @Test
    public void forATooSmallNumber_returnFalse() {
        assertFalse(validator.isValid(TOO_SMALL_IMO_NUMBER, null));
    }
    
    @Test
    public void forATooBigNumber_returnFalse() {
        assertFalse(validator.isValid(TOO_BIG_IMO_NUMBER, null));
    }
    
}
