package com.mygdx.app;

import java.io.*;
import java.net.*;
import java.util.*;

// Server class
class SocketServer {

    private static ServerEnvironment serverEnvironment = new ServerEnvironment();

    public static void main(String[] args) {
        ServerSocket server = null;

        try {
            // server is listening on port 9999
            //server = new ServerSocket(9999);

            InetAddress inet = InetAddress.getLocalHost();
            String host = inet.getHostName();
            System.out.println("Local Host: " + inet);
            System.out.println("Host Name: " + host);

            server = new ServerSocket();
            //server.bind(new InetSocketAddress("128.210.107.129", 9999));

            //server.bind(new InetSocketAddress("localhost", 9999));
            server.bind(new InetSocketAddress("localhost", 9999));
            //server.bind(new InetSocketAddress("172.20.10.2", 9999));
            server.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            while (true) {

                // socket object to receive incoming client
                // requests
                System.out.println("Searching for clients");

                Socket client = server.accept();

                // Displaying that new client is connected
                // to server
                System.out.println("New client connected"); //Test

                // create a new thread object
                ClientHandler clientSocket = new ClientHandler(client);

                // This thread will handle the client
                // separately
                new Thread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // ClientHandler class
    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        // Constructor
        public ClientHandler(Socket client) {
            this.clientSocket = client;
        }

        public void run() {
            ObjectOutputStream serverOut = null;
            ObjectInputStream clientIn = null;
            try {

                //Client Input and Output
                serverOut = new ObjectOutputStream(clientSocket.getOutputStream());
                clientIn = new ObjectInputStream(clientSocket.getInputStream());

                System.out.println("Server looking for request..."); //Test
                ServerMessage message = (ServerMessage) clientIn.readObject();
                System.out.println("Server received request"); //Test

                while (message.getRequest() != "Exit Request") {

                    switch (message.getRequest()) {

                        case ("TestRequest"):
                            message = testRequest(message);
                            break;

                        case ("LoginRequest"):
                            message = loginRequest(message);
                            break;

                        case ("JoinLeaderboardRequest"):
                            message = joinLeaderboardRequest(message);
                            break;

                        case ("RefreshRequest"):
                            message = refreshRequest(message);
                            break;

                        default:
                    }
                    serverOut.writeObject(message);
                    serverOut.flush();

                    System.out.println("Response sent: " + message.getResponse()); //Test
                    System.out.println("Server looking for request..."); //Test

                    message = (ServerMessage) clientIn.readObject();
                    System.out.println("Server received request"); //Test
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (serverOut != null) {
                        serverOut.close();
                        System.out.println("Closing serverOut"); //Test
                    }
                    if (clientIn != null) {
                        clientIn.close();
                        clientSocket.close();
                        System.out.println("Closing clientIn"); //Test
                    }
                    System.out.println("Closing client thread"); //Test
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public ServerMessage testRequest(ServerMessage request) {
            System.out.println("Test request found"); //Test
            request.setResponse("Test Success");
            System.out.println("Test Response Compiled"); //Test
            return request;
        }

        public ServerMessage loginRequest(ServerMessage request) {
            System.out.println("Login request found:\n     usrnm: " + request.getObject1() + "    pwd: " + request.getObject2()); //Test
            String username = (String) request.getObject1();
            String password = (String) request.getObject2();

            User user = serverEnvironment.getUser((String) request.getObject1());
            if (user == null) {
                user = new User(username, password);
                serverEnvironment.addUser(user);
                request.setResponse("New User Login");
            } else {
                request.setResponse("Existing User Login");
            }
            request.setObject3(user);
            System.out.println("Login Response Compiled"); //Test

            System.out.println("Logged in: " + serverEnvironment.getUser(username).getUsername());

            return request;
        }

        public ServerMessage joinLeaderboardRequest(ServerMessage request) {
            System.out.println("Join Leaderboard request found:\n     user: " + ((User) request.getObject1()).getUsername() + "    crtr: " + request.getObject2()); //Test
            User user = (User) request.getObject1();
            String username = (String) request.getObject2();

            User creator = serverEnvironment.getUser(username);
            request.setResponse("Leaderboard not found");
            if (!(creator == null)) {
                creator.getLeaderboard().addUser(user);
                user.getLeaderboard().removeUser(user);
                user.setLeaderboard(creator.getLeaderboard());
                request.setResponse("Joined new Leaderboard");
            }
            return request;
        }

        public ServerMessage refreshRequest(ServerMessage request) {
            User user = (User) request.getObject1();
            User sUser = serverEnvironment.getUser(user.getUsername());
            sUser.overrideUser(user);

            String res = "refreshed User: " + sUser.getUsername() + " | All Users: ";
            for (User cUser : serverEnvironment.getUsers()) {
                cUser.refreshHours();
                res = res + cUser.getUsername() + ", ";
            }
            request.setResponse(res);
            request.setObject3(serverEnvironment.getUsers());
            return request;
        }
    }
}
