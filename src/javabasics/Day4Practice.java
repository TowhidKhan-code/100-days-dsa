package javabasics;

import java.util.Scanner;

public class Day4Practice {

    /*
    ============================================================
    DAY 4 - PRACTICE PROGRAMS
    Methods, Return Values, Parameters, VarArgs, Overloading
    ============================================================
    */


    // ============================================================
    // PROGRAM 1 — Greet User
    // Method takes a name as input and prints a greeting
    // Type: has input, no output (void)
    // ============================================================

    static void greet(String name) {
        System.out.println("Hello " + name + ", welcome to Day 4!");
    }


    // ============================================================
    // PROGRAM 2 — Add Two Numbers
    // Method takes two numbers and returns their sum
    // Type: has input, has output
    // ============================================================

    static int add(int a, int b) {
        return a + b;
    }


    // ============================================================
    // PROGRAM 3 — Check Even or Odd
    // Method returns true if number is even, false if odd
    // Uses modulus operator — if remainder is 0 when divided by 2, it is even
    // ============================================================

    static boolean isEven(int n) {
        return n % 2 == 0;
    }


    // ============================================================
    // PROGRAM 4 — Find Maximum of Three Numbers
    // Method takes three numbers and returns the largest one
    // Compares a with b first, then compares winner with c
    // ============================================================

    static int findMax(int a, int b, int c) {
        int max = a; // assume a is max first
        if (b > max) {
            max = b; // b is bigger, update max
        }
        if (c > max) {
            max = c; // c is bigger, update max
        }
        return max;
    }


    // ============================================================
    // PROGRAM 5 — Factorial
    // factorial(5) = 5 x 4 x 3 x 2 x 1 = 120
    // factorial(0) = 1 (by definition)
    // Multiply result with every number from 1 to n using a loop
    // ============================================================

    static long factorial(int n) {
        long result = 1; // start with 1 because multiplying by 0 gives 0
        for (int i = 1; i <= n; i++) {
            result = result * i; // multiply result with each number
        }
        return result;
    }


    // ============================================================
    // PROGRAM 6 — Check Prime
    // A prime number is divisible only by 1 and itself
    // Check if any number from 2 to n-1 divides n evenly
    // If yes — not prime. If no — prime.
    // Optimization: only check up to square root of n
    // ============================================================

    static boolean isPrime(int n) {
        if (n <= 1) {
            return false; // 0 and 1 are not prime by definition
        }
        // check divisibility from 2 up to square root of n
        // if n has a factor larger than sqrt(n), it must also have one smaller
        // so we only need to check up to sqrt(n)
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false; // found a divisor — not prime
            }
        }
        return true; // no divisor found — it is prime
    }


    // ============================================================
    // PROGRAM 7 — Method Overloading (Multiply)
    // Two methods with same name but different number of parameters
    // Java picks the right one based on how many arguments you pass
    // ============================================================

    // multiply two numbers
    static int multiply(int a, int b) {
        return a * b;
    }

    // multiply three numbers — same name, different parameter count
    static int multiply(int a, int b, int c) {
        return a * b * c;
    }


    // ============================================================
    // PROGRAM 8 — Reverse a Number using a Method
    // Same logic as Day 3 but now wrapped inside a clean method
    // Takes a number, returns its reverse
    // Example: reverse(1234) returns 4321
    // ============================================================

    static int reverse(int n) {
        int reversed = 0;
        while (n != 0) {
            int lastDigit = n % 10;              // extract last digit
            reversed = reversed * 10 + lastDigit; // build reversed number
            n = n / 10;                           // remove last digit
        }
        return reversed;
    }


    // ============================================================
    // Pass by Value Demonstration
    // Shows why changing a variable inside a method does nothing
    // Java passes a COPY — original is always untouched
    // ============================================================

    static void tryToChange(int num, String name) {
        num = 999;      // changes the COPY of num — original untouched
        name = "Rahul"; // creates NEW String object — original untouched
        System.out.println("Inside method — num: " + num + ", name: " + name);
    }


    // ============================================================
    // MAIN — calling all practice programs
    // ============================================================

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Program 1 — Greet
        System.out.println("--- Program 1: Greet ---");
        greet("Towhid");


        // Program 2 — Add two numbers
        System.out.println("\n--- Program 2: Add ---");
        System.out.print("Enter first number: ");
        int n1 = sc.nextInt();
        System.out.print("Enter second number: ");
        int n2 = sc.nextInt();
        System.out.println("Sum: " + add(n1, n2));


        // Program 3 — Even or Odd
        System.out.println("\n--- Program 3: Even or Odd ---");
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        if (isEven(num)) {
            System.out.println(num + " is Even");
        } else {
            System.out.println(num + " is Odd");
        }


        // Program 4 — Find Maximum
        System.out.println("\n--- Program 4: Find Maximum ---");
        System.out.print("Enter three numbers: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        System.out.println("Maximum: " + findMax(a, b, c));


        // Program 5 — Factorial
        System.out.println("\n--- Program 5: Factorial ---");
        System.out.print("Enter a number: ");
        int factNum = sc.nextInt();
        System.out.println(factNum + "! = " + factorial(factNum));
        // test cases
        System.out.println("factorial(0) = " + factorial(0)); // 1
        System.out.println("factorial(5) = " + factorial(5)); // 120
        System.out.println("factorial(10) = " + factorial(10)); // 3628800


        // Program 6 — Prime Check
        System.out.println("\n--- Program 6: Prime Check ---");
        System.out.print("Enter a number: ");
        int primeNum = sc.nextInt();
        if (isPrime(primeNum)) {
            System.out.println(primeNum + " is Prime");
        } else {
            System.out.println(primeNum + " is Not Prime");
        }
        // test cases
        System.out.println("isPrime(1)  = " + isPrime(1));  // false
        System.out.println("isPrime(2)  = " + isPrime(2));  // true
        System.out.println("isPrime(7)  = " + isPrime(7));  // true
        System.out.println("isPrime(10) = " + isPrime(10)); // false
        System.out.println("isPrime(17) = " + isPrime(17)); // true


        // Program 7 — Method Overloading
        System.out.println("\n--- Program 7: Overloading ---");
        System.out.println("multiply(3, 4)    = " + multiply(3, 4));     // 12
        System.out.println("multiply(2, 3, 4) = " + multiply(2, 3, 4)); // 24
        // Java automatically called the right method each time


        // Program 8 — Reverse a Number
        System.out.println("\n--- Program 8: Reverse Number ---");
        System.out.print("Enter a number to reverse: ");
        int revNum = sc.nextInt();
        System.out.println("Reversed: " + reverse(revNum));
        // test cases
        System.out.println("reverse(1234) = " + reverse(1234)); // 4321
        System.out.println("reverse(100)  = " + reverse(100));  // 1
        System.out.println("reverse(9)    = " + reverse(9));    // 9


        // Pass by Value
        System.out.println("\n--- Bonus: Pass by Value ---");
        int myNum = 10;
        String myName = "Towhid";
        System.out.println("Before method — num: " + myNum + ", name: " + myName);
        tryToChange(myNum, myName);
        System.out.println("After method  — num: " + myNum + ", name: " + myName);
        // num is still 10, name is still Towhid — Java passed copies

        sc.close();
    }
}