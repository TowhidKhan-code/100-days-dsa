package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13CyclicSort {

    /*
    ============================================================
    DAY 13 - CYCLIC SORT
    Cyclic Sort Pattern + 6 LeetCode Problems
    LC: 268, 448, 287, 442, 645, 41
    ============================================================
    */


    // ============================================================
    // WHAT IS CYCLIC SORT?
    // Works on arrays containing numbers in range 1 to n
    // Each number belongs at index: number - 1
    // Place each element at its correct index by swapping
    //
    // PATTERN:
    // int correct = arr[i] - 1  → where this element belongs
    // if arr[i] != arr[correct] → swap to correct position
    // else                      → already correct, move forward
    //
    // Time: O(n) — each element swapped at most once
    // Space: O(1)
    // ============================================================


    // ============================================================
    // HELPER — swap two elements
    // Reused across ALL programs below
    // ============================================================

    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }


    // ============================================================
    // BASIC CYCLIC SORT
    // Sort array containing numbers 1 to n
    // Each number goes to index number-1
    // ============================================================

    static void cyclicSort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            int correct = arr[i] - 1; // where arr[i] should be
            if (arr[i] != arr[correct]) {
                swap(arr, i, correct); // send to correct position
            } else {
                i++; // already at correct position — move forward
            }
        }
    }


    // ============================================================
    // CYCLIC SORT PATTERN — ALL 6 PROBLEMS FOLLOW THIS TEMPLATE
    //
    // Step 1 — Cyclic sort with small condition variation
    // Step 2 — Loop through sorted array
    // Step 3 — Index where nums[j] != j or j+1 is the answer
    // ============================================================


    // ============================================================
    // LEETCODE 268 — Missing Number
    // Array has n numbers in range 0 to n — one is missing
    //
    // KEY: nums[i] < n check prevents index out of bounds
    // n itself cannot be placed (no index n) — skip it
    // After sort: first index where nums[j] != j is missing
    // If none found — n is missing
    // ============================================================

    static int missingNumber(int[] nums) {
        int i = 0;
        int n = nums.length;

        while (i < n) {
            int correct = nums[i]; // range 0 to n — correct index IS nums[i]
            if (nums[i] < n && nums[i] != nums[correct]) {
                swap(nums, i, correct); // using helper
            } else {
                i++;
            }
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j) {
                return j;
            }
        }
        return n;
    }


    // ============================================================
    // LEETCODE 448 — Find All Numbers Disappeared in Array
    // Array has n numbers in range 1 to n — some are missing
    //
    // After sort: every index j where nums[j] != j+1
    // means j+1 is missing — collect all such j+1
    // ============================================================

    static List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0;
        int n = nums.length;
        ArrayList<Integer> ans = new ArrayList<>();

        while (i < n) {
            int correct = nums[i] - 1;
            if (nums[i] != nums[correct]) {
                swap(nums, i, correct); // using helper
            } else {
                i++;
            }
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                ans.add(j + 1);
            }
        }
        return ans;
    }


    // ============================================================
    // LEETCODE 287 — Find the Duplicate Number
    // Array has n+1 numbers in range 1 to n — one is duplicate
    //
    // KEY: when nums[i] == nums[correct] AND not in right place
    // Cannot swap — would loop forever
    // This means nums[i] is the duplicate — return immediately
    // ============================================================

    static int findDuplicate(int[] nums) {
        int i = 0;
        int n = nums.length;

        while (i < n) {
            if (nums[i] != i + 1) {
                int correct = nums[i] - 1;
                if (nums[i] != nums[correct]) {
                    swap(nums, i, correct); // using helper
                } else {
                    return nums[i]; // duplicate found
                }
            } else {
                i++;
            }
        }
        return -1;
    }


    // ============================================================
    // LEETCODE 442 — Find All Duplicates in Array
    // Array has n numbers in range 1 to n — some appear twice
    //
    // After sort: index where nums[j] != j+1 means nums[j] is duplicate
    // nums[j] is sitting at wrong index — already placed correct copy
    // ============================================================

    static List<Integer> findDuplicates(int[] nums) {
        int i = 0;
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();

        while (i < n) {
            int correct = nums[i] - 1;
            if (nums[i] != nums[correct]) {
                swap(nums, i, correct); // using helper
            } else {
                i++;
            }
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                ans.add(nums[j]);
            }
        }
        return ans;
    }


    // ============================================================
    // LEETCODE 645 — Set Mismatch
    // Array has n numbers 1 to n — one duplicated, one missing
    //
    // After sort: first index j where nums[j] != j+1 gives BOTH:
    // nums[j] = duplicate, j+1 = missing
    // One loop, two answers — elegant
    // ============================================================

    static int[] findErrorNums(int[] nums) {
        int i = 0;
        int n = nums.length;

        while (i < n) {
            int correct = nums[i] - 1;
            if (nums[i] != nums[correct]) {
                swap(nums, i, correct); // using helper
            } else {
                i++;
            }
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                return new int[]{nums[j], j + 1}; // {duplicate, missing}
            }
        }
        return new int[]{-1, -1};
    }


    // ============================================================
    // LEETCODE 41 — First Missing Positive (HARD)
    // Find smallest missing positive integer
    // Array can have negatives, zeros, duplicates, large numbers
    //
    // KEY INSIGHT: answer is always in range 1 to n+1
    // Only place numbers in range 1 to n — ignore everything else
    // nums[i] > 0 && nums[i] <= n — valid range check
    // After sort: first index where nums[j] != j+1 is answer
    // If all correct — answer is n+1
    // ============================================================

    static int firstMissingPositive(int[] nums) {
        int i = 0;
        int n = nums.length;

        while (i < n) {
            int correct = nums[i] - 1;
            if (nums[i] > 0 && nums[i] <= n && nums[i] != nums[correct]) {
                swap(nums, i, correct); // using helper
            } else {
                i++;
            }
        }

        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }
        return n + 1;
    }


    public static void main(String[] args) {
        int[] arr = {5, 3, 4, 6, 1, 2};
        cyclicSort(arr);
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4, 5, 6]

//        System.out.println(missingNumber(new int[]{3, 0, 1}));              // 2
//        System.out.println(findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1})); // [5,6]
//        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));        // 2
//        System.out.println(findDuplicates(new int[]{4,3,2,7,8,2,3,1}));     // [2,3]
//        System.out.println(Arrays.toString(findErrorNums(new int[]{1,2,2,4}))); // [2,3]
//        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));   // 2
    }
}