package oop.Day33.generics.exceptionHandling;

/*
============================================================
DAY 33 - CUSTOM EXCEPTION
============================================================
*/

public class MyException extends Exception {
    /*
    ============================================================
    CUSTOM EXCEPTION
    Definition:
    A user-defined exception class to represent specific error conditions
    in your application.

    Why create custom exceptions?
    - To make error messages more meaningful and specific
    - To handle business logic errors separately
    - Better code readability and maintainability

    Two Ways to Create Custom Exception:
    1. Extend Exception class     → Checked Exception
    2. Extend RuntimeException    → Unchecked Exception
    ============================================================
    */

    public MyException(String message) {
        super(message);   // pass message to parent Exception class
    }
}