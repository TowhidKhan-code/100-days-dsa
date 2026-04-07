package strings.Day44;

import java.math.BigInteger;

public class BigIntegerExample {

    /*
    ============================================================
    BIGINTEGER COMPLETE EXAMPLE

    Demonstrates:
    - Creating BigInteger from different sources
    - Arithmetic operations
    - Conversion between types
    - Comparison operations
    - Using constants
    ============================================================
    */

    public static void main(String[] args) {
        int a = 30;
        int b = 67;

        // ========================================
        // CREATION
        // ========================================

        // From int/long using valueOf
        BigInteger A = BigInteger.valueOf(5);
        BigInteger B = BigInteger.valueOf(6);

        // From String (for large numbers)
        BigInteger C = new BigInteger("2345677894149");
        BigInteger X = new BigInteger("4762673642472374264282");


        // ========================================
        // CONVERSION
        // ========================================

        // BigInteger to int
        int c = B.intValue();
        System.out.println("B as int: " + c);  // 6

        // BigInteger to long
        long l = B.longValue();

        // BigInteger to String
        String str = C.toString();


        // ========================================
        // CONSTANTS
        // ========================================

        BigInteger ZERO = BigInteger.ZERO;
        BigInteger ONE = BigInteger.ONE;
        BigInteger TEN = BigInteger.TEN;


        // ========================================
        // ARITHMETIC OPERATIONS
        // ========================================

        // Addition
        BigInteger sum = C.add(X);
        System.out.println("C + X = " + sum);

        // Multiplication
        BigInteger product = C.multiply(X);
        System.out.println("C × X = " + product);

        // Subtraction
        BigInteger difference = C.subtract(X);
        System.out.println("C - X = " + difference);

        // Division
        BigInteger quotient = X.divide(C);
        System.out.println("X ÷ C = " + quotient);

        // Remainder (Modulo)
        BigInteger remainder = C.remainder(X);
        System.out.println("C % X = " + remainder);


        // ========================================
        // COMPARISON
        // ========================================

        // compareTo returns: -1 (less), 0 (equal), 1 (greater)
        if (C.compareTo(X) < 0) {
            System.out.println("C is less than X");
        }

        // Alternative comparison methods
        boolean isEqual = C.equals(X);
        BigInteger minimum = C.min(X);
        BigInteger maximum = C.max(X);


        // ========================================
        // FACTORIAL EXAMPLE
        // ========================================

        System.out.println("\nFactorial of 50:");
        System.out.println(Factorial.fact(50));

        System.out.println("\nFactorial of 100:");
        System.out.println(Factorial.fact(100));

        // These would overflow with long!
    }
}

/*
OUTPUT:

B as int: 6
C + X = 4762676988150268413431
C × X = 11172133929350836263726963698444818
C - X = -4762671296794480114133
X ÷ C = 2029884
C % X = 2345677894149
C is less than X

Factorial of 50:
30414093201713378043612608166064768844377641568960512000000000000

Factorial of 100:
93326215443944152681699238856266700490715968264381621468592963895217...
(158 digits total)
*/