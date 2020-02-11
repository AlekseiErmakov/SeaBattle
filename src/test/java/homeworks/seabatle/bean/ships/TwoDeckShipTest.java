package homeworks.seabatle.bean.ships;

import homeworks.seabatle.aplication.parser.RequestParser;
import homeworks.seabatle.bean.request.DoublePointRequest;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TwoDeckShipTest {
    TwoDeckShip gorShip;
    TwoDeckShip verShip;
    TwoDeckShip notInZoneShip;
    @Before
    public void setUp() throws Exception {
        RequestParser parser = new RequestParser();

        DoublePointRequest gorRequest = parser.getTwoCoords("F4 F5");
        DoublePointRequest verRequest = parser.getTwoCoords("F4 G4");
        DoublePointRequest notInZoneRequest = parser.getTwoCoords("H8 H9");

        gorShip = new TwoDeckShip(gorRequest);

        verShip = new TwoDeckShip(verRequest);
        notInZoneShip = new TwoDeckShip(notInZoneRequest);
    }
    @Test
    public void decrementLife() {
        assertEquals(2,gorShip.getLifes());
        gorShip.decrementLife();
        assertEquals(1,gorShip.getLifes());
    }

    @Test
    public void containsCoords() {

        assertTrue(gorShip.containsCoords(5,4));
        assertFalse(gorShip.containsCoords(3,6));

        assertTrue(verShip.containsCoords(6,3));
        assertFalse(verShip.containsCoords(4,5));
    }

    @Test
    public void isShipZone() {
        assertTrue(verShip.isShipZone(gorShip.getShipCoords()));

        assertFalse(gorShip.isShipZone(notInZoneShip.getShipCoords()));
    }

    @Test
    public void getId() {
        Long id = gorShip.getId();
        assertThat(id,is(gorShip.getId()));
    }

    @Test
    public void getLifes() {
        assertEquals(2,gorShip.getLifes());
    }

    @Test
    public void getShipCoords() {
        int[] coords = {53,54};
        assertArrayEquals(coords,gorShip.getShipCoords());
    }
}