package homeworks.seabatle.exception.ship;

import homeworks.seabatle.exception.IncorrectRequestException;

public class ShipCreationRequestExeption extends IncorrectRequestException {
    public ShipCreationRequestExeption(String request) {
        super(request);
    }
}
