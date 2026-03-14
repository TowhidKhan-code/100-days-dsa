package recursion;

import java.util.Arrays;

public class Day23QuickSort {

    // ══════════════════════════════════════════════════════════════════════
    // Quick Sort — Hoare's Partition Scheme
    // ══════════════════════════════════════════════════════════════════════
    //
    // HOARE vs LOMUTO:
    //   Lomuto: one pointer, pivot always at end, simpler but more swaps
    //   Hoare:  two pointers moving toward each other, fewer swaps on average
    //
    // KEY IDEA:
    //   1. Pick a pivot
    //   2. Partition: move all elements <= pivot to left, >= pivot to right
    //   3. Recursively sort left and right partitions
    //   4. Base case: low >= high (0 or 1 elements — already sorted)
    //
    // PIVOT STRATEGIES (all implemented below):
    //   Middle element — safest default, avoids worst case on sorted arrays
    //   Last element   — Lomuto style, worst case O(n²) on sorted input
    //   First element  — worst case O(n²) on sorted input
    //   Random element — best for unknown input, guaranteed average O(n log n)
    static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;                                    // base: 0 or 1 elements, already sorted
        }
        int s = low;
        int e = high;

        // PIVOT — middle element (safest default)
        int m = s + (e - s) / 2;
        int pivot = arr[m];

        // PIVOT — last element
        // int pivot = arr[e];

        // PIVOT — first element
        // int pivot = arr[s];

        // PIVOT — random element
        // int random = low + (int)(Math.random() * (high - low + 1));
        // int pivot = arr[random];

        // ── Hoare's Partition ──────────────────────────────────────────
        // Two pointers s and e move toward each other
        // s finds element >= pivot from left side
        // e finds element <= pivot from right side
        // Swap them, then keep moving inward
        // When s and e cross: left side has elements <= pivot
        //                     right side has elements >= pivot
        while (s <= e) {
            while (arr[s] < pivot) s++;               // move s right until arr[s] >= pivot
            while (arr[e] > pivot) e--;               // move e left  until arr[e] <= pivot
            if (s <= e) {
                int temp = arr[s];
                arr[s] = arr[e];
                arr[e] = temp;
                s++;                                  // move both pointers after swap
                e--;
            }
        }
        // After loop: e is end of left partition, s is start of right partition
        sort(arr, low, e);                            // sort left partition  [low, e]
        sort(arr, s, high);                           // sort right partition [s, high]
    }


    public static void main(String[] args) {
        System.out.println("=== Quick Sort — Middle Pivot ===");
        int[] arr = {5, 2, 6, 8, 1, 4, 9, 0, 3, 7};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));     // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        System.out.println("\n=== Already Sorted — Middle Pivot (no worst case) ===");
        int[] sorted = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        sort(sorted, 0, sorted.length - 1);
        System.out.println(Arrays.toString(sorted));  // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        System.out.println("\n=== Reverse Sorted ===");
        int[] reverse = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        sort(reverse, 0, reverse.length - 1);
        System.out.println(Arrays.toString(reverse)); // [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}