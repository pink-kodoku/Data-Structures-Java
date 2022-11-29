package linearStructures.linkedList;

public class DoublyLinkedList<T extends Comparable<T>> {
    private class Node<E> {
        private E value;
        private Node<E> next;
        private Node<E> prev;

        public Node(E value) {
            this.value = value;
        }
    }

    private Node<T> front;
    private Node<T> rear;
    private int size;

    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);

        if (isEmpty()) {
            initialize(newNode);
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }

        size++;
    }

    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            initialize(newNode);
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        size++;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        T removedValue = rear.value;
        Node<T> prev = rear.prev;
        prev.next = null;
        rear.prev = null;
        rear = prev;


        size--;

        return removedValue;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        Node<T> newNode = front.next;
        T removedValue = front.value;
        front.next = null;
        newNode.prev = null;
        front = newNode;

        size--;

        return removedValue;
    }

    private void initialize(Node<T> node) {
        front = rear = node;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}
