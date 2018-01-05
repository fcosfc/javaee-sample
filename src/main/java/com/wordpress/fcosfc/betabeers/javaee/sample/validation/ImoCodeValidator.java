package com.wordpress.fcosfc.betabeers.javaee.sample.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Implementation for the custom validator example.
 *
 * Implementación para el ejemplo de validador personalizado.
 *
 * @author Paco Saucedo
 * @see http://en.wikipedia.org/wiki/IMO_numbers
 */
public class ImoCodeValidator implements ConstraintValidator<ImoCode, Integer> {

    @Override
    public void initialize(ImoCode constraintAnnotation) {
        // Nothing to do
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value != null) {
            String stringValue = value.toString();

            if (stringValue.length() == 7) {
                int result = 0;
                String stringResult;

                for (int i = 0, multiplier = 7; i < 6; i++, multiplier--) {
                    result += Integer.parseInt(stringValue.substring(i, i + 1)) * multiplier;
                }

                stringResult = Integer.toString(result);

                return stringValue.substring(6).equals(stringResult.substring(stringResult.length() - 1));
            }
        }

        return false;
    }
    
}
