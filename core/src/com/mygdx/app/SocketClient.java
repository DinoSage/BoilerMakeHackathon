package com.mygdx.app;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClient {

    ServerMessage request;
    ServerMessage response;
    ObjectOutputStream clientOut;
    ObjectInputStream serverIn;

    public SocketClient() { }





    public void testServerRequest() {
        //Search for server host
        System.out.println("Searching for server");
        try (Socket socket = new Socket("10.184.46.131", 9999)) {
            //try (Socket socket = new Socket("10.184.46.131", 9999)) {
            System.out.println("Connected to server");

            //Server Input and Output Streams
            clientOut = new ObjectOutputStream(socket.getOutputStream());
            serverIn = new ObjectInputStream(socket.getInputStream());

            clientOut.writeObject(new ServerMessage("TestRequest"));
            clientOut.flush();
            response = (ServerMessage) serverIn.readObject();
            System.out.println(response.getResponse());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void loginRequest(String username, String password) {
        ServerMessage response = sendServerRequest(new ServerMessage("LoginRequest", username, password));
    }


    private ServerMessage sendServerRequest(ServerMessage message) {
        //Search for server host
        System.out.println("Searching for server");
        try (Socket socket = new Socket("10.184.46.131", 9999)) {
            //try (Socket socket = new Socket("10.184.46.131", 9999)) {
            System.out.println("Connected to server");

            //Server Input and Output Streams
            clientOut = new ObjectOutputStream(socket.getOutputStream());
            serverIn = new ObjectInputStream(socket.getInputStream());

            clientOut.writeObject(message);
            clientOut.flush();
            response = (ServerMessage) serverIn.readObject();
            System.out.println(response.getResponse());
            return response;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
