package recursion;

public class Day20recursionL1 {

    // ══════════════════════════════════════════════════════════════════════
    // Q-01 : Print n to 1
    // ══════════════════════════════════════════════════════════════════════
    static void nTo1(int n) {
        if (n == 0) {
            return;                  // base case: stop at 0
        }
        System.out.println(n);      // print BEFORE recursing → top-down (n...1)
        nTo1(n - 1);
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q-02 : Print 1 to n
    // ══════════════════════════════════════════════════════════════════════
    static void oneToN(int n) {
        if (n == 0) {
            return;                  // base case: stop at 0
        }
        oneToN(n - 1);              // recurse FIRST → go all the way down
        System.out.println(n);      // print AFTER returning → bottom-up (1...n)
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q-03 : Product of n to 1  (n factorial)
    // ══════════════════════════════════════════════════════════════════════
    static int productOfNTo1(int n) {
        if (n <= 1) {
            return 1;                // base case: 0! = 1! = 1
        }
        return n * productOfNTo1(n - 1);
    }



    // ══════════════════════════════════════════════════════════════════════
    // Q-04 : Sum of n to 1
    // ══════════════════════════════════════════════════════════════════════
    static int sumOfNTo1(int n) {
        if (n == 0) return 0;
        return n + sumOfNTo1(n - 1);
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q-05 : Sum of digits
    // ══════════════════════════════════════════════════════════════════════
    static int sumOfDigits(int n) {
        if (n == 0) {
            return 0;
        }
        int rem = n % 10;            // peel last digit
        return rem + sumOfDigits(n / 10); // last digit + sum of rest
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q-06 : Product of digits
    // ══════════════════════════════════════════════════════════════════════
    // Why 1 and not 0? Because anything × 0 = 0 → would destroy the whole product
    static int productOfDigits(int n) {
        if (n == 0) return 1;
        return (n % 10) * productOfDigits(n / 10);
    }

    // ══════════════════════════════════════════════════════════════════════
    // CONCEPT: Passing Numbers (Accumulator Pattern)
    // ══════════════════════════════════════════════════════════════════════
    // Pass extra parameter carrying "work done so far"
    // When you hit base case, that parameter IS your answer — return it directly
    // Your numberOfZeros and flip already use this correctly!

    // ══════════════════════════════════════════════════════════════════════
    // Q-07 : Reverse a number — Way 1 (approach with static variable)
    // ══════════════════════════════════════════════════════════════════════
    static int sum = 0;
    static int reverse1_original(int n) {
        if (n == 0) {
            return 0;
        }
        int rem = n % 10;
        sum = sum * 10 + rem;
        return reverse1_original(n / 10);
    }
    // Call once: sum=54321 ✓
    // Call again: sum=54321*10+... completely wrong ✗

    // IMPROVEMENT — remove static variable, use accumulator parameter instead
    // Same formula (acc * 10 + rem) but safely passed as parameter
    static int reverse1(int n) {
        return reverseAccumulator(n, 0); // start accumulator at 0
    }
    static int reverseAccumulator(int n, int acc) {
        if (n == 0) return acc;
        return reverseAccumulator(n / 10, acc * 10 + n % 10);
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q-07 : Reverse a number — Way 2
    // ══════════════════════════════════════════════════════════════════════
    static int reverse2(int n) {
        int digits = (int) Math.log10(n) + 1; // count total digits upfront
        return reverseHelper(n, digits);
    }

    static int reverseHelper(int n, int base) {
        if (n == 0) {
            return 0;
        }
        int rem = n % 10;
        return (int)(rem * Math.pow(10, base - 1) + reverseHelper(n / 10, base - 1));
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q-08 : Palindrome
    // ══════════════════════════════════════════════════════════════════════

    static boolean isPalindrome(int n) {
        return (n == reverse2(n));   // palindrome = number equals its own reverse
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q-09 : Count Zeros
    // ══════════════════════════════════════════════════════════════════════
    static int countZeros(int n, int count) {
        if (n == 0) {
            return count;            // all digits processed, return accumulated count
        }
        int rem = n % 10;
        if (rem == 0) {
            return countZeros(n / 10, count + 1); // zero found: increment count
        }
        return countZeros(n / 10, count);         // not zero: pass count unchanged
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q-10 : Count Steps to reach 0
    // ══════════════════════════════════════════════════════════════════════
    static int countSteps(int n) {
        return countStepsHelper(n, 0);
    }
    static int countStepsHelper(int n, int c) {
        if (n == 0) {
            return c;                // reached 0, return total steps counted
        }
        if (n % 2 == 0) {
            return countStepsHelper(n / 2, c + 1);  // even: halve
        }
        return countStepsHelper(n - 1, c + 1);      // odd: subtract 1
    }


    // ══════════════════════════════════════════════════════════════════════
    // MAIN
    // ══════════════════════════════════════════════════════════════════════
    public static void main(String[] args) {

        System.out.println("=== Q-01: nTo1(5) ===");
        nTo1(5);                                              // 5 4 3 2 1

        System.out.println("\n=== Q-02: oneToN(5) ===");
        oneToN(5);                                            // 1 2 3 4 5

        System.out.println("\n=== Q-03: productOfNTo1(5) ===");
        System.out.println(productOfNTo1(5));                 // 120

        System.out.println("\n=== Q-04: sumOfNTo1(5) ===");
        System.out.println(sumOfNTo1(5));                     // 15
        System.out.println("sumOfNTo1(0) = " + sumOfNTo1(0));// 0 (was 1 with bug)

        System.out.println("\n=== Q-05: sumOfDigits(1342) ===");
        System.out.println(sumOfDigits(1342));                // 10

        System.out.println("\n=== Q-06: productOfDigits(12453) ===");
        System.out.println(productOfDigits(12453));           // 120

        System.out.println("\n=== Q-07: reverse1(12345) — Way 1 ===");
        System.out.println(reverse1(12345));                  // 54321
        System.out.println(reverse1(12345));                  // 54321 again (no bug now)

        System.out.println("\n=== Q-07: reverse2(12345) — Way 2 ===");
        System.out.println(reverse2(12345));                  // 54321

        System.out.println("\n=== Q-08: isPalindrome ===");
        System.out.println(isPalindrome(12321));              // true
        System.out.println(isPalindrome(12345));              // false

        System.out.println("\n=== Q-09: countZeros(30403030) ===");
        System.out.println(countZeros(30403030, 0));          // 3

        System.out.println("\n=== Q-10: countSteps(8) ===");
        System.out.println(countSteps(8));                    // 4
        System.out.println("countSteps(6) = " + countSteps(6)); // 4
    }
}