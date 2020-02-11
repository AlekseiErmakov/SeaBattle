package homeworks.seabatle.aplication.parser;

import homeworks.seabatle.bean.request.DoublePointRequest;
import homeworks.seabatle.bean.request.SinglePointRequest;
import homeworks.seabatle.exception.parser.IncorrectInputParseExeption;

import java.util.Arrays;
import java.util.List;

public class RequestParser {
    private static final List<String> strCoords = Arrays.asList("A","B","C","D","E","F","G","H","I","J");

    public SinglePointRequest getOneCoord(String request){

        if (request.length() == 2){
            String xStr = request.substring(1,2);
            String yStr = request.substring(0,1);
            int x = chekInt(xStr)-1;
            int y;
            if (strCoords.contains(yStr) && 0 <= x && x <= 9){
                y = strCoords.indexOf(yStr);
                return new SinglePointRequest(x,y);
            }
                throw new IncorrectInputParseExeption(request);
        }else {
            throw new IncorrectInputParseExeption(request);
        }
    }
    public DoublePointRequest getTwoCoords(String request){
        if (request.length() == 5){
            String xOneStr = request.substring(1,2);
            String yOneStr = request.substring(0,1);
            String xTwoStr = request.substring(4);
            String yTwoStr = request.substring(3,4);
            int x1 = chekInt(xOneStr) - 1;
            int x2 = chekInt(xTwoStr) - 1;
            int y1;
            int y2;
            if (strCoords.contains(yOneStr) && strCoords.contains(yTwoStr)
                    && 0 <= x1 && x1 <=10 && 0 <= x2 && x2 <= 10){
                y1 = strCoords.indexOf(yOneStr);
                y2 = strCoords.indexOf(yTwoStr);
                return new DoublePointRequest(x1,y1,x2,y2);
            } else {
                throw new IncorrectInputParseExeption(request);
            }
        }else {
            throw new IncorrectInputParseExeption(request);
        }
    }
    private int chekInt(String request){
        try{
            return Integer.parseInt(request);
        } catch (NumberFormatException ex){
            throw new IncorrectInputParseExeption(request);
        }
    }
}
