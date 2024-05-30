public class SudukoSolver {

    private static final int GRID_SIZE = 9;
    public static void main(String[] args) {
        // Sample Starting Suduko Board:
        int[][] board = { 
            {7, 0, 2, 0, 5, 0, 6, 0, 0},
            {0, 0, 0, 0, 0, 3, 0, 0, 0},
            {1, 0, 0, 0, 0, 9, 5, 0, 0},
            {8, 0, 0, 0, 0, 0, 0, 9, 0},
            {0, 4, 3, 0, 0, 0, 7, 5, 0},
            {0, 9, 0, 0, 0, 0, 0, 0, 8},
            {0, 0, 9, 7, 0, 0, 0, 0, 5},
            {0, 0, 0, 2, 0, 0, 0, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        // Prints the starting suduko board
        System.out.println("Unsolved Board: ");
        printBoard(board);

        // Prints the solved board if applicable
        if (solveBoard(board)) {
            System.out.println("\nSolved Successfully!");
            System.out.println("\nSolved Board: ");
            printBoard(board);
        } else {
            System.out.println("\nUnsolvable board... :(");
        }


    }

    /**
     * Method that prints the solved board
     * @param board the suduko game board
     */
    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID_SIZE; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

    /**
     * Helper method to check if a number is in a specified row
     * @param board the suduko game board
     * @param number the number to be searched for
     * @param row the specified row in which to search for the number
     * @return boolean value of if the number is in the row
     */
    private static boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to check if a number is in a specified column
     * @param board the suduko game board
     * @param number the number to be searched for
     * @param column the specified column in which to search for the number
     * @return boolean value of if the number is in the column
     */
    private static boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Helper method to check if a number is in a specified 3x3 "box"
     * @param board the suduko game board
     * @param number the number to be searched for
     * @param row the specified row for the desired box
     * @param column the specified column for the desired box
     * @return boolean value of if the number is in the box
     */
    private static boolean isNumberInBox(int[][] board, int number, int row, int column) {
        /*
        * Formula to find the top left corner coordinate of any 3x3 box
        * in the grid. Takes in a row-column coordinate and produces the corresponding
        * top left corner for the box of that coordinate.
        */
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        // Using the top left corner as a starting point, iterate through the 3x3 box
        for (int i = localBoxRow; i < localBoxRow + 3; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper method to check that a number belongs in a placement
     * by calling all three of the checks, row, column, and box.
     * @param board the suduko game board
     * @param number the number to be placed
     * @param row the specified row for the placement
     * @param column the specified column for the placement
     * @return boolean value of if the desired spot is valid
     */
    private static boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board, number, row) &&
               !isNumberInColumn(board, number, column) &&
               !isNumberInBox(board, number, row, column);
    }

    /**
     * Method for the key recursive algorithm that returns true if 
     * every number has been successfully placed on the board in it's proper spot.
     * @param board the suduko game board
     * @return boolean value of if the board has been solved
     */
    private static boolean solveBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++) {
                if (board[row][column] == 0) { // If a space = 0, it means it is empty therefore, we can try a number there
                    for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) { // Iterate through numbers 1-9 at an empty spot until one passes isValidPlacement
                        if (isValidPlacement(board, numberToTry, row, column)) {
                            board[row][column] = numberToTry;

                            if (solveBoard(board)) { // Recursive call so the next number can be placed
                                return true; // The placement allowed for all future placements to work, the board is solved
                            } 
                            else {
                                board[row][column] = 0; // The last placement did not allow for the rest of the board to be solved, reset it to 0
                            }
                        }
                    }
                    return false; // No valid number 1-9 at this placement, therefore we backtrack and reset the last placed number to 0
                }
            }
        }
        return true; // The board has fully been iterated through, no empty spots, the board is solved
    }
}
