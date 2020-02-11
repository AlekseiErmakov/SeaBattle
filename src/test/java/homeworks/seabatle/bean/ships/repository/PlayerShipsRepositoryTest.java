package homeworks.seabatle.bean.ships.repository;


import homeworks.seabatle.aplication.parser.RequestParser;
import homeworks.seabatle.bean.request.DoublePointRequest;
import homeworks.seabatle.bean.request.SinglePointRequest;
import homeworks.seabatle.bean.ships.*;
import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerShipsRepositoryTest {
    PlayerShipsRepository repository;
    RequestParser parser;
    FourDeckShip four;
    ThreeDeckShip three;
    TwoDeckShip two;
    OneDeckShip one;
    @Before
    public void setUp() throws Exception {
        repository = new PlayerShipsRepository();
        parser = new RequestParser();
        DoublePointRequest request1 = parser.getTwoCoords("B2 B5");
        four = new FourDeckShip(request1);
        DoublePointRequest request2 = parser.getTwoCoords("D1 D3");
        three = new ThreeDeckShip(request2);
        DoublePointRequest request3 = parser.getTwoCoords("F6 G6");
        two = new TwoDeckShip(request3);
        SinglePointRequest request4 = parser.getOneCoord("B6");
        one = new OneDeckShip(request4);

    }
    @Test
    public void testAddFourShip() {
        repository.addShip(four);
        repository.addShip(three);
        repository.addShip(two);
    }
    @Test
    public void testAddOneShip() {

        repository.addShip(three);
        repository.addShip(two);
        repository.addShip(one);
    }
    @Test(expected = ShipCreationRequestExeption.class)
    public void testFailToAdd(){
        repository.addShip(four);
        repository.addShip(three);
        repository.addShip(two);
        repository.addShip(one);
    }
    @Test
    public void getShip() {
        repository.addShip(four);
        Ship ship4 = repository.getShip(2,1);
        assertThat(ship4,is(four));

        repository.addShip(three);
        Ship ship3 = repository.getShip(1,3);
        assertThat(ship3,is(three));

        repository.addShip(two);
        Ship ship2 = repository.getShip(5,5);
        assertThat(ship2,is(two));

        repository.delete(four);

        repository.addShip(one);
        Ship ship1 = repository.getShip(5,1);
        assertThat(ship1,is(one));

    }

    @Test
    public void updateShip() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getSize() {
    }

    @Test
    public void delete() {
    }
}