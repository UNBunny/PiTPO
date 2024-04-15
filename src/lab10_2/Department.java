package lab10_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Department {


    public static void main(String[] args) {
        try {
            File file = new File("input2.txt");
            Scanner scanner = new Scanner(file);

            int testCases = scanner.nextInt();

            for (int t = 0; t < testCases; t++) {
                int firestations = scanner.nextInt();
                int intersections = scanner.nextInt();

                int[] distanceToClosestStation = new int[intersections + 1];

                // Инициализация массива расстояний до ближайшей станции
                for (int i = 0; i <= intersections; i++) {
                    distanceToClosestStation[i] = Integer.MAX_VALUE;
                }

                // Чтение информации о станциях
                for (int i = 0; i < firestations; i++) {
                    int station = scanner.nextInt();
                    distanceToClosestStation[station] = 0;
                }

                int[][] distance = new int[intersections + 1][intersections + 1];

                // Инициализация матрицы расстояний
                for (int i = 1; i <= intersections; i++) {
                    for (int j = 1; j <= intersections; j++) {
                        distance[i][j] = Integer.MAX_VALUE;
                    }
                }

                // Чтение информации о дорогах и добавление ребер
                for (int i = 0; i < intersections; i++) {
                    int from = scanner.nextInt();
                    int to = scanner.nextInt();
                    int length = scanner.nextInt();
                    distance[from][to] = distance[to][from] = length;
                }

                floydWarshall(distance, intersections);

                int INFINITY_VALUE = 100000000;
                int bestPlace = computeBestPlace(distanceToClosestStation, distance, intersections, INFINITY_VALUE);

                System.out.println(bestPlace);

                // Пустая строка между вводом первой цифры и вторых двух
                if (t < testCases - 1) {
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
    }

    // Функция для вычисления кратчайших путей с использованием алгоритма Флойда-Уоршелла
    public static void floydWarshall(int[][] distance, int intersections) {
        for (int k = 1; k <= intersections; k++) {
            for (int i = 1; i <= intersections; i++) {
                for (int j = 1; j <= intersections; j++) {
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }
    }

    // Функция для определения лучшего места для строительства пожарной станции
    public static int computeBestPlace(int[] distanceToClosestStation, int[][] distance, int intersections, int INFINITY_VALUE) {
        int minCost = INFINITY_VALUE;
        int bestPlaceToBuildTheGoddamnStation = 0;

        for (int k = 1; k <= intersections; k++) {
            if (distanceToClosestStation[k] == 0) {
                continue;
            }

            // Делаем вершину станцией
            int savedValue = distanceToClosestStation[k];
            distanceToClosestStation[k] = 0;

            for (int i = 1; i <= intersections; i++) {
                if (distanceToClosestStation[i] != 0) {
                    distanceToClosestStation[i] = INFINITY_VALUE;
                }
            }

            // Обновляем расстояние до ближайшей станции для каждой вершины, не являющейся станцией
            for (int i = 1; i <= intersections; i++) {
                for (int j = 1; j <= intersections; j++) {
                    if (distanceToClosestStation[i] == 0 && distance[i][j] < distanceToClosestStation[j]) {
                        distanceToClosestStation[j] = distance[i][j];
                    }
                }
            }

            // Вычисляем стоимость
            int cost = 0;
            for (int i = 1; i <= intersections; i++) {
                if (distanceToClosestStation[i] != INFINITY_VALUE) {
                    cost += distanceToClosestStation[i];
                }
            }

            if (cost < minCost) {
                minCost = cost;
                bestPlaceToBuildTheGoddamnStation = k;
            }

            // Восстанавливаем старое расстояние для следующей итерации
            distanceToClosestStation[k] = savedValue;
        }

        return bestPlaceToBuildTheGoddamnStation;
    }
}