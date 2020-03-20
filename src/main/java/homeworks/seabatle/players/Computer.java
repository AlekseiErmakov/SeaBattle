package homeworks.seabatle.players;


import homeworks.seabatle.board.field.StrikeResult;
import homeworks.seabatle.servises.coordinates.ShipAreaCreatorImpl;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class Computer extends Player {
    @Getter
    private String name = "Computer";
    private List<Integer> targets;
    private List<Integer> targetArea;
    @Getter
    @Setter
    private List<Integer> ship;
    private boolean isWounded = false;
    @Setter
    private int currentTarget;
    private ShipAreaCreatorImpl creator;
    private Random r;

    public Computer() {
        r = new Random();
        targets = getTargets();
        ship = new ArrayList<>();
        creator = new ShipAreaCreatorImpl();

    }

    public void notifyShootResult(StrikeResult result) {
        switch (result) {
            case KILL:
                isWounded = false;
                updateTargets();
                clearShip();
                break;
            case WOUND:
                isWounded = true;
                updateTargets(currentTarget);
                targetArea = makeArea(currentTarget);
                addToShip();
                break;
            case MISS:
                updateTargets(currentTarget);
                break;
        }
    }

    public Integer shoot() {
        int coordinate;
        if (isWounded) {
            coordinate = getTargetedInt();
        } else {
            coordinate = getRandomInt();
        }
        if (targets.contains(coordinate)) {
            setCurrentTarget(coordinate);
            return coordinate;
        } else {
            return shoot();
        }
    }

    private int getRandomInt() {

        int index = r.nextInt(targets.size());
        return targets.get(index);
    }

    private int getTargetedInt() {
        if (ship.size() == 1) {
            return makeAreaShoot();
        } else {
            return makeLineShoot();
        }
    }

    private int makeAreaShoot() {
        int index = r.nextInt(targetArea.size());
        int target = targetArea.get(index);
        targetArea.remove(index);
        return target;
    }

    private int makeLineShoot() {
        Collections.sort(ship);
        int step = ship.get(1) - ship.get(0);
        int choose = r.nextInt(2);
        if (choose == 0) {
            return ship.get(0) - step;
        } else {
            return ship.get(ship.size() - 1) + step;
        }
    }

    private List<Integer> makeArea(int target) {
        return creator.getCrossArea(target);
    }

    private void updateTargets(Integer target) {
        targets.remove(target);
    }

    private void updateTargets() {
        List<Integer> tempCoordinates = new ArrayList<>();
        for (int coord : ship) {
            tempCoordinates.addAll(creator.getCrossArea(coord));
            tempCoordinates.addAll(creator.getDiagonalArea(coord));
        }
        updateTargets(tempCoordinates);
    }

    private void updateTargets(List<Integer> tempCoords) {
        for (Integer coord : tempCoords) {
            if (targets.contains(coord)) {
                targets.remove(coord);
            }
        }
    }

    private void addToShip() {
        ship.add(currentTarget);
    }

    private void clearShip() {
        ship.clear();
    }

    private List<Integer> getTargets() {
        List<Integer> targets = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            targets.add(i);
        }
        return targets;
    }

}
