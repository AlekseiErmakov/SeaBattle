package homeworks.seabatle.board.field;

import homeworks.seabatle.board.field.repository.PlayerShipsRepository;
import homeworks.seabatle.board.field.repository.ShipsRepository;
import homeworks.seabatle.functional.StringMaker;
import homeworks.seabatle.servises.generator.ShipAutoGenerator;
import homeworks.seabatle.ship.Ship;

import java.util.function.Function;

public class FieldExt {
    private ShipsRepository fleet;
    private String[][] matrix;
    private final static int BORDER = 10;
    private final static String WATER = "*";
    private final static String DECK = "D";
    private final static String KILLED = "X";
    private final static String EMPTY = "E";
    private final static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private final static String[] indexis = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private Function<Integer,Integer> del;
    private Function<Integer,Integer> rem;

    public FieldExt(ShipsRepository fleet) {
        this.fleet = fleet;
        del = coordinate -> coordinate/BORDER;
        rem = coordinate -> coordinate%BORDER;
        matrix = new String[BORDER][BORDER];
        createField();
    }
    public StrikeResult getStrikeRes(int coordinate){
        int x = del.apply(coordinate);
        int y = rem.apply(coordinate);
        Ship ship = fleet.getShip(coordinate);
        return getStrikeRes(x,y,ship);
    }
    private StrikeResult getStrikeRes(int x, int y, Ship ship) {
        String square = matrix[x][y];
        updateField(x, y);
        if (square.equals(DECK)) {
            if (ship.getLives() > 1) {
                return getWoundResult(ship);
            } else {
               return getKilledResult(ship);
            }
        } else if (square.equals(KILLED) || square.equals(EMPTY)) {
            return StrikeResult.SHOOT;
        } else {
            return StrikeResult.MISS;
        }
    }
    private void updateField(int x, int y) {
        String target = matrix[x][y];
        matrix[x][y] = target.equals(DECK) ? KILLED : EMPTY;
    }
    private void createField() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = WATER;
            }
        }
    }

    private void locateShips() {
        for (Ship ship : fleet.getAll()) {
            int[] coords = ship.getCoords();
            for (int coord : coords) {
                setCell(del.apply(coord),rem.apply(coord));
            }
        }
    }

    private void setCell(int x, int y) {
        matrix[x][y] = DECK;
    }

    private StrikeResult getKilledResult(Ship ship){
        fleet.delete(ship);
        if (fleet.getSize() > 0) {
            return StrikeResult.KILL;
        } else {
            return StrikeResult.LOSE;
        }
    }
    private StrikeResult getWoundResult(Ship ship){
        ship.decrementLives();
        fleet.updateShip(ship);
        return StrikeResult.WOUND;
    }

    @Override
    public String toString() {
        StringMaker maker = string -> string + " ";
        StringBuilder sb = new StringBuilder();
        for (String s : indexis) {
            sb.append(maker.make(s));
        }
        sb.append("\n");
        for (int i = 0; i < matrix.length; i++) {
            sb.append(maker.make(letters[i]));
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(maker.make(matrix[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PlayerShipsRepository fleet = new ShipAutoGenerator().getGeneratedRepository();
        FieldExt field = new FieldExt(fleet);
        System.out.println(field);
        System.out.println();
        field.locateShips();
        System.out.println(field);
    }
}


