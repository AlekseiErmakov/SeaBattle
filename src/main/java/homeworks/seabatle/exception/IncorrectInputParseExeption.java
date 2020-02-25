package homeworks.seabatle.exception;

import homeworks.seabatle.exception.IncorrectRequestException;

public class IncorrectInputParseExeption extends IncorrectRequestException {
    public IncorrectInputParseExeption(String request) {
        super(request);
    }
}
