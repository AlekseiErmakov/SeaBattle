package homeworks.seabatle.aplication;

import homeworks.seabatle.controller.GameController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SeaBattle implements Runnable {
    @Override
    public void run() {
        GameController controller = new GameController();
<<<<<<< HEAD

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(controller.chooseMode(reader));

        System.out.println(controller.nameUsers(reader));

        System.out.println(controller.createGameBoard(reader));

        System.out.println(controller.printBattleField());

        System.out.println(controller.runBattle(reader, 1000));
=======
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //choosing mode
        System.out.println(controller.chooseMode(reader));
        //players choose their names
        System.out.println(controller.nameUsers(reader));
        //generating fields
        System.out.println(controller.createGameBoard(reader));
        //show game fields
        System.out.println(controller.printBattleField());
        //game
        System.out.println(controller.runBattle(reader,1000));
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
    }
}
