package homeworks.seabatle.exception;

import homeworks.seabatle.exception.IncorrectRequestException;

public class ShipNotFoundExeption extends IncorrectRequestException {
    public ShipNotFoundExeption(String request) {
        super(request);
    }
}
