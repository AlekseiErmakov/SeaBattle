package homeworks.seabatle.bean.ships.repository;

import homeworks.seabatle.bean.ships.Ship;
import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;
import homeworks.seabatle.exception.ship.ShipNotFoundExeption;

import java.util.LinkedList;
import java.util.List;

public class PlayerShipsRepository implements ShipsRepository{
    private List<Ship> ships;
    private static final String SUCCESS = "Ship successfully added!";
    public PlayerShipsRepository(){
        ships = new LinkedList<>();
    }
    @Override
    public Ship getShip(int x, int y) {
        for (Ship ship : ships){
            if (ship.containsCoords(x,y)){
                return ship;
            }
        }
        throw new ShipNotFoundExeption("something goes wrong");
    }
    @Override
    public String addShip(Ship ship) {
        for (Ship inListShip : ships){
           if (inListShip.isShipZone(ship.getShipCoords())){
               throw new ShipCreationRequestExeption("You can't create ship here,there" +
                       " is another ship in this coordinates");
           }
        }
        ships.add(ship);
        return SUCCESS;
    }

    @Override
    public void updateShip(Ship ship) {
        int ind = -1;
        for (Ship inListShip : ships){
            if (inListShip.getId() == ship.getId()){
                ind = ships.indexOf(inListShip);
            }
        }
        if (ind != -1){
            ships.set(ind,ship);
        }

    }
    @Override
    public void clear() {
        ships.clear();
    }

    @Override
    public List<Ship> getAll() {
        return ships;
    }

    @Override
    public int getSize() {
        return ships.size();
    }

    @Override
    public void delete(Ship ship) {
        int index = -1;
        for (Ship inListShip : ships){
            if (inListShip.getId() == ship.getId()){
               index = ships.indexOf(inListShip);
            }
        }
        if (index != -1){
            ships.remove(index);
        }

    }
}
