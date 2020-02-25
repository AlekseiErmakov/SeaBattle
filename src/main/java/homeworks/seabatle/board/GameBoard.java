package homeworks.seabatle.board;

import homeworks.seabatle.board.field.Field;
import homeworks.seabatle.board.field.StrikeResult;
import homeworks.seabatle.players.Player;
import homeworks.seabatle.exception.IncorrectShootRequestException;
import homeworks.seabatle.servises.coordinates.LocationService;
import homeworks.seabatle.servises.coordinates.LocationServiceImpl;

import java.util.List;


public class GameBoard {
    private Player playerOne;
    private Player playerTwo;
    LocationService service;

    public GameBoard(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        service = new LocationServiceImpl();
    }

    public StrikeResult getPlayerStrikeResult(String request, Player player) {
        Field playerField = player.getField();
        if (request.length() == 2 || request.length() == 3) {
            System.out.println(request);
            int coord = service.getCoordinates(request)[0];
            StrikeResult result = playerField.getStrikeRes(coord);
            return result;
        } else {
            throw new IncorrectShootRequestException(request);
        }

    }
    public StrikeResult getPlayerStrikeResult(int coord, Player player) {
        Field playerField = player.getField();
        System.out.println(service.translateRequest(coord));
        return playerField.getStrikeRes(coord);
    }

    public void printBatlefield() {

        List<String> pOneField = playerOne.getField().getFieldArray();
        List<String> pTwoField = playerTwo.getField().getFieldArray();
        System.out.println(playerOne.getName() + "                            " + playerTwo.getName());
        for (int i = 0; i < pOneField.size(); i++){
            System.out.println(pOneField.get(i) + "          " + pTwoField.get(i));
        }
    }

}
