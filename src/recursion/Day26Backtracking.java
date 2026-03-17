package recursion;

import java.util.Arrays;

public class Day26Backtracking {

    // ══════════════════════════════════════════════════════════════════════
    // Q1 : Count all paths in a maze (only down and right)
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: to reach (r,c) you must come from (r-1,c) or (r,c-1)
    // Base case: any cell on the first row or first column has exactly 1 path
    //   (you can only go right along the top row, or only go down the left column)
    static int countPath(int r, int c) {
        if (r == 1 || c == 1) {
            return 1;                       // base: only one direction possible on edges
        }
        int fromAbove = countPath(r - 1, c);
        int fromLeft  = countPath(r, c - 1);
        return fromAbove + fromLeft;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q2 : Print all paths (only down and right)
    // ══════════════════════════════════════════════════════════════════════
    // Guards r>1 and c>1 prevent going out of bounds
    static void printPath(String p, int r, int c) {
        if (r == 1 && c == 1) {
            System.out.println(p);
            return;
        }
        if (r > 1) {
            printPath(p + "D", r - 1, c);
        }
        if (c > 1) {
            printPath(p + "R", r, c - 1);
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q3 : Include diagonal paths
    // ══════════════════════════════════════════════════════════════════════
    // Added diagonal 'd' with both r>1 AND c>1 guard
    // Three branches: diagonal, down, right
    static void diagonalPath(String p, int r, int c) {
        if (r == 1 && c == 1) {
            System.out.println(p);
            return;
        }
        if (r > 1 && c > 1) {
            diagonalPath(p + "d", r - 1, c - 1); // diagonal move (both r and c decrease)
        }
        if (r > 1) {
            diagonalPath(p + "D", r - 1, c);     // down
        }
        if (c > 1) {
            diagonalPath(p + "R", r, c - 1);     // right
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q4 : Maze with obstacles
    // ══════════════════════════════════════════════════════════════════════
    // board[r][c] == false means obstacle — return immediately
    // No backtracking needed here: only moving right and down, never revisit
    static void obstacle(String p, boolean[][] board, int r, int c) {
        if (r == board.length - 1 && c == board[0].length - 1) {
            System.out.println(p);
            return;
        }
        if (!board[r][c]) {
            return;                         // obstacle: this path is blocked
        }
        if (r < board.length - 1) {
            obstacle(p + "D", board, r + 1, c);
        }
        if (c < board[0].length - 1) {
            obstacle(p + "R", board, r, c + 1);
        }
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q5 : All paths including up and left (Backtracking)
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: with all 4 directions, you can revisit cells → infinite loops
    // SOLUTION: mark current cell as visited (false) BEFORE exploring
    //           unmark it (true) AFTER all paths from here are explored
    //           This is BACKTRACKING — undo the choice when done
    //
    // WHY UNMARK: each PATH needs its own fresh board state
    //   After exploring one path, restore the cell so other paths can use it
    //   Without unmark: once visited, cell is blocked for ALL future paths → wrong
    static void allPaths(String p, boolean[][] board, int r, int c) {
        if (r == board.length - 1 && c == board[0].length - 1) {
            System.out.println(p);
            return;
        }
        if (!board[r][c]) {
            return;                         // obstacle or already visited on this path
        }
        board[r][c] = false;               // MARK: visited on current path

        if (r < board.length - 1)  allPaths(p + "D", board, r + 1, c);
        if (c < board[0].length - 1) allPaths(p + "R", board, r, c + 1);
        if (r > 0)                  allPaths(p + "U", board, r - 1, c);
        if (c > 0)                  allPaths(p + "L", board, r, c - 1);

        board[r][c] = true;                // UNMARK: backtrack, restore for other paths
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q6 : Print matrix and paths
    // ══════════════════════════════════════════════════════════════════════
    // path[][] stores the step number at each cell for the current path
    // Both board and path are backtracked at the end
    static void printMatrixAndPath(String p, boolean[][] board, int r, int c, int[][] path, int steps) {
        if (r == board.length - 1 && c == board[0].length - 1) {
            path[r][c] = steps;
            for (int[] row : path) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println(p);
            System.out.println();
            return;
        }
        if (!board[r][c]) {
            return;
        }
        board[r][c] = false;               // mark visited
        path[r][c] = steps;               // record step number at this cell

        if (r < board.length - 1)    printMatrixAndPath(p + "D", board, r + 1, c, path, steps + 1);
        if (c < board[0].length - 1) printMatrixAndPath(p + "R", board, r, c + 1, path, steps + 1);
        if (r > 0)                   printMatrixAndPath(p + "U", board, r - 1, c, path, steps + 1);
        if (c > 0)                   printMatrixAndPath(p + "L", board, r, c - 1, path, steps + 1);

        board[r][c] = true;                // unmark: backtrack board
        path[r][c] = 0;                    // unmark: backtrack path matrix
    }


    public static void main(String[] args) {
        System.out.println("=== Q1: Count paths (3x3) ===");
        System.out.println(countPath(3, 3));               // 6

        System.out.println("\n=== Q2: Print paths (3x3) ===");
        printPath("", 3, 3);

        System.out.println("\n=== Q3: Diagonal paths (3x3) ===");
        diagonalPath("", 3, 3);

        boolean[][] board = {
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };

        System.out.println("\n=== Q4: Obstacle maze ===");
        obstacle("", board, 0, 0);

        System.out.println("\n=== Q5: All paths with backtracking ===");
        boolean[][] board2 = {
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        allPaths("", board2, 0, 0);

        System.out.println("\n=== Q6: Print matrix and paths ===");
        boolean[][] board3 = {
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        int[][] path = new int[board3.length][board3[0].length];
        printMatrixAndPath("", board3, 0, 0, path, 1);
    }
}