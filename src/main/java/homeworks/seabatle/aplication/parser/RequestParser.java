package homeworks.seabatle.aplication.parser;


import homeworks.seabatle.bean.request.DoublePointRequest;
import homeworks.seabatle.bean.request.SinglePointRequest;
import homeworks.seabatle.exception.parser.IncorrectInputParseExeption;

import java.util.Arrays;
import java.util.List;

public class RequestParser {
    private static final List<String> strCoords = Arrays.asList("A","B","C","D","E","F","G","H","I","J");

    public SinglePointRequest getOneCoord(String request){

        if (1 < request.length() && request.length() <= 3){
            String yStr = request.replaceAll("[A-Z]","");
            String xStr = request.substring(0,1);
            int x;
            int y = chekInt(yStr)-1;
            if (strCoords.contains(xStr) && 0 <= y && y <= 9){
                x = strCoords.indexOf(xStr);
                return new SinglePointRequest(x,y);
            }
                throw new IncorrectInputParseExeption(xStr + " is not a coord");
        }else {
            throw new IncorrectInputParseExeption(request + " are not valid coords ");
        }
    }
    public DoublePointRequest getTwoCoords(String request){
        if ( 4 < request.length() && request.length() <= 7){
            String[] withoutLetters = request.replaceAll("[A-Z]","").split("\\s+");
            String[] withoutNums = request.replaceAll("[0-9]","").split("\\s+");
            String xOneStr;
            String xTwoStr;
            String yOneStr;
            String yTwoStr;
            try {
                xOneStr = withoutNums[0];
                xTwoStr = withoutNums[1];

                yOneStr = withoutLetters[0];
                yTwoStr = withoutLetters[1];
            }catch (ArrayIndexOutOfBoundsException e){
                throw new IncorrectInputParseExeption(request + " is not a coords");
            }

            int x1;
            int x2;
            int y1 = chekInt(yOneStr) - 1;
            int y2 = chekInt(yTwoStr) - 1;
            if (strCoords.contains(xOneStr) && strCoords.contains(xTwoStr)
                    && 0 <= y1 && y1 <=10 && 0 <= y2 && y2 <= 10){
                x1 = strCoords.indexOf(xOneStr);
                x2 = strCoords.indexOf(xTwoStr);
                return new DoublePointRequest(x1,y1,x2,y2);
            } else {
                throw new IncorrectInputParseExeption(request + " is not a ccord");
            }
        }else {
            throw new IncorrectInputParseExeption(request + " are not valid coords.");
        }
    }
    private int chekInt(String request){
        try{
            return Integer.parseInt(request);
        } catch (NumberFormatException ex){
            throw new IncorrectInputParseExeption(request + " is not Integer");
        }
    }
}
