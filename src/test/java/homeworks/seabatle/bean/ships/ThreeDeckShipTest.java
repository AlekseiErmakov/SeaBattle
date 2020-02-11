package homeworks.seabatle.bean.ships;

import homeworks.seabatle.aplication.parser.RequestParser;
import homeworks.seabatle.bean.request.DoublePointRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ThreeDeckShipTest {
    ThreeDeckShip gorShip;
    ThreeDeckShip verShip;
    ThreeDeckShip notInZoneShip;
    @Before
    public void setUp() throws Exception {
        RequestParser parser = new RequestParser();

        DoublePointRequest gorRequest = parser.getTwoCoords("F4 F6");
        DoublePointRequest verRequest = parser.getTwoCoords("F4 H4");
        DoublePointRequest notInZoneRequest = parser.getTwoCoords("H8 H10");

        gorShip = new ThreeDeckShip(gorRequest);

        verShip = new ThreeDeckShip(verRequest);
        notInZoneShip = new ThreeDeckShip(notInZoneRequest);
    }

    @Test
    public void decrementLife() {
        assertEquals(3,gorShip.getLifes());
        gorShip.decrementLife();
        assertEquals(2,gorShip.getLifes());
    }

    @Test
    public void containsCoords() {

        assertTrue(gorShip.containsCoords(4,5));
        assertFalse(gorShip.containsCoords(3,6));

        assertTrue(verShip.containsCoords(3,6));
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
        assertEquals(3,gorShip.getLifes());
    }

    @Test
    public void getShipCoords() {
        int[] coords = {53,54,55};
        assertArrayEquals(coords,gorShip.getShipCoords());
    }
}