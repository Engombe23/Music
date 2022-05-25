import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {
        String name, serverMessage;

        Socket clientSocket = new Socket("127.0.0.1", 80);
        System.out.println("Client is running");

        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            System.out.println("Enter the artist name:");
            name = inFromUser.readLine();
            System.out.println("You entered " + name);

            outToServer.println(name);

            serverMessage = inFromServer.readLine();
            System.out.println("FROM SERVER: " + serverMessage);
            if(name.equals("stop"))
                break;
        }
        inFromServer.close();
        inFromUser.close();
        outToServer.close();
        clientSocket.close();
    }
}
