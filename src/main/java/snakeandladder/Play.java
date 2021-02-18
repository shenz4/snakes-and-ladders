package snakeandladder;

import java.util.Scanner;

public class Play {
    // Game main method
    public static void main(String[] args) {

        System.out.println("Input 1 to play with AUTO mode, 2 to play with HUMAN mode:");
        int mode = new Scanner(System.in).nextInt();
        if (mode == 1) {
            // Auto
            Player autoPlayerE = new Player('E');
            Player autoPlayerS = new Player('S');

            // Init
            Board gameBoard = new Board(10, 5);
            gameBoard.putPlayer(autoPlayerE, 0);
            gameBoard.putPlayer(autoPlayerS, 0);
            System.out.println(gameBoard.toString());

            // Play
            while (!gameBoard.takeTurns()) ;

        } else if (mode == 2) {
            // Human play game
            Player humanPlayerE = new HumanPlayer('E');
            Player humanPlayerS = new HumanPlayer('S');

            Board humanGameBoard = new Board(10, 5);
            humanGameBoard.putPlayer(humanPlayerE, 0);
            humanGameBoard.putPlayer(humanPlayerS, 0);
            System.out.println(humanGameBoard.toString());

            while (!humanGameBoard.takeTurns()) ;
        } else {
            System.out.println("Invalid input");
        }
        System.out.println("Game Over!");
    }
}
