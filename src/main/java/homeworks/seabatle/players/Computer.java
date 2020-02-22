package homeworks.seabatle.players;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class Computer extends Player  {
    String name = "Skynet";
    ArrayList<Integer> shoots;
    public Computer(){
        shoots = new ArrayList<>();
    }
    private List<Integer> target;

    public Integer getShoot(){
        int i = getRandomInt(100);
        return i;
    }
    private int getRandomInt(int bound){
        Random r = new Random();
        int shoot = r.nextInt(bound);
        if (shoots.contains(shoot)){
            return getRandomInt(bound);
        } else {
            return shoot;
        }
    }
}
