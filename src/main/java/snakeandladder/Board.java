package snakeandladder;


import java.util.HashMap;
import java.util.LinkedList;

public class Board {
    private int rows, cols;
    private HashMap<Integer, Integer> deltaHashMap;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    private Square[][] panel;
    private LinkedList<Player> players;

    public Board(int rows, int cols, HashMap<Integer, Integer> hashMap) {
        this.rows = rows;
        this.cols = cols;
        this.deltaHashMap = hashMap;
        initPanel();
    }

    private void initPanel() {
        // Create panel
        panel = new Square[rows][cols];

        // Init panel
        for (int i = 0; i < panel.length; i++) {
            for (int j = 0; j < panel[0].length; j++) {
                panel[i][j] = new Square(getPosition(i, j));
            }
        }

        deltaHashMap.forEach((key, value) -> {
            int[] res = new int[2];
            getRowCol(key, res);
            panel[res[0]][res[1]].setDelta(value);
        });
    }

    public boolean takeTurns() {
        for (Player player : players) {
            if (player.move()) {
                return true;
            }
        }
        System.out.println();
        return false;
    }

    public void putPlayer(Player player, int pos) {
        if (players == null) {
            players = new LinkedList<>();
        }

        player.setGameBoard(this);
        Square square = getSquare(pos);
        player.setCurrentSquare(square);
        players.add(player);
        square.addPlayer(player);
    }

    public int getPosition(int row, int col) {
        if ((row & 1) == 1) {
            return (row + 1) * this.cols - col - 1;
        }
        return row * this.cols + col;
    }

    public void getRowCol(int position, int[] res) {
        if (((position / this.cols) & 1) == 1) {
            res[1] = this.cols - 1 - (position % 5);
        } else {
            res[1] = position % this.cols;
        }
        res[0] = position / this.cols;
    }

    public Square getSquare(int row, int col) {
        return panel[row][col];
    }

    public Square getSquare(int pos) {
        int[] res = new int[2];
        getRowCol(pos, res);
        return getSquare(res[0], res[1]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Board{")
                .append("rows=").append(rows)
                .append(", cols=").append(cols)
                .append("}\n");
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                builder.append(String.format("%2d", panel[i][j].getPosition()))
                        .append('(')
                        .append(String.format("%2d", panel[i][j].getDelta()))
                        .append(")  ");
            }
            builder.append('\n');
        }

        for (Player player : players) {
            builder.append(player.getName())
                    .append("'s position: ")
                    .append(player.getCurrentSquare().getPosition())
                    .append('\n');
        }
        return builder.toString();
    }
}