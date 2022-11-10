package stack;

public class Stack<T extends Comparable<T>> {
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

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == items.length;
    }
}
