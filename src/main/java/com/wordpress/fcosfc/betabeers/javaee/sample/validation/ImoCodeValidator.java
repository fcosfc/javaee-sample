package com.wordpress.fcosfc.betabeers.javaee.sample.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Paco Saucedo
 */
public class ImoCodeValidator implements ConstraintValidator<ImoCode, Integer> {
    
    @Override
    public void initialize(ImoCode constraintAnnotation) {
    }
    
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        char[] charValues = value.toString().toCharArray();
        
        if (charValues.length == 7) {
            int result = 0;
            char[] resultValues;
            
            for (int i = 0, multiplier = 7; i < 6; i++, multiplier--) {
                result += charValues[i] * multiplier;
            }
            
            resultValues = (new Integer(result)).toString().toCharArray();
            
            return charValues[6] == resultValues[resultValues.length - 1];
        }
        
        return false;
    }
}
