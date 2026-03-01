package arrays;

public class Day10AdvancedBinarySearch {

    /*
    ============================================================
    DAY 10 - BINARY SEARCH PRACTICE PART 2
    Find Peak Element, Find in Mountain Array,
    Search in Rotated Sorted Array, Rotated with Duplicates,
    Rotation Count, Split Array Largest Sum
    LeetCode: 162, 1095, 33, 81, 410
    ============================================================
    */


    // ============================================================
    // FIND PIVOT — helper used across multiple problems
    // Pivot = index of largest element = point where rotation happened
    // Returns -1 if array is not rotated
    // ============================================================

    static int findPivot(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // mid element is greater than next — pivot found
            if (mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            // mid element is smaller than previous — pivot found
            if (mid > start && arr[mid] < arr[mid - 1]) {
                return mid;
            }
            // left half is unsorted — pivot is in left half
            if (arr[start] >= arr[mid]) {
                end = mid - 1;
            } else {
                // right half is unsorted — pivot is in right half
                start = mid + 1;
            }
        }
        return -1; // not rotated
    }


    // ============================================================
    // STANDARD BINARY SEARCH — helper used across multiple problems
    // ============================================================

    static int binarySearch(int[] arr, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid;
            }
            if (target > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }


    // ============================================================
    // LEETCODE 162 — Find Peak Element
    // Peak = element greater than both its neighbors
    // Array can have multiple peaks — find any one
    //
    // arr[mid] > arr[mid+1] → on descending side → peak is left or at mid
    // arr[mid] < arr[mid+1] → on ascending side  → peak is right of mid
    //
    // IMPORTANT: use start < end (not <=) so mid+1 never goes out of bounds
    // When loop ends start == end — both point to peak
    // ============================================================

    static int findPeakElement(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] > arr[mid + 1]) {
                end = mid;       // on descending side — peak at mid or left
            } else {
                start = mid + 1; // on ascending side — peak is right
            }
        }
        return end; // start == end at this point — both are peak
    }


    // ============================================================
    // FIND IN MOUNTAIN ARRAY — Logic (LC 1095 needs OOP/interface)
    // Will submit on LeetCode after OOP is covered
    //
    // APPROACH:
    // Step 1 — find peak index
    // Step 2 — binary search on ascending left side (0 to peak)
    // Step 3 — if not found, binary search on descending right side
    //
    // binarySearch handles both asc and desc using isAsc flag
    // ============================================================

    static int findInMountainArray(int[] arr, int target) {
        // step 1 — find peak
        int peak = peakIndex(arr);

        // step 2 — search ascending left side
        int firstTry = orderAgnosticSearch(arr, target, 0, peak);
        if (firstTry != -1) {
            return firstTry; // found on left side — return immediately
        }

        // step 3 — search descending right side
        return orderAgnosticSearch(arr, target, peak + 1, arr.length - 1);
    }

    static int peakIndex(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > arr[mid + 1]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    // handles both ascending and descending using isAsc flag
    static int orderAgnosticSearch(int[] arr, int target, int start, int end) {
        boolean isAsc = arr[start] < arr[end];

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            if (isAsc) {
                if (arr[mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                // descending — flip the comparison
                if (arr[mid] > target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }


    // ============================================================
    // LEETCODE 33 — Search in Rotated Sorted Array
    // Array was sorted then rotated — example: {4,5,6,7,0,1,2}
    //
    // APPROACH:
    // Step 1 — find pivot (largest element)
    // Step 2 — if not rotated, normal binary search
    // Step 3 — check if pivot itself is target
    // Step 4 — target >= nums[0] → search left of pivot
    //           target < nums[0]  → search right of pivot
    // ============================================================

    static int searchRotated(int[] nums, int target) {
        int pivot = findPivot(nums);

        // not rotated — normal binary search on full array
        if (pivot == -1) {
            return binarySearch(nums, target, 0, nums.length - 1);
        }

        // pivot itself is the target
        if (nums[pivot] == target) {
            return pivot;
        }

        // target is in left sorted half
        if (target >= nums[0]) {
            return binarySearch(nums, target, 0, pivot - 1);
        }

        // target is in right sorted half
        return binarySearch(nums, target, pivot + 1, nums.length - 1);
    }


    // ============================================================
    // LEETCODE 81 — Search in Rotated Array with Duplicates
    // Same as LC 33 but array has duplicate values
    //
    // EXTRA CASE: arr[start] == arr[mid] == arr[end]
    // Cannot determine which half is sorted
    // Solution — shrink both sides: start++, end--
    // ============================================================

    static boolean searchRotatedWithDuplicates(int[] nums, int target) {
        int pivot = findPivotWithDuplicates(nums);

        if (pivot == -1) {
            return binarySearchBool(nums, target, 0, nums.length - 1);
        }
        if (nums[pivot] == target) {
            return true;
        }
        if (target >= nums[0]) {
            return binarySearchBool(nums, target, 0, pivot - 1);
        }
        return binarySearchBool(nums, target, pivot + 1, nums.length - 1);
    }

    static int findPivotWithDuplicates(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid < end && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            if (mid > start && arr[mid] < arr[mid - 1]) {
                return mid;
            }

            // extra case for duplicates — cannot determine sorted half
            // shrink both sides
            if (arr[start] == arr[mid] && arr[end] == arr[mid]) {
                if (start < end && arr[start] > arr[start + 1]) {
                    return start;
                }
                start++;
                if (end > start && arr[end] < arr[end - 1]) {
                    return end;
                }
                end--;
            } else if (arr[start] < arr[mid] || (arr[start] == arr[mid] && arr[end] < arr[mid])) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    static boolean binarySearchBool(int[] arr, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return true;
            }
            if (target > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return false;
    }


    // ============================================================
    // ROTATION COUNT
    // How many times was the array rotated?
    // Rotation count = pivot index + 1
    // If not rotated — return 0
    //
    // WHY pivot + 1:
    // arr = {4,5,6,7,0,1,2} — rotated 4 times
    // pivot = index 3 (element 7)
    // rotation count = 3 + 1 = 4 ✅
    //
    // -1 + 1 = 0 handles not rotated automatically
    // No if check needed — clean one liner
    // ============================================================

    static int rotationCount(int[] arr) {
        return findPivot(arr) + 1; // -1 + 1 = 0 if not rotated
    }


    // ============================================================
    // LEETCODE 410 — Split Array Largest Sum
    // Split array into k subarrays minimizing the largest subarray sum
    //
    // KEY INSIGHT: binary search on the ANSWER not the array
    // Search space = [max element, sum of all elements]
    // max element = minimum possible answer (k == nums.length)
    // sum of all  = maximum possible answer (k == 1)
    //
    // For each mid — check if array can be split into <= k pieces
    // with max subarray sum = mid
    // ============================================================

    static int splitArray(int[] nums, int k) {
        int start = 0;
        int end = 0;

        // start = max element (minimum answer)
        // end = sum of all elements (maximum answer)
        for (int i = 0; i < nums.length; i++) {
            start = Math.max(start, nums[i]);
            end += nums[i];
        }

        return binarySearchSplit(nums, k, start, end);
    }

    static int binarySearchSplit(int[] arr, int k, int start, int end) {
        while (start < end) {
            // try mid as potential maximum subarray sum
            int mid = start + (end - start) / 2;

            // count how many pieces array splits into with max sum = mid
            int sum = 0;
            int pieces = 1;
            for (int num : arr) {
                if (sum + num > mid) {
                    // cannot add to current subarray — start new one
                    sum = num;
                    pieces++;
                } else {
                    sum += num;
                }
            }

            if (pieces <= k) {
                end = mid;       // valid split — try smaller max sum
            } else {
                start = mid + 1; // too many pieces — increase max sum
            }
        }
        return end; // start == end — this is the answer
    }


    public static void main(String[] args) {
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        int[] mountain = {0, 5, 10, 15, 12, 9, 3, 1};
        int[] splitArr = {7, 2, 5, 10, 8};

//        System.out.println(findPeakElement(mountain));       // 3
//        System.out.println(findInMountainArray(mountain, 9)); // 5
//        System.out.println(searchRotated(rotated, 0));        // 4
//        System.out.println(rotationCount(rotated));           // 4
//        System.out.println(splitArray(splitArr, 2));          // 18
    }
}