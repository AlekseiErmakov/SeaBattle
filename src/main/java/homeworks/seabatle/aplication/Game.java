package homeworks.seabatle.aplication;

<<<<<<< HEAD
public class Game implements Runnable {
=======
public class Game implements Runnable{
>>>>>>> 196c9c899e8cc36f8da0cb21bf63ded50215e9d6
    @Override
    public void run() {
        StartingLogo logo = new StartingLogo();
        SeaBattle seaBattle = new SeaBattle();
        Thread startLogo = new Thread(logo);
        Thread gameThread = new Thread(seaBattle);
        try {
            startLogo.start();
            startLogo.join();
            gameThread.start();
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
