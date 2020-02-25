package homeworks.seabatle.servises.coordinates;

import java.util.ArrayList;
import java.util.List;

public class ShipAreaCreator {
    public List<Integer> getCrossArea(Integer target){
        List<Integer> targetList = new ArrayList<>();
        targetList.add(target - 1);
        targetList.add(target + 1);
        targetList.add(target - 10);
        targetList.add(target + 10);
        return targetList;
    }
    public List<Integer> getDiagonalArea(Integer target){
        List<Integer> targetList = new ArrayList<>();
        targetList.add(target - 9);
        targetList.add(target + 9);
        targetList.add(target - 11);
        targetList.add(target + 11);
        return targetList;
    }
}
