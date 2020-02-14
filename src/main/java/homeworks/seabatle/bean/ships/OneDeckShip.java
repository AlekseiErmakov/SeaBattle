package homeworks.seabatle.bean.ships;



import homeworks.seabatle.bean.coordinates.Coordinate;
import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;
import lombok.ToString;


public class OneDeckShip extends Ship{

    public OneDeckShip(){
        super();
    }

    @Override
    public void setShipCoords(Coordinate coordinate) {
        int[] deckCoord = oneDeckerCoords(coordinate);
        if (deckCoord.length == 2){
            super.setShipCoords(deckCoord);
        } else {
            throw new ShipCreationRequestExeption("not valid coordinates");
        }
    }
}
