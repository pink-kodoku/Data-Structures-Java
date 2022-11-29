package trees.avlTree;

class Node<T extends Comparable<T>> {
    public T value;
    public int height;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;

    public Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public boolean isEqual(T other) {
        return value.compareTo(other) == 0;
    }

    public boolean isSmaller(T other) {
        return value.compareTo(other) < 0;
    }

    public boolean isLarger(T other) {
        return value.compareTo(other) > 0;
    }

    @Override
    public String toString() {
        return "Node{" +
          "value=" + value +
          '}';
    }
}
