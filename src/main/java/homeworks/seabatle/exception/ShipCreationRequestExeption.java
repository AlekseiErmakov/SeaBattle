package homeworks.seabatle.exception;


public class ShipCreationRequestExeption extends IncorrectRequestException {
    public ShipCreationRequestExeption(String request) {
        super(request);
    }
}
