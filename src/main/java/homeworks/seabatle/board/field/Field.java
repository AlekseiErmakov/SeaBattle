package homeworks.seabatle.board.field;


import homeworks.seabatle.board.field.repository.ShipsRepository;
import homeworks.seabatle.ship.Ship;

import java.util.List;

public class Field {
    private String[][] matrix;
    private ShipsRepository repository;
    private List<Ship> shipList;
    private String deck = "D";
    private String water = "*";

    public Field(ShipsRepository repository) {
        int length = 10;
        int width = 10;
        matrix = new String[length][width];
        this.repository = repository;
        shipList = repository.getAll();
        createField();
    }

    public StrikeResult getStrikeRes(int coordinate) {
        int x = getX(coordinate);
        int y = getY(coordinate);
        String square = matrix[x][y];
        switchChar(x, y);
        if (square.equals(deck)) {
            Ship ship = repository.getShip(coordinate);
            if (ship.getLives() > 1) {
                ship.decrementLives();
                repository.updateShip(ship);
                return StrikeResult.WOUND;
            } else {
                repository.delete(ship);
                if (repository.getSize() > 0) {
                    return StrikeResult.KILL;
                } else {
                    return StrikeResult.LOSE;
                }
            }
        } else if (square.equals("X") || square.equals("E")) {
            return StrikeResult.SHOOT;
        } else {
            return StrikeResult.MISS;
        }
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(matrix[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void switchChar(int x, int y) {
        String target = matrix[x][y];
        String killed = "X";
        String empty = "E";
        matrix[x][y] = target.equals(deck) ? killed : empty;
    }

    private void createField() {
        for (Ship ship : shipList) {
            setDeckCell(ship.getCoords());
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == null) {
                    matrix[i][j] = water;
                }
            }
        }
    }

    private void setDeckCell(int... ints) {
        for (int xy : ints) {
            int x = getX(xy);
            int y = getY(xy);
            matrix[x][y] = deck;
        }
    }
    private int getX(int coord){
        return coord/10;
    }
    private int getY(int coord){
        return coord%10;
    }


}
