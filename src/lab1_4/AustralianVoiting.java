package lab1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AustralianVoiting {
    public static void main(String[] args) throws IOException {
        VotingSystem votingSystem = new VotingSystem();
        votingSystem.runVoting();
    }
}

class VotingSystem {
    private static final String ELIMINATED = "eliminated";
    private final BufferedReader reader;
    public VotingSystem() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    public void runVoting() throws IOException {
        int numTestCases = Integer.parseInt(reader.readLine());
        reader.readLine(); // Skip blank line
        for (int testCase = 1; testCase <= numTestCases; testCase++) {
            int numCandidates = Integer.parseInt(reader.readLine());
            List<String> candidates = new ArrayList<>();
            for (int candidate = 0; candidate < numCandidates; candidate++) {
                candidates.add(reader.readLine());
            }
            List<int[]> ballots = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null && !line.equals("")) {
                int[] ballot = parseBallot(line);
                ballots.add(ballot);
            }

            VotingAlgorithm votingAlgorithm = new VotingAlgorithm();
            String[] winners = votingAlgorithm.getWinner(ballots, candidates.toArray(new String[0]));
            printWinners(winners);

            if (testCase < numTestCases) {
                System.out.println();
            }
        }
    }

    private int[] parseBallot(String line) {
        StringTokenizer st = new StringTokenizer(line);
        List<Integer> ballotList = new ArrayList<>();
        while (st.hasMoreTokens()) {
            ballotList.add(Integer.parseInt(st.nextToken()));
        }
        return ballotList.stream().mapToInt(Integer::intValue).toArray();
    }

    private void printWinners(String[] winners) {
        for (int i = 0; i < winners.length; i++) {
            System.out.print(winners[i]);
            if (i < winners.length - 1) {
                System.out.println();
            }
        }
    }
}

class VotingAlgorithm {
    private static final String ELIMINATED = "eliminated";

    public String[] getWinner(List<int[]> ballots, String[] candidates) {
        int[] tally = new int[candidates.length];

        while (true) {
            int[] results = tallyResults(tally, ballots, candidates);
            int maxIndex = results[0];
            int minIndex = results[1];
            int maxVote = tally[maxIndex];
            int minVote = tally[minIndex];
            int totalVotes = ballots.size();

            if (maxVote == minVote) {
                List<String> names = new ArrayList<>();
                for (int i = 0; i < candidates.length; i++) {
                    if (!candidates[i].equals(ELIMINATED)) {
                        names.add(candidates[i]);
                    }
                }
                return names.toArray(new String[0]);
            } else if (Math.round(((double) maxVote / totalVotes) * 100) > 50) {
                return new String[]{candidates[maxIndex]};
            }

            for (int i = 0; i < candidates.length; i++) {
                if (tally[i] == minVote) {
                    candidates[i] = ELIMINATED;
                }
            }
        }
    }

    private int[] tallyResults(int[] tally, List<int[]> ballots, String[] candidates) {
        resetTally(tally);

        for (int[] ballot : ballots) {
            tally[getIndex(ballot, candidates)] += 1;
        }

        int maxIndex = Integer.MIN_VALUE, minIndex = Integer.MIN_VALUE;
        for (int i = 0; i < candidates.length; i++) {
            if (!candidates[i].equals(ELIMINATED)) {
                if (maxIndex == Integer.MIN_VALUE && minIndex == Integer.MIN_VALUE) {
                    maxIndex = i;
                    minIndex = i;
                } else {
                    if (tally[i] > tally[maxIndex])
                        maxIndex = i;
                    else if (tally[i] < tally[minIndex])
                        minIndex = i;
                }
            }
        }

        return new int[]{maxIndex, minIndex};
    }

    private int getIndex(int[] ballot, String[] candidates) {
        for (int i = 0; i < ballot.length; i++) {
            if (!candidates[ballot[i] - 1].equals(ELIMINATED)) {
                return ballot[i] - 1;
            }
        }
        return -1;
    }

    private void resetTally(int[] tally) {
        for (int i = 0; i < tally.length; i++) {
            tally[i] = 0;
        }
    }
}
