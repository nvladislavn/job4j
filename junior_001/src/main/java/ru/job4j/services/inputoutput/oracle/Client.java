package ru.job4j.services.inputoutput.oracle;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client
 *
 * @author Vladislav Nechaev
 * @since 17.07.2019
 */
public class Client {

    private Socket socket;
    private InputStream input;

    public Client(Socket socket, InputStream input) {
        this.socket = socket;
        this.input = input;
    }

    /**
     * start
     * Receives messages from the console, forwards from to the server and receives response from server.
     */
    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Scanner console = new Scanner(this.input);
            System.out.println("Please type something:");
            String message;
            while (console.hasNextLine()) {
                message = console.nextLine();
                System.out.println(String.format("Send the message \"%s\" to the server...", message));
                out.println(message);
                String serverMessage = in.readLine();
                if (serverMessage.isEmpty()) {
                    System.out.println("Client: program completed.");
                    socket.close();
                    return;
                }
                System.out.println("Server: " + serverMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client(new Socket(args[0], Integer.valueOf(args[1])), System.in);
        client.start();
    }
}
