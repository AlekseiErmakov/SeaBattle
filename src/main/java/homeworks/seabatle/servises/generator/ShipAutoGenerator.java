package homeworks.seabatle.servises.generator;

import homeworks.seabatle.board.field.repository.PlayerShipsRepository;
import homeworks.seabatle.exception.ShipCreationRequestExeption;
import homeworks.seabatle.servises.generator.geninteface.Generator;
import homeworks.seabatle.ship.Ship;
import homeworks.seabatle.servises.shipfactory.ShipFactory;
import homeworks.seabatle.ship.ShipType;
import homeworks.seabatle.servises.fillservise.GorrizontalFillStrategy;
import homeworks.seabatle.servises.fillservise.VerticalFillStrategy;
import homeworks.seabatle.servises.fillservise.stratinterface.FillStrategy;


import java.util.Random;


public class ShipAutoGenerator implements Generator {

    private static final int BOATBOUND = 100;
    private static final int DESTROYERLIVES = 2;
    private static final int CRUISERLIVES = 3;
    private static final int BATTLESHIPLIVES = 4;

    @Override
    public PlayerShipsRepository getGeneratedRepository() {
        return autoGenerateRep();
    }

    private PlayerShipsRepository autoGenerateRep() {
        PlayerShipsRepository repository = new PlayerShipsRepository();
        ShipFactory factory = new ShipFactory();
        for (ShipType type : ShipType.values()) {
            for (int j = 0; j < type.ordinal() + 1; j++) {
                Ship ship = factory.getShip(type);
                addToRepository(ship, repository);
            }
        }
        return repository;
    }

    private void addToRepository(Ship ship, PlayerShipsRepository repository) {
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
            } catch (ShipCreationRequestExeption ex) {
                isGenerating = true;
            }
        }

    }

    private int[] getGeneratedCoordinates(ShipType type) {
        if (type.equals(ShipType.BOAT)) {
            return getOneDeckShipCoordinate();
        } else {
            return getMultiDeckShipCoordinate(type);
        }
    }

    private int[] getOneDeckShipCoordinate() {
        Random random = new Random();
        int[] coords = {random.nextInt(BOATBOUND)};
        return coords;
    }

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

    public boolean checkCoords(int[] coords) {
        for (int i = 0; i < coords.length; i++) {
            if (coords[i] > 99) {
                return false;
            }
        }
        return true;
    }

    private boolean isEnoughPlace(int firstCell, int lives) {
        return (firstCell % 10 + lives - 1) < 9;
    }

}
