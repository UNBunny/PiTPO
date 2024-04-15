package lab1_4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

public class VotingSystemTest {

    @Test
    public void testVotingSystem() {
        String input = "1\n\n3\nJohn Doe\nJane Smith\nJane Austen\n1 2 3\n2 1 3\n2 3 1\n1 2 3\n3 1 2\n\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        VotingSystem votingSystem = new VotingSystem();
        try {
            votingSystem.runVoting();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String[] expectedOutput = {"John Doe"};

        assertArrayEquals(expectedOutput, lines);
    }

    @Test
    public void testVotingSystem2() {
        String input = "2\n\n3\nEmily Johnson\nBenjamin Parker\nSophia Williams\n1 2 3\n2 1 3\n2 3 1\n1 2 3\n3 1 2\n\n3\nEthan Thompson\nOlivia Garcia\nLiam Martinez\n1 2 3\n3 2 1\n1 3 2\n\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        VotingSystem votingSystem = new VotingSystem();
        try {
            votingSystem.runVoting();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] lines = outputStream.toString().split(System.lineSeparator());
        String[] expectedOutput = {"Emily Johnson", "Ethan Thompson"};

        assertArrayEquals(expectedOutput, lines);
    }
}
