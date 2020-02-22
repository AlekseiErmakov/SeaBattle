package homeworks.seabatle.board;

import homeworks.seabatle.board.field.Field;
import homeworks.seabatle.board.field.StrikeResult;
import homeworks.seabatle.players.Player;
import homeworks.seabatle.exception.shoot.IncorrectShootRequestException;
import homeworks.seabatle.servises.coordinates.LocationService;


public class GameBoard {
    private Player playerOne;
    private Player playerTwo;
    LocationService service;

    public GameBoard(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public StrikeResult getPlayerStrikeResult(String request, Player player) {
        Field playerField = player.getField();
        if (request.length() == 2) {
            int coord = service.getCoordinates(request)[0];
            StrikeResult result = playerField.getStrikeRes(coord);
            return result;
        } else {
            throw new IncorrectShootRequestException(request);
        }

    }

    public void printBatlefield() {
        System.out.println(playerOne.getField().print());
        System.out.println(playerTwo.getField().print());
    }

}
