package com.local.app;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws MyException {
        Scanner readFromConsole = new Scanner(System.in);
        while (true) {
            System.out.println("Введите математическое выражение для римских или арабских чисел в формате 'операнд1 оператор операнд2' (с использованием пробелов): ");
            String mathExpression = readFromConsole.nextLine();
            String[] elementsOfMathExpression = mathExpression.split(" ");
            if (elementsOfMathExpression.length < 3) {
                System.out.println("Ошибка ввода! Выражение должно быть в формате 'операнд1 оператор операнд2' (с использованием пробелов)");
                continue;
            }

            String[] operators = new String[]{"+", "-", "*", "/"};
            if (!Arrays.asList(operators).contains(elementsOfMathExpression[1])) {
                System.out.println("Ошибка ввода! Калькулятор работает толко с операциями '+', '-', '*', '/' !");
                continue;
            }

            Calculator calculator = new Calculator();
            int[] entireOperands = calculator.tryGetEntireNumbers(elementsOfMathExpression);
            if (entireOperands.length == 0) {
                System.out.println("Ошибка! Оба операнда должны быть либо римскими либо арабскими числами в диапозоне от 1 до 10!");
                continue;
            }
            String operator = elementsOfMathExpression[1];
            String result;

            try {
                result = calculator.getResult(entireOperands, operator);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                continue;
            }

            System.out.println("Результат выполнения выражения: ");
            System.out.println(result);
            break;
        }
    }
}
