package homeworks.seabatle.aplication;

public interface MyAplication extends Runnable {
    default void action(){
        run();
    }
}
