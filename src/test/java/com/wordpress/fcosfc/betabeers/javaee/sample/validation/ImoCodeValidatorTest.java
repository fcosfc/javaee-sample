package com.wordpress.fcosfc.betabeers.javaee.sample.validation;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paco
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
    
}
