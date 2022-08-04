package homework4;

public class Main {
    public volatile String symbol = "";

    public String getSymbol() {
        return symbol;
    }

    public synchronized void setSymbolA() {
        if(symbol.length() == 0) {
            symbol = "A";
            System.out.print("A");
        }
        else if(symbol.endsWith("C")) {
            symbol += "A";
            System.out.print("A");
            notifyAll();
        }
        else {
            try {
              //  System.out.println("Поток A заблокирован");
                wait();
              //  System.out.println("Поток A разблокирован");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public synchronized void setSymbolB() {

        if(symbol.length() == 0) {
            symbol = "B";
            System.out.print("B");
        }
        else if(symbol.endsWith("A")) {
            symbol += "B";
            System.out.print("B");
            notifyAll();

        }
        else {
            try {
               // System.out.println("Поток B заблокирован");
                wait();
               // System.out.println("Поток B разблокирован");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
    public synchronized void setSymbolC() {
        if(symbol.length() == 0) {
            symbol = "C";
            System.out.print("C");
        }
        else if(symbol.endsWith("B")) {
            symbol += "C";
            System.out.print("C");
            notifyAll();
        }
        else {
            try {
               // System.out.println("Поток C заблокирован");
                wait();
               // System.out.println("Поток C разблокирован");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

class MainStore {
    public static void main(String[] args) {

        Main store = new Main();


        Thread t1 = new Thread(() -> {
            while (store.getSymbol().length() <= 15) {
                store.setSymbolA();

            }
        });
               t1.start();


        Thread t2 = new Thread(() -> {
            while (store.getSymbol().length() <=  15) {
                store.setSymbolB();

            }

        });
        t2.start();

        Thread t3 = new Thread(() -> {
            while (store.getSymbol().length() <=  15) {

                store.setSymbolC();


            }

        });
        t3.start();

    }
}
