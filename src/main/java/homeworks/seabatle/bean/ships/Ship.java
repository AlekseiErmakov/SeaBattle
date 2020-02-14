package homeworks.seabatle.bean.ships;

import homeworks.seabatle.bean.coordinates.Coordinate;

import homeworks.seabatle.bean.ships.fillstrategy.strategyinterface.FillStrategy;
import homeworks.seabatle.bean.ships.fillstrategy.GorrizontalFillStrategy;
import homeworks.seabatle.bean.ships.fillstrategy.VerticalFillStrategy;
import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;
import lombok.Getter;
import lombok.ToString;

import java.util.Random;

@ToString
public abstract class Ship {

    @Getter
    private long id;
    private static final int xConst = 10;
    @Getter
    private int lifes;
    @Getter
    private int[] shipCoords;
    public Ship(){
        Random r = new Random();
        id = r.nextLong();
    }
    public void setShipCoords(int[] coords){
        if (coords.length == 4){
            setMultiDeckerShipCoord(coords);
        } else {
            setOneDeckerShipCoord(coords);
        }

    }

    public void decrementLife(){
        lifes --;
    }
    public boolean containsCoords(int x, int y){
        int cell = x * xConst + y;
        for (int i : shipCoords){
            if (cell == i){
                return true;
            }
        }
        return false;
    }

    public boolean isShipZone(int[] anotherShip){
        for (int i = 0; i < shipCoords.length; i++){
            int x1 = shipCoords[i]/10;
            int y1 = shipCoords[i]%10;
            for (int j = 0; j < anotherShip.length; j++){
                int x2 = anotherShip[j]/10;
                int y2 = anotherShip[j]%10;
                if (Math.abs(x2-x1) <= 1 && Math.abs(y2-y1) <= 1){
                    return true;
                }
            }
        }
        return false;
    }
    private void setOneDeckerShipCoord(int[] coords){
        int firstCell = coords[0] * 10 + coords[1];
        shipCoords = new int[1];
        shipCoords[0] = firstCell;
        lifes = shipCoords.length;
    }
    public int[] multideckerCoords(Coordinate coordinate){
        int[] coords = coordinate.getCoords();
        if (coords.length == 4){
            return coords;
        } else {
            throw new ShipCreationRequestExeption("Input is not valid!!!");
        }
    }
    public int[] oneDeckerCoords(Coordinate coordinate){
        int[] coords = coordinate.getCoords();
        if (coords.length == 2){
            return coords;
        } else {
            throw new ShipCreationRequestExeption("Input is not valid!!!");
        }
    }
    public abstract void setShipCoords(Coordinate coordinate);
    private void setMultiDeckerShipCoord(int[] coords){
        int x1 = coords[0];
        int y1 = coords[1];
        int x2 = coords[2];
        int y2 = coords[3];
        int firstCell;
        int length;
        FillStrategy strategy;
        if (x2 - x1 == 0){
            strategy = new GorrizontalFillStrategy();
            int minY = y1 < y2 ? y1 : y2;
            firstCell = x2 *xConst + minY;
            length = Math.abs(y2-y1) + 1;
            lifes = length;
        }else {
            strategy = new VerticalFillStrategy();
            int minX = x1 < x2 ? x1 : x2;
            firstCell = minX*xConst + y1;
            length = (Math.abs(x2-x1) + 1);
            lifes = length;
        }
        shipCoords = strategy.getShipCoords(firstCell,length);
    }

}
