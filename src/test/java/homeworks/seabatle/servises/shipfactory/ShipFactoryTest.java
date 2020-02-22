package homeworks.seabatle.servises.shipfactory;

import homeworks.seabatle.ship.Ship;
import homeworks.seabatle.ship.ShipType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShipFactoryTest {
    ShipFactory factory;
    @Before
    public void setUp() throws Exception {
        factory = new ShipFactory();
    }

    @Test
    public void testGetShip() {

       assertNotNull(factory.getShip(ShipType.BOAT));
       assertNotNull(factory.getShip(ShipType.DESTROYER));
       assertNotNull(factory.getShip(ShipType.CRUISER));
       assertNotNull(factory.getShip(ShipType.BATTLESHIP));

       assertEquals(ShipType.BOAT,factory.getShip(ShipType.BOAT).getType());
       assertEquals(ShipType.DESTROYER,factory.getShip(ShipType.DESTROYER).getType());
       assertEquals(ShipType.CRUISER,factory.getShip(ShipType.CRUISER).getType());
       assertEquals(ShipType.BATTLESHIP,factory.getShip(ShipType.BATTLESHIP).getType());

       assertEquals(1,factory.getShip(ShipType.BOAT).getLives());
       assertEquals(2,factory.getShip(ShipType.DESTROYER).getLives());
       assertEquals(3,factory.getShip(ShipType.CRUISER).getLives());
       assertEquals(4,factory.getShip(ShipType.BATTLESHIP).getLives());

    }
}