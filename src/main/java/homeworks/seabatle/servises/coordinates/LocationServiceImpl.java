package homeworks.seabatle.servises.coordinates;

import homeworks.seabatle.exception.parser.IncorrectInputParseExeption;
import homeworks.seabatle.servises.fillservise.GorrizontalFillStrategy;
import homeworks.seabatle.servises.fillservise.VerticalFillStrategy;
import homeworks.seabatle.servises.fillservise.stratinterface.FillStrategy;

import java.util.Arrays;
import java.util.List;

public class LocationServiceImpl implements LocationService {
    private static final List<String> strCoords = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");

    @Override
    public int[] getCoordinates(String request) {
        if (0 < request.length() && request.length() <= 3) {
            int x = checkSrting(request.substring(0, 1));
            int y = checkInt(request.replaceAll("[A-Z]", ""));
            return getCoordinates(x, y);
        } else if (5 <= request.length() && request.length() <= 7) {
            String coords[] = request.split("\\s+");
            if (coords.length == 2) {
                int x1 = checkSrting(coords[0].substring(0, 1));
                int y1 = checkInt(coords[0].replaceAll("[A-Z]", ""));
                int x2 = checkSrting(coords[1].substring(0, 1));
                int y2 = checkInt(coords[1].replaceAll("[A-Z]", ""));
                return getCoordinates(x1, y1, x2, y2);
            } else {
                throw new IncorrectInputParseExeption(request + " is not valid");
            }
        } else {
            throw new IncorrectInputParseExeption(request + " is not valid");
        }
    }

    private int[] getCoordinates(int x, int y) {
        int coef = 10;
        --y;
        int xy = x * coef + y;
        int[] coords = {xy};
        return coords;
    }

    private int[] getCoordinates(int x1, int y1, int x2, int y2) {
        FillStrategy strategy;
        --y1;
        --y2;
        int konst = 10;
        int firstCell;
        int length;
        if (x2 - x1 == 0) {
            strategy = new GorrizontalFillStrategy();
            int minY = y1 < y2 ? y1 : y2;
            firstCell = x2 * konst + minY;
            length = Math.abs(y2 - y1) + 1;
        } else {
            strategy = new VerticalFillStrategy();
            int minX = x1 < x2 ? x1 : x2;
            firstCell = minX * konst + y1;
            length = (Math.abs(x2 - x1) + 1);
        }
        return strategy.getShipCoords(firstCell, length);
    }


    private int checkInt(String request) {
        try {
            int coordinate = Integer.parseInt(request);
            if (1 <= coordinate && coordinate <= 10) {
                return coordinate;
            } else {
                throw new IncorrectInputParseExeption(request + " should be from \"1\" to \"10\"");
            }
        } catch (NumberFormatException ex) {
            throw new IncorrectInputParseExeption(request + " is not Integer");
        }
    }

    private int checkSrting(String request) {
        if (strCoords.contains(request)) {
            return strCoords.indexOf(request);
        } else {
            throw new IncorrectInputParseExeption(request + " should be from \"A\" to \"J\"");
        }
    }
}
