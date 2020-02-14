package homeworks.seabatle;

import homeworks.seabatle.aplication.MyAplication;
import homeworks.seabatle.aplication.SeaBattleAp;

public class Main {
    public static void main(String[] args) {
        MyAplication application = new SeaBattleAp();
        Thread thread = new Thread(application);
        thread.start();
    }
}
