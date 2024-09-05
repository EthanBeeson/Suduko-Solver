# Sudoku Game and Solver

## Description
This project includes a **Sudoku Game** that can be played in the browser, built using HTML, CSS, and JavaScript, 
as well as a **Sudoku Solver** implemented in Java. The game allows users to manually play Sudoku and includes a timer 
and error counter. The solver automatically finds the solution to a given Sudoku puzzle.

## Sudoku Game
### Features
- **Interactive Sudoku Board:** A 9x9 grid where users can play Sudoku.
- **Timer:** Tracks how long it takes the user to solve the puzzle.
- **Error Counter:** Displays the number of errors made during the game.
- **Solve Button:** Automatically solves the puzzle. (Linking of solver to interactive game is under development currently)

### Files
- **HTML (index.html):** Defines the structure of the webpage, including the Sudoku grid and controls like the solve button and timer.
- **CSS (Sudoku.css):** Styles the Sudoku board and other elements.
- **JavaScript (Sudoku.js):** Implements game logic such as user interactions, error checking, and the puzzle-solving algorithm.

### How to Play
1. Open `index.html` in a web browser.
2. A Sudoku grid will be displayed. Users can click on cells to input numbers.
3. The game keeps track of time and errors.
4. Press the **Solve** button to let the JavaScript-based solver complete the puzzle automatically.

## Sudoku Solver (Java)
### Features
- **Command-Line Sudoku Solver:** Solves any valid Sudoku puzzle that is provided in the form of a 9x9 grid.
- **Backtracking Algorithm:** Utilizes a backtracking approach to find the solution efficiently.

### Files
- **Java (SudokuSolver.java):** The main file containing the logic to solve the puzzle using backtracking.

### How to Use
1. Compile and run `SudokuSolver.java` in a Java development environment.
2. Input the Sudoku puzzle as a 9x9 grid, where empty cells are represented by 0.
3. The solver will output the solved puzzle.
