package homeworks.seabatle.exception.parser;

import homeworks.seabatle.exception.IncorrectRequestException;

public class IncorrectInputParseExeption extends IncorrectRequestException {
    public IncorrectInputParseExeption(String request) {
        super(request + "is not valid");
    }
}
