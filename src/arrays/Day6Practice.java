package arrays;

import java.util.Arrays;
import java.util.Scanner;

public class Day6Practice {

    /*
    ============================================================
    DAY 6 - ARRAY PRACTICE PROGRAMS
    Array Input/Output, Max/Min, Sum/Average, Reverse,
    Check Sorted, Count Occurrences, Linear Search,
    2D Array, Row Wise Sum, Move Zeros
    ============================================================
    */


    // ============================================================
    // PROGRAM 1 — Array Input and Output
    // Take size and elements from user
    // Print using both normal for loop and for-each loop
    // ============================================================

    static void arrayInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size: ");
        int size = sc.nextInt();
        int[] numbersArray = new int[size];

        // take input
        for (int i = 0; i < size; i++) {
            numbersArray[i] = sc.nextInt();
        }

        // output using normal for loop — use when you need index
        System.out.println("Normal for loop:");
        for (int i = 0; i < size; i++) {
            System.out.println(numbersArray[i]);
        }

        // output using for-each loop — cleaner when you just need values
        System.out.println("For-each loop:");
        for (int nums : numbersArray) {
            System.out.println(nums);
        }
    }


    // ============================================================
    // PROGRAM 2 — Find Maximum and Minimum
    // Start max and min at arr[0] — NOT 0
    // Starting at 0 gives wrong answer for negative number arrays
    // Check empty array first to avoid errors
    // ============================================================

    static void findMaxAndMin(int[] arr) {
        // edge case — empty array, nothing to find
        if (arr.length == 0) {
            return;
        }

        // start with first element as both min and max
        // this handles negative numbers correctly
        int min = arr[0];
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i]; // found smaller — update min
            }
            if (arr[i] > max) {
                max = arr[i]; // found larger — update max
            }
        }

        System.out.println("Minimum is: " + min);
        System.out.println("Maximum is: " + max);
    }


    // ============================================================
    // PROGRAM 3 — Sum and Average
    // 1.0 * sum forces double division without casting
    // avg = (double) sum / arr.length also works
    // ============================================================

    static void printSumAndAverage(int[] arr) {
        int sum = 0;
        double avg;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // add each element to sum
        }

        // 1.0 * sum converts sum to double before dividing
        // without this int/int gives integer result — decimals lost
        avg = 1.0 * sum / arr.length;

        System.out.println("Sum of array is: " + sum);
        System.out.println("Average of the array is: " + avg);
    }


    // ============================================================
    // PROGRAM 4 — Reverse an Array (In Place)
    // Two pointer approach — start from both ends, swap, move inward
    // Does not use a second array — modifies original
    // Time complexity: O(n/2) = O(n)
    // ============================================================

    static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        int temp;

        // keep swapping until start and end meet in middle
        while (start < end) {
            // swap arr[start] and arr[end]
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++; // move start forward
            end--;   // move end backward
        }

        System.out.println(Arrays.toString(arr));
    }


    // ============================================================
    // PROGRAM 5 — Check if Array is Sorted (Ascending)

    // start with true, return false on first unsorted pair
    // also handles single element array correctly — returns true
    // ============================================================

    static boolean checkIfAscending(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false; // found one unsorted pair — done
            }
        }
        return true; // no unsorted pair found — it is sorted
    }


    // ============================================================
    // PROGRAM 6 — Count Occurrences
    // Count how many times target appears in array
    // Using for-each — no index needed, just values
    // ============================================================

    static void countOccurrences(int[] arr, int target) {
        int count = 0;

        // for-each is cleaner here — we only need values not index
        for (int j : arr) {
            if (j == target) {
                count++; // found target — increment count
            }
        }

        System.out.println("Count of " + target + ": " + count);
    }


    // ============================================================
    // PROGRAM 7 — Linear Search
    // Search for target and return its index
    // Return -1 if not found — standard convention
    // Break as soon as found — no need to check remaining elements
    // ============================================================

    static void printIndex(int[] arr, int target) {
        int index = -1; // -1 means not found — standard convention

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                index = i;
                break; // found — stop searching
            }
        }

        System.out.println("Index of " + target + ": " + index);
    }


    // ============================================================
    // PROGRAM 8 — 2D Array (Print in Table Format)
    // Used a counter to fill matrix automatically
    // Smarter than hardcoding {1,2,3},{4,5,6},{7,8,9}
    // ============================================================

    static int[][] twoDArray() {
        int num = 1;
        int[][] twoDNumbersArray = new int[3][3];

        for (int i = 0; i < twoDNumbersArray.length; i++) {
            for (int j = 0; j < twoDNumbersArray[i].length; j++) {
                twoDNumbersArray[i][j] = num; // fill with counter
                num++;
            }
        }

        // print in table format
        System.out.println("2D Array:");
        for (int i = 0; i < twoDNumbersArray.length; i++) {
            for (int j = 0; j < twoDNumbersArray[i].length; j++) {
                System.out.print(twoDNumbersArray[i][j] + "\t");
            }
            System.out.println();
        }

        return twoDNumbersArray;
    }


    // ============================================================
    // PROGRAM 9 — Row Wise Sum of 2D Array
    // sum variable reset to 0 inside outer loop
    // without this, sum keeps adding across rows — wrong answer
    // ============================================================

    static void rowWiseSum(int[][] twoDArr) {
        for (int i = 0; i < twoDArr.length; i++) {
            int sum = 0; // reset sum for each row — very important

            for (int j = 0; j < twoDArr[i].length; j++) {
                sum += twoDArr[i][j];
            }

            System.out.println("Row " + i + " sum: " + sum);
        }
    }


    // ============================================================
    // PROGRAM 10 — Move Zeros to End
    // Keep order of non-zero elements
    // Example: {0,1,0,3,12} → {1,3,12,0,0}
    //
    // MY SOLUTION — nested loops, O(n²)
    // outer loop finds zeros
    // inner loop finds next non-zero and swaps with it
    // works correctly but slower for large arrays
    //
    // BETTER SOLUTION (AI) — two pointer, O(n)
    // j tracks where next non-zero element should go
    // first pass: move all non-zero to front
    // second pass: fill remaining with zeros
    // interviews expect this O(n) approach
    // ============================================================

    static void moveZeros(int[] arr) {

        // MY SOLUTION — O(n²)
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                // find next non-zero element and swap with current zero
                for (int j = i; j < arr.length; j++) {
                    if (arr[j] != 0) {
                        arr[i] = arr[j];
                        arr[j] = 0;
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(arr));

        /*
        BETTER SOLUTION — O(n) two pointer approach:
        j tracks position where next non-zero should go

        int j = 0;

        // first pass — move all non-zero elements to front in order
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[j] = arr[i];
                j++;
            }
        }

        // second pass — fill remaining positions with zeros
        while (j < arr.length) {
            arr[j] = 0;
            j++;
        }

        In interviews always say:
        "I have an O(n²) approach using nested loops,
         but I can optimize it to O(n) using two pointers"
        That thinking pattern is what impresses interviewers.
        */
    }
// ============================================================
    // MAIN — uncomment the program you want to run
    // ============================================================

    public static void main(String[] args) {

//        arrayInput();

        int[] arr = {0, 1, 0, 3, 12, 9, 6, 0, 8, 4, 9, 0, 7};

//        findMaxAndMin(arr);
//        printSumAndAverage(arr);
//        reverseArray(arr);
//        System.out.println(checkIfAscending(arr));
//        countOccurrences(arr, 0);
//        printIndex(arr, 7);

        int[][] num = twoDArray();
        rowWiseSum(num);

//        moveZeros(arr);
    }
}
