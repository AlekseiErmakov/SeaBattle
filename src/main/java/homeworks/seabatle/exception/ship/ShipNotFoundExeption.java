package homeworks.seabatle.exception.ship;

import homeworks.seabatle.exception.IncorrectRequestException;

public class ShipNotFoundExeption extends IncorrectRequestException {
    public ShipNotFoundExeption(String request) {
        super(request);
    }
}
