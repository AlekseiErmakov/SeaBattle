package homeworks.seabatle.bean.ships;


import lombok.Data;
import homeworks.seabatle.bean.request.SinglePointRequest;

@Data
public class OneDeckShip extends Ship{
    private int x;
    private int y;
    public OneDeckShip(SinglePointRequest request){
        x = request.getX();
        y = request.getY();
        setShipCoords(x, y);
    }
}
