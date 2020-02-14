package homeworks.seabatle.bean.ships;


import homeworks.seabatle.bean.coordinates.Coordinate;
import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;

public class ThreeDeckShip extends Ship{

    public ThreeDeckShip(){
      super();
    }
    @Override
    public void setShipCoords(Coordinate coordinate) throws ShipCreationRequestExeption {
        int[] coords = multideckerCoords(coordinate);
        if (Math.abs(coords[3]-coords[1]) == 2 && Math.abs(coords[2]-coords[0]) == 0 ||
                Math.abs(coords[2]-coords[0]) == 2 && Math.abs(coords[3]-coords[1]) == 0){
            super.setShipCoords(coords);
        } else {
            throw new ShipCreationRequestExeption("Ship length is to small for ThreeDecker");
        }

    }
}
