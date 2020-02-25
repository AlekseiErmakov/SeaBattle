package homeworks.seabatle.board.field;


import homeworks.seabatle.board.field.repository.ShipsRepository;
import homeworks.seabatle.functional.AreaCreator;
import homeworks.seabatle.functional.StringMaker;
import homeworks.seabatle.servises.coordinates.ShipAreaCreator;
import homeworks.seabatle.ship.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Field {
    private ShipsRepository fleet;
    private String[][] matrix;
    private final static int BORDER = 10;
    private final static String WATER = "~";
    private final static String DECK = "@";
    private final static String KILLED = "X";
    private final static String EMPTY = "*";
    private final static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    private final static String[] indexis = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private Function<Integer, Integer> del;
    private Function<Integer, Integer> rem;
    private StringMaker maker;

    public Field(ShipsRepository fleet) {
        this.fleet = fleet;
        del = coordinate -> coordinate / BORDER;
        rem = coordinate -> coordinate % BORDER;
        matrix = new String[BORDER][BORDER];
        maker = string -> string + " ";
        createField();
        locateShips();
    }

    public StrikeResult getStrikeRes(int coordinate) {
        int x = del.apply(coordinate);
        int y = rem.apply(coordinate);
        return getStrikeRes(x, y);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(getHat());
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
    public List<String> getFieldArray(){
        List<String> fieldMap = new ArrayList<>();
        fieldMap.add(getHat());
        for (int i = 0; i < matrix.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(maker.make(letters[i]));
            for (int j = 0; j < matrix[i].length; j++) {
                sb.append(maker.make(matrix[i][j]));
            }
            fieldMap.add(sb.toString());
        }
        return fieldMap;
    }
    private String getHat(){
        StringBuilder sb = new StringBuilder();
        for (String s : indexis) {
            sb.append(maker.make(s));
        }
        String hat = sb.toString();
        return hat.substring(0,hat.length()-1);
    }
    private StrikeResult getStrikeRes(int x, int y) {
        String square = matrix[x][y];
        updateField(x, y);
        if (square.equals(DECK)) {
            Ship ship = fleet.getShip(x*10+y);
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
        if (isInRange(x, y)){
            String target = matrix[x][y];
            if (target.equals(DECK) || target.equals(KILLED)){
                matrix[x][y] = KILLED;
            } else {
                matrix[x][y] = EMPTY;
            }

        }
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
                setCell(del.apply(coord), rem.apply(coord));
            }
        }
    }

    private void setCell(int x, int y) {
        matrix[x][y] = DECK;
    }

    private StrikeResult getKilledResult(Ship ship) {
        afterKillUpdate(ship.getCoords());
        fleet.delete(ship);
        if (fleet.getSize() > 0) {
            return StrikeResult.KILL;
        } else {
            return StrikeResult.LOSE;
        }
    }

    private StrikeResult getWoundResult(Ship ship) {
        ship.decrementLives();
        fleet.updateShip(ship);
        return StrikeResult.WOUND;
    }
    private void afterKillUpdate(int [] coords){
        List<Integer> area = new ArrayList<>();
        ShipAreaCreator creator = new ShipAreaCreator();
        for (int coord : coords){
            area.addAll(creator.getCrossArea(coord));
            area.addAll(creator.getDiagonalArea(coord));
        }
        afterKillUpdate(area);

    }
    private void afterKillUpdate(List<Integer> area){
        for (int coord : area){
            int x = del.apply(coord);
            int y = rem.apply(coord);
            updateField(x,y);
        }
    }
    private boolean isInRange(int x, int y){
        return 0 <= x && x <= 9 && 0 <= y && y <= 9;
    }

}
