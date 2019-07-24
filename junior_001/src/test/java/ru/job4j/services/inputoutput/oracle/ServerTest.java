package ru.job4j.services.inputoutput.oracle;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Server
 *
 * @author Vladislav Nechaev
 * @since 23.07.2019
 */
public class ServerTest {

    /**
     * whenByeThanEmptyString
     * tests start()
     *
     * @throws IOException
     */
    @Test
    public void whenByeThanEmptyString() throws IOException {
        testServer("bye", "" + System.lineSeparator(), new HashMap<>(), new ArrayList<String>());
    }

    /**
     * whenHelloThanHELLO
     * tests start()
     *
     * @throws IOException
     */
    @Test
    public void whenHelloThanHELLO() throws IOException {
        Map<String, String> greeting = new HashMap<>();
        greeting.put("привет", "HELLO!");
        testServer("привет", "HELLO!" + System.lineSeparator(), greeting, new ArrayList<String>());
    }

    /**
     * shouldReturnFINE
     * tests start()
     *
     * @throws IOException
     */
    @Test
    public void shouldReturnFINE() throws IOException {
        List<String> answers = new ArrayList<>(1);
        answers.add("FINE!");
        testServer("как дела", "FINE!" + System.lineSeparator(), new HashMap<>(), answers);
    }

    /**
     * testServer
     *
     * @param input    - incoming string
     * @param exp      - expected string.
     * @param greeting - the answers Map.
     * @param answers  - the answers List.
     * @throws IOException
     */
    private void testServer(String input, String exp, Map<String, String> greeting, List<String> answers) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Socket mock = mock(Socket.class);
        when(mock.getInputStream()).thenReturn(in);
        when(mock.getOutputStream()).thenReturn(out);
        Server server = new Server(mock, greeting, answers);
        server.start();
        assertThat(out.toString(), is(exp));
    }
}