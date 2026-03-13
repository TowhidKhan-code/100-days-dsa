package recursion;

import java.util.Arrays;

public class Day22MergeSort {

    // ══════════════════════════════════════════════════════════════════════
    // E1 : Recursive Merge Sort — standard (with extra space)
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: divide and conquer
    //   1. Split array into two halves
    //   2. Recursively sort each half
    //   3. Merge the two sorted halves back together
    //
    // Base case: array of length 1 is already sorted — return it
    // Arrays.copyOfRange handles splitting cleanly without manual indexing
    static int[] mergeSort(int[] arr) {
        if (arr.length == 1) {
            return arr;                                    // base: single element, already sorted
        }
        int mid = arr.length / 2;
        int[] first  = mergeSort(Arrays.copyOfRange(arr, 0, mid));   // sort left half
        int[] second = mergeSort(Arrays.copyOfRange(arr, mid, arr.length)); // sort right half
        return merge(first, second);                      // merge two sorted halves
    }

    // ══════════════════════════════════════════════════════════════════════
    // merge() — combine two sorted arrays into one sorted array
    // ══════════════════════════════════════════════════════════════════════
    // THREE PHASES:
    //   Phase 1: compare elements from both halves, pick the smaller
    //   Phase 2: drain remaining elements from left half (if any)
    //   Phase 3: drain remaining elements from right half (if any)
    //
    // Only ONE half will have remaining elements after phase 1
    // k++ outside the if/else is clean — one increment handles both branches
    static int[] merge(int[] first, int[] second) {
        int[] ans = new int[first.length + second.length]; // result array
        int i = 0; // pointer for first
        int j = 0; // pointer for second
        int k = 0; // pointer for ans

        // Phase 1: compare and pick smaller
        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                ans[k] = first[i];
                i++;
            } else {
                ans[k] = second[j];
                j++;
            }
            k++;
        }
        // Phase 2: drain remaining left elements
        while (i < first.length) {
            ans[k] = first[i];
            i++;
            k++;
        }
        // Phase 3: drain remaining right elements
        while (j < second.length) {
            ans[k] = second[j];
            j++;
            k++;
        }
        return ans;
    }

    // ══════════════════════════════════════════════════════════════════════
    // In-place Merge Sort
    // ══════════════════════════════════════════════════════════════════════
    // DIFFERENCE from standard:
    //   Standard: creates new arrays for each half (more memory)
    //   In-place: works with indices s and e on the ORIGINAL array
    //             only creates a temp array during the merge step itself
    //
    // Base case: e - s == 1 means one element in this window — stop
    // Mid calculation: s + (e-s)/2 — safe, avoids integer overflow
    static void mergeSortInPlace(int[] arr, int s, int e) {
        if (e - s == 1) {
            return;                                        // base: one element, already sorted
        }
        int m = s + (e - s) / 2;
        mergeSortInPlace(arr, s, m);                      // sort left half  [s, m)
        mergeSortInPlace(arr, m, e);                      // sort right half [m, e)
        mergeInPlace(arr, s, m, e);                       // merge both halves in original array
    }

    // ══════════════════════════════════════════════════════════════════════
    // mergeInPlace() — merge window [s,e) of original array using temp array
    // ══════════════════════════════════════════════════════════════════════
    // KEY DIFFERENCE from merge():
    //   Works on a WINDOW of the original array using indices s, m, e
    //   Left half:  arr[s..m-1]
    //   Right half: arr[m..e-1]
    //   Creates temp array of size (e-s) for this window only
    //   Copies sorted result back into the original array at position s+l
    //
    // The copy-back loop: arr[s+l] = ans[l]
    //   s offset is critical — writes back to the correct section of arr
    static void mergeInPlace(int[] arr, int s, int m, int e) {
        int[] ans = new int[e - s];                        // temp array for this window only
        int i = s; // pointer for left half  [s, m)
        int j = m; // pointer for right half [m, e)
        int k = 0; // pointer for ans

        // Phase 1: compare and pick smaller
        while (i < m && j < e) {
            if (arr[i] < arr[j]) {
                ans[k] = arr[i];
                i++;
            } else {
                ans[k] = arr[j];
                j++;
            }
            k++;
        }
        // Phase 2: drain remaining left elements
        while (i < m) {
            ans[k] = arr[i];
            i++;
            k++;
        }
        // Phase 3: drain remaining right elements
        while (j < e) {
            ans[k] = arr[j];
            j++;
            k++;
        }
        // Copy sorted window back into original array
        for (int l = 0; l < ans.length; l++) {
            arr[s + l] = ans[l];
        }
    }


    public static void main(String[] args) {
        System.out.println("=== Standard Merge Sort ===");
        int[] arr1 = {2, 6, 7, 5, 9, 3, 8, 1, 4};
        arr1 = mergeSort(arr1);
        System.out.println(Arrays.toString(arr1));         // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        System.out.println("\n=== In-place Merge Sort ===");
        int[] arr2 = {2, 6, 7, 5, 9, 3, 8, 1, 4};
        mergeSortInPlace(arr2, 0, arr2.length);
        System.out.println(Arrays.toString(arr2));         // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}