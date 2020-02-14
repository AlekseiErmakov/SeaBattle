package homeworks.seabatle.bean.coordinates;


import homeworks.seabatle.exception.parser.IncorrectInputParseExeption;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class Coordinate {
    @Getter
    int[] coords;
    private static final List<String> strCoords = Arrays.asList("A","B","C","D","E","F","G","H","I","J");
    public Coordinate(String userInput){
        if (0 < userInput.length() && userInput.length() <= 3){
            coords = getOnePointCoord(userInput);
        } else if (5 <= userInput.length() && userInput.length() <= 7){
            coords = getTwoPointCoord(userInput);
        } else {
            throw new IncorrectInputParseExeption(userInput + " is not valid");
        }
    }
    private int[] getOnePointCoord(String userInput){
        int[] coords = new int[2];
        String yStr = userInput.replaceAll("[A-Z]","");
        String xStr = userInput.substring(0,1);
        coords[1] = chekInt(yStr)-1;

        if (strCoords.contains(xStr) && 0 <= coords[1] && coords[1] <= 9){
            coords[0] = strCoords.indexOf(xStr);
            int i = coords[0];
            return coords;
        }
        throw new IncorrectInputParseExeption(xStr + " is not a coordinate");

    }
    private int[] getTwoPointCoord(String userInput){
        String[] withoutLetters = userInput.replaceAll("[A-Z]","").split("\\s+");
        String[] withoutNums = userInput.replaceAll("[0-9]","").split("\\s+");
        int[] coords = new int[4];
        try {
            coords[1] = chekInt(withoutLetters[0]) - 1;
            coords[3] = chekInt(withoutLetters[1]) - 1;
            if (strCoords.contains(withoutNums[0]) && strCoords.contains(withoutNums[1])
                    && 0 <= coords[1] && coords[1] <=10 && 0 <= coords[3] && coords[3] <= 10){
                coords[0] = strCoords.indexOf(withoutNums[0]);
                coords[2] = strCoords.indexOf(withoutNums[1]);
                return coords;
            } else {
                throw new IncorrectInputParseExeption(userInput + " is not a ccord");
            }
        }catch (ArrayIndexOutOfBoundsException e){
            throw new IncorrectInputParseExeption(userInput + " is not a coords");
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
