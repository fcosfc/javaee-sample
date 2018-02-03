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

    private static final int MIN_IMO_NUMBER = 1000000;
    private static final int MAX_IMO_NUMBER = 9999999;
    private static final int CONTROL_DIGIT_INDEX = 6;
    private static final int LENGHT_IMO_CODE = 7;
    private static final int MAX_INDEX_ARRAY_DIGITS = 6;

    @Override
    public void initialize(ImoCode constraintAnnotation) {
        // Nothing to do
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null || value < MIN_IMO_NUMBER || value > MAX_IMO_NUMBER) {
            return false;
        } else {
            return isEqualsTheExpectedControlDigitToTheActualOne(value);
        }
    }

    private boolean isEqualsTheExpectedControlDigitToTheActualOne(Integer value) {
        int[] digits = getDigits(value);

        return digits[CONTROL_DIGIT_INDEX] == calulateExpectedControlDigit(digits);
    }

    private int[] getDigits(Integer value) {
        int[] digits = new int[LENGHT_IMO_CODE];

        for (int digitsArrayIndex = MAX_INDEX_ARRAY_DIGITS;
                value > 0;
                digitsArrayIndex--) {
            int quotient = value / 10;
            digits[digitsArrayIndex] = value - quotient * 10;
            value = quotient;
        }

        return digits;
    }

    private int calulateExpectedControlDigit(int[] digits) {
        int controlCalulationResult = 0;

        for (int digitsArrayIndex = 0, multiplier = LENGHT_IMO_CODE;
                digitsArrayIndex < MAX_INDEX_ARRAY_DIGITS;
                digitsArrayIndex++, multiplier--) {
            controlCalulationResult += digits[digitsArrayIndex] * multiplier;
        }
        
        return getLastDigit(controlCalulationResult);
    }
    
    private int getLastDigit(int value) {
        return value - (value / 10 * 10);
    }
}
