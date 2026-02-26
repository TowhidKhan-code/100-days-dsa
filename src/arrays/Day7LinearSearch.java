package arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class Day7LinearSearch {

    /*
    ============================================================
    DAY 7 - LINEAR SEARCH
    Basic Search, String Array Search, 2D Array Search,
    All Occurrences, Search in Range, Find Minimum Index
    ============================================================
    */


    // ============================================================
    // WHAT IS LINEAR SEARCH?
    // Check every element one by one from start to end
    // Until target is found or end of array is reached
    // Simplest searching algorithm
    //
    // WHEN TO USE:
    // Array is unsorted
    // Array is small
    // Searching only once
    //
    // TIME COMPLEXITY:
    // Best case  → O(1) — target is at first position
    // Worst case → O(n) — target at last position or not found
    // Average    → O(n)
    // ============================================================


    // ============================================================
    // PROGRAM 1 — Basic Linear Search
    // Return index if found, -1 if not found
    // -1 is the standard convention for "not found"
    // ============================================================

    static int basicLinearSearch(int[] arr, int target) {
        if (arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }


    // ============================================================
    // PROGRAM 2 — Search in String Array
    // IMPORTANT: use .equals() NOT == for String comparison
    // == compares memory addresses (references)
    // .equals() compares actual content — always use for Strings
    // ============================================================

    static int searchingInStringArray(String[] names, String target) {
        if (names.length == 0) {
            return -1;
        }
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }


    // ============================================================
    // PROGRAM 3 — Search in 2D Array
    // Return {row, col} if found, {-1, -1} if not found
    // Named variables row and col instead of i and j — more readable
    // ============================================================

    static int[] searchingIn2DArray(int[][] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        for (int row = 0; row < nums.length; row++) {
            for (int col = 0; col < nums[row].length; col++) {
                if (nums[row][col] == target) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1};
    }


    // ============================================================
    // PROGRAM 4 — Find All Occurrences
    // Return ALL indices where target appears
    //
    // KEY INSIGHT: use ArrayList because we don't know how many
    // occurrences there will be upfront
    // int[] needs fixed size — ArrayList grows dynamically
    // Convert ArrayList to int[] at the end to return
    //
    // PATTERN TO REMEMBER:
    // Unknown result size = use ArrayList, convert to array at end
    // ============================================================

    static int[] allOccurrences(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1};
        }

        ArrayList<Integer> occurrences = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                occurrences.add(i);
            }
        }

        if (occurrences.isEmpty()) {
            return new int[]{-1};
        }

        // convert ArrayList to int[] — ArrayList cant be returned as int[]
        int[] result = new int[occurrences.size()];
        for (int i = 0; i < occurrences.size(); i++) {
            result[i] = occurrences.get(i);
        }
        return result;
    }


    // ============================================================
    // PROGRAM 5 — Search in Range
    // Search only between index start and end
    // ============================================================

    static int searchInRange(int[] nums, int target, int start, int end) {
        if (nums.length == 0) {
            return -1;
        }
        for (int i = start; i <= end; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }


    // ============================================================
    // PROGRAM 6 — Find Minimum and Its Index
    // Returns both minimum value AND its index as int[]
    // Start min at arr[0] — NOT 0 (handles negative numbers)
    // Loop from index 1 — already checked index 0
    // ============================================================

    static int[] findMin(int[] nums) {
        if (nums.length == 0) {
            return new int[]{-1};
        }
        int min = nums[0];
        int index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
                index = i;
            }
        }
        return new int[]{min, index};
    }


    // ============================================================
    // LEETCODE PROBLEMS — DAY 7
    // ============================================================

    // 1920. Build Array from Permutation
    // ans[i] = nums[nums[i]] — use element value as index
    static int[] buildArray(int[] nums) {
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums[i]];
        }
        return ans;
    }


    // 1480. Running Sum of 1D Array
    // Solution 1 — separate array, does not modify input
    static int[] runningSum(int[] nums) {
        int[] ans = new int[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans[i] = sum;
        }
        return ans;
    }

    // Solution 2 — modifies input, O(1) extra space
    // In interviews mention both — shows you think about space
    // "I can solve with O(n) space or O(1) space by modifying input"
    static int[] runningSumOptimal(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            nums[i] = sum;
        }
        return nums;
    }


    // 1929. Concatenation of Array
    // Cleaner solution — second copy starts exactly at index n
    // No j variable, no if/else needed
    static int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2 * n];
        for (int i = 0; i < n; i++) {
            ans[i] = nums[i];     // first copy
            ans[i + n] = nums[i]; // second copy starts at n
        }
        return ans;
    }


    // 1672. Richest Customer Wealth
    // 2D array — sum each row, track maximum
    // reset sum = 0 inside outer loop — same pattern as row wise sum
    static int maximumWealth(int[][] accounts) {
        int maxSum = 0;
        for (int i = 0; i < accounts.length; i++) {
            int sum = 0; // reset for each customer — critical
            for (int j = 0; j < accounts[i].length; j++) {
                sum += accounts[i][j];
            }
            if (sum > maxSum) {
                maxSum = sum;
            }
        }
        return maxSum;
    }


    // 1295. Find Numbers with Even Number of Digits
    // Separated digit counting into checkEven method — clean design
    // Each method does ONE job — separation of concerns
    static int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (checkEven(num)) {
                count++;
            }
        }
        return count;
    }

    static boolean checkEven(int num) {
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10; // remove last digit each time
        }
        return count % 2 == 0;
    }


    // 28. Find Index of First Occurrence in String
    // At each i, check if window of needle length matches needle
    // Loop to haystack.length - needle.length — needle cant start where it wont fit
    // WRONG approach: haystack.contains(needle) always returns same result — ignores i
    static int strStr(String haystack, String needle) {
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }


    // 35. Search Insert Position
    // First element >= target is either target or insert position
    // If nothing found, target goes at end — return nums.length
    static int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }


    public static void main(String[] args) {
        int[] nums = {12, 4, 5, 6, 7, 91, 34, -1};
        String[] names = {"Towhid", "Nida", "Nilu", "TKhan", "Thor"};
        int[][] numbers = {
                {1, 2, 6},
                {13, 4, 5},
                {56, 8, 1}
        };

//        System.out.println(basicLinearSearch(nums, 91));
//        System.out.println(searchingInStringArray(names, "TKhan"));
//        System.out.println(Arrays.toString(searchingIn2DArray(numbers, 5)));
//        System.out.println(Arrays.toString(allOccurrences(nums, 6)));
//        System.out.println(searchInRange(nums, 91, 3, 6));
//        System.out.println(Arrays.toString(findMin(nums)));
    }
}