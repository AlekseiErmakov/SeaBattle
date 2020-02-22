package homeworks.seabatle.servises.shipfactory;

import homeworks.seabatle.ship.Ship;
import homeworks.seabatle.ship.ShipType;

public class ShipFactory {
    private static final int BOATLIVES = 1;
    private static final int DESTROYERLIVES = 2;
    private static final int CRUISERLIVES = 3;
    private static final int BATTLESHIPLIVES = 4;

    public Ship getShip(ShipType type){
         Ship ship = new Ship();
         switch (type){
             case BOAT:
                 ship = new Ship(ShipType.BOAT,BOATLIVES);
                 break;
             case DESTROYER:
                 ship = new Ship(ShipType.DESTROYER,DESTROYERLIVES);
                 break;
             case CRUISER:
                 ship = new Ship(ShipType.CRUISER,CRUISERLIVES);
                 break;
             case BATTLESHIP:
                 ship = new Ship(ShipType.BATTLESHIP,BATTLESHIPLIVES);
                 break;
         }
         return ship;
     }
}
