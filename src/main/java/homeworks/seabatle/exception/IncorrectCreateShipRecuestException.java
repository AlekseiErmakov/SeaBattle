package homeworks.seabatle.exception;

import homeworks.seabatle.exception.IncorrectRequestException;

public class IncorrectCreateShipRecuestException extends IncorrectRequestException {
    public IncorrectCreateShipRecuestException(String request) {
        super(request);
    }
}
