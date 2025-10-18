import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        runGame();
    }

    public static void runGame() {
        String winner = "C";
        Scanner keyword = new Scanner(System.in);
        String[][] gameBoard = new String[3][3];

        System.out.println("Welcome to Tic Tac Toe!");
        initializeGameBoard(gameBoard);
        printCurrentBoard(gameBoard);

        int turnCount = 0;
        boolean xTurn;
        while (true) {
            xTurn = turnCount % 2 == 0;
            winner = (xTurn) ? "X" : "O";
            getUserInput(xTurn, gameBoard, keyword);
            printCurrentBoard(gameBoard);
            boolean isBoardFull = isBoardFull(gameBoard);
            if (isBoardFull) {
                winner = "C";
                break;
            }
            if (getWinner(gameBoard)) {
                break;
            }
            turnCount++;
        }
        System.out.println("\nThe winner is " + winner);
        keyword.close();
    }

    public static void initializeGameBoard(String[][] gameBoard) {
        for (int col = 0; col < gameBoard.length; col++) {
            for (int row = 0; row < gameBoard.length; row ++) {
                gameBoard[col][row] = " ";
            }
        }
    }

    public static void printCurrentBoard(String[][] gameBoard) {
        for (int row = 0; row < gameBoard.length; row++){
            System.out.print("\t");
            for (int col = 0; col < gameBoard.length; col++) {
                System.out.print(gameBoard[row][col]);
                if (col == 2) {
                    break;
                }
                System.out.print(" | ");
            }
            System.out.println();
            if (row < 2) {
                System.out.println("\t- - - - -");
            }
        }

    }

    public static void getUserInput(boolean xTurn,String[][] gameBoard, Scanner keyword) {
        int row = 0;
        int col = 0;
        String rowString = null;
        String colString = null;
        String turn = xTurn ? "X" : "O";
        System.out.println();
        System.out.println("It is " + turn + "'s turn");

        while (true) {
            System.out.println("Please enter the row THEN the column, each from 0, 1, "
                    + "or 2, separated by a space");
            String input = keyword.nextLine();
            String[] parts = input.split(" ");

            try {
                rowString = parts[0];
                colString = parts[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Incorrect input. Try again. " + e.getMessage());
                continue;
            }

            try {
                row = Integer.parseInt(rowString);
                col = Integer.parseInt(colString);
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input. Try again. " + e.getMessage());
                continue;
            }
            if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
                boolean cellAlreadyOccupiedValue = cellAlreadyOccupied(row, col, gameBoard);
                if (cellAlreadyOccupiedValue) {
                    System.out.println("Incorrect input. The cell " + row + " " + col + " is occupied. Try again");
                    continue;
                }
                gameBoard[row][col] = turn;
                break;
            } else {
                System.out.println("Incorrect input. Try again");
            }
        }
    }

    public static boolean cellAlreadyOccupied(int row, int col, String[][] gameBoard) {
        return !gameBoard[row][col].equals(" ");
    }

    public static boolean getWinner(String[][] gameBoard) {
        // Row check
        for (int row = 0; row < gameBoard.length; row++) {
            String row2Check = gameBoard[row][0];
            if (!row2Check.equals(" ") && row2Check.equals(gameBoard[row][1]) && row2Check.equals(gameBoard[row][2])) {
                return true;
            }
        }

        // COL Check
        for (int col = 0; col < gameBoard.length; col++){
            String col2Check = gameBoard[0][col];
            if (!col2Check.equals(" ") && col2Check.equals(gameBoard[1][col]) && col2Check.equals(gameBoard[2][col])){
                return true;
            }
        }

        // Left Diagonal
        String leftDia2Check = gameBoard[0][0];
        if (!leftDia2Check.equals(" ") && leftDia2Check.equals(gameBoard[1][1]) && leftDia2Check.equals(gameBoard[2][2])) {
            return true;
        }

        // Right Diagonal
        String rightDia2Check = gameBoard[0][2];
        if (!rightDia2Check.equals(" ") && rightDia2Check.equals(gameBoard[1][1]) && rightDia2Check.equals(gameBoard[2][0])) {;
            return true;
        }

        return false;
    }

    public static boolean isBoardFull(String[][] gameBoard) {
        for (int row = 0; row < gameBoard.length; row++) {
            for (int col = 0; col < gameBoard.length; col++){
                if (gameBoard[row][col].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}