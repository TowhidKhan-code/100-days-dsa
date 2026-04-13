// ============================================================
// DAY 51 - COUNT SORT
// ============================================================

package advance_sorting.Day51;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CountSort {

    /*
    ============================================================
    NON-COMPARISON BASED SORTING
    ============================================================
    Key Insight:
    All comparison-based sorts (merge, quick, heap) have a
    theoretical LOWER BOUND of O(n log n). You cannot beat this
    if sorting is done purely by comparing elements.

    Non-comparison sorts BYPASS this limit by exploiting
    specific properties of input data (integer values, digits).

    Three Non-Comparison Sorts:
    1. Count Sort   → O(n + k)    where k = value range
    2. Radix Sort   → O(d × n)    where d = number of digits
    3. Bucket Sort  → O(n + k)    distributes into range buckets

    When to Use Count Sort:
    ✓ Elements are non-negative integers (standard version)
    ✓ Range of values (max - min) is small
    ✓ Need stable sort
    ✓ Values are discrete and bounded

    Do NOT Use Count Sort When:
    ✗ Values are floating point
    ✗ Range is very large relative to n (wastes memory)
    ✗ Need in-place sorting
    ============================================================
    */

    /*
    ============================================================
    COUNT SORT — STANDARD (ARRAY VERSION)
    ============================================================
    Algorithm:
    1. Find maximum value in array
    2. Create count array of size max + 1
       → Index represents value, cell represents frequency
    3. Count each element: countArray[value]++
    4. Reconstruct sorted array:
       → For each index i from 0 to max:
         → Place value i exactly countArray[i] times

    Time:  O(n + k) where k = max value
    Space: O(k) for count array
    Stable: Yes
    In-place: No

    Limitation:
    - Fails for negative numbers (negative array index!)
    - Wastes memory for sparse arrays
      Example: arr={1, 1000000} needs 1M size array for 2 elements

    Visual Example:
    Input:      [6, 3, 10, 9, 2, 4, 9, 7]

    Count Array (index=value, cell=frequency):
    Index: 0  1  2  3  4  5  6  7  8  9  10
    Count: 0  0  1  1  1  0  1  1  0  2   1

    Reconstruct (read left to right):
    index 2 → put 2 (×1)
    index 3 → put 3 (×1)
    index 4 → put 4 (×1)
    index 6 → put 6 (×1)
    index 7 → put 7 (×1)
    index 9 → put 9 (×2)
    index 10→ put 10 (×1)

    Result: [2, 3, 4, 6, 7, 9, 9, 10] ✓
    ============================================================
    */
    public static void countSort(int[] arr) {
        // Edge case: null or single element already sorted
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Step 1: Find maximum value
        // Count array needs to be size max + 1
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }

        // Step 2: Build count array
        // countArray[i] = number of times value i appears
        int[] countArray = new int[max + 1]; // auto-initialized to 0

        for (int num : arr) {
            countArray[num]++; // index = value, increment its count
        }

        // Step 3: Reconstruct sorted array from count array
        int index = 0;
        for (int i = 0; i <= max; i++) {
            while (countArray[i] > 0) {
                arr[index] = i;     // place value i
                index++;
                countArray[i]--;    // decrement count
            }
        }
        // Since we iterate 0 → max, result is automatically sorted!
    }

    /*
    ============================================================
    COUNT SORT — HASHMAP VERSION (HANDLES NEGATIVES + SPARSE)
    ============================================================
    Why HashMap Version?
    - Standard count sort fails for negative numbers
    - Standard count sort wastes memory for sparse arrays
    - HashMap only allocates for values that actually appear

    Algorithm:
    1. Find min and max using streams
    2. Build frequency map: HashMap<value, count>
    3. Iterate from min to max
    4. Place each value exactly count times

    Trade-off:
    - Handles negatives ✓
    - Handles sparse arrays ✓
    - Slightly slower due to HashMap overhead
    - Still O(n + range) but range can be large

    Example with negatives:
    Input: [-3, 1, -1, 2, 0]
    min = -3, max = 2
    Map: {-3:1, 1:1, -1:1, 2:1, 0:1}
    Iterate -3 to 2: place each value once
    Result: [-3, -1, 0, 1, 2] ✓
    ============================================================
    */
    public static void countSortHash(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // Find range using streams
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        // Build frequency map — only stores values that appear
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Reconstruct from min to max
        int index = 0;
        for (int i = min; i <= max; i++) {
            int count = countMap.getOrDefault(i, 0); // 0 if value not present
            for (int j = 0; j < count; j++) {
                arr[index] = i;
                index++;
            }
        }
    }

    /*
    ============================================================
    EDGE CASES TO REMEMBER
    ============================================================
    1. Null or empty array        → return immediately
    2. Single element             → return immediately
    3. All same values            → count array has one non-zero entry
    4. Negative numbers           → use HashMap version
    5. Very large max value       → count array too big → use Radix Sort
    6. Sparse values              → HashMap version saves memory
    7. Duplicates                 → handled by count (place multiple times)

    COMMON MISTAKES:
    - countArray size is max, not max+1 → ArrayIndexOutOfBounds
    - Using standard version with negatives → ArrayIndexOutOfBounds
    - Confusing Count Sort with Bucket Sort
      Count Sort: one slot per distinct value
      Bucket Sort: distributes into ranges, sorts each bucket
    ============================================================
    */

    static void main(String[] args) {
        int[] arr = {6, 3, 10, 9, 2, 4, 9, 7};

        System.out.print("Original array: ");
        System.out.println(Arrays.toString(arr));

        // Test standard version
        // countSort(arr);

        // Test HashMap version (handles negatives and sparse)
        countSortHash(arr);

        System.out.print("Sorted array:   ");
        System.out.println(Arrays.toString(arr));
        // Output: [2, 3, 4, 6, 7, 9, 9, 10]
    }
}