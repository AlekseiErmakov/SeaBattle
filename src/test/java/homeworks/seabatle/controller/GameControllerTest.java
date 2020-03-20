package homeworks.seabatle.controller;

<<<<<<< HEAD
import homeworks.seabatle.servises.locationservice.LocationService;
import homeworks.seabatle.servises.locationservice.LocationServiceImpl;
=======
import homeworks.seabatle.servises.coordinates.LocationService;
import homeworks.seabatle.servises.coordinates.LocationServiceImpl;
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
<<<<<<< HEAD
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
=======

import static org.junit.Assert.*;
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6

public class GameControllerTest {

    GameController controller;
<<<<<<< HEAD

=======
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
    @Before
    public void setUp() throws Exception {
        controller = new GameController();
    }
<<<<<<< HEAD

    @Test
    public void chooseRegime() {
        BufferedReader reader = getReader("singleplayer");
        String expected = "You chose singleplayer mode";
=======
    @Test
    public void chooseRegime() {
        BufferedReader reader = getReader("1player");
        String expected = "You chose 1player mode";
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
        String actual;
        try {
            actual = controller.chooseMode(reader);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
        assertEquals(expected, actual);
    }

    @Test
    public void choose2Regime() {
        BufferedReader reader = getReader("multiplayer");
        String expected = "You chose multiplayer mode";
=======
        assertEquals(expected,actual);
    }
    @Test
    public void choose2Regime() {
        BufferedReader reader = getReader("2players");
        String expected = "You chose 2players mode";
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
        String actual;
        try {
            actual = controller.chooseMode(reader);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
        assertEquals(expected, actual);
    }

=======
        assertEquals(expected,actual);
    }
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
    @Test
    public void nameUsers() {
        chooseRegime();
        BufferedReader reader = getReader("Alex");
        String expected = "Hello Alex! Hello Computer!";
        String actual;
        try {
            actual = controller.nameUsers(reader);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
        assertEquals(expected, actual);
=======
        assertEquals(expected,actual);
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
    }

    @Test
    public void createGameBoard() {
        chooseRegime();
        nameUsers();
<<<<<<< HEAD
        String request = "auto";
=======
        String request = String.format("auto");
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
        BufferedReader reader = getReader(request);
        String expected = "Game Board is created!";
        String actual;
        try {
            actual = controller.createGameBoard(reader);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
        assertEquals(expected, actual);
=======
        assertEquals(expected,actual);
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
    }

    @Test
    public void printBattleField() {
        chooseRegime();
        nameUsers();
        createGameBoard();
        String battleField = controller.printBattleField();
<<<<<<< HEAD
        assertEquals(562, battleField.length());
=======
        assertEquals(562,battleField.length());
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
    }


    @Test
    public void runBattle() {
<<<<<<< HEAD
=======

>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
        chooseRegime();
        nameUsers();
        createGameBoard();
        printBattleField();
        LocationService service = new LocationServiceImpl();
<<<<<<< HEAD
        String request = IntStream
                .range(0, 100)
                .mapToObj(i -> service.translateRequest(i) + "\n")
                .collect(Collectors.joining());
=======
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <100 ; i++){
            sb.append(service.translateRequest(i));
            sb.append("\n");
        }
        String request = sb.toString();
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
        BufferedReader reader = getReader(request);
        String expected = "is a winner!!! Congratulations, admiral!";
        String actual;
        try {
<<<<<<< HEAD
            actual = controller.runBattle(reader, 0);
=======
            actual = controller.runBattle(reader,0);
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assertTrue(actual.contains(expected));
<<<<<<< HEAD
    }

    @Test
    public void testManualShipBuild() {
=======


    }

    @Test
    public void testManualShipBuild (){
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
        String request = "manual\nA1 A4\nF2 H2\nJ7 J9\nG10 I10\nA9 B9\nC1 C2\nF7 F8\nA5 A6\nH10\nA6\nI5\nD9";
        chooseRegime();
        nameUsers();
        System.out.println(request);
        BufferedReader reader = getReader(request);
        String expected = "Game Board is created!";
        String actual;
        try {
            actual = controller.createGameBoard(reader);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
<<<<<<< HEAD
        assertEquals(expected, actual);
    }

    private BufferedReader getReader(String request) {
=======
        assertEquals(expected,actual);
    }
    private BufferedReader getReader(String request){
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
        StringReader reader = new StringReader(request);
        return new BufferedReader(reader);
    }
}