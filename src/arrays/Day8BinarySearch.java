package arrays;

public class Day8BinarySearch {

    /*
    ============================================================
    DAY 8 - BINARY SEARCH
    Iterative Binary Search, First Occurrence,
    Last Occurrence, Count Occurrences
    ============================================================
    */


    // ============================================================
    // WHAT IS BINARY SEARCH?
    // Instead of checking every element — divide search space in half
    // Each step eliminates half the remaining elements
    // ONLY works on SORTED arrays
    //
    // TIME COMPLEXITY:
    // Best case  → O(1)     — target found at mid on first check
    // Worst case → O(log n) — halving search space each time
    // Array of 1 billion elements → maximum 30 comparisons only
    //
    // vs Linear Search O(n) — 1 billion comparisons worst case
    // ============================================================


    // ============================================================
    // PROGRAM 1 — Basic Binary Search (Iterative)
    // Search for target in sorted array
    // Return index if found, -1 if not found
    //
    // IMPORTANT: always use low + (high - low) / 2 for mid
    // NEVER use (low + high) / 2
    // Reason: if low and high are both very large ints
    // low + high can OVERFLOW int range
    // low + (high - low) / 2 gives same result but never overflows
    // ============================================================

    static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2; // safe mid formula

            if (arr[mid] == target) {
                return mid;        // found — return index
            } else if (arr[mid] < target) {
                start = mid + 1;   // target is in right half — go right
            } else {
                end = mid - 1;     // target is in left half — go left
            }
        }
        return -1; // not found
    }

    // ============================================================
    // ORDER AGNOSTIC BINARY SEARCH
    // When you don't know if array is sorted ascending or descending
    // Check first and last element to determine order
    // Then apply normal binary search accordingly
    // ============================================================

    static int orderAgnosticBinarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        // check if array is ascending or descending
        boolean isAsc = arr[start] < arr[end];

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (isAsc) {
                if (arr[mid] < target) {
                    start = mid + 1; // go right
                } else {
                    end = mid - 1;   // go left
                }
            } else {
                if (arr[mid] > target) {
                    start = mid + 1; // go right — descending
                } else {
                    end = mid - 1;   // go left — descending
                }
            }
        }
        return -1;
    }

    // ============================================================
    // PROGRAM 2 — First Occurrence
    // Array may have duplicates — return FIRST occurrence index
    //
    // MISTAKE TO AVOID: returning immediately when arr[mid] == target
    // You might land on second or third duplicate — not the first
    //
    // FIX: when target found — save index, keep searching LEFT
    // There might be an earlier occurrence on the left side
    // ============================================================

    static int firstOccurrence(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int result = -1; // stores best answer found so far

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                result = mid;  // save this index — might not be first
                end = mid - 1; // keep searching LEFT for earlier occurrence
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }


    // ============================================================
    // PROGRAM 3 — Last Occurrence
    // Array may have duplicates — return LAST occurrence index
    //
    // Same idea as first occurrence but search RIGHT instead of left
    // When target found — save index, keep searching RIGHT
    // There might be a later occurrence on the right side
    // ============================================================

    static int lastOccurrence(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int result = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                result = mid;   // save this index — might not be last
                start = mid + 1; // keep searching RIGHT for later occurrence
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }


    // ============================================================
    // PROGRAM 4 — Count Occurrences
    // Count how many times target appears in sorted array
    //
    // CORRECT APPROACH: use first and last occurrence
    // count = lastOccurrence - firstOccurrence + 1
    // This is O(log n) — two binary searches
    //
    // FORMULA TO REMEMBER:
    // count = last - first + 1
    // ============================================================

    static int countOccurrence(int[] arr, int target) {
        int first = firstOccurrence(arr, target);

        // if first not found — target doesnt exist in array
        if (first == -1) {
            return 0;
        }

        int last = lastOccurrence(arr, target);

        // count = distance between first and last + 1
        return last - first + 1;
    }


    // ============================================================
    // LEETCODE 704 — Binary Search
    // Direct application of basic binary search
    // ============================================================

    static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 8, 9, 17, 28, 36, 36, 36, 47, 68};
        int target = 36;

//        System.out.println(binarySearch(arr, 17));
//        System.out.println(firstOccurrence(arr, target));  // 8
//        System.out.println(lastOccurrence(arr, target));   // 10
//        System.out.println(countOccurrence(arr, target));  // 3
    }
}