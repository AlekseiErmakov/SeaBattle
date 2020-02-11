package homeworks.seabatle.aplication.parser;

import homeworks.seabatle.bean.request.DoublePointRequest;
import homeworks.seabatle.bean.request.SinglePointRequest;
import homeworks.seabatle.exception.parser.IncorrectInputParseExeption;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class RequestParserTest {
    RequestParser parser;

    @Before
    public void setUp(){
        parser = new RequestParser();
    }

    @Test
    public void testGetOneCoord() {
        String userRequest = "C2";
        SinglePointRequest request = parser.getOneCoord(userRequest);

        assertEquals(1,request.getY());
        assertEquals(2,request.getX());

        String userRequest2 = "G4";
        SinglePointRequest request2 = parser.getOneCoord(userRequest2);

        assertEquals(3,request2.getY());
        assertEquals(6,request2.getX());
    }

    @Test
    public void testGetTwoCoords() {
        String userRequest = "C2 C5";
        DoublePointRequest request = parser.getTwoCoords(userRequest);

        assertEquals(1,request.getYOne());
        assertEquals(2,request.getXOne());
        assertEquals(4,request.getYTwo());
        assertEquals(2,request.getXTwo());

        String userRequest2 = "D4 G4";
        DoublePointRequest request2 = parser.getTwoCoords(userRequest2);

        assertEquals(3,request2.getYOne());
        assertEquals(3,request2.getXOne());
        assertEquals(3,request2.getYTwo());
        assertEquals(6,request2.getXTwo());

        String userRequest3 = "D10 G10";
        DoublePointRequest request3 = parser.getTwoCoords(userRequest3);

        assertEquals(9,request3.getYOne());
        assertEquals(3,request3.getXOne());
        assertEquals(9,request3.getYTwo());
        assertEquals(6,request3.getXTwo());
    }

    @Test(expected = IncorrectInputParseExeption.class)
    public void testExceptionOneCoord(){
        String  userRequest = "fgfgdk";
        SinglePointRequest request = parser.getOneCoord(userRequest);
    }

    @Test(expected = IncorrectInputParseExeption.class)
    public void testExceptionTwoCoord(){
        String  userRequest = "fw ww";
        DoublePointRequest request = parser.getTwoCoords(userRequest);

    }
    @Test(expected = IncorrectInputParseExeption.class)
    public void testExceptionLengthOneCoord(){
        String  userRequest = "f";
        SinglePointRequest request = parser.getOneCoord(userRequest);
    }

    @Test(expected = IncorrectInputParseExeption.class)
    public void testExceptionLengthTwoCoord(){
        String  userRequest = "A222 A234";
        DoublePointRequest request = parser.getTwoCoords(userRequest);

    }
    @Test(expected = IncorrectInputParseExeption.class)
    public void testExceptionIsIllegalLetterOneCoord(){
        String  userRequest = "M1";
        SinglePointRequest request = parser.getOneCoord(userRequest);
    }

    @Test(expected = IncorrectInputParseExeption.class)
    public void testExceptionIsIllegalLetterTwoCoord(){
        String  userRequest = "M2 P10";
        DoublePointRequest request = parser.getTwoCoords(userRequest);

    }
    @Test(expected = IncorrectInputParseExeption.class)
    public void testExceptionOutOfBoundTwoCoord(){
        String  userRequest = "AA BB";
        DoublePointRequest request = parser.getTwoCoords(userRequest);

    }


}