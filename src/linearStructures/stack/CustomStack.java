package linearStructures.stack;

import java.util.Stack;

public class CustomStack<T extends Comparable<T>> {
    private int size;
    private int initialCapacity = 100;
    private T[] items = (T[]) new Comparable[initialCapacity];


    public void push(T value) {
        items[size++] = value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return items[--size];
    }

    public T maxValue(T[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException();
        }
        Stack<T> mainStack = new Stack<>();
        Stack<T> maxStack = new Stack<>();

        for (T el : arr) {
            mainStack.push(el);
            if (maxStack.isEmpty()) {
                maxStack.push(el);
            } else if (isLess(maxStack.peek(), mainStack.peek())) {
                maxStack.push(el);
            } else {
                maxStack.push(maxStack.peek());
            }
        }

        return maxStack.peek();
    }

    private boolean isLess(T first, T second) {
        return first.compareTo(second) < 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == items.length;
    }
}
