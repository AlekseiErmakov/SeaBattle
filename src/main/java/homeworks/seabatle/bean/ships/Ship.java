package homeworks.seabatle.bean.ships;

import homeworks.seabatle.bean.ships.fillstrategy.FillStrategy;
import homeworks.seabatle.bean.ships.fillstrategy.GorrizontalFillStrategy;
import homeworks.seabatle.bean.ships.fillstrategy.VerticalFillStrategy;
import lombok.Data;

@Data
public abstract class Ship {
    private FillStrategy fillStrategy;
    private static final int xConst = 10;
    private int lifes;
    private long id;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private int[] shipCoords;
    public void setShipCoords(int x, int y){
        int cell = x * xConst + y;
        shipCoords = new int[1];
        shipCoords[0] = cell;
    }
    public void setShipCoords(int x1, int y1, int x2, int y2){
        int cell;
        int length;
        if (x2 - x1 == 0){
            fillStrategy = new VerticalFillStrategy();
            int minY = y1 < y2 ? y1 : y2;
            cell = x1*xConst + minY;
            length = Math.abs(y2-y1) + 1;
        }else {
            fillStrategy = new GorrizontalFillStrategy();
            int minX = x1 < x2 ? x1 : x2;
            cell = minX*xConst + y1;
            length = (Math.abs(x2-x1) + 1);
        }
        shipCoords = fillStrategy.getShipCoords(cell,length);
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
