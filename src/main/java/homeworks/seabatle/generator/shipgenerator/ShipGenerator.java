package homeworks.seabatle.generator.shipgenerator;

import homeworks.seabatle.bean.ships.*;
import homeworks.seabatle.generator.inteface.Generator;

import java.util.Iterator;

public class ShipGenerator
        implements Generator<Ship>, Iterable<Ship> {
    private Class[] types = {FourDeckShip.class, ThreeDeckShip.class, TwoDeckShip.class, OneDeckShip.class};
    private int size = 10;
    private int countShips = 0;

    @Override
    public Ship next() {
        try {
            countShips++;
            if (countShips < 2) {
                return (Ship) types[0].getDeclaredConstructor().newInstance();
            } else if (countShips < 4) {
                return (Ship) types[1].getDeclaredConstructor().newInstance();
            } else if (countShips < 7) {
                return (Ship) types[2].getDeclaredConstructor().newInstance();
            } else {
                return (Ship) types[3].getDeclaredConstructor().newInstance();
            }
        } catch (Exception ex) {
            throw new RuntimeException();
        }
    }

    @Override
    public Iterator<Ship> iterator() {
        return new ShipIterator();
    }

    class ShipIterator implements Iterator<Ship> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Ship next() {
            count--;
            return ShipGenerator.this.next();
        }
    }
}
