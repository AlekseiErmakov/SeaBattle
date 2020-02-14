package homeworks.seabatle.bean.field;


import homeworks.seabatle.bean.coordinates.Coordinate;

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

    @Before
    public void init(){
        repository = new PlayerShipsRepository();


        Coordinate coordinate = new Coordinate("D3 D6");
        Coordinate coordinate1 = new Coordinate("A8");
        Coordinate coordinate2 = new Coordinate("A2 A3");



        FourDeckShip shipFour = new FourDeckShip();
        shipFour.setShipCoords(coordinate);
        OneDeckShip shipOne = new OneDeckShip();
        shipOne.setShipCoords(coordinate1);
        TwoDeckShip shipOne2 = new TwoDeckShip();
        shipOne2.setShipCoords(coordinate2);
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

        Coordinate miss = new Coordinate("F3");
        StrikeResult missResult = field.getStrikeRes(miss.getCoords()[0],miss.getCoords()[1]);
        assertEquals(StrikeResult.MISS.name(),missResult.name());

        Coordinate wound1 = new Coordinate("D3");
        Coordinate wound2 = new Coordinate("D4");
        Coordinate wound3 = new Coordinate("D5");
        Coordinate wound4 = new Coordinate("D6");

        StrikeResult result1 = field.getStrikeRes(wound1.getCoords()[0],wound1.getCoords()[1]);
        assertEquals(StrikeResult.WOUND.name(),result1.name());
        StrikeResult result2 = field.getStrikeRes(wound2.getCoords()[0],wound2.getCoords()[1]);
        assertEquals(StrikeResult.WOUND.name(),result2.name());
        StrikeResult result3 = field.getStrikeRes(wound3.getCoords()[0],wound3.getCoords()[1]);
        assertEquals(StrikeResult.WOUND.name(),result3.name());
        StrikeResult result4 = field.getStrikeRes(wound4.getCoords()[0],wound4.getCoords()[1]);
        assertEquals(StrikeResult.KILL.name(),result4.name());

        Coordinate kill = new Coordinate("A8");
        StrikeResult result5 = field.getStrikeRes(kill.getCoords()[0],kill.getCoords()[1]);
        assertEquals(StrikeResult.KILL.name(),result5.name());

        Coordinate shoot = new Coordinate("D4");
        StrikeResult result6 = field.getStrikeRes(shoot.getCoords()[0], shoot.getCoords()[1]);
        assertEquals(StrikeResult.SHOOT.name(),result6.name());
        for(Ship ship : repository.getAll()){
            System.out.println(Arrays.toString(ship.getShipCoords()));
        }
        Coordinate lose = new Coordinate("A2");
        StrikeResult result7 = field.getStrikeRes(lose.getCoords()[0],lose.getCoords()[1]);
        assertEquals(StrikeResult.WOUND.name(),result7.name());
        Coordinate lose2 = new Coordinate("A3");
        StrikeResult result8 = field.getStrikeRes(lose2.getCoords()[0],lose2.getCoords()[1]);
        assertEquals(StrikeResult.LOSE.name(),result8.name());
    }

    @Test
    public void testPrint() {
        System.out.println(field.print());
        assertEquals(210,field.print().length());
    }
}