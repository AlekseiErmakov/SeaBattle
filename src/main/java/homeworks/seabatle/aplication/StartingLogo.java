package homeworks.seabatle.aplication;

import lombok.SneakyThrows;

public class StartingLogo implements Runnable,Dialog{
    @Override
    public void run() {
        printCompany();
        printInformation(UPDATINGDRIVERS);
        printInformation(CONFIGURATINGSYSTEM);
        printTitle();
        System.out.println(CHOOSEREGIME);
    }
    @SneakyThrows
    private void printCompany(){
        for (int i = 0; i < COMPANY.length(); i++){
            System.out.print(COMPANY.charAt(i));
            Thread.sleep(100);
        }
        Thread.sleep(2000);
        System.out.println();
    }
    @SneakyThrows
    private void printInformation(String string){
        System.out.print(string);
        for (int i = 0; i < 8; i++){
            System.out.print(".");
            Thread.sleep(500);
        }
        System.out.println();
    }
    @SneakyThrows
    private void printTitle(){
        System.out.println(TITLE);
        Thread.sleep(1000);
    }


}
