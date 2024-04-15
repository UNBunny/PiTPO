package lab4_1;

import java.util.*;

class NameId {
    String name;
    int id;
}

public class Shell {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();
        scanner.nextLine();

        for (int c = 0; c < cases; c++) {
            int n = scanner.nextInt();
            scanner.nextLine();

            Map<String, Integer> desiredMap = new HashMap<>();
            List<NameId> original = new ArrayList<>();
            List<String> desiredVector = new ArrayList<>();


            for (int j = 0; j < n; j++) {
                String name = scanner.nextLine();
                NameId nameId = new NameId();
                nameId.id = 0; // мы еще не знаем id, поэтому сначала нам нужно прочитать нужный заказ
                nameId.name = name;
                original.add(nameId);
            }

            // Прочитайте нужный заказ
            for (int j = 0; j < n; j++) {
                String name = scanner.nextLine();
                desiredMap.put(name, j); // первый нужный элемент будет равен 0, второй 1 и так далее
                desiredVector.add(name); // те же отношения, но в виде вектора, так что мы можем обращаться к ним через индекс
            }

            // Теперь запишите правильный идентификатор для оригинального заказа
            for (int j = 0; j < n; j++) {
                original.get(j).id = desiredMap.get(original.get(j).name);
            }

            // Считается, что черепаха находится в обратном направлении, если ее номер меньше, чем наибольший номер черепахи над ней в
            // Нахождение наибольшего и второго наибольшего идентификаторов черепах
            int biggest = -1, biggestReverse = -1;
            for (int j = 0; j < n; j++) {
                int currentId = original.get(j).id;
                // Если текущий идентификатор больше наибольшего, обновляем оба наибольших идентификатора
                if (currentId > biggest) {
                    biggestReverse = biggest;
                    biggest = currentId;
                } else if (currentId > biggestReverse) { // Если текущий идентификатор больше второго наибольшего, обновляем только второй наибольший идентификатор
                    biggestReverse = currentId;
                }
            }


            // Теперь нам нужно просто вывести имена
            for (int j = biggestReverse; j >= 0; j--) {
                System.out.println(desiredVector.get(j));
            }

            System.out.println();
        }

        scanner.close();
    }
}
