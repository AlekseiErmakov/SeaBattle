package homeworks.seabatle.bean.ships.fillstrategy;

import homeworks.seabatle.bean.ships.fillstrategy.strategyinterface.FillStrategy;

public class GorrizontalFillStrategy implements FillStrategy {
    @Override
    public int[] getShipCoords(int firstCell, int length) {
        int[] coords = new int[length];
        for (int i = 0; i < length; i++){
            coords[i] = firstCell;
            firstCell ++;
        }
        return coords;
    }
}
