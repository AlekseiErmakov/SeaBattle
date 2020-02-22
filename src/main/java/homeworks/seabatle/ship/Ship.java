package homeworks.seabatle.ship;


import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
public class Ship {
    private int lives;
    private long id;
    private int[] coords;
    private ShipType type;

    public Ship() {
        id = new Random().nextLong();
    }
    public Ship(ShipType type, int lives){
        id = new Random().nextLong();
        this.type = type;
        this.lives = lives;
    }
    public void decrementLives() {
        lives--;
    }

    public void setCoords(int[] coords) {
        if (coords.length == lives) {
            this.coords = coords;
        } else throw new ShipCreationRequestExeption(" requested dimension is invalid");
    }
}
