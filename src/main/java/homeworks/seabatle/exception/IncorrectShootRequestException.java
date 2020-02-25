package homeworks.seabatle.exception;

import homeworks.seabatle.exception.IncorrectRequestException;

public class IncorrectShootRequestException extends IncorrectRequestException {
    public IncorrectShootRequestException(String request) {
        super(request + " is not valid");
    }
}