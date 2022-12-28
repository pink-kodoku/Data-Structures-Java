package linearStructures.linkedList;

import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> implements Iterable<T> {
    protected class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = root;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T value = current.value;
            current = current.next;

            return value;
        }

    }

    protected int size;
    protected Node<T> root;

    // O(1)
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            initialize(newNode);
        } else {
            newNode.next = root;
            root = newNode;
        }
        size++;
    }

    protected void initialize(Node<T> node) {
        root = node;
    }

    // O(1)
    public T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        T removedValue = root.value;
        Node<T> next = root.next;
        root.next = null;
        root = next;
        size--;

        return removedValue;
    }

    // O(n)
    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            initialize(newNode);
        } else {
            getLastNode().next = newNode;
        }
        size++;
    }

    // O(n)
    public T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        Node<T> previousNode = getPreviousNode();
        T removedValue = previousNode.next.value;
        previousNode.next = null;
        size--;

        return removedValue;
    }

    public void remove(T value) {
        Node<T> current = root;
        Node<T> prev = root;

        while (current.next != null) {
            if (isEqual(current.value, value)) {
                prev.next = current.next;
                return;
            }

            size--;
            prev = current;
            current = current.next;
        }
    }

    public T removeAt(int index) {
        if (index >= size) {
            throw new IllegalStateException();
        }
        Node<T> current = root;
        Node<T> prev = root;

        for (int i = 0; i < index; i++) {
            prev = current;
            current = current.next;
        }

        T removedValue = current.value;
        prev.next = current.next;
        size--;

        return removedValue;
    }


    private Node<T> getLastNode() {
        Node<T> current = root;
        while (current.next != null) {
            current = current.next;
        }

        return current;
    }

    private Node<T> getPreviousNode() {
        Node<T> current = root;
        Node<T> prev = current;
        while (current.next != null) {
            prev = current;
            current = current.next;
        }

        return prev;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }

        return getNodeAtDistance(index).value;
    }

    private Node<T> getNodeAtDistance(int index) {
        Node<T> current = root;
        int i = 0;
        while (i != index && current != null) {
            current = current.next;
            i++;
        }

        return current;
    }

    // O(n)
    public void reverse() {
        Node<T> prev = root;
        Node<T> current = root.next;

        root.next = null;

        while (current != null) {
            Node<T> next = current.next;

            current.next = prev;

            prev = current;
            current = next;
        }

        root = prev;
    }

    public T[] toArray() {
        T[] newArr = (T[]) new Comparable[size];

        Node<T> current = root;
        int index = 0;
        while (current != null) {
            newArr[index++] = current.value;
            current = current.next;
        }

        return newArr;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean contains(T value) {
        Node<T> current = root;
        while (current != null) {
            if (isEqual(current.value, value)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public int indexOf(T value) {
        Node<T> current = root;
        int index = 0;
        while (current != null) {
            if (isEqual(current.value, value)) {
                return index;
            }

            index++;
            current = current.next;
        }

        return -1;
    }

    public T getMiddleValue() {
        Node<T> slow = root;
        Node<T> fast = root;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.value;
    }

    protected boolean isEqual(T first, T second) {
        return first.compareTo(second) == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }
}
