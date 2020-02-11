package homeworks.seabatle.board;

import homeworks.seabatle.bean.field.Field;
import homeworks.seabatle.bean.field.StrikeResult;
import homeworks.seabatle.bean.players.Player;
import homeworks.seabatle.exception.shoot.IncorrectShootRequestException;

import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private Player playerOne;
    private Player playerTwo;

    public GameBoard(Player playerOne, Player playerTwo){
       this.playerOne = playerOne;
       this.playerTwo = playerTwo;
    }
    public StrikeResult getPlayerStrikeResult(String request, Player player){
        Field playerField = player.getField();
        if (request.length() == 2){
            int x = checkInt(request.replaceAll("[A-Z]",""));
            int y = yCoordInterpritator(request.substring(0,1));
            StrikeResult result = playerField.getStrikeRes(x,y);
            return result;
        } else {
            throw new IncorrectShootRequestException(request);
        }

    }
    public void printBatlefield(){
        System.out.println(playerOne.getField().print());
        System.out.println(playerTwo.getField().print());
    }

    private int yCoordInterpritator(String yCoord){
        List<String> stringCoords = Arrays.asList("A","B","C","D","E","F","G","H","I","J");
        if (stringCoords.contains(yCoord)){
            return stringCoords.indexOf(yCoord);
        }
        throw new IncorrectShootRequestException(yCoord);
    }

    private int checkInt(String xCoord){
        try {
            int x = Integer.valueOf(xCoord);
            return x;
        }catch (NumberFormatException e){
            throw new IncorrectShootRequestException(xCoord);
        }
    }
}
