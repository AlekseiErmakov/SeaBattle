package homeworks.seabatle.exception;



public class IncorrectInputParseExeption extends IncorrectRequestException {
    public IncorrectInputParseExeption(String request) {
        super(request);
    }
}
