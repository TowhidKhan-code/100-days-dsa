package arrays;

import java.util.Arrays;

public class Day11BinarySearch2D {

    /*
    ============================================================
    DAY 11 - BINARY SEARCH IN 2D ARRAYS
    Row Wise Sorted Matrix, Fully Sorted Matrix,
    LeetCode: 74, 240
    ============================================================
    */


    // ============================================================
    // TYPE 1 — ROW WISE SORTED MATRIX
    // Each row is sorted left to right
    // Each column is sorted top to bottom
    // Last element of row is NOT necessarily < first of next row
    //
    // APPROACH: Start from TOP RIGHT corner
    // If current > target → move left (col--)
    // If current < target → move down (row++)
    // Each step eliminates one full row or one full column
    // Time: O(m + n)
    // ============================================================

    static int[] rowSorted(int[][] matrix, int target) {
        int r = 0;
        int c = matrix[0].length - 1; // start from top right corner

        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) {
                return new int[]{r, c}; // found
            }
            if (matrix[r][c] > target) {
                c--; // current too big — go left
            } else {
                r++; // current too small — go down
            }
        }
        return new int[]{-1, -1}; // not found
    }


    // ============================================================
    // TYPE 2 — FULLY SORTED MATRIX (First Approach)
    // Each row sorted AND last element of row < first of next row
    // The whole matrix acts like one long sorted array
    //
    // This approach uses column middle + binary search on rows
    // Works but complex — see secondWayWholeSorted for cleaner solution
    // ============================================================

    static int[] wholeSortedMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        if (cols == 0) {
            return new int[]{-1, -1};
        }
        if (rows == 1) {
            return binarySearch(matrix, 0, 0, cols - 1, target);
        }

        // row start, row end, column middle
        int rStart = 0;
        int rEnd = rows - 1;
        int cMid = cols / 2;

        while (rStart < (rEnd - 1)) {
            int mid = rStart + (rEnd - rStart) / 2;

            if (matrix[mid][cMid] == target) {
                return new int[]{mid, cMid};
            }
            if (matrix[mid][cMid] > target) {
                rEnd = mid;
            } else {
                rStart = mid;
            }
        }

        if (matrix[rStart][cMid] == target) {
            return new int[]{rStart, cMid};
        }
        if (matrix[rStart + 1][cMid] == target) {
            return new int[]{rStart + 1, cMid};
        }
        if (matrix[rStart][cMid - 1] >= target) {
            return binarySearch(matrix, rStart, 0, cMid - 1, target);
        }
        if (matrix[rStart][cMid + 1] <= target && matrix[rStart][cols - 1] >= target) {
            return binarySearch(matrix, rStart, cMid + 1, cols - 1, target);
        }
        // rEnd == rStart + 1
        if (matrix[rStart + 1][cMid - 1] >= target) {
            return binarySearch(matrix, rStart + 1, 0, cMid - 1, target);
        } else {
            return binarySearch(matrix, rStart + 1, cMid + 1, cols - 1, target);
        }
    }

    // binary search on a specific row between cStart and cEnd
    static int[] binarySearch(int[][] arr, int row, int cStart, int cEnd, int target) {
        while (cStart <= cEnd) {
            int mid = cStart + (cEnd - cStart) / 2;

            if (arr[row][mid] == target) {
                return new int[]{row, mid};
            } else if (arr[row][mid] < target) {
                cStart = mid + 1;
            } else {
                cEnd = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }


    // ============================================================
    // TYPE 2 — FULLY SORTED MATRIX (Clean Approach) ✅ PREFERRED
    // Treat entire 2D matrix as a 1D sorted array
    //
    // KEY FORMULA:
    // row = mid / cols
    // col = mid % cols
    //
    // Total elements = rows * cols
    // Binary search from 0 to rows*cols - 1
    // Convert mid to row/col using division and modulo
    //
    // This is the cleanest solution — one binary search, no edge cases
    // Time: O(log(m*n))
    // ============================================================

    static int[] secondWayWholeSorted(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int start = 0;
        int end = rows * cols - 1; // treat as 1D array

        while (start <= end) {
            int mid = start + (end - start) / 2;

            // convert 1D index to 2D row and col
            int row = mid / cols;
            int col = mid % cols;

            int element = matrix[row][col];

            if (element == target) {
                return new int[]{row, col};
            }
            if (element > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return new int[]{-1, -1};
    }


    // ============================================================
    // LEETCODE 74 — Search a 2D Matrix
    // Fully sorted matrix — last of row < first of next row
    // Direct application of secondWayWholeSorted
    // row = mid / cols, col = mid % cols
    // ============================================================

    static boolean searchMatrix74(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int start = 0;
        int end = rows * cols - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int row = mid / cols;
            int col = mid % cols;

            int element = matrix[row][col];

            if (element == target) {
                return true;
            }
            if (element > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return false;
    }


    // ============================================================
    // LEETCODE 240 — Search a 2D Matrix II
    // Row wise sorted — last of row NOT necessarily < first of next
    // Direct application of rowSorted
    // Start from top right — eliminate row or column each step
    // Time: O(m + n)
    // ============================================================

    static boolean searchMatrix240(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length - 1; // start from top right

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            }
            if (matrix[row][col] > target) {
                col--; // too big — go left
            } else {
                row++; // too small — go down
            }
        }
        return false;
    }


    public static void main(String[] args) {
        // row wise sorted matrix
        int[][] arr1 = {
                {10, 20, 30, 40},
                {15, 25, 35, 45},
                {23, 27, 37, 47},
                {24, 29, 38, 50}
        };
        System.out.println(Arrays.toString(rowSorted(arr1, 45))); // [1, 3]

        // fully sorted matrix
        int[][] arr2 = {
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9,  10, 11, 12},
                {13, 14, 15, 16}
        };
        int[][] arr3 = {{1, 3}};

//        System.out.println(Arrays.toString(wholeSortedMatrix(arr2, 2)));
//        System.out.println(Arrays.toString(secondWayWholeSorted(arr3, 3)));
    }
}