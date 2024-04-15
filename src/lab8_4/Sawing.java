package lab8_4;

import java.util.Scanner;

public class Sawing {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int length = scanner.nextInt();
            if (length == 0) {
                break; // Если длина бруса равна 0, завершаем программу
            }
            int numCuts = scanner.nextInt();
            int[] cuts = new int[numCuts];
            for (int i = 0; i < numCuts; i++) {
                cuts[i] = scanner.nextInt();
            }

            // Находим и выводим минимальную стоимость пилки
            int minPrice = minCuttingPrice(length, cuts);
            System.out.println("The minimum cutting price is " + minPrice + ".");
        }
    }

    // Функция для нахождения минимальной стоимости пилки
    public static int minCuttingPrice(int length, int[] cuts) {
        int n = cuts.length + 2; // Количество точек распилов, включая начало и конец бруса
        int[] a = new int[n]; // Массив для точек распилов
        a[0] = 0;
        System.arraycopy(cuts, 0, a, 1, cuts.length);
        a[n - 1] = length;

        // Создаем массив для хранения стоимости распилов
        int[][] dp = new int[n][n];

        // Инициализируем массив dp
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = 0; // Стоимость распила отрезка длиной 1 равна 0
        }

        // Находим минимальную стоимость пилки
        for (int len = 2; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
                dp[i][j] += a[j] - a[i];
            }
        }

        // Возвращаем минимальную стоимость пилки для всего бруса
        return dp[0][n - 1];
    }
}