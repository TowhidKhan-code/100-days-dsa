package strings.Day44;

import java.math.BigInteger;

public class Factorial {

    /*
    ============================================================
    FACTORIAL CALCULATION WITH BIGINTEGER

    Computes n! for large values that exceed long range

    EXAMPLES:
    20! = 2,432,902,008,176,640,000 (fits in long)
    21! = overflows long
    100! = 158 digits (impossible with primitives)

    TIME COMPLEXITY: O(n)
    SPACE COMPLEXITY: O(1) — reuses same BigInteger
    ============================================================
    */

    static BigInteger fact(int num) {
        // Start with 1
        BigInteger ans = BigInteger.ONE;

        // Multiply by 2, 3, 4, ... num
        for (int i = 2; i <= num; i++) {
            ans = ans.multiply(BigInteger.valueOf(i));
        }

        return ans;
    }

    // Recursive version
    static BigInteger factRecursive(int num) {
        if (num <= 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(num).multiply(factRecursive(num - 1));
    }

    public static void main(String[] args) {
        // Test different factorials
        System.out.println("5! = " + fact(5));     // 120
        System.out.println("10! = " + fact(10));   // 3628800
        System.out.println("20! = " + fact(20));   // 2432902008176640000

        System.out.println("\n50! = ");
        System.out.println(fact(50));

        System.out.println("\n100! = ");
        System.out.println(fact(100));

        System.out.println("\n200! has " + fact(200).toString().length() + " digits!");

        // Compare with recursive version
        System.out.println("\nRecursive 10! = " + factRecursive(10));
    }
}