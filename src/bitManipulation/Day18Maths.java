package bitManipulation;

public class Day18Maths {

    /*
    ============================================================
    DAY 18 - MATHS FOR DSA + BIT MANIPULATION
    Operators, Number Systems, Base Conversion,
    Binary Representation, Optimization Algorithms
    16 Problems + LC 832
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




        // ============================================================
        // Program 6 — Find ith Bit
        // Check if bit at position i is 0 or 1
        //
        // METHOD 1: Right shift then AND
        //   Shift bit i to position 0, then isolate with & 1
        //
        // METHOD 2: Left shift then AND
        //   Create mask with only bit i set, AND with n
        //   If result != 0 → bit is 1
        // ============================================================

        static int getIthBit(int n, int i) {
            // METHOD 1 — right shift
            return (n >> i) & 1;

            // METHOD 2 — left shift mask
            // return (n & (1 << i)) != 0 ? 1 : 0;
        }


        // ============================================================
        // Program 7 — Set ith Bit (force bit i to 1)
        //
        // METHOD 1: OR with mask
        //   Create mask 1 << i (only bit i is 1)
        //   OR with n → forces bit i to 1, rest unchanged
        //
        // METHOD 2: using XOR — only if bit is currently 0
        //   n ^ (1 << i) toggles bit i
        //   If bit was 0 → becomes 1 ✓
        //   If bit was 1 → becomes 0 ✗ — NOT reliable for setting
        //   OR is the correct approach for guaranteed set
        // ============================================================

        static int setIthBit(int n, int i) {
            // METHOD 1 — OR with mask (correct and reliable)
            return n | (1 << i);

            // METHOD 2 — XOR (only works if bit is currently 0)
            // return n ^ (1 << i);
        }


        // ============================================================
        // Program 8 — Reset ith Bit (force bit i to 0)
        //
        // METHOD 1: AND with inverted mask
        //   ~(1 << i) creates mask with all 1s except bit i
        //   AND with n → forces bit i to 0, rest unchanged
        //
        // METHOD 2: using XOR — only if bit is currently 1
        //   n ^ (1 << i) toggles bit i
        //   If bit was 1 → becomes 0 ✓
        //   If bit was 0 → becomes 1 ✗ — NOT reliable for clearing
        //   AND with ~mask is the correct approach
        // ============================================================

        static int resetIthBit(int n, int i) {
            // METHOD 1 — AND with inverted mask (correct and reliable)
            return n & ~(1 << i);

            // METHOD 2 — XOR (only works if bit is currently 1)
            // return n ^ (1 << i);
        }


        // ============================================================
        // Program 9 — Position of Rightmost Set Bit
        // Find the index of the lowest bit that is 1
        //
        // METHOD 1: n & (-n) isolates lowest set bit, then log2
        //   -n in two's complement flips all bits then adds 1
        //   n & (-n) = only the rightmost set bit survives
        //   log2 of that gives its position
        //
        // METHOD 2: loop with right shift
        //   Shift right until last bit is 1, count positions
        //
        // Example: n = 12 = 1100
        //   Rightmost set bit at position 2 (0-indexed)
        // ============================================================

        static int rightmostSetBit(int n) {
            // METHOD 1 — n & (-n) trick
            if (n == 0) return -1; // no set bits
            int lowestBit = n & (-n); // isolate rightmost set bit
            return (int)(Math.log(lowestBit) / Math.log(2)); // its position

            // METHOD 2 — loop with right shift
        /*
        int pos = 0;
        while ((n & 1) == 0) {
            n = n >> 1;
            pos++;
        }
        return pos;
        */
        }


        // ============================================================
        // Program 10 — All Numbers Appear 3 Times Except One — Find It
        // Cannot use XOR here — XOR works for pairs not triples
        //
        // APPROACH: count each bit across all numbers
        //   For each bit position (0 to 31):
        //     count how many numbers have that bit set
        //     if count % 3 != 0 → the unique number has that bit set
        //   Reconstruct the unique number bit by bit
        //
        // WHY: triples contribute 0 or 3 to each bit count
        //      count % 3 removes triple contributions
        //      remaining 1 or 0 belongs to unique number
        //
        // Time: O(32 × n) = O(n), Space: O(1)
        // ============================================================

        static int findUniqueAmongTriples(int[] arr) {
            int result = 0;
            for (int i = 0; i < 32; i++) {          // for each bit position
                int bitSum = 0;
                for (int num : arr) {
                    bitSum += (num >> i) & 1;        // count set bits at position i
                }
                if (bitSum % 3 != 0) {               // this bit belongs to unique
                    result = result | (1 << i);      // set this bit in result
                }
            }
            return result;
        }



        // ============================================================
        // Program 11 — Sum of nth Row in Pascal's Triangle
        // Row 0: 1          sum = 1  = 2^0
        // Row 1: 1 1        sum = 2  = 2^1
        // Row 2: 1 2 1      sum = 4  = 2^2
        // Row 3: 1 3 3 1    sum = 8  = 2^3
        //
        // PATTERN: sum of row n = 2^n
        //
        // METHOD 1: left shift — 1 << n = 2^n
        // METHOD 2: Math.pow(2, n)
        // ============================================================

        static int pascalRowSum(int n) {
            // METHOD 1 — left shift (fastest)
            return 1 << n;   // 2^n

            // METHOD 2 — Math.pow
            // return (int) Math.pow(2, n);
        }


        // ============================================================
        // Program 12 — Check if Number is Power of 2
        // Powers of 2 have exactly ONE bit set in binary
        // 1=0001, 2=0010, 4=0100, 8=1000
        //
        // METHOD 1: n & (n-1) == 0
        //   n-1 flips all bits below the set bit and the set bit itself
        //   n & (n-1) clears the lowest set bit
        //   If result is 0 → only one bit was set → power of 2
        //
        // METHOD 2: count set bits — if count == 1 → power of 2
        //
        // METHOD 3: keep dividing by 2 — if remainder ever != 0 → not power of 2
        //
        // EDGE CASE: 0 is not a power of 2 — must check n > 0
        // ============================================================

        static boolean isPowerOf2(int n) {
            // METHOD 1 — n & (n-1) trick (most elegant)
            return n > 0 && (n & (n - 1)) == 0;

            // METHOD 2 — count set bits
        /*
        if (n <= 0) return false;
        int count = 0;
        while (n > 0) {
            count += n & 1;
            n = n >> 1;
        }
        return count == 1;
        */
        }


        // ============================================================
        // Program 13 — Calculate pow(a, b) — Fast Power using Bits
        // Naive: multiply a by itself b times → O(b)
        // Fast Power: use binary representation of b → O(log b)
        //
        // IDEA: a^13 where 13 = 1101 in binary
        //   13 = 8 + 4 + 1 = 2³ + 2² + 2⁰
        //   a^13 = a^8 × a^4 × a^1
        //   Square a repeatedly: a, a², a⁴, a⁸, a¹⁶...
        //   Multiply into result only when corresponding bit is 1
        //
        // METHOD 1: right shift to check bits of b
        // METHOD 2: XOR not applicable here — multiplication problem
        // ============================================================

        static long fastPow(long a, int b) {
            // METHOD 1 — fast power with right shift
            long result = 1;
            while (b > 0) {
                if ((b & 1) == 1) {     // if current bit of b is 1
                    result *= a;         // multiply current power of a
                }
                a *= a;                  // square a for next bit
                b = b >> 1;             // move to next bit of b
            }
            return result;

            // ALTERNATIVE — recursive fast power
        /*
        if (b == 0) return 1;
        long half = fastPow(a, b / 2);
        if (b % 2 == 0) return half * half;
        else return half * half * a;
        */
        }


        // ============================================================
        // Program 14 — Count Number of Set Bits (Hamming Weight)
        // Count how many 1s are in binary representation of n
        //
        // METHOD 1: right shift — check each bit one by one
        //
        // METHOD 2: Brian Kernighan's Algorithm
        //   n & (n-1) removes the lowest set bit each iteration
        //   Count how many times until n becomes 0
        //   Faster when few bits are set
        //
        // METHOD 3: XOR not directly applicable here
        //   But note: n ^ (n-1) sets all bits up to lowest set bit
        //   Not useful for counting directly
        // ============================================================

        static int countSetBits(int n) {
            // METHOD 1 — right shift (check every bit)
            int count = 0;
            while (n > 0) {
                count += n & 1;   // add last bit (0 or 1)
                n = n >> 1;       // shift right to next bit
            }
            return count;

            // METHOD 2 — Brian Kernighan (faster for sparse bits)
        /*
        int count = 0;
        while (n > 0) {
            n = n & (n - 1);  // clear lowest set bit
            count++;
        }
        return count;
        */
        }


        // ============================================================
        // Program 15 — XOR of Numbers from 0 to n
        // Brute force: XOR all numbers 0 to n → O(n)
        // PATTERN: XOR from 0 to n follows a cycle of 4:
        //
        //   n % 4 == 0 → result = n
        //   n % 4 == 1 → result = 1
        //   n % 4 == 2 → result = n + 1
        //   n % 4 == 3 → result = 0
        //
        // WHY: XOR of 4 consecutive numbers starting from multiple of 4
        //   4k ^ (4k+1) ^ (4k+2) ^ (4k+3) = 0 always
        //   Pattern repeats every 4 numbers
        //
        // METHOD 1: use the pattern → O(1)
        // METHOD 2: brute force loop → O(n)
        // ============================================================

        static int xorUpToN(int n) {
            // METHOD 1 — O(1) pattern
            switch (n % 4) {
                case 0: return n;
                case 1: return 1;
                case 2: return n + 1;
                case 3: return 0;
            }
            return 0;

            // METHOD 2 — brute force
        /*
        int result = 0;
        for (int i = 0; i <= n; i++) {
            result ^= i;
        }
        return result;
        */
        }


        // ============================================================
        // Program 16 — XOR of All Numbers Between a and b (inclusive)
        // XOR(a to b) = XOR(0 to b) ^ XOR(0 to a-1)
        //
        // WHY: XOR(0 to a-1) ^ XOR(0 to b)
        //   = (0^1^...^(a-1)) ^ (0^1^...^(a-1)^a^...^b)
        //   = a ^ (a+1) ^ ... ^ b  (first part cancels)
        //
        // Use xorUpToN() as helper for O(1) total
        // ============================================================

        static int xorFromAtoB(int a, int b) {
            // XOR(0 to b) ^ XOR(0 to a-1)
            return xorUpToN(b) ^ xorUpToN(a - 1);
        }


        // ============================================================
        // LC 832 — Flipping an Image
        // Step 1: Flip each row (reverse the array)
        // Step 2: Invert each element (0→1, 1→0)
        //
        // OPTIMIZATION: flip AND invert simultaneously
        //   Two pointer — swap elements from both ends
        //   If elements are DIFFERENT: flipping then inverting = no change
        //   If elements are SAME: flip then invert = flip then flip = 1^1
        //
        // XOR TRICK: element ^ 1 inverts a bit (0^1=1, 1^1=0)
        //
        // METHOD 1: two pointer with XOR invert
        // METHOD 2: reverse then XOR each element
        // ============================================================

        static int[][] flipAndInvertImage(int[][] image) {
            // METHOD 1 — two pointer (optimal O(n))
            for (int[] row : image) {
                int left = 0;
                int right = row.length - 1;
                while (left <= right) {
                    if (row[left] == row[right]) {
                        // same values — flip position AND invert value
                        row[left] ^= 1;   // invert using XOR
                        row[right] ^= 1;  // invert using XOR
                    } // different values — flipping cancels inversion
                    left++;
                    right--;
                }
            }
            return image;

            // METHOD 2 — reverse then invert separately
        /*
        for (int[] row : image) {
            // step 1: reverse
            int left = 0, right = row.length - 1;
            while (left < right) {
                int temp = row[left];
                row[left] = row[right];
                row[right] = temp;
                left++; right--;
            }
            // step 2: invert using XOR
            for (int i = 0; i < row.length; i++) {
                row[i] ^= 1;
            }
        }
        return image;
        */
        }


        public static void main(String[] args) {
            int n = 12; // 1100
            int[] arr = {4, 6, 1, 2, 2, 3, 1, 3, 4};
            int[] arr2 = {-2, 3, 2, 4, -5, 5, -4};

//        System.out.println(isOdd(n));           // false
//        System.out.println(findUnique(arr));     // 6
//        System.out.println(nonNegative(arr2));   // unpaired number
//        System.out.println(magicNumber(5));      // 130
//            System.out.println(noOfDigits(6));       // 3

//        System.out.println(getIthBit(n, 2));           // 1
//        System.out.println(setIthBit(n, 0));            // 13 (1101)
//        System.out.println(resetIthBit(n, 2));          // 8  (1000)
//        System.out.println(rightmostSetBit(n));         // 2
//        System.out.println(findUniqueAmongTriples(new int[]{1,1,1,2,2,2,3})); // 3
//        System.out.println(magicNumber(5));              // 130
//        System.out.println(numberOfDigits(6, 2));        // 3
//        System.out.println(pascalRowSum(4));             // 16
//        System.out.println(isPowerOf2(16));              // true
//        System.out.println(fastPow(2, 10));              // 1024
//        System.out.println(countSetBits(n));             // 2
//        System.out.println(xorUpToN(7));                 // 0
//        System.out.println(xorFromAtoB(3, 7));           // xor of 3,4,5,6,7
        }
    }
