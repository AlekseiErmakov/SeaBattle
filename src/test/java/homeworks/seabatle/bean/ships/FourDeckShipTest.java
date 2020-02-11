package homeworks.seabatle.bean.ships;

import homeworks.seabatle.aplication.parser.RequestParser;
import homeworks.seabatle.bean.request.DoublePointRequest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FourDeckShipTest {
    FourDeckShip gorShip;
    FourDeckShip verShip;
    FourDeckShip notInZoneShip;
    @Before
    public void init(){
        RequestParser parser = new RequestParser();
        DoublePointRequest GorRequest = parser.getTwoCoords("A1 A4");
        DoublePointRequest VerRequest = parser.getTwoCoords("A3 D3");

        DoublePointRequest notInZone = parser.getTwoCoords("C5 F5");


        gorShip = new FourDeckShip(GorRequest);
        verShip = new FourDeckShip(VerRequest);

        notInZoneShip = new FourDeckShip(notInZone);

    }

    @Test
    public void decrementLife() {
        assertEquals(4,gorShip.getLifes());
        gorShip.decrementLife();
        assertEquals(3,gorShip.getLifes());

        assertEquals(4,verShip.getLifes());
        verShip.decrementLife();
        assertEquals(3,verShip.getLifes());
    }

    @Test
    public void containsCoords() {
        assertTrue(verShip.containsCoords(2,3));
        assertFalse(verShip.containsCoords(1,2));

        assertTrue(gorShip.containsCoords(0,0));
        assertFalse(gorShip.containsCoords(5,5));
    }

    @Test
    public void isShipZone() {
       assertTrue(verShip.isShipZone(gorShip.getShipCoords()));

       assertFalse(verShip.isShipZone(notInZoneShip.getShipCoords()));
       assertFalse(gorShip.isShipZone(notInZoneShip.getShipCoords()));
    }

    @Test
    public void getLifes() {
        assertEquals(4,verShip.getLifes());
        assertEquals(4,gorShip.getLifes());
    }

    @Test
    public void getShipCoords() {
        int[] expectedVert = {2,12,22,32};
        int[] expectedGor = {0,1,2,3};

        assertArrayEquals(expectedGor,gorShip.getShipCoords());
        assertArrayEquals(expectedVert,verShip.getShipCoords());
    }


}