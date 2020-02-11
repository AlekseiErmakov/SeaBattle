package homeworks.seabatle.exception.create;

import homeworks.seabatle.exception.IncorrectRequestException;

public class IncorrectCreateShipRecuestException extends IncorrectRequestException {
    public IncorrectCreateShipRecuestException(String request) {
        super(request);
    }
}
