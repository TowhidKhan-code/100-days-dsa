package recursion;

public class Day27BacktrackingProblems {

    // ══════════════════════════════════════════════════════════════════════
    // Q1 : N-Queens Problem
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: place queens row by row
    //   For each row, try every column
    //   If placement is safe → place queen → recurse on next row → unplace
    //   Base case: all rows filled → valid configuration found
    //
    // WHY row by row:
    //   Guarantees exactly one queen per row automatically
    //   Only need to check column and diagonals — not rows
    static int nQueen(boolean[][] board, int r) {
        if (r == board.length) {
            display(board);            // base: all queens placed successfully
            System.out.println();
            return 1;                  // count this valid configuration
        }
        int count = 0;
        for (int c = 0; c < board[0].length; c++) {
            if (isSafe(board, r, c)) {
                board[r][c] = true;    // place queen
                count += nQueen(board, r + 1); // recurse on next row
                board[r][c] = false;   // unplace queen (backtrack)
            }
        }
        return count;
    }

    // ══════════════════════════════════════════════════════════════════════
    // isSafe — check if queen at (r,c) is safe from all previous queens
    // ══════════════════════════════════════════════════════════════════════
    // THREE checks (no need to check rows below — not placed yet):
    //   1. Column check:        any queen in same column above?
    //   2. Left diagonal:       any queen on upper-left diagonal?
    //   3. Right diagonal:      any queen on upper-right diagonal?
    //
    // maxLeft  = min(r, c)              → steps until hitting top or left wall
    // maxRight = min(r, cols-c-1)       → steps until hitting top or right wall
    static boolean isSafe(boolean[][] board, int r, int c) {
        // Check 1: column — scan all rows above in same column
        for (int i = 0; i < r; i++) {
            if (board[i][c]) return false;
        }

        // Check 2: upper-left diagonal
        int maxLeft = Math.min(r, c);
        for (int i = 1; i <= maxLeft; i++) {
            if (board[r - i][c - i]) return false;
        }

        // Check 3: upper-right diagonal
        int maxRight = Math.min(r, board.length - c - 1);
        for (int i = 1; i <= maxRight; i++) {
            if (board[r - i][c + i]) return false;
        }

        return true;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q2 : N-Knights Problem
    // ══════════════════════════════════════════════════════════════════════
    // KEY INSIGHT: knights only attack in an L-shape
    //   When placing at (r,c), only need to check cells ALREADY PLACED
    //   All cells in previous rows and previous columns in current row
    //   Cells in FUTURE positions cannot conflict yet
    //
    // Moving cell by cell left to right, top to bottom
    // At each cell: try placing knight (if safe) OR skip
    // Base case: all knights placed
    static void nKnights(boolean[][] board, int r, int c, int knights) {
        if (knights == 0) {
            display(board);            // base: all knights placed
            System.out.println();
            return;
        }
        if (r == board.length - 1 && c == board.length) {
            return;                    // base: exhausted all cells, not enough room
        }
        if (c == board.length) {
            nKnights(board, r + 1, 0, knights); // end of row → go to next row
            return;
        }
        if (isOk(board, r, c)) {
            board[r][c] = true;
            nKnights(board, r, c + 1, knights - 1); // placed knight, move forward
            board[r][c] = false;       // backtrack
        }
        nKnights(board, r, c + 1, knights); // skip this cell
    }

    // ══════════════════════════════════════════════════════════════════════
    // isOk — check if knight at (r,c) conflicts with any already-placed knight
    // ══════════════════════════════════════════════════════════════════════
    // Only check 4 of the 8 possible knight moves — the ones ALREADY PLACED
    // The other 4 moves point to future cells (not placed yet → no conflict)
    //
    // Already-placed positions from (r,c):
    //   (r-1, c+2), (r-1, c-2), (r-2, c+1), (r-2, c-1)
    static boolean isOk(boolean[][] board, int r, int c) {
        if (isValid(board, r-1, c+2) && board[r-1][c+2]) return false;
        if (isValid(board, r-1, c-2) && board[r-1][c-2]) return false;
        if (isValid(board, r-2, c+1) && board[r-2][c+1]) return false;
        if (isValid(board, r-2, c-1) && board[r-2][c-1]) return false;
        return true;
    }

    static boolean isValid(boolean[][] board, int r, int c) {
        return r >= 0 && r < board.length && c >= 0 && c < board.length;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Q3 : LC 37 — Sudoku Solver
    // ══════════════════════════════════════════════════════════════════════
    // KEY IDEA: find next empty cell → try 1-9 → if valid place and recurse
    //   If recursion returns true → solution found, propagate true upward
    //   If no number works → backtrack (set to 0) → return false
    //
    // emptyLeft flag: starts true, set to false when empty cell found
    //   After loops: if still true → no empty cells → board solved
    static boolean isSudokuSolved(int[][] board) {
        int n = board.length;
        int row = -1;
        int col = -1;
        boolean emptyLeft = true;

        // Find next empty cell (value == 0)
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    emptyLeft = false;
                    break outer;
                }
            }
        }

        if (emptyLeft) return true;    // no empty cells → solved

        for (int num = 1; num <= 9; num++) {
            if (isCorrect(board, row, col, num)) {
                board[row][col] = num; // try this number
                if (isSudokuSolved(board)) {
                    return true;       // propagate success upward
                } else {
                    board[row][col] = 0; // backtrack: this number didn't work
                }
            }
        }
        return false;                  // no number worked → trigger backtrack above
    }

    // ══════════════════════════════════════════════════════════════════════
    // isCorrect — check if num is valid at (row, col)
    // ══════════════════════════════════════════════════════════════════════
    // THREE checks:
    //   1. Row check:     num already in this row?
    //   2. Column check:  num already in this column?
    //   3. Box check:     num already in the 3×3 box?
    //
    // Box start calculation:
    //   sqrt = sqrt(9) = 3
    //   rowStart = row - row%3   (rounds down to box boundary)
    //   colStart = col - col%3   (rounds down to box boundary)
    //   Works for any board size (4x4, 9x9, 16x16)
    static boolean isCorrect(int[][] board, int row, int col, int num) {
        // Check 1: row
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == num) return false;
        }

        // Check 2: column
        for (int[] nums : board) {
            if (nums[col] == num) return false;
        }

        // Check 3: 3×3 box
        int sqrt = (int)(Math.sqrt(board.length));
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;
        for (int i = rowStart; i < rowStart + sqrt; i++) {
            for (int j = colStart; j < colStart + sqrt; j++) {
                if (board[i][j] == num) return false;
            }
        }
        return true;
    }

    // ══════════════════════════════════════════════════════════════════════
    // Display helpers
    // ══════════════════════════════════════════════════════════════════════
    static void display(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean cell : row) {
                System.out.print(cell ? "Q " : "X ");
            }
            System.out.println();
        }
    }

    static void display(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        System.out.println("=== N-Queens (n=4) ===");
        int n = 4;
        boolean[][] queenBoard = new boolean[n][n];
        System.out.println("Total solutions: " + nQueen(queenBoard, 0)); // 2

        System.out.println("\n=== N-Knights (n=4, knights=4) ===");
        boolean[][] knightBoard = new boolean[4][4];
        nKnights(knightBoard, 0, 0, 4);

        System.out.println("\n=== Sudoku Solver ===");
        int[][] grid = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        if (isSudokuSolved(grid)) {
            display(grid);
        } else {
            System.out.println("Cannot be solved");
        }
    }
}