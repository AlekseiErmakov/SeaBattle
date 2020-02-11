package homeworks.seabatle.bean.ships;

import homeworks.seabatle.bean.request.DoublePointRequest;
import homeworks.seabatle.bean.request.SinglePointRequest;
import homeworks.seabatle.bean.ships.fillstrategy.FillStrategy;
import homeworks.seabatle.bean.ships.fillstrategy.GorrizontalFillStrategy;
import homeworks.seabatle.bean.ships.fillstrategy.VerticalFillStrategy;
import lombok.Data;
import lombok.Getter;

import java.util.Random;


public abstract class Ship {
    private FillStrategy fillStrategy;
    @Getter
    private long id;
    private static final int xConst = 10;
    @Getter
    private int lifes;
    @Getter
    private int[] shipCoords;
    public Ship (SinglePointRequest request){
        int x = request.getX();
        int y = request.getY();
        int cell = x * xConst + y;
        shipCoords = new int[1];
        shipCoords[0] = cell;
    }
    public Ship(DoublePointRequest request){
        Random r = new Random();
        id = r.nextLong();
        int x1 = request.getXOne();
        int y1 = request.getYOne();
        int x2 = request.getXTwo();
        int y2 = request.getYTwo();
        int cell;
        int length;
        if (x2 - x1 == 0){
            fillStrategy = new VerticalFillStrategy();
            int minY = y1 < y2 ? y1 : y2;
            cell = minY*xConst + x1;
            length = Math.abs(y2-y1) + 1;
            lifes = length;
        }else {
            fillStrategy = new GorrizontalFillStrategy();
            int minX = x1 < x2 ? x1 : x2;
            cell = minX*xConst + y1;
            length = (Math.abs(x2-x1) + 1);
            lifes = length;
        }
        shipCoords = fillStrategy.getShipCoords(cell,length);
    }

    public void decrementLife(){
        lifes --;
    }
    public boolean containsCoords(int x, int y){
        int cell = y * xConst + x;
        for (int i : shipCoords){
            if (cell == i){
                return true;
            }
        }
        return false;
    }

    public boolean isShipZone(int[] anotherShip){
        for (int i = 0; i < shipCoords.length; i++){
            int y1 = shipCoords[i]/10;
            int x1 = shipCoords[i]%10;
            for (int j = 0; j < anotherShip.length; j++){
                int y2 = anotherShip[j]/10;
                int x2 = anotherShip[j]%10;
                if (Math.abs(x2-x1) <= 1 && Math.abs(y2-y1) <= 1){
                    return true;
                }
            }
        }
        return false;
    }

}
