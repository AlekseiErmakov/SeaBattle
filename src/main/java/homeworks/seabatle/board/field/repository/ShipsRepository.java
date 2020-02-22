package homeworks.seabatle.board.field.repository;


import homeworks.seabatle.ship.Ship;

import java.util.List;

public interface ShipsRepository {
   Ship getShip(int coordinate);
   String addShip(Ship ship);
   void updateShip(Ship ship);
   List<Ship> getAll();
   int getSize();
   void delete(Ship ship);
}
