package snakeandladder;

import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner scanner = new Scanner(System.in);

    public HumanPlayer(char name) {
        super(name);
    }

    @Override
    boolean move() {
        int step;
        do {
            System.out.println("Please input an integer between 1 and 6:");
        } while ((step = scanner.nextInt()) < 1 || step > 6);

        return super.moveTo(step);
    }
}