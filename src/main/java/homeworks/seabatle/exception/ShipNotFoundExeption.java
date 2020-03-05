package homeworks.seabatle.exception;


public class ShipNotFoundExeption extends IncorrectRequestException {
    public ShipNotFoundExeption(String request) {
        super(request);
    }
}
