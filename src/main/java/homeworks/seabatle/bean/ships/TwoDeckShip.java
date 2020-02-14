package homeworks.seabatle.bean.ships;


import homeworks.seabatle.bean.coordinates.Coordinate;
import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;

public class TwoDeckShip extends Ship {

    public TwoDeckShip(){
        super();
    }

    @Override
    public void setShipCoords(Coordinate coordinate) {
        int[] coords = multideckerCoords(coordinate);
        if (Math.abs(coords[3]-coords[1]) == 1 && Math.abs(coords[2]-coords[0]) == 0 ||
                Math.abs(coords[2]-coords[0]) == 1 && Math.abs(coords[3]-coords[1]) == 0){
            super.setShipCoords(coords);
        } else {
            throw new ShipCreationRequestExeption("Ship length is to small for TwoDecker");
        }
    }
}
