package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer implements Runnable{

    protected int serverPort;
    protected ServerSocket serverSocket;
    public MultiThreadedServer(int serverPort){
        this.serverPort = serverPort;
    }

    public void run(){
        openServerSocket();
        while(true){
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            new Thread(new WorkerThread(clientSocket))
                    .start();
        }
    }
    private void openServerSocket(){
        try{
            this.serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}