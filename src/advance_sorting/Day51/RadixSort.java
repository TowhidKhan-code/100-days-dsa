// ============================================================
// DAY 51 - RADIX SORT
// ============================================================

package advance_sorting.Day51;

import java.util.Arrays;

public class RadixSort {

    /*
    ============================================================
    RADIX SORT
    ============================================================
    Definition:
    Radix Sort is a non-comparison sorting algorithm that sorts
    integers digit by digit starting from the LEAST significant
    digit (ones) to the MOST significant digit (hundreds, thousands).
    At each digit position it uses a STABLE sort (count sort).

    Core Insight:
    Instead of sorting by full value, sort one digit at a time.
    After processing all digits, the array is fully sorted.

    Why LSD (Least Significant Digit) First?
    - If we sort by MSD first, we'd need to independently
      sort each group recursively (much more complex)
    - LSD: each pass builds on the previous one
    - After ones pass: correct relative order for ones digit
    - After tens pass: correct relative order for tens digit
      while PRESERVING ones digit ordering for ties

    Why STABLE inner sort is CRITICAL:
    - Stability means equal elements keep their relative order
    - After sorting by ones: 91 appears before 29 (both end in 1,9)
      Wait — 471 and 91 both end in 1, 471 comes first
    - When sorting by tens: both 8 and 29 have tens digit 0
    - Stability ensures 8 stays before 29 (correct!)
    - Without stability: previous pass ordering is destroyed

    Time:  O(d × (n + k)) where d = digits, k = base (10)
           For fixed-length integers: d is constant → effectively O(n)
    Space: O(n + k) — output array + count array
    Stable: Yes (because inner count sort is stable)
    In-place: No

    Handles negatives? No (standard version)
    Fix: Separate negatives, sort each half, merge

    When Radix Beats Comparison Sorts:
    d × (n + k) < n log n
    For 32-bit integers: at most 10 decimal digits
    10 × n vs n log n → Radix wins when n is large

    Radix Sort vs Count Sort:
    Count Sort: O(n + k) — fails when k (range) is huge
    Radix Sort: O(d × n) — works for large values with few digits
    ============================================================
    */

    /*
    ============================================================
    MAIN RADIX SORT DRIVER
    ============================================================
    How it works:
    - Find max to know how many digit positions exist
    - exp = 1 → ones digit (num/1 % 10)
    - exp = 10 → tens digit (num/10 % 10)
    - exp = 100 → hundreds (num/100 % 10)
    - Loop until exp > max (no more digits to process)

    Digit Extraction Formula: (arr[i] / exp) % 10
    Example — arr[i] = 471:
      exp=1:   471/1=471,  471%10=1   → ones digit = 1
      exp=10:  471/10=47,  47%10=7    → tens digit = 7
      exp=100: 471/100=4,  4%10=4     → hundreds = 4
    ============================================================
    */
    public static void radixSort(int[] arr) {
        // Find maximum value to determine number of digit passes needed
        int max = Arrays.stream(arr).max().getAsInt();

        // Process each digit position: ones → tens → hundreds → ...
        // Loop ends when exp > max (no more significant digits)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp); // Stable sort by current digit
        }
    }

    /*
    ============================================================
    STABLE COUNT SORT BY DIGIT POSITION
    ============================================================
    This is a modified count sort that sorts ONLY by one digit
    at the position given by exp.

    Three Phases:

    PHASE 1 — COUNT:
    Count how many numbers have each digit (0-9) at position exp.
    count[d]++ for each element whose digit = d

    PHASE 2 — CUMULATIVE (PREFIX SUM):
    Convert raw counts to positions.
    count[i] += count[i-1]
    Now count[d] = total elements with digit 0,1,...,d
    This tells us where in output each group ENDS.

    Example:
    raw count  = [1, 2, 0, 1, 0, 0, 1, 0, 1, 1]
    digit:        0  1  2  3  4  5  6  7  8  9

    cumulative = [1, 3, 3, 4, 4, 4, 5, 5, 6, 7]
    → Elements with digit 0: end at position 0 (1 element, index 0)
    → Elements with digit 1: end at position 2 (2 elements, indices 1-2)
    → Elements with digit 3: end at position 3 (1 element, index 3)

    PHASE 3 — BUILD OUTPUT (RIGHT TO LEFT — STABILITY!):
    Iterate input RIGHT TO LEFT.
    For each element: place at output[count[digit] - 1]
    Then decrement count[digit].

    WHY RIGHT TO LEFT?
    → Ensures stability (relative order of equal elements preserved)
    → Elements appearing LATER in input with same digit
      get placed at LATER positions in output
    → This preserves the ordering from previous passes
    → Going left to right would REVERSE relative order (unstable!)

    Example without stability:
    After pass 1: [471, 91, 83, 36, 8, 29]
    In pass 2, both 8 and 29 have tens digit 0.
    Correct: 8 before 29 (from pass 1 order)
    Stable (right to left): processes 29 first, places at pos 1
                            processes 8 next, places at pos 0
                            Result: 8 at 0, 29 at 1 ✓
    ============================================================
    */
    public static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];  // Sorted output for this pass
        int[] count = new int[10];  // Count for each digit 0-9

        Arrays.fill(count, 0);

        // PHASE 1: Count occurrences of each digit at position exp
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
            // (arr[i] / exp) extracts relevant digit group
            // % 10 gets the single digit we care about
        }

        System.out.println("\nCount array for exp=" + exp + ": " + Arrays.toString(count));

        // PHASE 2: Convert to cumulative count (prefix sum)
        // count[i] now = number of elements with digit <= i
        // This gives us the ENDING POSITION of each digit group
        for (int i = 1; i < 10; i++) {
            count[i] = count[i] + count[i - 1];
        }

        System.out.println("Cumulative count: " + Arrays.toString(count));

        // PHASE 3: Build output array RIGHT TO LEFT (for stability)
        // Right to left ensures elements with same digit keep
        // their relative order from previous passes
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;

            // Place at correct position (count[digit]-1 because 0-indexed)
            output[count[digit] - 1] = arr[i];

            // Decrement so next element with same digit goes one position earlier
            count[digit]--;
        }

        System.out.println("Output array:     " + Arrays.toString(output));

        // Copy sorted output back to original array
        System.arraycopy(output, 0, arr, 0, n);
    }

    /*
    ============================================================
    COMPLETE TRACE — [29, 83, 471, 36, 91, 8]
    ============================================================
    max = 471 → 3 passes needed (exp = 1, 10, 100)

    PASS 1 (exp=1, ONES digit):
    Extract ones: 29→9, 83→3, 471→1, 36→6, 91→1, 8→8

    count raw:  [0,2,0,1,0,0,1,0,1,1]  (digit 1 appears twice: 471,91)
    cumulative: [0,2,2,3,3,3,4,4,5,6]

    Fill right to left:
    i=5: arr[5]=8,   digit=8, pos=5-1=4, output[4]=8
    i=4: arr[4]=91,  digit=1, pos=2-1=1, output[1]=91
    i=3: arr[3]=36,  digit=6, pos=4-1=3, output[3]=36
    i=2: arr[2]=471, digit=1, pos=1-1=0, output[0]=471
    i=1: arr[1]=83,  digit=3, pos=3-1=2, output[2]=83
    i=0: arr[0]=29,  digit=9, pos=6-1=5, output[5]=29

    After pass 1: [471, 91, 83, 36, 8, 29]

    PASS 2 (exp=10, TENS digit):
    Extract tens: 471→7, 91→9, 83→8, 36→3, 8→0, 29→2

    count raw:  [1,0,1,1,0,0,0,1,1,1]
    cumulative: [1,1,2,3,3,3,3,4,5,6]

    Fill right to left:
    i=5: arr[5]=29,  digit=2, pos=2-1=1, output[1]=29
    i=4: arr[4]=8,   digit=0, pos=1-1=0, output[0]=8
    i=3: arr[3]=36,  digit=3, pos=3-1=2, output[2]=36
    i=2: arr[2]=83,  digit=8, pos=5-1=4, output[4]=83
    i=1: arr[1]=91,  digit=9, pos=6-1=5, output[5]=91
    i=0: arr[0]=471, digit=7, pos=4-1=3, output[3]=471

    After pass 2: [8, 29, 36, 471, 83, 91]

    PASS 3 (exp=100, HUNDREDS digit):
    Extract hundreds: 8→0, 29→0, 36→0, 471→4, 83→0, 91→0

    count raw:  [5,0,0,0,1,0,0,0,0,0]
    cumulative: [5,5,5,5,6,6,6,6,6,6]

    Fill right to left:
    i=5: arr[5]=91,  digit=0, pos=5-1=4, output[4]=91
    i=4: arr[4]=83,  digit=0, pos=4-1=3, output[3]=83
    i=3: arr[3]=471, digit=4, pos=6-1=5, output[5]=471
    i=2: arr[2]=36,  digit=0, pos=3-1=2, output[2]=36
    i=1: arr[1]=29,  digit=0, pos=2-1=1, output[1]=29
    i=0: arr[0]=8,   digit=0, pos=1-1=0, output[0]=8

    After pass 3: [8, 29, 36, 83, 91, 471] ✓ SORTED!
    ============================================================
    */

    /*
    ============================================================
    EDGE CASES
    ============================================================
    1. All same values      → all go to same digit bucket, stable
    2. Single element       → exp loop runs, count sort trivially works
    3. Numbers diff lengths → shorter get 0 for missing higher digits
                              8 at tens place = 0 → sorts before 83 ✓
    4. Negative numbers     → standard radix FAILS
                              Fix: separate, sort each, merge
    5. Very large numbers   → more passes, still O(d × n)
    6. Array with zeros     → (0/exp)%10 = 0, handled correctly

    COMMON MISTAKES:
    - Forgetting right to left in Phase 3 → unstable, wrong result
    - count[digit]-1 not count[digit] → off by one error
    - Not copying output back with arraycopy
    - Using exp > max instead of max/exp > 0 as loop condition
    ============================================================
    */

    static void main(String[] args) {
        int[] arr = {29, 83, 471, 36, 91, 8};

        System.out.print("Original array: ");
        System.out.println(Arrays.toString(arr));

        radixSort(arr);

        System.out.print("Sorted array:   ");
        System.out.println(Arrays.toString(arr));
        // Output: [8, 29, 36, 83, 91, 471]
    }
}