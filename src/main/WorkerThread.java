package main;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class WorkerThread implements Runnable{

    protected Socket clientSocket;

    public WorkerThread(Socket clientSocket){
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try{
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream());
            List<String> httpMessage = getHttpMessageStartLine(input.readLine());
            System.out.println(httpMessage);
            String requestedPath = httpMessage.get(1);
            if(requestedPath.equals("/") || requestedPath.equals("/index.html")){
                String responseBody = getFileContentAsString("./src/resources/index.html");
                output.write("HTTP/1.1 200 OK\r\n\r\n" + responseBody + "\r\n");
            }else{
                output.write("HTTP/1.1 400 Bad Request\r\n\r\nNot found\r\n");
            }
            output.close();
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getHttpMessageStartLine(String clientRequest){
        return Arrays.stream(clientRequest.split(" ")).toList();
    }

    public static String getFileContentAsString(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String content = "";
        String read;
        while ((read = reader.readLine())!=null){
            content = content + read + "\r\n";
        }
        return content;
    }
}