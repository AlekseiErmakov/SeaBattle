package homeworks.seabatle.servises.coordinates;

import homeworks.seabatle.exception.IncorrectInputParseExeption;

public interface LocationService {
    int[] getCoordinates(String request) throws IncorrectInputParseExeption;
    String translateRequest(int coord);
}
