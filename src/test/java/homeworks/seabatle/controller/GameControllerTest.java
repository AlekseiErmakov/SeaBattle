package homeworks.seabatle.controller;

import homeworks.seabatle.servises.coordinates.LocationService;
import homeworks.seabatle.servises.coordinates.LocationServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.Assert.*;

public class GameControllerTest {

    GameController controller;
    @Before
    public void setUp() throws Exception {
        controller = new GameController();
    }
    @Test
    public void chooseRegime() {
        BufferedReader reader = getReader("1player");
        String expected = "You chose 1player mode";
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
        assertEquals(expected,actual);
    }
    @Test
    public void choose2Regime() {
        BufferedReader reader = getReader("2players");
        String expected = "You chose 2players mode";
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
        assertEquals(expected,actual);
    }
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
        assertEquals(expected,actual);
    }

    @Test
    public void createGameBoard() {
        chooseRegime();
        nameUsers();
        String request = String.format("auto");
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
        assertEquals(expected,actual);
    }

    @Test
    public void printBattleField() {
        chooseRegime();
        nameUsers();
        createGameBoard();
        String battleField = controller.printBattleField();
        assertEquals(562,battleField.length());
    }


    @Test
    public void runBattle() {

        chooseRegime();
        nameUsers();
        createGameBoard();
        printBattleField();
        LocationService service = new LocationServiceImpl();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <100 ; i++){
            sb.append(service.translateRequest(i));
            sb.append("\n");
        }
        String request = sb.toString();
        BufferedReader reader = getReader(request);
        String expected = "is a winner!!! Congratulations, admiral!";
        String actual;
        try {
            actual = controller.runBattle(reader,0);
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assertTrue(actual.contains(expected));


    }

    @Test
    public void testManualShipBuild (){
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
        assertEquals(expected,actual);
    }
    private BufferedReader getReader(String request){
        StringReader reader = new StringReader(request);
        return new BufferedReader(reader);
    }
}