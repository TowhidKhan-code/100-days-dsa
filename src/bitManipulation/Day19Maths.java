package bitManipulation;

public class Day19Maths {

    /*
    ============================================================
    DAY 19 - MATHS FOR DSA PART 2
    Prime Numbers, Sieve of Eratosthenes, Square Root,
    Newton Raphson, Factors, Modulo, GCD, LCM
    ============================================================
    */


    // ============================================================
    // PROGRAM 1 — Check if Number is Prime
    //
    // BRUTE FORCE: check divisors from 2 to n-1 → O(n)
    //
    // OPTIMISED: check divisors from 2 to sqrt(n) → O(√n)
    // WHY: if n = a × b and a > √n then b < √n
    //      so one factor is always ≤ √n
    //      no need to check beyond √n
    //
    // FURTHER OPTIMISED: after checking 2, only check odd numbers
    //   All even numbers > 2 are not prime
    //   Start at 3, increment by 2
    //
    // Edge cases: n <= 1 not prime, n == 2 is prime
    // ============================================================

    static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false; // all even > 2 not prime

        // check odd divisors from 3 to sqrt(n)
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }


    // ============================================================
    // PROGRAM 2 — Sieve of Eratosthenes
    // Find all prime numbers from 2 to n
    //
    // IDEA: start with all numbers marked as prime
    //   For each prime p starting from 2:
    //     Mark all multiples of p as NOT prime
    //     Start from p² (all smaller multiples already marked)
    //
    // WHY start from p²:
    //   2p already marked when processing 2
    //   3p already marked when processing 3
    //   p² is the first multiple not yet marked
    //
    // Time: O(n log log n) — very close to O(n)
    // Space: O(n) — boolean array
    // ============================================================

    static void sieveOfEratosthenes(int n) {
        boolean[] isComposite = new boolean[n + 1]; // false = prime

        for (int i = 2; i * i <= n; i++) {
            if (!isComposite[i]) {               // i is prime
                for (int j = i * i; j <= n; j += i) {
                    isComposite[j] = true;        // mark multiples as composite
                }
            }
        }

        System.out.print("Primes up to " + n + ": ");
        for (int i = 2; i <= n; i++) {
            if (!isComposite[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }


    // ============================================================
    // PROGRAM 3 — Square Root using Binary Search
    // Find floor(√n) — largest integer whose square ≤ n
    //
    // APPROACH: binary search on answer space 1 to n
    //   If mid² == n → exact answer
    //   If mid² < n  → possible answer, search right for bigger
    //   If mid² > n  → too big, search left
    //
    // Use long for mid*mid to avoid integer overflow
    // Time: O(log n)
    // ============================================================

    static long sqrtBinarySearch(long n) {
        if (n < 0) return -1;
        if (n == 0) return 0;

        long start = 1;
        long end = n;
        long ans = 1;

        while (start <= end) {
            long mid = start + (end - start) / 2;
            long square = mid * mid;

            if (square == n) return mid;       // exact square root
            else if (square < n) {
                ans = mid;                     // possible answer — try bigger
                start = mid + 1;
            } else {
                end = mid - 1;                 // too big — go smaller
            }
        }
        return ans; // floor of square root
    }


    // ============================================================
    // PROGRAM 4 — Square Root using Newton Raphson Method
    // Numerical method — converges very fast to √n
    //
    // FORMULA: x_new = (x + n/x) / 2
    //   Start with initial guess x = n
    //   Each iteration brings x closer to √n
    //   Stop when |x_new - x| < precision threshold
    //
    // WHY IT WORKS:
    //   If x > √n then n/x < √n
    //   Average of x and n/x is closer to √n than x was
    //   Repeat until converged
    //
    // Time: O(log log n) — extremely fast convergence
    // ============================================================

    static double sqrtNewtonRaphson(double n) {
        if (n < 0) return -1;
        if (n == 0) return 0;

        double x = n;         // initial guess
        double precision = 1e-7;

        while (true) {
            double xNew = (x + n / x) / 2.0;
            if (Math.abs(xNew - x) < precision) break; // converged
            x = xNew;
        }
        return x;
    }


    // ============================================================
    // PROGRAM 5 — Find All Factors of n
    //
    // BRUTE FORCE: check every number 1 to n → O(n)
    //
    // OPTIMISED: check 1 to √n only → O(√n)
    //   If i divides n → both i AND n/i are factors
    //   Only need to find one of each pair
    //   Special case: if i == n/i (perfect square) add once only
    //
    // Print in sorted order: collect small factors first,
    // then print large factors (n/i) in reverse
    // ============================================================

    static void findFactors(int n) {
        System.out.print("Factors of " + n + ": ");

        // OPTIMISED — O(√n)
        // collect large factors separately to print in order
        java.util.ArrayList<Integer> largeFactor = new java.util.ArrayList<>();

        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");                    // small factor
                if (i != n / i) {
                    largeFactor.add(0, n / i);               // large factor — add to front
                }
            }
        }

        for (int f : largeFactor) {
            System.out.print(f + " ");
        }
        System.out.println();
    }


    // ============================================================
    // PROGRAM 6 — GCD using Euclidean Algorithm
    // GCD(a, b) = greatest common divisor
    //
    // EUCLIDEAN ALGORITHM:
    //   GCD(a, b) = GCD(b, a % b)
    //   GCD(a, 0) = a  ← base case
    //
    // WHY IT WORKS:
    //   Any divisor of a and b also divides a % b
    //   So GCD(a,b) = GCD(b, a%b)
    //   Each step reduces the problem — b becomes smaller
    //   Stops when b = 0 — remaining a is the GCD
    //
    // Time: O(log(min(a,b))) — very fast
    //
    // ITERATIVE VERSION also shown
    // ============================================================

    static int gcd(int a, int b) {
        // RECURSIVE — Euclidean algorithm
        if (b == 0) return a;         // base case
        return gcd(b, a % b);         // recursive case

        // ITERATIVE version
        /*
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
        */
    }


    // ============================================================
    // PROGRAM 7 — LCM using GCD
    // LCM(a, b) = least common multiple
    //
    // FORMULA: LCM(a, b) = (a × b) / GCD(a, b)
    //
    // WHY: a × b = GCD(a,b) × LCM(a,b) always
    //      rearranging: LCM = (a × b) / GCD
    //
    // OVERFLOW CAUTION: a × b can overflow int
    //   Divide first: (a / GCD) × b instead of (a × b) / GCD
    //   Since GCD divides a, a/GCD is always integer
    // ============================================================

    static long lcm(int a, int b) {
        return (long) a / gcd(a, b) * b; // divide first to avoid overflow
    }


    // ============================================================
    // BONUS — Modulo Properties
    //
    // (a + b) % m = ((a % m) + (b % m)) % m
    // (a × b) % m = ((a % m) × (b % m)) % m
    // (a - b) % m = ((a % m) - (b % m) + m) % m  ← +m for negative
    // (a / b) % m ≠ (a%m / b%m) — division does NOT distribute
    //
    // USED FOR: large number computations where result % MOD needed
    // ============================================================


    public static void main(String[] args) {
//        System.out.println(isPrime(17));                    // true
//        sieveOfEratosthenes(30);                            // 2 3 5 7 11 13 17 19 23 29
//        System.out.println(sqrtBinarySearch(36));           // 6
//        System.out.println(sqrtNewtonRaphson(2));           // 1.4142135...
//        findFactors(36);                                    // 1 2 3 4 6 9 12 18 36
//        System.out.println(gcd(48, 18));                    // 6
//        System.out.println(lcm(4, 6));                      // 12
    }
}