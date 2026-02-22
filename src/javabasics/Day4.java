package javabasics;

public class Day4 {
    /*
    ============================================================
    DAY 4 - Methods, Return Values, Parameters, Scope,
            Shadowing, VarArgs, Method Overloading
    ============================================================
    */

        // ============================================================
        // METHODS (FUNCTIONS)
        // A method is a reusable block of code that does one job
        // Write once, call as many times as you need
        // Every method in Java belongs to a class
        // ============================================================

        // Basic method - no input, no output
        // void means it returns nothing
        static void greet() {
            System.out.println("Hello!");
        }

        // Method with input - has parameters, no output
        static void greetUser(String name) {
            System.out.println("Hello " + name);
        }

        // Method with output - no input, returns a value
        // return type must match what you actually return
        static int getNumber() {
            return 42;
        }

        // Method with input AND output - most common in DSA
        static int add(int a, int b) {
            return a + b;
        }


        // ============================================================
        // RETURN VALUES
        // void    = does the job, gives nothing back
        // int     = gives back a whole number
        // double  = gives back a decimal
        // boolean = gives back true or false
        // String  = gives back text
        // Execution STOPS immediately when return is hit
        // ============================================================

        static void printSum(int a, int b) {
            System.out.println(a + b); // just prints, returns nothing
            // nothing after this runs if return was here
        }

        static int getSum(int a, int b) {
            return a + b; // gives the result back to whoever called it
            // anything written after return is UNREACHABLE - compile error
        }

        static boolean isAdult(int age) {
            return age >= 18; // returns true or false
        }


        // ============================================================
        // PARAMETERS vs ARGUMENTS
        // Parameters = placeholders in the method definition
        // Arguments  = actual values you pass when calling the method
        // ============================================================

        // a and b are PARAMETERS - they are placeholders
        static int multiply(int a, int b) {
            return a * b;
        }

        // ============================================================
        // SCOPE
        // Scope = where a variable can be accessed
        // 3 types: Method scope, Block scope, Loop scope
        // ============================================================

        static void scopeExample() {

            // METHOD SCOPE
            // Variable declared here lives for the entire method
            int methodVar = 10;
            System.out.println(methodVar); // works anywhere in this method

            // BLOCK SCOPE
            // Variable declared inside {} only lives inside those {}
            if (true) {
                int blockVar = 20; // only exists inside this if block
                System.out.println(blockVar); // works here
            }
            // System.out.println(blockVar); // ERROR - blockVar is dead here

            // LOOP SCOPE
            // Loop variable only lives inside the loop
            for (int i = 0; i < 3; i++) {
                int loopVar = i * 2; // only exists inside this loop
                System.out.println(loopVar); // works here
            }
            // System.out.println(i);       // ERROR - i is dead here
            // System.out.println(loopVar); // ERROR - loopVar is dead here

            System.out.println(methodVar); // still works - method scope lives longest
        }


        // ============================================================
        // SHADOWING
        // When a local variable has the same name as an outer variable
        // The inner one SHADOWS (hides) the outer one inside its block
        // The outer variable is NOT changed - just hidden temporarily
        // ============================================================

        static int x = 100; // class level variable (static field)

        static void shadowingExample() {
            System.out.println(x); // prints 100 - class variable

            int x = 50; // LOCAL x shadows the class x inside this method
            System.out.println(x); // prints 50 - local variable shadows class variable

            if (true) {
                int y = 10;
                System.out.println(x); // prints 50 - still sees local x
            }
        }
        // after method ends, local x is gone - class x is still 100


        // ============================================================
        // VARARGS (Variable Arguments) - int... v
        // Allows a method to accept ANY number of arguments
        // Syntax: type... variableName
        // Java treats varargs as an ARRAY inside the method
        // RULES:
        //   1. Only ONE varargs per method
        //   2. Varargs MUST be the LAST parameter
        // ============================================================

        static int sumAll(int... numbers) {
            // numbers is treated as an int array inside here
            int total = 0;
            for (int num : numbers) { // enhanced for loop to go through each number
                total += num;
            }
            return total;
        }

        // varargs with other parameters - varargs must be LAST
        static void printInfo(String name, int... scores) {
            System.out.print(name + " scored: ");
            for (int score : scores) {
                System.out.print(score + " ");
            }
            System.out.println();
        }


        // ============================================================
        // METHOD OVERLOADING
        // Same method name, different parameters
        // Java decides which one to call based on arguments you pass
        // Parameters must differ in: number, type, or order
        // Return type alone is NOT enough to overload
        // ============================================================

        // same name "add" - different number of parameters
        static int add(int a, int b, int c) {
            return a + b + c;
        }

        // same name "add" - different parameter types
        static double add(double a, double b) {
            return a + b;
        }

        // same name "describe" - different parameter types
        static void describe(int n) {
            System.out.println("Integer: " + n);
        }

        static void describe(String s) {
            System.out.println("String: " + s);
        }

        static void describe(double d) {
            System.out.println("Double: " + d);
        }


        // ============================================================
        // MAIN METHOD - entry point, everything runs from here
        // ============================================================

        public static void main(String[] args) {

            // calling methods
            greet();                            // Hello!
            greetUser("Towhid");               // Hello Towhid
            int num = getNumber();              // captures 42
            System.out.println(num);           // 42

            // 5 and 3 are ARGUMENTS - actual values being passed
            int result = add(5, 3);            // a=5, b=3 inside method
            System.out.println(result);        // 8

            // return values
            printSum(3, 4);                    // prints 7
            int sum = getSum(3, 4);            // captures 7
            boolean adult = isAdult(20);       // captures true
            System.out.println(adult);         // true

            // scope
            scopeExample();

            // shadowing
            shadowingExample();

            // varargs - can pass any number of arguments
            System.out.println(sumAll(1, 2, 3));          // 6
            System.out.println(sumAll(10, 20, 30, 40));   // 100
            System.out.println(sumAll(5));                // 5
            System.out.println(sumAll());                 // 0 - zero args also works

            printInfo("Towhid", 85, 90, 78);   // Towhid scored: 85 90 78

            // method overloading - Java picks the right one automatically
            System.out.println(add(2, 3));        // calls add(int, int)     = 5
            System.out.println(add(1, 2, 3));     // calls add(int,int,int)  = 6
            System.out.println(add(2.5, 3.5));    // calls add(double,double)= 6.0

            describe(42);           // Integer: 42
            describe("hello");      // String: hello
            describe(3.14);         // Double: 3.14
        }
    }

