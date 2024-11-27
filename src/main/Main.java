package main;

public class Main {
    public static void main(String[] args) {
        MultiThreadedServer server = new MultiThreadedServer(8080);
        new Thread(server).start();
    }
}