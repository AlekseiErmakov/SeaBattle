package homeworks.seabatle.bean.ships.repository;

import homeworks.seabatle.bean.ships.Ship;

import java.util.List;

public interface ShipsRepository {
   Ship getShip(int x, int y);
   String addShip(Ship ship);
   void updateShip(Ship ship);
   void clear();
   List<Ship> getAll();
   int getSize();
   void delete(Ship ship);
}
