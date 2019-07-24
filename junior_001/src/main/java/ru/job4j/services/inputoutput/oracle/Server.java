package ru.job4j.services.inputoutput.oracle;

import ru.job4j.services.inputoutput.chat.ChatConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Server
 *
 * @author Vladislav Nechaev
 * @since 17.07.2019
 */
public class Server {

    private static final String BYE = "bye";
    private static final String FINISH = "";
    private Socket socket;
    private Map<String, String> greetings;
    private List<String> randomAnswers;

    public Server(Socket socket, Map<String, String> greetings, List<String> randomAnswers) {
        this.socket = socket;
        this.greetings = greetings;
        this.randomAnswers = randomAnswers;
    }

    /**
     * start
     * handles client request.
     */
    public void start() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Server is waiting...");
            String line;
            String answer;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                line = line.toLowerCase();
                if (line.equals(BYE)) {
                    System.out.println("Server: program completed.");
                    out.println(FINISH);
                    socket.close();
                    return;
                }
                if (greetings.containsKey(line)) {
                    answer = greetings.get(line);
                } else {
                    answer = randomAnswers.get(new Random().nextInt(randomAnswers.size()));
                }
                System.out.println(String.format("Send the message \"%s\" to the client...", answer));
                out.println(answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        ChatConfig config = new ChatConfig(args[0]);
        try (Socket socket = new ServerSocket(Integer.valueOf(args[1])).accept()) {
            Server server = new Server(socket,
                    config.getHiByeAnswers(),
                    config.getRandomAnswers());
            server.start();
        }
    }
}