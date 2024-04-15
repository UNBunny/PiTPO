package lab9_4;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Cubes {

    static String[] face = {"front", "back", "left", "right", "top", "bottom"};

    static int[] dp, p, cu, top, bot, num, df;
    static int n, m;

    static void printpath(int k) {
        if (k == -1)
            return;
        printpath(p[k]);
        System.out.println(num[k] + " " + face[df[k]]);
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
            Scanner scanner = new Scanner(inputFile);
            int t = 1;
            boolean first = true;
            while (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n == 0)
                    break;

                if (!first)
                    System.out.println();
                else
                    first = false;

                m = 0;
                dp = new int[3001];
                p = new int[3001];
                cu = new int[6];
                top = new int[3001];
                bot = new int[3001];
                num = new int[3001];
                df = new int[3001];

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < 6; j++)
                        cu[j] = scanner.nextInt();
                    for (int j = 0; j < 6; j++) {
                        df[m] = j;
                        num[m] = i + 1;
                        top[m] = cu[j];
                        if (j % 2 == 1)
                            bot[m] = cu[j - 1];
                        else
                            bot[m] = cu[j + 1];
                        m++;
                    }
                }

                for (int i = 0; i < m; i++)
                    dp[i] = 0;
                for (int i = 0; i < m; i++)
                    p[i] = -1;

                for (int i = 0; i < m; i++) {
                    for (int j = i + 1; j < m; j++) {
                        if (num[j] > num[i] && bot[i] == top[j] && dp[j] < dp[i] + 1) {
                            dp[j] = dp[i] + 1;
                            p[j] = i;
                        }
                    }
                }

                int max = 0, k = 0;
                for (int i = 1; i < m; i++) {
                    if (dp[i] > max) {
                        max = dp[i];
                        k = i;
                    }
                }

                System.out.println("Case #" + t++);
                System.out.println(max + 1);
                printpath(k);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}