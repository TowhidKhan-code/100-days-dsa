package projects.Day40TicTacToe;

import java.util.Scanner;

public class Main {

    /*
    ============================================================
    TIC-TAC-TOE CONSOLE GAME

    GAME RULES:
    - 3x3 board
    - Two players: X and O
    - X always goes first
    - Win by getting 3 in a row (horizontal, vertical, diagonal)
    - Draw if board is full with no winner

    DATA STRUCTURE:
    - char[][] board — 2D array representing the grid
    - char player — current player ('X' or 'O')
    - boolean gameOver — flag to end game loop

    BOARD REPRESENTATION:
         0   1   2
       +---+---+---+
    0  |   |   |   |
       +---+---+---+
    1  |   |   |   |
       +---+---+---+
    2  |   |   |   |
       +---+---+---+
    ============================================================
    */

    public static void main(String[] args) {
        // ============================================================
        // STEP 1: Initialize the game board
        //
        // Create 3x3 board filled with empty spaces
        // ' ' represents an empty cell
        // ============================================================
        char[][] board = new char[3][3];

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = ' ';
            }
        }

        // ============================================================
        // STEP 2: Initialize game state
        //
        // player — current player's symbol
        // gameOver — controls main game loop
        // ============================================================
        char player = 'X';      // X always starts
        boolean gameOver = false;
        Scanner sc = new Scanner(System.in);

        // ============================================================
        // STEP 3: Main game loop
        //
        // Continue until someone wins (or could add draw detection)
        // Each iteration:
        //   1. Display board
        //   2. Get player input
        //   3. Validate and make move
        //   4. Check for win
        //   5. Switch player
        // ============================================================
        while (!gameOver) {
            // Display current board state
            printBoard(board);

            // Get player's move
            System.out.println("Player " + player + " enter: ");
            int row = sc.nextInt();
            int col = sc.nextInt();

            // Validate move — cell must be empty
            if (board[row][col] == ' ') {
                // Make the move
                board[row][col] = player;

                // Check if this move won the game
                gameOver = hasWon(board, player);

                if (gameOver) {
                    System.out.println("Player " + player + " has won!");
                } else {
                    // Switch to other player
                    // Ternary operator: if X then O, else X
                    player = (player == 'X') ? 'O' : 'X';
                }
            } else {
                // Cell already occupied
                System.out.println("Invalid Move. Try again!");
            }
        }

        // Display final board
        printBoard(board);
    }


    // ============================================================
    // PRINT BOARD
    //
    // Displays the current state of the board
    // Format: X |   | O |
    // ============================================================
    public static void printBoard(char[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col] + " | ");
            }
            System.out.println();
        }
    }


    // ============================================================
    // CHECK WIN CONDITION
    //
    // Returns true if the given player has won
    //
    // WIN CONDITIONS:
    // 1. Any row has all same symbols
    // 2. Any column has all same symbols
    // 3. Main diagonal (top-left to bottom-right)
    // 4. Anti-diagonal (top-right to bottom-left)
    // ============================================================
    public static boolean hasWon(char[][] board, char player) {
        // Check all rows
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == player &&
                    board[row][1] == player &&
                    board[row][2] == player) {
                return true;
            }
        }

        // Check all columns
        for (int col = 0; col < board.length; col++) {
            if (board[0][col] == player &&
                    board[1][col] == player &&
                    board[2][col] == player) {
                return true;
            }
        }

        // Check main diagonal (0,0) → (1,1) → (2,2)
        if (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) {
            return true;
        }

        // Check anti-diagonal (0,2) → (1,1) → (2,0)
        if (board[0][2] == player &&
                board[1][1] == player &&
                board[2][0] == player) {
            return true;
        }

        return false;
    }
}