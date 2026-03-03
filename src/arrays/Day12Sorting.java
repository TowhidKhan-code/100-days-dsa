package arrays;

import java.util.Arrays;

public class Day12Sorting {

    /*
    ============================================================
    DAY 12 - SORTING ALGORITHMS
    Bubble Sort, Selection Sort, Insertion Sort
    LeetCode: 912, 75
    ============================================================
    */


    // ============================================================
    // HELPER — Swap two elements in array
    // Extracted as separate method — reused across all sorting algorithms
    // This is clean software design — each method does one job
    // ============================================================

    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }


    // ============================================================
    // BUBBLE SORT
    // Compare adjacent elements — swap if wrong order
    // Largest element bubbles to end after each pass
    //
    // OPTIMIZATION: swapped flag
    // If no swaps in a pass — array is already sorted — stop early
    // Best case with flag: O(n) — already sorted array
    //
    // Time: O(n²) worst, O(n) best
    // Space: O(1)
    // ============================================================

    static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;

        // run for n-1 passes
        for (int i = 0; i < n; i++) {
            swapped = false;

            // after each pass, last i elements are already sorted
            // so inner loop runs one less each time
            for (int j = 1; j < n - i; j++) {
                // swap if current element is smaller than previous
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                    swapped = true;
                }
            }

            // if no swap happened — array is already sorted — stop
            // !false = true → break
            if (!swapped) {
                break;
            }
        }
    }


    // ============================================================
    // SELECTION SORT
    // Find max in unsorted portion, place at correct end position
    // OR find min in unsorted portion, place at correct start position
    // Both approaches are correct — implemented both here
    //
    // No early exit optimization possible — always O(n²)
    // Time: O(n²) always
    // Space: O(1)
    // ============================================================

    static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            // find max in remaining array and swap to correct end position
            int last = n - i - 1;
            int maxIndex = getMaxIndex(arr, 0, last);
            swap(arr, maxIndex, last);

            // alternative — find min and swap to correct start position
            // int first = i;
            // int lowestIndex = getLowestIndex(arr, first, n - 1);
            // swap(arr, lowestIndex, first);
        }
    }

    // find index of maximum element between start and end
    static int getMaxIndex(int[] arr, int start, int end) {
        int max = start; // assume first element is max
        for (int i = start; i <= end; i++) {
            if (arr[i] > arr[max]) {
                max = i; // found larger — update max index
            }
        }
        return max;
    }

    // find index of minimum element between start and end
    static int getLowestIndex(int[] arr, int start, int end) {
        int lowest = start;
        for (int i = start; i <= end; i++) {
            if (arr[i] < arr[lowest]) {
                lowest = i;
            }
        }
        return lowest;
    }


    // ============================================================
    // INSERTION SORT
    // Take each element — insert into correct position in sorted left portion
    // Inner loop moves backwards — shifts elements right until correct spot found
    // Break when no swap needed — element already in correct position
    //
    // Best case: O(n) — nearly sorted array (few shifts needed)
    // Time: O(n²) worst, O(n) best
    // Space: O(1)
    // ============================================================

    static void insertionSort(int[] arr) {
        for (int i = 0; i <= arr.length - 2; i++) {
            // take element at i+1 and insert into correct position
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1); // shift left
                } else {
                    break; // correct position found — stop inner loop
                }
            }
        }
    }


    // ============================================================
    // LEETCODE 912 — Sort an Array
    // Insertion sort solution — O(n²)
    // Note: problem expects O(n log n) but insertion sort gets accepted
    // Revisit with merge sort or quick sort later
    // ============================================================

    static int[] sortArray(int[] nums) {
        for (int i = 0; i <= nums.length - 2; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        return nums;
    }


    // ============================================================
    // LEETCODE 75 — Sort Colors
    // Array has only 0s, 1s, and 2s — sort in single pass
    //
    // MY SOLUTION — insertion sort O(n²) — works, gets accepted
    //
    // BETTER SOLUTION — Dutch National Flag O(n) single pass
    // Three pointers: low, mid, high
    // 0s go to front (before low)
    // 1s stay in middle (between low and high)
    // 2s go to end (after high)
    //
    // In interviews always mention:
    // "I can do this in O(n) single pass using Dutch National Flag"
    // ============================================================

    static void sortColors(int[] nums) {

        // MY SOLUTION — insertion sort
        for (int i = 0; i <= nums.length - 2; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                } else {
                    break;
                }
            }
        }

        /*
        BETTER SOLUTION — Dutch National Flag O(n):

        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);  // 0 goes to front
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;                 // 1 already in correct section
            } else {
                swap(nums, mid, high); // 2 goes to end
                high--;
                // don't increment mid — swapped element needs checking
            }
        }
        */
    }


    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 1, 2};

//        bubbleSort(arr);
//        selectionSort(arr);
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}