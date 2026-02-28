package arrays;

public class Day9BinarySearchPractice {

    /*
    ============================================================
    DAY 9 - BINARY SEARCH PRACTICE PART 1
    Ceiling, Floor, Infinite Array, Peak Index
    LeetCode: 744, 34, 852
    ============================================================
    */


    // ============================================================
    // PROGRAM 1 — Ceiling of a Number
    // Return index of smallest element >= target
    // If target > largest element — return -1 (no ceiling exists)
    //
    // WHY return start after loop:
    // When loop ends, start > end
    // start points to smallest element >= target
    // ============================================================

    // return the index of smallest number >= target
    static int ceiling(int[] nums, int target) {

        // if the target is greater than the greatest element in the array
        if (target > nums[nums.length - 1]) {
            return -1;
        }

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
        return start; // start points to smallest element >= target
    }


    // ============================================================
    // PROGRAM 2 — Floor of a Number
    // Return index of greatest element <= target
    // If target < smallest element — return -1 (no floor exists)
    //
    // WHY return end after loop:
    // When loop ends, start > end
    // end points to greatest element <= target
    //
    // SYMMETRY:
    // Ceiling → return start after loop
    // Floor   → return end after loop
    // ============================================================

    // return the index of greatest number <= target
    static int floor(int[] nums, int target) {

        // if the target is smaller than the smallest element in the array
        if (target < nums[0]) {
            return -1;
        }

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
        return end; // end points to greatest element <= target
    }


    // ============================================================
    // PROGRAM 3 — Position of Element in Infinite Sorted Array
    // Cannot use .length — array is infinite
    //
    // STEP 1: find range by doubling window size
    //   [0,1] → [2,5] → [6,13] → [14,29]...
    //   stop when target <= nums[end]
    //
    // STEP 2: binary search within that range
    // ============================================================

    static int answer(int[] nums, int target) {
        int start = 0;
        int end = 1;

        // keep doubling window until target is within range
        while (target > nums[end]) {
            int newStart = end + 1;               // new range starts after current end
            end = end + (end - start + 1) * 2;   // double the window size
            start = newStart;
        }
        return positionInInfiniteArray(nums, target, start, end);
    }

    // standard binary search within given range
    static int positionInInfiniteArray(int[] nums, int target, int start, int end) {
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


    // ============================================================
    // LEETCODE 852 — Peak Index in Mountain Array
    // Mountain array goes up then comes down
    // Find index of peak — element larger than both neighbors
    //
    // arr[mid] > arr[mid+1] → on descending side → peak is left
    // arr[mid] < arr[mid+1] → on ascending side  → peak is right
    //
    // Always use start = mid+1 not start = start+1
    // ============================================================

    static int peakIndexInMountainArray(int[] arr) {

        // brute force approach — O(n)
        /*
        int index = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[i-1] > arr[i]){
                index = i-1;
                break;
            }
        }
        return index;
        */

        // binary search — O(log n)
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] > arr[mid + 1]) {
                end = mid - 1;   // on descending side — peak is left
            } else {
                start = mid + 1; // on ascending side — peak is right
            }
        }
        return start;
    }


    // ============================================================
    // LEETCODE 34 — Find First and Last Position in Sorted Array
    // Reuse firstOccurrence and lastOccurrence
    // Empty array check at top
    // Medium difficulty — solved independently ✅
    // ============================================================

    static int[] searchRange(int[] nums, int target) {
        int first;
        int last;

        if (nums.length == 0) {
            first = -1;
            last = -1;
        } else {
            first = firstOccurence(nums, target);
            last = lastOccurence(nums, target);
        }

        int[] result = {first, last};
        return result;
    }

    static int firstOccurence(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int result = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                result = mid;  // save — keep searching left
                end = mid - 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }

    static int lastOccurence(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        int result = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                result = mid;   // save — keep searching right
                start = mid + 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    }


    // ============================================================
    // LEETCODE 744 — Find Smallest Letter Greater Than Target
    // Find smallest letter strictly greater than target
    // Array wraps around — if no letter found return first letter
    //
    // KEY TRICK: letters[start % letters.length]
    // If start goes out of bounds (target > all letters)
    // % wraps it back to index 0 — the first letter
    // Cleaner than writing a separate if check
    // ============================================================

    static char nextGreatestLetter(char[] letters, char target) {
        // same as the last return if no result is found
        // if(target > letters[letters.length - 1]){
        //     return letters[0];
        // }

        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (letters[mid] > target) {
                end = mid - 1;   // mid could be answer — check left too
            } else {
                start = mid + 1; // mid not greater than target — go right
            }
        }
        // start % letters.length handles wrap around automatically
        // if start == letters.length → wraps to 0 (first letter)
        return letters[start % letters.length];
    }


    public static void main(String[] args) {
//        int[] arr = {2, 3, 5, 6, 7, 8, 10, 11, 12, 15, 20, 23, 30};
//        int target = 14;
//        System.out.println(ceiling(arr, target));
//        System.out.println(floor(arr, target));

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
        int target = 20;
        System.out.println(answer(nums, target)); // prints 19

//        int[] mountain = {0, 5, 10, 15, 12, 9, 3, 1};
//        System.out.println(peakIndexInMountainArray(mountain)); // 3
    }
}
