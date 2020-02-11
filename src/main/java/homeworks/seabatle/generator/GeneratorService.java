package homeworks.seabatle.generator;


import homeworks.seabatle.aplication.parser.RequestParser;
import homeworks.seabatle.bean.request.DoublePointRequest;
import homeworks.seabatle.bean.request.SinglePointRequest;
import homeworks.seabatle.bean.ships.*;
import homeworks.seabatle.bean.ships.repository.PlayerShipsRepository;
import homeworks.seabatle.bean.ships.repository.ShipsRepository;
import homeworks.seabatle.exception.ship.ShipCreationRequestExeption;

public class GeneratorService implements Generateble{
    ShipsRepository repository;
    RequestParser parser;

    public GeneratorService(){
        repository = new PlayerShipsRepository();
        parser = new RequestParser();
    }

    public ShipsRepository generate(){
        System.out.println("Введите координаты 4х палубника");

        return new PlayerShipsRepository();
    }
    public Ship generateFourDeckShip(String coords){
        DoublePointRequest request = parser.getTwoCoords(coords);
        if (request.getLength() == 4){
            Ship ship = new FourDeckShip(request);
            return ship;
        }
        throw new ShipCreationRequestExeption(coords + " ship is to shot or to small");
    }
    public Ship generateThreeDeckShip(String coords){
        DoublePointRequest request = parser.getTwoCoords(coords);
        if (request.getLength() == 3){
            Ship ship = new ThreeDeckShip(request);
            return ship;
        }
        throw new ShipCreationRequestExeption(coords + " ship is to shot or to small");
    }
    public Ship generateTwoDeckShip(String coords){
        DoublePointRequest request = parser.getTwoCoords(coords);
        if (request.getLength() == 2){
            Ship ship = new TwoDeckShip(request);
            return ship;
        }
        throw new ShipCreationRequestExeption(coords + " ship is to shot or to small");
    }
    public Ship generateOneDeckShip(String coords){
        SinglePointRequest request = parser.getOneCoord(coords);
        return new OneDeckShip(request);
    }
    private boolean isMultiDeck (int x1, int y1, int x2, int y2, int coef ){
        return Math.abs(x2 - x1) == coef && (y2 - y1) == 0 || Math.abs(y2 - y1) == coef && (x2 - x1) == 0;
    }


}