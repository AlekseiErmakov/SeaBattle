package homeworks.seabatle.players;

import homeworks.seabatle.board.field.StrikeResult;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ComputerTest {
    private int[] ship = {12,13,14,15};
    private List<Integer> deckArea = Arrays.asList(2,11,13,22);
    private Computer computer;
    @Before
    public void setUp() throws Exception {
        computer = new Computer();
    }
    @Test
    public void testAreaShoot() {
        computer.setCurrentTarget(12);
        computer.notifyShootResult(StrikeResult.WOUND);
        assertTrue(deckArea.contains(computer.shoot()));
    }
    @Test
    public void testKillShoot() {
        computer.setCurrentTarget(16);
        computer.notifyShootResult(StrikeResult.MISS);
        computer.setCurrentTarget(11);
        computer.notifyShootResult(StrikeResult.MISS);
        List<Integer> gorWoundedShip = new ArrayList<>();
        gorWoundedShip.add(13);
        gorWoundedShip.add(14);
        computer.setShip(gorWoundedShip);
        computer.setCurrentTarget(14);
        computer.notifyShootResult(StrikeResult.WOUND);
        List<Integer> gorShoots = new ArrayList<>();
        gorShoots.add(computer.shoot());
        computer.notifyShootResult(StrikeResult.WOUND);
        gorShoots.add(computer.shoot());
        computer.notifyShootResult(StrikeResult.KILL);
        System.out.println(gorShoots.toString());
        assertTrue(gorShoots.contains(12));
        assertTrue(gorShoots.contains(15));



        computer.setCurrentTarget(36);
        computer.notifyShootResult(StrikeResult.MISS);
        computer.setCurrentTarget(86);
        computer.notifyShootResult(StrikeResult.MISS);
        List<Integer> verWoundedShip = new ArrayList<>();
        verWoundedShip.add(56);
        verWoundedShip.add(66);
        computer.setShip(verWoundedShip);
        computer.setCurrentTarget(66);
        computer.notifyShootResult(StrikeResult.WOUND);
        List<Integer> shoots = new ArrayList<>();
        shoots.add(computer.shoot());
        computer.notifyShootResult(StrikeResult.WOUND);
        System.out.println("neb");
        shoots.add(computer.shoot());
        computer.notifyShootResult(StrikeResult.KILL);
        System.out.println(shoots);
        assertTrue(shoots.contains(46));
        assertTrue(shoots.contains(76));
        computer.shoot();
    }
    @Test
    public void notifyShootResult() {
    }


}