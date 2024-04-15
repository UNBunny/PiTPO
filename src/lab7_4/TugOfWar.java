package lab7_4;

import java.util.Arrays;
import java.util.Scanner;

public class TugOfWar {
    // Функция для решения задачи для одного блока
    public static void solveBlock(int n, int[] weights) {
        Arrays.sort(weights);
        int[] team1 = new int[n];  // Ком 1
        int[] team2 = new int[n];  // Ком 2
        int sum1 = 0;  // Суммарный вес ком 1
        int sum2 = 0;  // Суммарный вес ком 2

        int idx1 = 0; // Индекс тек участника в команде 1
        int idx2 = 0; // Индекс тек участника в команде 2

        // Распределяем участников по командам
        for (int i = n - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                team1[idx1++] = weights[i];
                sum1 += weights[i];
            } else {
                team2[idx2++] = weights[i];
                sum2 += weights[i];
            }
        }
        System.out.println(Math.min(sum1, sum2) + " " + Math.max(sum1, sum2));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numBlocks = scanner.nextInt();
        scanner.nextLine();

        for (int block = 0; block < numBlocks; block++) {
            scanner.nextLine();
            int n = scanner.nextInt();
            int[] weights = new int[n];
            for (int i = 0; i < n; i++) {
                weights[i] = scanner.nextInt();
            }
            solveBlock(n, weights);
            if (block < numBlocks - 1) {
                System.out.println();
            }
        }
    }
}