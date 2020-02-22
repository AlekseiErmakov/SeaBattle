package homeworks.seabatle.aplication;


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
import homeworks.seabatle.exception.shoot.IncorrectShootRequestException;

import homeworks.seabatle.servises.coordinates.LocationService;
import homeworks.seabatle.servises.coordinates.LocationServiceImpl;
import homeworks.seabatle.servises.generator.ShipAutoGenerator;

import lombok.SneakyThrows;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@ToString
public class SeaBattleAp implements MyAplication {
    private Player playerOne;
    private Player playerTwo;
    GameBoard gameBoard;

    StartingLogo log = new StartingLogo();
    Thread logoThread = new Thread(log);

    @Override
    public void run() {
        playerOne = new User();
        //logoThread.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //выбираем сингл или мультиплеер
        System.out.println(chooseRegime(reader));
        //называем игроков
        System.out.println(nameUsers(reader));
        //генерируем поле
        System.out.println(createGameBoard(reader));
        //показываем игровое поле
        gameBoard.printBatlefield();
        //играем
        runBattle(reader);
    }

    private String nameUsers(BufferedReader reader) {
        playerOne.setName(chooseName(reader));
        String pOneName = playerOne.getName();
        if (playerTwo instanceof User) {
            playerTwo.setName(chooseName(reader));
        }
        String pTwoname = playerTwo.getName();
        return "Привет " + pOneName + "! " + "Привет " + pTwoname + "!";
    }

    private String createGameBoard(BufferedReader reader) {
        ShipsRepository pOneShipsRep = generateField(playerOne, reader);
        ShipsRepository pTwoShipsRep = generateField(playerTwo, reader);
        Field playerOneField = new Field(pOneShipsRep);
        playerOne.setField(playerOneField);
        Field playerTwoField = new Field(pTwoShipsRep);
        playerTwo.setField(playerTwoField);
        gameBoard = new GameBoard(playerOne, playerTwo);
        return "Game Board is created!";
    }

    private String runBattle(BufferedReader reader) {
        boolean pOneWin = false;
        boolean pTwoWin = false;
        String result = "Game Over";
        while (!pOneWin || !pTwoWin) {
            pOneWin = shoot(playerOne, playerTwo, reader);

            if (!pOneWin){
                pTwoWin = shoot(playerTwo, playerOne, reader);
            }
        }
        return result;
    }

    @SneakyThrows
    private boolean shoot(Player shooter, Player defender, BufferedReader reader) {
        boolean shooting = true;
        StrikeResult strikeResult1 = null;
        while (shooting) {
            System.out.println(shooter.getName() + " shooting");
            try {
                strikeResult1 = gameBoard.getPlayerStrikeResult(reader.readLine(), defender);
                System.out.println(strikeResult1.getDescription());
                shooting = false;
            } catch (IncorrectShootRequestException e) {
                System.out.println(e.getMessage());
            }
        }
        return strikeResult1.equals(StrikeResult.LOSE);
    }

    private ShipsRepository generateField(Player player, BufferedReader reader) {
        ShipsRepository repository;
        System.out.println(String.format("%s now let's generate your field", player.getName()));
        repository = new ShipAutoGenerator().getGeneratedRepository();
        return repository;
//        if (player instanceof Computer) {
//            repository = new ShipAutoGenerator().getGeneratedRepository();
//            return repository;
//        } else {
//            repository = new ShipAutoGenerator().getGeneratedRepository();
//            return repository;
//        }
    }


    private ShipsRepository getRepository(BufferedReader reader) {
        ShipsRepository repository = new PlayerShipsRepository();
        for (ShipType type : ShipType.values()) {
            for (int i = 0; i < type.ordinal()+1; i++){
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
    private void printShipAdvice(ShipType type){
        System.out.println(type + " is Creating");
        if (ShipType.BOAT.equals(type)) {
            System.out.println("Please write the coordinates in format \"A1\"");
        } else {
            System.out.println("\"Please write the coordinates in format \"A1 A3\"");
        }
    }

    private String chooseRegime(BufferedReader reader) {
        System.out.println("Choose regime 1player/2players");
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
        return "You choosed " + result + " regime";
    }

    private String chooseName(BufferedReader reader) {
        boolean isAlright = false;
        String name = null;
        while (!isAlright) {
            try {
                System.out.println("Write your name");
                name = reader.readLine();
                if (!name.equals("") && name.equals("Skynet")) {
                    System.out.println("You can't take this name. Only me can be Skynet");
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

    private Player getPlayerTwo(String result) {
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
