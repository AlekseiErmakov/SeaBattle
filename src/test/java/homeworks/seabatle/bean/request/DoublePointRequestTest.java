package homeworks.seabatle.bean.request;

import homeworks.seabatle.aplication.parser.RequestParser;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoublePointRequestTest {

    @Test
    public void getTestLength() {
        RequestParser parser = new RequestParser();
        DoublePointRequest request1 = parser.getTwoCoords("A4 A7");
        DoublePointRequest request2 = parser.getTwoCoords("A4 D4");
        assertEquals(4,request1.getLength());
        assertEquals(4,request2.getLength());
    }
}