package homeworks.seabatle.bean.coordinates;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoordinateTest {
    Coordinate coordinate1;
    Coordinate coordinate2;
    Coordinate coordinate3;
    @Before
    public void setUp() throws Exception {
        coordinate1 = new Coordinate("B2");
        coordinate2 = new Coordinate("A1 A4");
        coordinate3 = new Coordinate("B2 E2");
    }

    @Test
    public void getCoords() {
        int[] expectedCoords1 ={1,1};
        int[] expectedCoords2 ={0,0,0,3};
        int[] expectedCoords3 ={1,1,4,1};

        assertArrayEquals(expectedCoords1,coordinate1.getCoords());
        assertArrayEquals(expectedCoords2,coordinate2.getCoords());
        assertArrayEquals(expectedCoords3,coordinate3.getCoords());


    }
}