package homeworks.seabatle.bean.ships;


import lombok.Data;
import homeworks.seabatle.bean.request.DoublePointRequest;

@Data
public class FourDeckShip extends Ship{
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    public FourDeckShip(DoublePointRequest request){
        x1 = request.getXOne();
        x2 = request.getXTwo();
        y1 = request.getYOne();
        y2 = request.getYTwo();
        setShipCoords(x1, y1, x2, y2);
    }

}
