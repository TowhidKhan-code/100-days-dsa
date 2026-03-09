package bitManipulation;

public class Day18MathsForDSA {

    /*
    ============================================================
    DAY 18 - MATHS FOR DSA + BIT MANIPULATION
    Operators, Number Systems, Base Conversion,
    Binary Representation, Optimization Algorithms
    ============================================================
    */


    // ============================================================
    // PROGRAM 1 — Check if Number is Odd
    //
    // Bit trick: last bit of any number tells odd/even
    // Odd numbers always have last bit = 1
    // Even numbers always have last bit = 0
    //
    // n & 1 isolates the last bit
    // Faster than n % 2
    //
    // Example:
    //   5  = 101     → last bit 1 → odd
    //   68 = 1000100 → last bit 0 → even
    // ============================================================

    static boolean isOdd(int n) {
        return (n & 1) == 1;
    }


    // ============================================================
    // PROGRAM 2 — Find Unique Number
    // Array has all numbers appearing twice except one
    // Find the number that appears only once
    //
    // XOR TRICK:
    //   n ^ n = 0  (any number XOR itself = 0)
    //   n ^ 0 = n  (any number XOR 0 = itself)
    //   XOR is commutative and associative
    //
    // All duplicate numbers cancel out → only unique survives
    //
    // Example:
    //   {4,6,1,2,2,3,1,3,4}
    //   4^6^1^2^2^3^1^3^4
    //   = (4^4) ^ 6 ^ (1^1) ^ (2^2) ^ (3^3)
    //   = 0 ^ 6 ^ 0 ^ 0 ^ 0 = 6
    //
    // Time: O(n), Space: O(1)
    // ============================================================

    static int findUnique(int[] arr) {
        int unique = 0;
        for (int num : arr) {
            unique ^= num; // XOR each element — duplicates cancel
        }
        return unique;
    }


    // ============================================================
    // PROGRAM 3 — Find Number Without Negative Counterpart
    // Array has numbers where most have a negative counterpart
    // Exactly ONE number has no negative counterpart — find it
    //
    // SUM TRICK:
    //   Paired numbers cancel: -2 + 2 = 0, -5 + 5 = 0
    //   Only the unpaired number remains in the sum
    //
    // IMPORTANT: works ONLY when exactly ONE number is unpaired
    // Question guarantees this condition
    //
    // Example:
    //   {-2, 3, 2, 4, -5, 5, -4}
    //   sum = -2+3+2+4-5+5-4 = 3
    //   (-2+2) cancel, (-5+5) cancel, (-4 has no +4)
    //   remaining = 3 (no -3) and -4 (no +4) → sum = -1
    //   guaranteed one unpaired → sum = that number
    //
    // Time: O(n), Space: O(1)
    // ============================================================

    static int nonNegative(int[] arr) {
        int sum = 0;
        for (int n : arr) {
            sum += n; // paired numbers cancel, unpaired remains
        }
        return sum;
    }


    // ============================================================
    // PROGRAM 4 — Magic Number
    // Convert n (in binary representation) to base 5
    // Read bits of n right to left, multiply each by power of 5
    //
    // Example: n = 5 (binary 101)
    //   bit 0: 1 × 5¹ = 5
    //   bit 1: 0 × 5² = 0
    //   bit 2: 1 × 5³ = 125
    //   answer = 5 + 0 + 125 = 130
    //
    // BIT TRICKS USED:
    //   n & 1  → get last bit (0 or 1)
    //   n >> 1 → shift right (move to next bit)
    //
    // Time: O(log n) — number of bits in n
    // ============================================================

    static int magicNumber(int n) {
        int ans = 0;
        int base = 5; // start with 5^1

        while (n > 0) {
            int last = n & 1;    // get last bit
            n = n >> 1;          // shift right to next bit
            ans += last * base;  // add bit × current power of 5
            base = base * 5;     // move to next power of 5
        }
        return ans;
    }


    // ============================================================
    // PROGRAM 5 — Number of Digits in Base b
    // How many digits does n have when written in base b?
    //
    // FORMULA: floor(log_b(n)) + 1
    //
    // Change of base formula:
    //   log_b(n) = Math.log(n) / Math.log(b)
    //   Math.log() gives natural log (base e)
    //   Dividing two natural logs gives log in any base
    //
    // Example: n = 6, b = 2
    //   6 in binary = 110 → 3 digits
    //   log_2(6) = log(6)/log(2) ≈ 2.58
    //   floor(2.58) + 1 = 3 ✓
    //
    // (int) cast floors the double automatically
    // ============================================================

    static int noOfDigits(int n) {
        int b = 2;
        int ans = (int) (Math.log(n) / Math.log(b)) + 1;
        return ans;
    }


    public static void main(String[] args) {
        int n = 68;
        int[] arr = {4, 6, 1, 2, 2, 3, 1, 3, 4};
        int[] arr2 = {-2, 3, 2, 4, -5, 5, -4};

//        System.out.println(isOdd(n));           // false
//        System.out.println(findUnique(arr));     // 6
//        System.out.println(nonNegative(arr2));   // unpaired number
//        System.out.println(magicNumber(5));      // 130
        System.out.println(noOfDigits(6));       // 3
    }
}