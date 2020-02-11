package homeworks.seabatle.bean.field;

import homeworks.seabatle.bean.ships.Ship;
import homeworks.seabatle.bean.ships.repository.ShipsRepository;
import lombok.Data;

import java.util.List;



public class Field {
    private int length;
    private int width;
    private String[][] matrix;
    private ShipsRepository repository;
    private List<Ship> shipList;
    private String deck = "D";
    private String water = "W";
    public Field (ShipsRepository repository){
        length = 10;
        width = 10;
        matrix = new String[length][width];
        this.repository = repository;
        shipList = repository.getAll();
        createField();
    }
    public StrikeResult getStrikeRes(int x, int y){

        System.out.println(x + "тут х");
        System.out.println(y + "тут у");
        System.out.println(matrix[x][y]);
        String square = matrix[x][y];
        switchChar(x,y);
        if (square.equals(deck)){
            Ship ship = repository.getShip(x,y);
            if (ship.getLifes() > 1){
                ship.decrementLife();
                repository.updateShip(ship);
                return StrikeResult.WOUND;
            } else{
                repository.delete(ship);
                if (repository.getSize() > 0){
                    return StrikeResult.KILL;
                }else {
                    return StrikeResult.LOSE;
                }
            }
        } else if (square.equals("X")||square.equals("*")){
            return StrikeResult.SHOOT;
        }
        else {
            return StrikeResult.MISS;
        }
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                sb.append(matrix[j][i]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void switchChar(int x, int y){
        String target = matrix[x][y];
        String killed = "X";
        String empty = "*";
        matrix[x][y] = target.equals(deck) ? killed : empty;
    }

    private void createField(){
        for (Ship ship : shipList){
            setDeckCell(ship.getShipCoords());
        }
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++){
                if (matrix[i][j]== null){
                    matrix[i][j] = water;
                }
            }
        }
    }
    private void setDeckCell(int  ... ints){
        for (int xy : ints){
            int x = xy%10;
            int y = xy/10;
            matrix[x][y] = deck;
        }

    }


}
