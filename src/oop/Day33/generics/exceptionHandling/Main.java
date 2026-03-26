package oop.Day33.generics.exceptionHandling;

/*
============================================================
DAY 33 - EXCEPTION HANDLING DEMO
============================================================
*/

public class Main {
    /*
    ============================================================
    EXCEPTION HANDLING IN JAVA

    Definition:
    Exception Handling is a mechanism to handle runtime errors
    so that normal flow of the program can be maintained.

    Keywords:
    - try     : Block where exception may occur
    - catch   : Block to handle the exception
    - finally : Block that always executes (cleanup code)
    - throw   : Used to throw an exception manually
    - throws  : Used in method signature to declare checked exceptions

    Hierarchy:
    Throwable
        ├── Error (e.g., OutOfMemoryError)
        └── Exception
                ├── Checked Exceptions (must be handled or declared)
                └── Unchecked Exceptions (RuntimeException)
    ============================================================
    */

    public static void main(String[] args) {
        int a = 5;
        int b = 0;

        try {
            // divide(a, b);   // This will throw ArithmeticException

            String name = "Kunal";
            if (name.equals("Kunal")) {
                throw new MyException("Name is Kunal - Custom Exception thrown");
            }
        }
        catch (MyException e) {
            System.out.println("Custom Exception: " + e.getMessage());
        }
        catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception: " + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Some other exception occurred");
        }
        finally {
            System.out.println("This block will ALWAYS execute - Cleanup code");
        }
    }

    // Method that declares it can throw ArithmeticException
    static int divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Please do not divide by zero");
        }
        return a / b;
    }
}