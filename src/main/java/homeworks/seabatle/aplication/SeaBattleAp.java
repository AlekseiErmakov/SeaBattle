package homeworks.seabatle.aplication;


import homeworks.seabatle.aplication.parser.RequestParser;
import homeworks.seabatle.bean.field.Field;
import homeworks.seabatle.bean.players.Computer;
import homeworks.seabatle.bean.players.Player;
import homeworks.seabatle.bean.players.User;
import homeworks.seabatle.bean.ships.Ship;
import homeworks.seabatle.bean.ships.repository.PlayerShipsRepository;
import homeworks.seabatle.bean.ships.repository.ShipsRepository;
import homeworks.seabatle.board.GameBoard;
import homeworks.seabatle.exception.IncorrectRequestException;
import homeworks.seabatle.generator.AutoGeneratorService;
import homeworks.seabatle.generator.Generateble;
import homeworks.seabatle.generator.GeneratorService;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SeaBattleAp implements MyAplication {
    private Player playerOne;
    private Player playerTwo;
    GameBoard gameBoard;
    private RequestParser parser = new RequestParser();
    StartingLogo log = new StartingLogo();
    Thread logoThread = new Thread(log);
    @Override
    public void run() {
        playerOne = new User();
        logoThread.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //выбираем сингл или мультиплеер

        chooseRegime(reader);
        //называем игроков

        playerOne.setName(chooseName(reader));
        String pOneName = playerOne.getName();
        if (playerTwo instanceof User){
            playerTwo.setName(chooseName(reader));
        }
        String pTwoname = playerTwo.getName();

        System.out.println("Привет " + pOneName + "! " + "Привет " + pTwoname + "! ");
        //генерируем поле
        ShipsRepository pOneShipsRep = generateField(playerOne, reader);
        ShipsRepository pTwoShipsRep = generateField(playerTwo, reader);
        Field playerOneField = new Field(pOneShipsRep);
        playerOne.setField(playerOneField);
        Field playerTwoField = new Field(pTwoShipsRep);
        playerTwo.setField(playerTwoField);
        gameBoard = new GameBoard(playerOne,playerTwo);
        gameBoard.printBatlefield();
        //играем
        String result = runBattle(reader);
    }
    private String runBattle(BufferedReader reader){
        boolean isRun = true;
        String result = null;
        while (isRun){

        }
        return result;
    }
    private ShipsRepository generateField(Player player, BufferedReader reader){
        Generateble generator;
        ShipsRepository repository;
        System.out.println(String.format("%s now let's generate your field",player.getName()));
        if (player instanceof Computer){
            generator = new AutoGeneratorService();
            return generator.generate();
        } else {
            repository = getRepository(new GeneratorService(),reader);
            return repository;
        }

    }
    private ShipsRepository getRepository(GeneratorService generator, BufferedReader reader){
        ShipsRepository repository = new PlayerShipsRepository();

        boolean isAded = false;
        while (!isAded){
            try {
                repository.addShip(generateFourDeckShip(generator,reader));
                isAded = true;
            } catch (IncorrectRequestException e){
                System.out.println(e.getMessage());
            }

        }
        isAded = false;
        for (int i = 0; i < 2; i++){
            while (!isAded){
                try {
                    repository.addShip(generateThreeDeckShip(generator,reader));
                    isAded = true;
                } catch (IncorrectRequestException e){
                    System.out.println(e.getMessage());
                }
            }
            isAded = false;
        }
        isAded = false;
        for (int i = 0; i < 3; i++){
            while (!isAded){
                try {
                    repository.addShip(generateTwoDeckShip(generator,reader));
                    isAded = true;
                } catch (IncorrectRequestException e){
                    System.out.println(e.getMessage());
                }
            }
            isAded = false;
        }
        isAded = false;
        for (int i = 0; i < 4; i++){
            while (!isAded){
                try {
                    repository.addShip(generateOneDeckShip(generator,reader));
                    isAded = true;
                } catch (IncorrectRequestException e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return repository;
    }
    @SneakyThrows
    private Ship generateFourDeckShip(GeneratorService generator, BufferedReader reader){
        System.out.println("Let's generate FourDecker. Write where you want to place it \"A1 A4\" \"A1 D1\"");
        String UsRequest = reader.readLine();
        return generator.generateFourDeckShip(UsRequest);
    }
    @SneakyThrows
    private Ship generateThreeDeckShip(GeneratorService generator, BufferedReader reader){
        System.out.println("Let's generate ThreeDecker. Write where you want to place it \"A1 A3\" \"A1 C1\"");
        String UsRequest = reader.readLine();
        return generator.generateThreeDeckShip(UsRequest);
    }
    @SneakyThrows
    private Ship generateTwoDeckShip(GeneratorService generator, BufferedReader reader){
        System.out.println("Let's generate TwoDecker. Write where you want to place it \"A1 A2\" \"A1 B1\"");
        String UsRequest = reader.readLine();
        return generator.generateTwoDeckShip(UsRequest);
    }
    @SneakyThrows
    private Ship generateOneDeckShip(GeneratorService generator, BufferedReader reader){
        System.out.println("Let's generate OneDecker. Write where you want to place it \"A1\" \"J9\"");
        String UsRequest = reader.readLine();
        return generator.generateOneDeckShip(UsRequest);
    }
    private void chooseRegime(BufferedReader reader){
        boolean isAllrite = false;
        while (!isAllrite){
            try {
                String result = reader.readLine();
                playerTwo = getPlayerTwo(result);
                System.out.println("You choosed " + result + " regime");
                isAllrite = true;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IncorrectRequestException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    private String chooseName(BufferedReader reader){
        boolean isAllrite = false;
        String name = null;
        while (!isAllrite){
            try {
                System.out.println("Write your name");
                name = reader.readLine();
                if (!name.equals("") && name.equals("Skynet")){
                    System.out.println("You can't take this name. Only me can be Skynet");
                } else if (!name.equals("")){
                    isAllrite = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IncorrectRequestException ex){
                System.out.println(ex.getMessage());
            }
        }
        return name;
    }
    private Player getPlayerTwo (String result){

        final String onePlayer = "1player";
        final String twoPlayers = "2players";
        switch (result){
            case onePlayer:
                return new Computer();
            case twoPlayers:
                return new User();
            default:
                throw new IncorrectRequestException(result + " there is not such regime!");
        }
    }
    public static void main(String[] args) {
        Thread t = new Thread(new SeaBattleAp());
        t.start();
    }
}
