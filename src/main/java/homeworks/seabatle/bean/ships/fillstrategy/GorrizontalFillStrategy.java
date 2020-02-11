package homeworks.seabatle.bean.ships.fillstrategy;

public class GorrizontalFillStrategy implements FillStrategy{
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
