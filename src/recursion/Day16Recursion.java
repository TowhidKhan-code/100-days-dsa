package recursion;

public class Day16Recursion {

    /*
    ============================================================
    DAY 16 - RECURSION
    Prerequisites: Memory Management, Methods, Stack
    Ex1, Ex2, Recursive Print, Fibonacci, Binary Search
    ============================================================
    */


    // ============================================================
    // EX1 — MESSAGE PROGRAM
    // 5 methods with same body, different names
    // Each calls the next — last one stops
    //
    // PROBLEM WITH THIS APPROACH:
    // 5 methods for 5 prints
    // 100 methods for 100 prints
    // Not scalable — this is exactly why recursion exists
    // ============================================================

    static void printMessage1() {
        System.out.println("Hello World!");
        printMessage2();
    }

    static void printMessage2() {
        System.out.println("Hello World!");
        printMessage3();
    }

    static void printMessage3() {
        System.out.println("Hello World!");
        printMessage4();
    }

    static void printMessage4() {
        System.out.println("Hello World!");
        printMessage5();
    }

    static void printMessage5() {
        System.out.println("Hello World!"); // last — no call
    }


    // ============================================================
    // EX2 — NUMBER PROGRAM
    // Same chain pattern but passes numbers 1 to 5
    // Shows the same scalability problem as Ex1
    // ============================================================

    static void print1(int n) {
        System.out.println(n);
        print2(2);
    }

    static void print2(int n) {
        System.out.println(n);
        print3(3);
    }

    static void print3(int n) {
        System.out.println(n);
        print4(4);
    }

    static void print4(int n) {
        System.out.println(n);
        print5(5);
    }

    static void print5(int n) {
        System.out.println(n); // last — no call
    }


    // ============================================================
    // RECURSIVE SOLUTION FOR EX2
    // Same output as Ex2 but ONE method handles everything
    // Base condition: n == 5 — stop after printing 5
    // Recursive case: print n, then call with n+1
    //
    // This is the power of recursion — one method replaces five
    // Works for any n — print(1) to print(100) same method
    // ============================================================

    static void print(int n) {
        if (n == 5) {
            System.out.println(n); // base condition — print and stop
            return;
        }
        System.out.println(n);
        print(n + 1); // recursive call — smaller problem (next number)
    }


    // ============================================================
    // FIBONACCI — Recursive
    // fib(0) = 0, fib(1) = 1
    // fib(n) = fib(n-1) + fib(n-2)
    //
    // BASE CONDITION: n < 2 → return n
    //   n=0 → return 0
    //   n=1 → return 1
    //   Both base cases in one line
    //
    // RECURSION TREE for fib(4):
    //          fib(4)
    //         /      \
    //      fib(3)   fib(2)
    //      /    \   /    \
    //   fib(2) fib(1) fib(1) fib(0)
    //   /    \
    // fib(1) fib(0)
    //
    // NOTE: fib(2) calculated TWICE — inefficiency of naive recursion
    // Solved by memoization (covered later)
    //
    // Time: O(2^n) — exponential
    // Space: O(n) — recursion stack depth
    // ============================================================

    static int fibo(int n) {
        if (n < 2) {
            return n; // base condition — n=0 returns 0, n=1 returns 1
        }
        return fibo(n - 1) + fibo(n - 2); // recursive case
    }


    // ============================================================
    // BINARY SEARCH — Recursive
    // Same logic as iterative but uses recursion instead of while loop
    //
    // BASE CONDITIONS:
    //   s > e → not found → return -1
    //   arr[m] == target → found → return m
    //
    // RECURSIVE CASES:
    //   arr[m] > target → search left half → return binarySearch(arr, target, s, m-1)
    //   arr[m] < target → search right half → return binarySearch(arr, target, m+1, e)
    //
    // Return result of recursive call directly — no extra variable needed
    //
    // RECURRENCE RELATION: T(n) = T(n/2) + O(1)
    // Time: O(log n)
    // Space: O(log n) — recursion stack (unlike iterative O(1))
    // ============================================================

    static int binarySearch(int[] arr, int target, int s, int e) {
        if (s > e) {
            return -1; // base condition — not found
        }

        int m = s + (e - s) / 2; // safe mid formula

        if (arr[m] == target) {
            return m; // base condition — found
        }
        if (arr[m] > target) {
            return binarySearch(arr, target, s, m - 1); // search left
        }
        return binarySearch(arr, target, m + 1, e); // search right
    }


    public static void main(String[] args) {
//        printMessage1();
//        print1(1);
//        print(1);
//        System.out.println(fibo(7)); // 13
        int[] arr = {1, 2, 3, 4, 55, 66, 78};
        System.out.println(binarySearch(arr, 55, 0, arr.length - 1)); // 4
    }
}
