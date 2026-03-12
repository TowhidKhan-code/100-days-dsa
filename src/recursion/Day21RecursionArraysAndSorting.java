package recursion;

import java.util.ArrayList;
import java.util.Arrays;

public class Day21RecursionArraysAndSorting {

    // ══════════════════════════════════════════════════════════════════════
    // Q1 : Check if array is sorted
    // ══════════════════════════════════════════════════════════════════════

    // KEY IDEA: compare arr[i] with arr[i+1], recurse on rest
    // && short-circuits — if this pair fails, stops immediately, no more calls
    static boolean isSorted(int[] arr, int i) {
        if (i == arr.length - 1) {
            return true;                               // base: one element left, always sorted
        }
        return arr[i] < arr[i + 1] && isSorted(arr, i + 1);
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q2 : Linear Search — return true/false
    // ══════════════════════════════════════════════════════════════════════
    // || short-circuits — stops immediately on first match
    static boolean exist(int[] arr, int i, int t) {
        if (i == arr.length) {
            return false;
        }
        return arr[i] == t || exist(arr, i + 1, t);  // match here OR search rest
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q3 : Linear Search — return index
    // ══════════════════════════════════════════════════════════════════════
    // YOUR SOLUTION ✅ correct
    static int exist2(int[] arr, int i, int t) {
        if (i == arr.length) {
            return -1;
        }
        return arr[i] == t ? i : exist2(arr, i + 1, t); // match → index, no match → recurse
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q4 : Return ArrayList — pass list as parameter (accumulator pattern)
    // ══════════════════════════════════════════════════════════════════════
    // YOUR SOLUTION ✅ correct
    // IMPROVEMENT: your two branches both recursed the same way
    //   if match → add, recurse
    //   no match → just recurse
    // Simplify: add if match, ALWAYS recurse — one return statement
    static ArrayList<Integer> allOccurence(int[] arr, int i, int t, ArrayList<Integer> list) {
        if (i == arr.length) {
            return list;
        }
        if (arr[i] == t) {
            list.add(i);
        }
        return allOccurence(arr, i + 1, t, list);
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q5 : Return ArrayList — WITHOUT passing list as argument (build on return path)
    // ══════════════════════════════════════════════════════════════════════
    //
    // "Not optimal as new object is created every time"  ← CORRECT
    //
    // TRADEOFF to know:
    //   Q4 (pass down):   ONE ArrayList shared across all calls  → O(n) space
    //   Q5 (return up):   NEW ArrayList created per call frame   → O(n²) space
    //   Q4 is preferred in practice. Q5 teaches the "build on return" pattern.
    static ArrayList<Integer> allOccurence2(int[] arr, int i, int t) {
        ArrayList<Integer> list = new ArrayList<>();
        if (i == arr.length) {
            return list;                               // base: return empty list
        }
        if (arr[i] == t) {
            list.add(i);                               // add current index if match
        }
        ArrayList<Integer> ansFromBelowCalls = allOccurence2(arr, i + 1, t);
        list.addAll(ansFromBelowCalls);               // merge results from below
        return list;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q6 : Rotated Binary Search
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: in a rotated array, at least ONE half is always sorted.
    //   Step 1: find mid
    //   Step 2: check which half is sorted (compare arr[s] with arr[m])
    //   Step 3: check if target lies within the sorted half's range
    //   Step 4: search there. If not, search the other half.
    //
    // arr[s] <= arr[m] → LEFT half is sorted
    //   t in [arr[s], arr[m]] → search left, else search right
    // otherwise → RIGHT half is sorted
    //   t in [arr[m], arr[e]] → search right, else search left
    static int rotatedBS(int[] arr, int t, int s, int e) {
        if (s > e) {
            return -1;                                 // base: search space exhausted
        }
        int m = s + (e - s) / 2;                     // safe mid (avoids int overflow)
        if (arr[m] == t) {
            return m;                                  // found
        }
        if (arr[s] <= arr[m]) {                       // left half is sorted
            if (arr[s] <= t && arr[m] >= t) {
                return rotatedBS(arr, t, s, m - 1);  // target in left half
            } else {
                return rotatedBS(arr, t, m + 1, e);  // target in right half
            }
        }
        // right half is sorted
        if (t >= arr[m] && t <= arr[e]) {
            return rotatedBS(arr, t, m + 1, e);      // target in right half
        } else {
            return rotatedBS(arr, t, s, m - 1);      // target in left half
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // PATTERNS Q1 : Triangle 1 — left-aligned, decreasing
    // ══════════════════════════════════════════════════════════════════════

    // Output for pattern1(4, 0):
    //   ****
    //   ***
    //   **
    //   *
    static void pattern1(int r, int c) {
        if (r == 0) return;                            // base: no more rows
        if (c < r) {
            System.out.print("*");                    // print BEFORE → left to right
            pattern1(r, c + 1);
        } else {
            System.out.println();                     // row done → newline
            pattern1(r - 1, 0);                      // next row, reset column
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // PATTERNS Q2 : Triangle 2 — right-aligned
    // ══════════════════════════════════════════════════════════════════════
    static void pattern2(int r, int c) {
        if (r == 0) return;
        if (c < r) {
            pattern2(r, c + 1);
            System.out.print("*");
        } else {
            pattern2(r - 1, 0);
            System.out.println();
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // SORTING Q3 : Bubble Sort — recursive
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: each pass bubbles the largest unsorted element to position r-1
    //   After each full pass, reduce r by 1. Stop when r == 0.
    static void bubbleSort(int[] arr, int r, int c) {
        if (r == 0) return;                            // base: nothing left to sort
        if (c < r - 1) {                              // FIXED: was c < r
            if (arr[c] > arr[c + 1]) {
                int temp = arr[c];
                arr[c] = arr[c + 1];
                arr[c + 1] = temp;
            }
            bubbleSort(arr, r, c + 1);
        } else {
            bubbleSort(arr, r - 1, 0);
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // SORTING Q4 : Selection Sort — recursive
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: find index of maximum in unsorted portion [0..r-1]
    //   max parameter carries the index of largest element seen so far
    //   When c reaches r (end of pass): swap arr[max] to position r-1
    //   Then recurse on [0..r-2]
    //
    // Call with (arr, arr.length, 0, 0):
    //   arr.length → full unsorted range
    //   c = 0     → start scanning from index 0
    //   max = 0   → initial max candidate is index 0
    static void selectionSort(int[] arr, int r, int c, int max) {
        if (r == 0) return;                            // base: nothing left to sort
        if (c < r) {
            if (arr[c] > arr[max]) {
                selectionSort(arr, r, c + 1, c);     // new max found: pass c as new max
            } else {
                selectionSort(arr, r, c + 1, max);   // keep current max
            }
        } else {
            // end of pass: swap max element to its sorted position
            int temp = arr[max];
            arr[max] = arr[r - 1];
            arr[r - 1] = temp;
            selectionSort(arr, r - 1, 0, 0);         // recurse on smaller range
        }
    }


    public static void main(String[] args) {
        int[] arr = {8, 1, 5, 7, 3, 2, 4, 6};

        System.out.println("=== Q1: isSorted ===");
        System.out.println(isSorted(arr, 0));                                   // false
        System.out.println(isSorted(new int[]{1, 2, 3, 4, 5}, 0));             // true

        System.out.println("\n=== Q2: exist ===");
        System.out.println(exist(arr, 0, 7));                                   // true
        System.out.println(exist(arr, 0, 9));                                   // false

        System.out.println("\n=== Q3: exist2 ===");
        System.out.println(exist2(arr, 0, 8));                                  // 0
        System.out.println(exist2(arr, 0, 9));                                  // -1

        System.out.println("\n=== Q4: allOccurence (pass list down) ===");
        int[] dups = {1, 3, 5, 3, 7, 3};
        System.out.println(allOccurence(dups, 0, 3, new ArrayList<>()));        // [1, 3, 5]

        System.out.println("\n=== Q5: allOccurence2 (build on return) ===");
        System.out.println(allOccurence2(dups, 0, 3));                          // [1, 3, 5]

        System.out.println("\n=== Q6: rotatedBS ===");
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(rotatedBS(rotated, 0, 0, rotated.length - 1));       // 4
        System.out.println(rotatedBS(rotated, 6, 0, rotated.length - 1));       // 6
        System.out.println(rotatedBS(rotated, 3, 0, rotated.length - 1));       // -1

        System.out.println("\n=== Pattern 1 ===");
        pattern1(4, 0);
        // ****
        // ***
        // **
        // *

        System.out.println("\n=== Pattern 2 ===");
        pattern2(4, 0);


        System.out.println("\n=== Bubble Sort ===");
        int[] b = {8, 1, 5, 7, 3, 2, 4, 6};
        bubbleSort(b, b.length, 0);
        System.out.println(Arrays.toString(b));                                 // [1, 2, 3, 4, 5, 6, 7, 8]

        System.out.println("\n=== Selection Sort ===");
        int[] s = {8, 1, 5, 7, 3, 2, 4, 6};
        selectionSort(s, s.length, 0, 0);
        System.out.println(Arrays.toString(s));                                 // [1, 2, 3, 4, 5, 6, 7, 8]
    }
}
