package homeworks.seabatle.servises.fillservise;

import homeworks.seabatle.servises.fillservise.stratinterface.FillStrategy;

public class VerticalFillStrategy implements FillStrategy {
    @Override
    public int[] getShipCoords(int firstCell, int length) {
        int[] coords = new int[length];
        for (int i = 0; i < length; i++){
            coords[i] = firstCell;
            firstCell += 10;
        }
        return coords;
    }
}
