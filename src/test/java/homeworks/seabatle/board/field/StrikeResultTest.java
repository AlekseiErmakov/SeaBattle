package homeworks.seabatle.board.field;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class StrikeResultTest {

    @Test
    public void getDescription() {
        String[] expected = {"killed", "wounded", "missed", "shooted", "loseed"};
        String[] actual = new String[5];
        int i = 0;
        for (StrikeResult result : StrikeResult.values()){
            actual[i] = result.getDescription();
            i++;
        }
        assertArrayEquals(expected,actual);
    }
}