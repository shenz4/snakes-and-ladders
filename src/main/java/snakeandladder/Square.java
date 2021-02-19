package snakeandladder;

import java.util.ArrayList;
import java.util.LinkedList;

public class Square {
    private int position;
    private ArrayList<Player> players;
    private int delta;

    public Square(int position) {
        this.position = position;
    }

    // test Square's toString() method
    public static void main(String[] args) {
        var s = new Square(5);
        s.setDelta(3);
        System.out.println(s.toString());
    }

    public int getPosition() {
        return position;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public void addPlayer(Player p) {

        if (players == null) {
            players = new ArrayList<>();
        }

        players.add(p);
    }

    public boolean removePlayer(Player p) {
        players.remove(p);
        return true;
    }

    public String getPlayers() {
        StringBuilder builder = new StringBuilder();

        if (players == null) {
            return String.format("%2s", " ");
        }

        for (Player player : players) {
            builder.append(player.getName());
        }
        return String.format("%2s", builder.toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (players != null) {
            for (Player player : players) {
                builder.append(player.getName())
                        .append(" ");
            }
        } else {
            builder.append("null");
        }

        return "Square{" +
                "position=" + position +
                ", delta=" + delta +
                ", players: " + builder.toString() +
                "}";
    }
}
