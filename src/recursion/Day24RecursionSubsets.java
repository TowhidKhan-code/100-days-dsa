package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day24RecursionSubsets {

    // ══════════════════════════════════════════════════════════════════════
    // UP / P PATTERN — the foundation of all string recursion today
    // ══════════════════════════════════════════════════════════════════════
    // p  = processed   → what we have built so far
    // up = unprocessed → what still needs to be handled
    //
    // Every call: take first char of up, decide what to do, recurse
    // Base case: up is empty → p is the complete result → print/return it

    // ══════════════════════════════════════════════════════════════════════
    // Q1 : Skip a character
    // ══════════════════════════════════════════════════════════════════════
    static void skipChar(String p, String up, char ignore) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        if (ch == ignore) {
            skipChar(p, up.substring(1), ignore);          // skip: don't add ch to p
        } else {
            skipChar(p + ch, up.substring(1), ignore);     // keep: add ch to p
        }
    }

    // Without p parameter — build result on return path
    static String skipChar2(String up) {
        if (up.isEmpty()) return "";
        char ch = up.charAt(0);
        if (ch == 'a') {
            return skipChar2(up.substring(1));             // skip 'a'
        } else {
            return ch + skipChar2(up.substring(1));        // keep and build on return
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q2 : Skip a whole word/string
    // ══════════════════════════════════════════════════════════════════════
    // KEY: startsWith(ignore) checks whole word at once
    //      substring(ignore.length()) skips the entire word in one step
    static void skipString(String p, String up, String ignore) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        if (up.startsWith(ignore)) {
            skipString(p, up.substring(ignore.length()), ignore); // skip whole word
        } else {
            skipString(p + up.charAt(0), up.substring(1), ignore); // keep one char
        }
    }

    // Without p parameter
    static String skipString2(String up) {
        if (up.isEmpty()) return "";
        if (up.startsWith("apple")) {
            return skipString2(up.substring(5));           // skip "apple"
        } else {
            return up.charAt(0) + skipString2(up.substring(1));
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q3 : Skip a string only if it is NOT the required string
    // ══════════════════════════════════════════════════════════════════════
    // KEY: startsWith(ignore) AND !startsWith("Apple")
    //      skips "App" only when it is NOT followed by "le"
    //      so "Apple" is preserved, "App" alone is skipped
    static void skipStringNotRequired(String p, String up, String ignore) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        if (up.startsWith(ignore) && !up.startsWith("Apple")) {
            skipStringNotRequired(p, up.substring(ignore.length()), ignore);
        } else {
            skipStringNotRequired(p + up.charAt(0), up.substring(1), ignore);
        }
    }

    // Without p parameter
    static String skipStringNotRequired2(String up) {
        if (up.isEmpty()) return "";
        if (up.startsWith("app") && !up.startsWith("apple")) {
            return skipStringNotRequired2(up.substring(3)); // skip "app" but not "apple"
        } else {
            return up.charAt(0) + skipStringNotRequired2(up.substring(1));
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q4 : Print all subsequences of a string
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: at each character, make TWO choices:
    //   Choice 1: INCLUDE ch → add to p, recurse on rest
    //   Choice 2: EXCLUDE ch → don't add, recurse on rest
    //
    // For string of length n → 2ⁿ subsequences
    // WHY 2ⁿ: each of n characters has 2 choices (include/exclude)
    //         2 × 2 × 2 × ... n times = 2ⁿ
    static void subseq(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);    // base: up exhausted, p is one complete subsequence
            return;
        }
        char ch = up.charAt(0);
        subseq(p + ch, up.substring(1));   // INCLUDE ch
        subseq(p, up.substring(1));        // EXCLUDE ch
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q5 : Return ArrayList of all subsequences
    // ══════════════════════════════════════════════════════════════════════
    // Build list on return path — each branch returns its own list, merge with addAll
    static ArrayList<String> subseqRet(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);              // base: add current subsequence to new list
            return list;
        }
        char ch = up.charAt(0);
        ArrayList<String> withChar    = subseqRet(p + ch, up.substring(1)); // include
        ArrayList<String> withoutChar = subseqRet(p, up.substring(1));      // exclude
        withChar.addAll(withoutChar);  // merge both results
        return withChar;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q6 : Print ASCII value of a character
    // ══════════════════════════════════════════════════════════════════════
    // ch + 0 forces int addition → gives ASCII value as int
    // (char)(ch + 0) casts back to char → gives original character
    static void ascii(char ch) {
        System.out.println(ch + 0);       // ASCII value (int)
        System.out.println((char)(ch + 0)); // back to character
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q7 : Subsequences with ASCII values
    // ══════════════════════════════════════════════════════════════════════
    // THREE branches at each character:
    //   Branch 1: include the character itself
    //   Branch 2: exclude the character
    //   Branch 3: include the ASCII value of the character
    //
    // For string of length n → 3ⁿ combinations (3 choices at each step)
    static void subseqAscii(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        char ch = up.charAt(0);
        subseqAscii(p + ch, up.substring(1));         // include char
        subseqAscii(p, up.substring(1));              // exclude
        subseqAscii(p + (ch + 0), up.substring(1));  // include ASCII value
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q8 : Iterative program to print subsequences (Subsets of array)
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: start with one empty subset [[]]
    //   For each number: take ALL existing subsets, copy each, add current number
    //   The copies become new subsets. Add them to the outer list.
    //
    //   Without copying: we modify the original subset → breaks algorithm
    //   With copying:    we extend a copy → original stays intact
    //
    // For {1,2,3}:
    //   Start:    [[]]
    //   Add 1:    [[], [1]]
    //   Add 2:    [[], [1], [2], [1,2]]
    //   Add 3:    [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]
    static List<List<Integer>> subset(int[] arr) {
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());                  // start with empty subset
        for (int num : arr) {
            int n = outer.size();                      // snapshot size before we add new ones
            for (int i = 0; i < n; i++) {
                List<Integer> internal = new ArrayList<>(outer.get(i)); // COPY existing subset
                internal.add(num);                     // extend copy with current number
                outer.add(internal);                   // add new subset
            }
        }
        return outer;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q9 : Subsequences with duplicate elements
    // ══════════════════════════════════════════════════════════════════════
    // PROBLEM: {1, 2, 2} would give duplicate subsets like [2] appearing twice
    //
    // KEY INSIGHT: sort array first so duplicates are adjacent
    //   When current element equals previous element:
    //     only extend subsets that were added in the PREVIOUS iteration
    //     (from end+1 to current outer.size()-1)
    //     NOT all existing subsets — that would create duplicates
    //
    // start and end track which subsets to extend:
    //   Normal element:    start=0, extend all subsets
    //   Duplicate element: start=end+1, extend only subsets from last iteration
    //
    static List<List<Integer>> subsetDupli(int[] arr) {
        List<List<Integer>> outer = new ArrayList<>();
        outer.add(new ArrayList<>());
        int start = 0;
        int end = 0;
        for (int i = 0; i < arr.length; i++) {
            start = 0;
            if (i > 0 && arr[i] == arr[i - 1]) {
                start = end + 1;                       // duplicate: only extend last batch
            }
            end = outer.size() - 1;                   // snapshot end before adding new subsets
            for (int j = start; j <= end; j++) {
                List<Integer> internal = new ArrayList<>(outer.get(j));
                internal.add(arr[i]);
                outer.add(internal);
            }
        }
        return outer;
    }


    public static void main(String[] args) {
        System.out.println("=== Q1: Skip char 'p' ===");
        skipChar("", "Towhid", 'o');
        System.out.println(skipChar2("abcabbda"));        // remove all 'a'

        System.out.println("\n=== Q2: Skip string 'Apple' ===");
        skipString("", "HelloAppleWorld", "Apple");
        System.out.println(skipString2("HelloappleWorld"));

        System.out.println("\n=== Q3: Skip 'App' but not 'Apple' ===");
        skipStringNotRequired("", "HelloAppleWorld", "App");
        System.out.println(skipStringNotRequired2("helloappleworld"));

        System.out.println("\n=== Q4: All subsequences of 'abc' ===");
        subseq("", "abc");

        System.out.println("\n=== Q5: Return ArrayList of subsequences ===");
        System.out.println(subseqRet("", "abc"));

        System.out.println("\n=== Q6: ASCII values ===");
        ascii('a');                                        // 97, a
        ascii('A');                                        // 65, A

        System.out.println("\n=== Q7: Subsequences with ASCII ===");
        subseqAscii("", "ab");

        System.out.println("\n=== Q8: Iterative subsets of {1,2,3} ===");
        int[] arr = {1, 2, 3};
        System.out.println(subset(arr));

        System.out.println("\n=== Q9: Subsets with duplicates {1,2,2} ===");
        int[] arrDup = {2, 1, 2};
        Arrays.sort(arrDup);
        System.out.println(subsetDupli(arrDup));
    }
}