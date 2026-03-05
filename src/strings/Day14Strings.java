package strings;

import java.util.Arrays;

public class Day14Strings {

    /*
    ============================================================
    DAY 14 - STRINGS AND STRINGBUILDER
    Reverse, Palindrome, Count Vowels, Count Occurrences,
    Char Array, LeetCode: 125, 242
    ============================================================
    */


    // ============================================================
    // PROGRAM 1 — Reverse a String
    //
    // TWO APPROACHES:
    // 1. StringBuilder.reverse() — clean, optimized, preferred
    // 2. Manual loop — not optimized, new String object created
    //    every concatenation due to String immutability
    //
    // Always use StringBuilder for string building in loops
    // ============================================================

    static String reverse(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        // APPROACH 1 — StringBuilder (preferred, optimized)
        StringBuilder str1 = new StringBuilder(str);
        str1.reverse();
        return str1.toString();

        // APPROACH 2 — manual loop (not optimized)
        // new String object created every iteration — avoid in production
        /*
        String rev = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            rev += str.charAt(i);
        }
        return rev;
        */
    }


    // ============================================================
    // PROGRAM 2 — Check if String is Palindrome
    //
    // Two pointer approach — compare from both ends toward middle
    // Loop only to str.length() / 2 — no need to go further
    // If any mismatch — not palindrome, return false immediately
    // Time: O(n/2) = O(n), Space: O(1)
    // ============================================================

    static boolean isPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return true; // or false depending on requirement
        }

        for (int i = 0; i <= str.length() / 2; i++) {
            char start = str.charAt(i);
            char end = str.charAt(str.length() - 1 - i);
            if (start != end) {
                return false; // mismatch found — not palindrome
            }
        }
        return true;
    }


    // ============================================================
    // PROGRAM 3 — Count Vowels in a String
    //
    // toLowerCase() before checking handles both cases in one shot
    // toCharArray() converts string to char array for iteration
    // ============================================================

    static int countVowels(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int count = 0;
        // toLowerCase() handles both upper and lower case
        char[] charArray = str.toLowerCase().toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 'a' || charArray[i] == 'e' ||
                    charArray[i] == 'i' || charArray[i] == 'o' ||
                    charArray[i] == 'u') {
                count++;
            }
        }
        return count;
    }


    // ============================================================
    // PROGRAM 4 — Count Occurrences of a Character
    //
    // charAt(i) directly — no need to convert to char array
    // Simple linear scan — O(n)
    // ============================================================

    static int countOcc(String str, char c) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }


    // ============================================================
    // PROGRAM 5 — Convert String to Char Array and Print
    //
    // toCharArray() — built in method to convert String to char[]
    // Arrays.toString() — prints array in readable format [a, b, c]
    // ============================================================

    static void printCharArray(String str) {
        if (str == null || str.length() == 0) {
            System.out.println("String is empty or null");
            return;
        }
        char[] charArray = str.toCharArray();
        System.out.println(Arrays.toString(charArray));
    }


    // ============================================================
    // LEETCODE 125 — Valid Palindrome
    // String has spaces, punctuation, mixed case
    // Check if it is palindrome ignoring non-alphanumeric characters
    //
    // MY FIRST APPROACH — filter, reverse, compare
    // Logic correct but creates 3 new strings — TLE on LeetCode
    //
    // OPTIMAL — two pointer in place
    // Skip non-alphanumeric from both ends
    // Compare characters after lowercasing
    // O(n) time, O(1) space — no new strings created
    // ============================================================

    static boolean isValidPalindrome(String s) {

        // MY FIRST APPROACH — TLE (too slow for large inputs)
        /*
        String str = filter(s);
        String revStr = reverse(str);
        return str.equals(revStr);
        */

        // OPTIMAL — two pointer in place
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            // skip non alphanumeric from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // skip non alphanumeric from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // compare ignoring case
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // helper used in first approach
    static String filter(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetterOrDigit(str.charAt(i))) {
                sb.append(str.toLowerCase().charAt(i));
            }
        }
        return sb.toString();
    }


    // ============================================================
    // LEETCODE 242 — Valid Anagram
    // Two strings are anagrams if they contain same characters
    // with same frequencies
    //
    // APPROACH: frequency array of size 26 (a to z)
    // Increment for s, decrement for t in SAME loop
    // If anagram — all counts end at 0
    //
    // s.charAt(i) - 'a' converts character to index 0-25
    // Uses ASCII value difference
    //
    // Time: O(n), Space: O(1) — fixed array of 26
    // Better than sorting both strings O(n log n)
    // ============================================================

    static boolean isAnagram(String s, String t) {
        // different lengths — cannot be anagram
        if (s.length() != t.length()) {
            return false;
        }

        int[] count = new int[26]; // frequency array for a-z

        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++; // increment for s
            count[t.charAt(i) - 'a']--; // decrement for t
        }

        // if anagram — all counts are 0
        for (int freq : count) {
            if (freq != 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        String name = "Towhid Khan";

//        System.out.println(reverse(name));
//        System.out.println(isPalindrome("racecar"));
//        System.out.println(countVowels(name));
//        System.out.println(countOcc(name, 'h'));
//        printCharArray(name);
//        System.out.println(isValidPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(isAnagram("anagram", "nagaram"));
    }
}