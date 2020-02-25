package homeworks.seabatle.servises.coordinates;

import homeworks.seabatle.exception.IncorrectInputParseExeption;
import homeworks.seabatle.functional.Calculator;
import homeworks.seabatle.functional.StringMaker;
import homeworks.seabatle.servises.fillservise.GorrizontalFillStrategy;
import homeworks.seabatle.servises.fillservise.VerticalFillStrategy;
import homeworks.seabatle.servises.fillservise.stratinterface.FillStrategy;

import java.util.Arrays;
import java.util.List;

public class LocationServiceImpl implements LocationService {
    private static final List<String> strCoords = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J");
    private static final int XCONST = 10;
    private static final int MINC = 1;
    private Calculator calculator;
    public LocationServiceImpl(){
        calculator = (x, y) -> x * XCONST + y;
    }
    @Override
    public int[] getCoordinates(String request) {
        StringMaker maker = string -> string.replaceAll("[A-Z]", "");
        StringMaker alert = string -> string + " is not valid";
        if (0 < request.length() && request.length() <= 3) {
            int x = checkSrting(request.substring(0, 1));
            int y = checkInt(maker.make(request));
            return getCoordinates(x, y);
        } else if (5 <= request.length() && request.length() <= 7) {
            String[] coords = request.split("\\s+");
            if (coords.length == 2) {
                int x1 = checkSrting(coords[0].substring(0, 1));
                int y1 = checkInt(maker.make(coords[0]));
                int x2 = checkSrting(coords[1].substring(0, 1));
                int y2 = checkInt(maker.make(coords[1]));
                return getCoordinates(x1, y1, x2, y2);
            } else {
                throw new IncorrectInputParseExeption(alert.make(request+"не тут"));
            }
        } else {
            throw new IncorrectInputParseExeption(alert.make(request+"а тут"));
        }
    }
    public String translateRequest(int coord){
        String x = strCoords.get(coord/10);
        String y = String.valueOf(coord%10 + 1);
        return x + y;
    }

    private int[] getCoordinates(int x, int y) {
        int[] ints = {calculator.calc(x,y)};
        return ints;
    }

    private int[] getCoordinates(int x1, int y1, int x2, int y2) {
        Calculator newLength = (a, b) -> Math.abs(a-b) + 1;
        FillStrategy strategy;
        int firstCell;
        int length;
        if (x2 - x1 == 0) {
            int minY = Math.min(y1,y2);
            firstCell = calculator.calc(x1,minY);
            length = newLength.calc(y2,y1);
            strategy = new GorrizontalFillStrategy();

        } else {
            int minX = Math.min(x1,x2);
            firstCell = calculator.calc(minX,y1);
            length = newLength.calc(x2,x1);
            strategy = new VerticalFillStrategy();
        }
        return strategy.getShipCoords(firstCell,length);
    }


    private int checkInt(String request) {
        try {
            int coordinate = Integer.parseInt(request)-1;
            if (0 <= coordinate && coordinate <= 9) {
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
