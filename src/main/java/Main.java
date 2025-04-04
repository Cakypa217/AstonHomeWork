import aston.MyArrayList;
import aston.MyLinkedList;
import aston.MyList;

public class Main {
    public static void main(String[] args) {
        // Демонстрация работы с ArrayList
        System.out.println("Работа с ArrayList:");
        MyList<Integer> arrayList = new MyArrayList<>();
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(30);

        arrayList.add(40, 1);
        System.out.println("Элемент на индексе 1: " + arrayList.get(1)); // Должно вывести 40

        arrayList.remove(20);
        System.out.println("После удаления 20, элемент на индексе 2: " + arrayList.get(2)); // Должно вывести 30

        arrayList.clear();
        System.out.println("ArrayList после очистки: ");
        try {
            System.out.println(arrayList.get(0)); // Должно выбросить исключение, т.к. список пуст
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e);
        }

        // Демонстрация работы с LinkedList
        System.out.println("\nРабота с LinkedList:");
        MyList<Integer> linkedList = new MyLinkedList<>();
        linkedList.add(100);
        linkedList.add(200);
        linkedList.add(300);

        linkedList.add(400, 2);
        System.out.println("Элемент на индексе 2: " + linkedList.get(2)); // Должно вывести 400

        linkedList.remove(200);
        System.out.println("После удаления 200, элемент на индексе 1: " + linkedList.get(1)); // Должно вывести 400

        linkedList.clear();
        System.out.println("LinkedList после очистки: ");
        try {
            System.out.println(linkedList.get(0)); // Должно выбросить исключение, т.к. список пуст
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Поймано исключение: " + e);
        }
    }
}