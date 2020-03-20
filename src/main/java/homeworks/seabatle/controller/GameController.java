package homeworks.seabatle.controller;


import homeworks.seabatle.ship.Ship;
import homeworks.seabatle.servises.shipfactory.ShipFactory;
import homeworks.seabatle.ship.ShipType;
import homeworks.seabatle.board.field.Field;
import homeworks.seabatle.board.field.StrikeResult;
import homeworks.seabatle.players.Computer;
import homeworks.seabatle.players.Player;
import homeworks.seabatle.players.User;

import homeworks.seabatle.board.field.repository.PlayerShipsRepository;
import homeworks.seabatle.board.field.repository.ShipsRepository;
import homeworks.seabatle.board.GameBoard;
import homeworks.seabatle.exception.IncorrectRequestException;

import homeworks.seabatle.servises.coordinates.LocationService;
import homeworks.seabatle.servises.coordinates.LocationServiceImpl;
import homeworks.seabatle.servises.generator.ShipAutoGenerator;

import java.io.BufferedReader;
import java.io.IOException;

public class GameController {
    private Player playerOne;
    private Player playerTwo;
    private GameBoard gameBoard;
    private static final String AUTO = "auto";
    private static final String MANUAL = "manual";


    public String nameUsers(BufferedReader reader) {
        playerOne = new User();
        playerOne.setName(chooseName(reader));
        String pOneName = playerOne.getName();
        if (playerTwo instanceof User) {
            playerTwo.setName(chooseName(reader));
        }
        String pTwoname = playerTwo.getName();
        return String.format("Hello %s! Hello %s!",pOneName,pTwoname);
    }

    public String chooseMode(BufferedReader reader) {
        System.out.println("Choose mode 1player/2players");
        boolean isAllrite = false;
        String result = "";
        while (!isAllrite) {
            try {
                result = reader.readLine();
                playerTwo = getPlayerTwo(result);
                isAllrite = true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IncorrectRequestException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return String.format("You chose %s mode",result);
    }

    public String createGameBoard(BufferedReader reader) {
        ShipsRepository pOneShipsRep = generateField(playerOne, reader);
        ShipsRepository pTwoShipsRep = generateField(playerTwo, reader);
        Field playerOneField = new Field(pOneShipsRep);
        playerOne.setField(playerOneField);
        Field playerTwoField = new Field(pTwoShipsRep);
        playerTwo.setField(playerTwoField);
        gameBoard = new GameBoard(playerOne, playerTwo);
        return "Game Board is created!";
    }

    public String printBattleField(){
        return gameBoard.printBattlefield();
    }

    public String runBattle(BufferedReader reader,int speed) {
        boolean pOneWin = false;
        boolean pTwoWin = false;
        while (!pOneWin && !pTwoWin) {
            pOneWin = shoot(playerOne, playerTwo, reader, speed);
            if (!pOneWin) {
                pTwoWin = shoot(playerTwo, playerOne, reader, speed);
            }
        }
        if (pOneWin) {
            return declareResult(playerOne.getName());
        } else {
            return declareResult(playerTwo.getName());
        }
    }

    private String declareResult(String name) {
        return String.format("Game Over \n%s is a winner!!! Congratulations, admiral!",name);
    }


    private boolean shoot(Player player, Player defender, BufferedReader reader, int speed) {
        boolean shooting = true;
        StrikeResult strikeResult1 = null;
        while (shooting) {
            System.out.println(String.format("%s write your choice",player.getName()));
            try {
                if (player instanceof User) {
                    strikeResult1 = gameBoard.getPlayerStrikeResult(reader.readLine(), defender);
                } else {
                    strikeResult1 = gameBoard.getPlayerStrikeResult(((Computer) player).shoot(), defender);
                    ((Computer) player).notifyShootResult(strikeResult1);
                    Thread.sleep(speed);
                }
                System.out.print(String.format("%s\n",strikeResult1.getDescription()));
                Thread.sleep(speed);
                System.out.println(gameBoard.printBattlefield());
                shooting = strikeResult1.equals(StrikeResult.WOUND) || strikeResult1.equals(StrikeResult.KILL)
                        || strikeResult1.equals(StrikeResult.SHOOT);
            } catch (IncorrectRequestException e) {
                System.out.println(e.getMessage());
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        return strikeResult1.equals(StrikeResult.LOSE);
    }

    private ShipsRepository generateField(Player player, BufferedReader reader) {
        ShipsRepository repository;
        System.out.println(String.format("%s now let's generate your field", player.getName()));
        ShipAutoGenerator generator = new ShipAutoGenerator();
        if (player instanceof Computer) {
            repository = generator.getGeneratedRepository();
        } else {
            String regime = chooseGenerateType(reader);
            if (regime.equals(AUTO)) {
                repository = generator.getGeneratedRepository();
            } else {
                repository = getRepository(reader);
            }
        }
        return repository;
    }


    private ShipsRepository getRepository(BufferedReader reader) {
        ShipsRepository repository = new PlayerShipsRepository();
        for (ShipType type : ShipType.values()) {
            for (int i = 0; i < type.ordinal() + 1; i++) {
                addShip(type, repository, reader);
            }

        }
        return repository;
    }

    private void addShip(ShipType type, ShipsRepository repository, BufferedReader reader) {
        boolean isLocated = false;
        ShipFactory factory = new ShipFactory();
        Ship ship = factory.getShip(type);
        while (!isLocated) {
            printShipAdvice(type);
            try {
                LocationService service = new LocationServiceImpl();
                ship.setCoords(service.getCoordinates(reader.readLine()));
                System.out.println(repository.addShip(ship));
                isLocated = true;
            } catch (IncorrectRequestException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void printShipAdvice(ShipType type) {
        System.out.println(String.format("%s is creating",type));
        if (ShipType.BOAT.equals(type)) {
            System.out.println("Please write the coordinates in format \"A1\"");
        } else {
            System.out.println("\"Please write the coordinates in format \"A1 A3\"");
        }
    }

    private String chooseGenerateType(BufferedReader reader) {
        System.out.println("Choose generator regime auto/manual");
        boolean isAllrite = false;
        String regime = "";
        while (!isAllrite) {
            try {
                regime = reader.readLine();
                if (regime.equals(AUTO) || regime.equals(MANUAL)) {
                    isAllrite = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("You chose %s regime",regime));
        return regime;
    }

    private String chooseName(BufferedReader reader) {
        boolean isAlright = false;
        String name = null;
        while (!isAlright) {
            try {
                System.out.println("Write your name");
                name = reader.readLine();
                if (!name.equals("") && name.equals("Computer")) {
                    System.out.println("You can't take this name. Only me can be Computer");
                } else if (!name.equals("")) {
                    isAlright = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IncorrectRequestException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return name;
    }

    private Player getPlayerTwo(String result) throws IncorrectRequestException {
        switch (result) {
            case "1player":
                return new Computer();
            case "2players":
                return new User();
            default:
                throw new IncorrectRequestException(result + " there is not such regime!");
        }
    }

}
