package com.local.app;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    public boolean isRoman = false;

    private static final Map<String, Integer> ROMANS_TO_ARABIAN = new HashMap<>() {
        {
            put("I", 1);
            put("II", 2);
            put("III", 3);
            put("IV", 4);
            put("V", 5);
            put("VI", 6);
            put("VII", 7);
            put("VIII", 8);
            put("IX", 9);
            put("X", 10);
        }
    };

    private static final Map<Integer, String> ARABIAN_UNITS_TO_ROMANS = new HashMap<>() {
        {
            put(1, "I");
            put(2, "II");
            put(3, "III");
            put(4, "IV");
            put(5, "V");
            put(6, "VI");
            put(7, "VII");
            put(8, "VIII");
            put(9, "IX");
        }
    };

    private static final Map<Integer, String> ARABIAN_TENS_TO_ROMANS = new HashMap<>() {
        {
            put(1, "X");
            put(2, "XX");
            put(3, "XXX");
            put(4, "XL");
            put(5, "L");
            put(6, "LX");
            put(7, "LXX");
            put(8, "LXXX");
            put(9, "XC");
            put(10, "C");
        }
    };

    public int[] tryGetEntireNumbers(String[] elementsOfMathExpression) {
        if (ROMANS_TO_ARABIAN.containsKey(elementsOfMathExpression[0]) && ROMANS_TO_ARABIAN.containsKey(elementsOfMathExpression[2])) {
            isRoman = true;
            return new int[]{ROMANS_TO_ARABIAN.get(elementsOfMathExpression[0]), ROMANS_TO_ARABIAN.get(elementsOfMathExpression[2])};
        }

        if (ROMANS_TO_ARABIAN.containsKey(elementsOfMathExpression[0]) || ROMANS_TO_ARABIAN.containsKey(elementsOfMathExpression[2])) {
            return new int[0];
        }

        int[] entireNumbersInArabian;
        try {
            entireNumbersInArabian = new int[]{Integer.parseInt(elementsOfMathExpression[0]), Integer.parseInt(elementsOfMathExpression[2])};
        } catch (NumberFormatException exception) {
            return new int[0];
        }
        return entireNumbersInArabian;
    }

    public String getResult(int[] validatedOperands, String operator) throws MyException {
        int result = this.calculate(validatedOperands, operator);
        if (!isRoman) {
            return String.valueOf(result);
        }
        if (result <= 0) {
            throw new MyException("В системе счисления, использующей Римские цифры," +
                    " нуль и отрицательные значения отсутствуют!");
        }

        if (result < 10) {
            return ARABIAN_UNITS_TO_ROMANS.get(result);
        }

        int tens = result / 10;
        int units = result % 10;
        if (units > 0) {
            return String.format("%s%s", ARABIAN_TENS_TO_ROMANS.get(tens), ARABIAN_UNITS_TO_ROMANS.get(units));
        } else {
            return ARABIAN_TENS_TO_ROMANS.get(tens);
        }
    }

    private int calculate(int[] validatedOperands, String operator) {
        return switch (operator) {
            case "+" -> validatedOperands[0] + validatedOperands[1];
            case "-" -> validatedOperands[0] - validatedOperands[1];
            case "*" -> validatedOperands[0] * validatedOperands[1];
            case "/" -> validatedOperands[0] / validatedOperands[1];
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }
}
