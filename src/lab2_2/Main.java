package lab2_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Team implements Comparable<Team> {
    private int number;
    private int time;
    private Set<Integer> solved = new HashSet<>();
    private int[] penalty = new int[10];

    public Team(int number) {
        this.number = number;
    }

    public void solveProblem(int problem, int time, String result) {
        if (result.equals("C")) {
            solved.add(problem);
            this.time += time + penalty[problem - 1] * 20;
        } else if (result.equals("I")) {
            penalty[problem - 1]++;
        }
    }

    @Override
    public int compareTo(Team other) {
        if (solved.size() != other.solved.size()) {
            return other.solved.size() - solved.size();
        } else {
            return time - other.time;
        }
    }

    @Override
    public String toString() {
        return number + " " + solved.size() + " " + time;
    }
}

public class Main {
    public static void main(String[] args) {
        Solver solver = new Solver();
        solver.sequential();
    }
}

class Solver {
    private List<List<String[]>> input = new ArrayList<>();
    private List<String> results = new ArrayList<>();

    public Solver() {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            int numOfTests = Integer.parseInt(br.readLine());
            for (int i = 0; i < numOfTests; i++) {
                List<String[]> subs = new ArrayList<>();
                br.readLine();
                while ((line = br.readLine()) != null && !line.isEmpty()) {
                    subs.add(line.split(" "));
                }
                input.add(subs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sequential() {
        long startTime = System.currentTimeMillis();
        for (List<String[]> test : input) {
            results.add(solve(test));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time in milliseconds: " + (endTime - startTime));
        makeOutput();
    }

    private String solve(List<String[]> subs) {
        Team[] teams = new Team[101];
        for (int i = 0; i < 101; i++) {
            teams[i] = new Team(i);
        }
        for (String[] s : subs) {
            int team = Integer.parseInt(s[0]);
            int problem = Integer.parseInt(s[1]);
            int time = Integer.parseInt(s[2]);
            String result = s[3];
            teams[team].solveProblem(problem, time, result);
        }
        java.util.Arrays.sort(teams);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < teams.length && teams[i].toString().charAt(0) != '0'; i++) {
            sb.append(teams[i].toString()).append("\n");
        }
        return sb.toString();
    }

    private void makeOutput() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            for (String result : results) {
                bw.write(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
