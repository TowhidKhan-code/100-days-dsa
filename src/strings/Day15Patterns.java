package strings;

public class Day15Patterns {

    /*
    ============================================================
    DAY 15 - PATTERNS + LEETCODE 409
    7 Pattern Programs + Longest Palindrome
    ============================================================
    */


    // ============================================================
    // PATTERN 1 — Square
    // Both loops run n times
    // * * * * *
    // * * * * *
    // * * * * *
    // ============================================================

    static void pattern1(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }


    // ============================================================
    // PATTERN 2 — Right Triangle
    // Inner loop runs i times — grows with each row
    // *
    // * *
    // * * *
    // ============================================================

    static void pattern2(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }


    // ============================================================
    // PATTERN 3 — Inverted Triangle
    // Inner loop shrinks each row using n + 1 - i
    // Alternative: j = i; j <= n; j++
    // * * * * *
    // * * * *
    // * * *
    // ============================================================

    static void pattern3(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n + 1 - i; j++) { // alternative: j=i; j<=n; j++
                System.out.print("* ");
            }
            System.out.println();
        }
    }


    // ============================================================
    // PATTERN 4 — Number Triangle
    // Same as pattern 2 but prints j instead of *
    // 1
    // 1 2
    // 1 2 3
    // ============================================================

    static void pattern4(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }


    // ============================================================
    // PATTERN 5 — Diamond
    // KEY TRICK: one ternary handles both upper and lower halves
    // totalColsInRow = i > n ? 2*n - i : i
    // No need for two separate loops
    //     *
    //    * *
    //   * * *
    //    * *
    //     *
    // ============================================================

    static void pattern5(int n) {
        for (int i = 1; i <= 2 * n - 1; i++) {
            // one formula for both halves
            int totalColsInRow = i > n ? 2 * n - i : i;
            for (int j = 1; j <= totalColsInRow; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }


    // ============================================================
    // PATTERN 6 — Pyramid
    // Same diamond logic with spaces added before stars
    // noOfSpaces = n - totalColsInRow
    //     *
    //    * *
    //   * * *
    //  * * * *
    // * * * * *
    // ============================================================

    static void pattern6(int n) {
        for (int i = 0; i <= 2 * n; i++) {
            int totalColsInRow = i > n ? 2 * n - i : i;
            int noOfSpaces = n - totalColsInRow;

            // print spaces first
            for (int k = 0; k < noOfSpaces; k++) {
                System.out.print(" ");
            }
            // print stars
            for (int j = 0; j < totalColsInRow; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }


    // ============================================================
    // PATTERN 7 — Number Pyramid (bonus — not required)
    // Three inner loops: spaces, descending numbers, ascending numbers
    //         1
    //       2 1 2
    //     3 2 1 2 3
    //   4 3 2 1 2 3 4
    // 5 4 3 2 1 2 3 4 5
    // ============================================================

    static void pattern7(int n) {
        for (int i = 1; i <= n; i++) {
            int noOfSpaces = n - i;

            // print spaces
            for (int k = 1; k <= noOfSpaces; k++) {
                System.out.print("  ");
            }
            // print descending numbers
            for (int j = i; j >= 1; j--) {
                System.out.print(j + " ");
            }
            // print ascending numbers (skip 1 — already printed)
            for (int k = 2; k <= i; k++) {
                System.out.print(k + " ");
            }
            System.out.println();
        }
    }


    // ============================================================
    // LEETCODE 409 — Longest Palindrome
    // Find length of longest palindrome that can be built
    // from characters in the string
    //
    // KEY INSIGHT:
    // Palindrome uses even frequency characters fully
    // Odd frequency characters → use count-1 (make even)
    // ONE odd character can sit in the center
    //
    // int[128] — covers all ASCII characters
    // freq[c]++ — character itself used as array index
    //
    // Time: O(n), Space: O(1) — fixed array of 128
    // ============================================================

    static int longestPalindrome(String s) {
        int[] freq = new int[128]; // covers all ASCII characters

        // count frequency of each character
        for (char c : s.toCharArray()) {
            freq[c]++;
        }

        int length = 0;
        boolean hasOdd = false;

        for (int count : freq) {
            if (count % 2 == 0) {
                length += count;      // even — use all
            } else {
                length += count - 1; // odd — make even, remove one
                hasOdd = true;       // mark odd character exists
            }
        }

        // one odd character can sit in center of palindrome
        if (hasOdd) {
            length += 1;
        }

        return length;
    }


    public static void main(String[] args) {
//        pattern1(5);
//        pattern2(5);
//        pattern3(5);
//        pattern4(5);
//        pattern5(5);
//        pattern6(5);
//        pattern7(5);
//        System.out.println(longestPalindrome("aabbccc")); // 7
    }
}