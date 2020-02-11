package homeworks.seabatle.bean.field;

import homeworks.seabatle.aplication.parser.RequestParser;
import homeworks.seabatle.bean.request.DoublePointRequest;
import homeworks.seabatle.bean.request.SinglePointRequest;
import homeworks.seabatle.bean.ships.FourDeckShip;
import homeworks.seabatle.bean.ships.OneDeckShip;
import homeworks.seabatle.bean.ships.Ship;
import homeworks.seabatle.bean.ships.TwoDeckShip;
import homeworks.seabatle.bean.ships.repository.PlayerShipsRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FieldTest {
    private PlayerShipsRepository repository;
    private Field field;
    private RequestParser parser;
    @Before
    public void init(){
        repository = new PlayerShipsRepository();
        parser = new RequestParser();

        DoublePointRequest request = parser.getTwoCoords("D3 D6");
        SinglePointRequest request1 = parser.getOneCoord("A8");
        DoublePointRequest request2 = parser.getTwoCoords("A2 A3");



        FourDeckShip shipFour = new FourDeckShip(request);
        OneDeckShip shipOne = new OneDeckShip(request1);
        TwoDeckShip shipOne2 = new TwoDeckShip(request2);

        repository.addShip(shipFour);
        repository.addShip(shipOne);
        repository.addShip(shipOne2);

        field = new Field(repository);


    }
    @Test
    public void testGetStrikeRes() {
        for(Ship ship : repository.getAll()){
            System.out.println(Arrays.toString(ship.getShipCoords()));
        }

        SinglePointRequest miss = parser.getOneCoord("F3");
        StrikeResult missResult = field.getStrikeRes(miss.getX(),miss.getY());
        assertEquals(StrikeResult.MISS.name(),missResult.name());

        SinglePointRequest wound1 = parser.getOneCoord("D3");
        SinglePointRequest wound2 = parser.getOneCoord("D4");
        SinglePointRequest wound3 = parser.getOneCoord("D5");
        SinglePointRequest wound4 = parser.getOneCoord("D6");

        StrikeResult result1 = field.getStrikeRes(wound1.getX(),wound1.getY());
        assertEquals(StrikeResult.WOUND.name(),result1.name());
        StrikeResult result2 = field.getStrikeRes(wound2.getX(),wound2.getY());
        assertEquals(StrikeResult.WOUND.name(),result2.name());
        StrikeResult result3 = field.getStrikeRes(wound3.getX(),wound3.getY());
        assertEquals(StrikeResult.WOUND.name(),result3.name());
        StrikeResult result4 = field.getStrikeRes(wound4.getX(),wound4.getY());
        assertEquals(StrikeResult.KILL.name(),result4.name());

        SinglePointRequest kill = parser.getOneCoord("A8");
        StrikeResult result5 = field.getStrikeRes(kill.getX(),kill.getY());
        assertEquals(StrikeResult.KILL.name(),result5.name());

        SinglePointRequest shoot = parser.getOneCoord("D4");
        StrikeResult result6 = field.getStrikeRes(shoot.getX(), shoot.getY());
        assertEquals(StrikeResult.SHOOT.name(),result6.name());
        for(Ship ship : repository.getAll()){
            System.out.println(Arrays.toString(ship.getShipCoords()));
        }
        SinglePointRequest lose = parser.getOneCoord("A2");
        StrikeResult result7 = field.getStrikeRes(lose.getX(),lose.getY());
        assertEquals(StrikeResult.WOUND.name(),result7.name());
        SinglePointRequest lose2 = parser.getOneCoord("A3");
        StrikeResult result8 = field.getStrikeRes(lose2.getX(),lose2.getY());
        assertEquals(StrikeResult.LOSE.name(),result8.name());
    }

    @Test
    public void testPrint() {
        assertEquals(110,field.print().length());
    }
}