package homeworks.seabatle.board.field.repository;


import homeworks.seabatle.exception.ShipCreationRequestExeption;
import homeworks.seabatle.exception.ShipNotFoundExeption;
import homeworks.seabatle.ship.Ship;

import java.util.LinkedList;
import java.util.List;

public class PlayerShipsRepository implements ShipsRepository {
    private List<Ship> ships;
    private static final String SUCCESS = " successfully added!";

    public PlayerShipsRepository() {
        ships = new LinkedList<>();
    }

    @Override
    public Ship getShip(int coordinate) {
        for (Ship ship : ships) {
            if (containsCoords(ship, coordinate)) {
                return ship;
            }
        }
        throw new ShipNotFoundExeption("something goes wrong");
    }

    @Override
    public String addShip(Ship ship) {

        for (Ship inListShip : ships) {
            if (inShipZone(inListShip, ship)) {
                throw new ShipCreationRequestExeption("You can't create ship here,there" +
                        " is another ship in this coordinates");
            }
        }
        ships.add(ship);
        return ship.getType() + SUCCESS ;
    }

    @Override
    public void updateShip(Ship ship) {
        int ind = -1;
        for (Ship inListShip : ships) {
            if (inListShip.getId() == ship.getId()) {
                ind = ships.indexOf(inListShip);

            }
        }
        if (ind != -1) {
            ships.set(ind, ship);
        }
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
        ships.remove(ship);


    }

    private boolean inShipZone(Ship repShip, Ship ship) {
        return inShipZone(repShip.getCoords(), ship.getCoords());
    }

    private boolean inShipZone(int[] repShip, int[] anotherShip) {
        boolean answer = false;
        for (int i = 0; i < repShip.length; i++) {
            int x1 = repShip[i] / 10;
            int y1 = repShip[i] % 10;
            for (int j = 0; j < anotherShip.length; j++) {
                int x2 = anotherShip[j] / 10;
                int y2 = anotherShip[j] % 10;
                if (Math.abs(x2 - x1) <= 1 && Math.abs(y2 - y1) <= 1) {
                    answer = true;
                }
            }
        }
        return answer;
    }


    private boolean containsCoords(Ship ship, int coordinate) {
        return containsCoords(ship.getCoords(), coordinate);
    }

    private boolean containsCoords(int[] coords, int coordinate) {
        boolean contains = false;
        for (int i : coords) {
            if (i == coordinate) {
                contains = true;
            }
        }
        return contains;
    }

}
