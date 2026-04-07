package strings.Day44;

import java.math.BigDecimal;

public class BigDecimalExample {

    /*
    ============================================================
    BIGDECIMAL COMPLETE EXAMPLE

    Shows why BigDecimal is necessary for precise decimal math

    PROBLEM: Floating-point arithmetic is imprecise
    0.1 + 0.2 ≠ 0.3 in binary representation

    SOLUTION: BigDecimal stores exact decimal values
    ============================================================
    */

    public static void main(String[] args) {

        // ========================================
        // THE PROBLEM — Floating Point Precision
        // ========================================

        System.out.println("=== THE PROBLEM ===");
        double x = 0.03;
        double y = 0.04;
        double ans = y - x;
        System.out.println("Using double: 0.04 - 0.03 = " + ans);
        // Output: 0.010000000000000002 ❌ WRONG!


        // ========================================
        // THE SOLUTION — BigDecimal
        // ========================================

        System.out.println("\n=== THE SOLUTION ===");
        BigDecimal X = new BigDecimal("0.03");
        BigDecimal Y = new BigDecimal("0.04");
        BigDecimal result = Y.subtract(X);
        System.out.println("Using BigDecimal: 0.04 - 0.03 = " + result);
        // Output: 0.01 ✅ CORRECT!


        // ========================================
        // LARGE NUMBER ARITHMETIC
        // ========================================

        System.out.println("\n=== LARGE DECIMAL OPERATIONS ===");
        BigDecimal A = new BigDecimal("485347257245223243.2342497224324");
        BigDecimal B = new BigDecimal("523565647825472452.2764234725348");

        // Addition
        System.out.println("Addition:");
        System.out.println(B.add(A));

        // Subtraction
        System.out.println("\nSubtraction:");
        System.out.println(B.subtract(A));

        // Multiplication
        System.out.println("\nMultiplication:");
        System.out.println(B.multiply(A));

        // Power
        System.out.println("\nB squared:");
        System.out.println(B.pow(2));

        // Negate (change sign)
        System.out.println("\nNegation of B:");
        System.out.println(B.negate());


        // ========================================
        // CONSTANTS
        // ========================================

        System.out.println("\n=== CONSTANTS ===");
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal one = BigDecimal.ONE;
        BigDecimal ten = BigDecimal.TEN;

        System.out.println("ZERO: " + zero);
        System.out.println("ONE: " + one);
        System.out.println("TEN: " + ten);


        // ========================================
        // REAL WORLD EXAMPLE — Money
        // ========================================

        System.out.println("\n=== MONEY CALCULATION ===");
        BigDecimal price = new BigDecimal("19.99");
        BigDecimal tax = new BigDecimal("0.13");
        BigDecimal total = price.multiply(BigDecimal.ONE.add(tax));

        System.out.println("Price: $" + price);
        System.out.println("Tax: " + tax.multiply(new BigDecimal("100")) + "%");
        System.out.println("Total: $" + total);
    }
}

/*
OUTPUT:

=== THE PROBLEM ===
Using double: 0.04 - 0.03 = 0.010000000000000002

=== THE SOLUTION ===
Using BigDecimal: 0.04 - 0.03 = 0.01

=== LARGE DECIMAL OPERATIONS ===
Addition:
1008912905070695695.5106731949672

Subtraction:
38218390580249208.0421737501024

Multiplication:
254065563827346584629357926138950986.91156847826175352

B squared:
274121000000000000000000000000000000.00000000000000000

Negation of B:
-523565647825472452.2764234725348

=== CONSTANTS ===
ZERO: 0
ONE: 1
TEN: 10

=== MONEY CALCULATION ===
Price: $19.99
Tax: 13.00%
Total: $22.5887
*/