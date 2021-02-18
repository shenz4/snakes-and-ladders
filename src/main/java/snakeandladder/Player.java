package snakeandladder;

import java.util.Random;

public class Player {
    private char name;
    private Square currentSquare;
    private Board gameBoard;

    public Player(char name) {
        this.name = name;
    }

    // test Play's toString() method
    public static void main(String[] args) {
        System.out.println(new Player('E').toString());
    }

    public char getName() {
        return name;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    // dispatch move method, covered by HumanPlayer
    boolean move() {
        Random random = new Random();
        int step = random.nextInt(6) + 1;
        return moveTo(step);
    }

    // core move method
    boolean moveTo(int step) {
        int endPosition = gameBoard.getRows() * gameBoard.getCols() - 1;
        int toPosition = currentSquare.getPosition() + step;

        Square oldSquare = getCurrentSquare();
        Square newSquare = toPosition < endPosition ? gameBoard.getSquare(toPosition) : gameBoard.getSquare(endPosition);

        // Move player
        setCurrentSquare(newSquare);
        oldSquare.removePlayer(this);
        newSquare.addPlayer(this);
        if (toPosition >= endPosition) {
            System.out.println(name + " moves " + String.format("%2d", step) + " steps to position " + toPosition + ", which is at or beyond the game end");
            return true;
        } else {
            System.out.println(name + " moves " + String.format("%2d", step) + " steps to position " + toPosition);
            int delta;
            if ((delta = getCurrentSquare().getDelta()) != 0) {
                int[] res = new int[2];
                gameBoard.getRowCol(toPosition, res);
                res[0] += delta;
                return slide(gameBoard.getPosition(res[0], res[1]), delta > 0 ? "up" : "down");
            }
        }
        return false;
    }

    // process snakes and ladders
    boolean slide(int toPosition, String direction) {
        int endPosition = gameBoard.getRows() * gameBoard.getCols() - 1;

        Square oldSquare = getCurrentSquare();
        Square newSquare = toPosition < endPosition ? gameBoard.getSquare(toPosition) : gameBoard.getSquare(endPosition);

        setCurrentSquare(newSquare);
        oldSquare.removePlayer(this);
        newSquare.addPlayer(this);

        if (toPosition >= endPosition) {
            System.out.println(name + " slides " + direction + " to position " + toPosition + ", which is at or beyond the game end");
            return true;
        }

        System.out.println(name + " slides " + direction + " to position " + toPosition);
        int delta;
        if ((delta = getCurrentSquare().getDelta()) != 0) {
            int[] res = new int[2];
            gameBoard.getRowCol(toPosition, res);
            res[0] += delta;
            return slide(gameBoard.getPosition(res[0], res[1]), delta > 0 ? "up" : "down");
        }
        return false;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                '}';
    }
}
