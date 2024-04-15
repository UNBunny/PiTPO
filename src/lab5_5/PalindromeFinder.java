package lab5_5;

import java.util.Scanner;

public class PalindromeFinder {
    static int iterations = 0;
    // Метод для проверки, является ли число палиндромом
    public static boolean isPalindrome(long number) {
        long temp = number;
        long reversedNumber = 0;
        while (temp != 0) {
            long remainder = temp % 10;
            reversedNumber = reversedNumber * 10 + remainder;
            temp /= 10;
        }
        return number == reversedNumber;
    }

    // Метод для нахождения палиндрома для заданного числа

    public static long findPalindrome(long number) {

        while (!isPalindrome(number)) {
            long reversedNumber = 0;
            long temp = number;
            while (temp != 0) {
                long remainder = temp % 10;
                reversedNumber = reversedNumber * 10 + remainder;
                temp /= 10;
            }
            number += reversedNumber;
            iterations++;
        }
        return number;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Чтение количества тестовых случаев
        int testCases = scanner.nextInt();
        scanner.nextLine(); // Переход на следующую строку после чтения числа

        // Обработка каждого тестового случая
        for (int i = 0; i < testCases; i++) {
            long number = scanner.nextLong();
            // Находим палиндром и количество итераций
            long palindrome = findPalindrome(number);
            // Выводим результат
            System.out.println(iterations + " " + palindrome);
            iterations = 0;
        }

        scanner.close();
    }
}
