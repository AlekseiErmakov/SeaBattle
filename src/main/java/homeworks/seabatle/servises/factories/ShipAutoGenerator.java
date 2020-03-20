package homeworks.seabatle.servises.factories;

import homeworks.seabatle.exception.ShipCreationRequestException;
import homeworks.seabatle.model.board.PlayerShipsRepository;
import homeworks.seabatle.model.board.ShipsRepository;
import homeworks.seabatle.model.ship.Ship;
import homeworks.seabatle.myenum.ShipType;
import homeworks.seabatle.servises.fillservise.FillStrategy;
import homeworks.seabatle.servises.fillservise.GorrizontalFillStrategy;
import homeworks.seabatle.servises.fillservise.VerticalFillStrategy;

import java.util.Random;



public class ShipAutoGenerator implements Generator {

    private static final int BOATBOUND = 100;
    private static final int DESTROYERLIVES = 2;
    private static final int CRUISERLIVES = 3;
    private static final int BATTLESHIPLIVES = 4;

<<<<<<< HEAD:src/main/java/homeworks/seabatle/servises/factories/ShipAutoGenerator.java
    /**
     * method uses ShipType order for code optimization
     *
     * @return autogenerated collection of ships
     */
    @Override
    public ShipsRepository getGeneratedRepository() {
        ShipsRepository repository = new PlayerShipsRepository();
=======

    @Override
    public PlayerShipsRepository getGeneratedRepository() {
        PlayerShipsRepository repository = new PlayerShipsRepository();
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6:src/main/java/homeworks/seabatle/servises/generator/ShipAutoGenerator.java
        ShipFactory factory = new ShipFactory();
        for (ShipType type : ShipType.values()) {
            for (int j = 0; j < type.ordinal() + 1; j++) {
                Ship ship = factory.getShip(type);
                addToRepository(ship, repository);
            }
        }
        return repository;
    }

    /**
     * method for adding ships to repository,
     * repository cheks, that coordinates are valid and then add the
     * ship, if coordinates are not valid, repository throws exception
     * and generator rebuild the ship
     *
     * @param ship       randomly generated
     * @param repository collection, where ships are located
     */
    private void addToRepository(Ship ship, ShipsRepository repository) {
        boolean isGenerating = true;
        while (isGenerating) {
            try {
                int[] coords = getGeneratedCoordinates(ship.getType());
                if (checkCoords(coords)) {
                    ship.setCoords(coords);
                    repository.addShip(ship);
                    isGenerating = false;
                } else {
                    isGenerating = true;
                }
            } catch (ShipCreationRequestException ex) {
                isGenerating = true;
            }
        }

    }

    /**
     * method choose build strategy, depends on ship type
     *
     * @param type enum
     * @return array with ship coordinates
     */
    private int[] getGeneratedCoordinates(ShipType type) {
        if (type.equals(ShipType.BOAT)) {
            return getOneDeckShipCoordinate();
        } else {
            return getMultiDeckShipCoordinate(type);
        }
    }

    /**
     * method produces coordinate for one deck ship
     *
     * @return Array with ship coordinates
     */
    private int[] getOneDeckShipCoordinate() {
        Random random = new Random();
        int[] coords = {random.nextInt(BOATBOUND)};
        return coords;
    }

    /**
     * method that produses amount of lifes
     * (for ships with amount of decks more then one),
     * depends on ship type
     *
     * @param type enum
     * @return array with ship coords;
     */
    private int[] getMultiDeckShipCoordinate(ShipType type) {
        int bound = 100;
        int lives = 0;
        switch (type) {
            case DESTROYER:
                lives = DESTROYERLIVES;
                break;
            case CRUISER:
                lives = CRUISERLIVES;
                break;
            case BATTLESHIP:
                lives = BATTLESHIPLIVES;
                break;
        }
        return getMultiDeckShipCoordinate(bound, lives);
    }

    /**
     * method randomly choose the strategy,
     * how the ship will be build vertical or horizontal,
     * if chosen strategy is horizontal also check, are there
     * enough place for ship, if is not enough method use vertical strategy
     *
     * @param bound integer from 0 to 99(if field is 10x10)
     * @param lives amount of cells for the ship
     * @return array with coordinates
     */
    private int[] getMultiDeckShipCoordinate(int bound, int lives) {
        FillStrategy strategy;
        Random random = new Random();
        int firstCell = random.nextInt(bound);
        int choice = random.nextInt(2);
        if (choice == 0 && isEnoughPlace(firstCell, lives)) {
            strategy = new GorrizontalFillStrategy();
            return strategy.getShipCoords(firstCell, lives);
        } else {
            strategy = new VerticalFillStrategy();
            return strategy.getShipCoords(firstCell, lives);
        }
    }

    /**
     * method validate array of coordinates. Each coordinate
     * should be from 0 to 99
     *
     * @param coords array with length from 1 to 4
     * @return true if the condition is met
     */
    public boolean checkCoords(int[] coords) {
        boolean isValid = true;
        for (int coord : coords) {
            if (coord > 99) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * method check is there enough place in the line
     * to place a ship
     *
     * @param firstCell is int from 0 to 99
     * @param lives     is int from 1 to 4
     * @return true if there is enough place
     */
    private boolean isEnoughPlace(int firstCell, int lives) {
        return (firstCell % 10 + lives - 1) < 9;
    }

}
