package linkedList;

import java.util.Arrays;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.removeLast();
        System.out.println("");
    }
}
