package homeworks.seabatle.bean.ships;

import homeworks.seabatle.aplication.parser.RequestParser;
import homeworks.seabatle.bean.request.SinglePointRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OneDeckShipTest {
    OneDeckShip first;
    OneDeckShip second;
    OneDeckShip third;
    @Before
    public void setUp() throws Exception {
        RequestParser parser = new RequestParser();

        SinglePointRequest requestFirst = parser.getOneCoord("A10");
        SinglePointRequest requestSecond = parser.getOneCoord("F6");
        SinglePointRequest requestThird = parser.getOneCoord("E7");

        first = new OneDeckShip(requestFirst);
        second = new OneDeckShip(requestSecond);
        third = new OneDeckShip(requestThird);
    }

    @Test
    public void decrementLife() {
        assertEquals(1,first.getLifes());
        first.decrementLife();
        assertEquals(0,first.getLifes());
    }

    @Test
    public void containsCoords() {
        assertFalse(first.containsCoords(8,0));
        assertFalse(first.containsCoords(1,10));
        assertTrue(first.containsCoords(0,9));
    }

    @Test
    public void isShipZone() {
        assertFalse(first.isShipZone(second.getShipCoords()));
        assertFalse(first.isShipZone(third.getShipCoords()));
        assertTrue(second.isShipZone(third.getShipCoords()));
    }

    @Test
    public void getId() {
        Long lonS = first.getId();
        assertThat(lonS,is(first.getId()));

    }

    @Test
    public void getLifes() {
        assertEquals(1,first.getLifes());
    }

    @Test
    public void getShipCoords() {
        int[] coordsFirst = {9};
        int[] coordsSecond = {55};
        int[] coordsThird = {46};
        assertArrayEquals(coordsFirst,first.getShipCoords());
        assertArrayEquals(coordsSecond,second.getShipCoords());
        assertArrayEquals(coordsThird,third.getShipCoords());
    }
}