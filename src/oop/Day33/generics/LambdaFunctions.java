package oop.Day33.generics;

import java.util.ArrayList;
import java.util.function.Consumer;

/*
============================================================
DAY 33 - Lambda Functions & Functional Interfaces
============================================================
*/

public class LambdaFunctions {
    /*
    ============================================================
    LAMBDA EXPRESSIONS (Java 8+)

    Definition:
    Lambda expression is an anonymous function that can be used
    to implement functional interfaces.

    Functional Interface:
    An interface with exactly one abstract method.

    Syntax: (parameters) -> expression
    ============================================================
    */

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arr.add(i + 1);
        }

        // Using Lambda with forEach
        arr.forEach((item) -> System.out.println(item * 2));

        // Storing Lambda in a variable
        Consumer<Integer> fun = (item) -> System.out.println(item * 2);
        arr.forEach(fun);

        // Custom Functional Interface with Lambda
        Operation sum = (a, b) -> a + b;
        Operation prod = (a, b) -> a * b;
        Operation sub = (a, b) -> a - b;

        LambdaFunctions calculator = new LambdaFunctions();

        System.out.println("Sum: " + calculator.operate(5, 3, sum));
        System.out.println("Product: " + calculator.operate(5, 3, prod));
        System.out.println("Subtract: " + calculator.operate(5, 3, sub));
    }

    private int operate(int a, int b, Operation op) {
        return op.operation(a, b);
    }
}

// Functional Interface
interface Operation {
    int operation(int a, int b);
}