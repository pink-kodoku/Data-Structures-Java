package trees.avlTree;

public interface Tree<T extends Comparable<T>> {
    void insert(T value);

    void delete(T value);

    Node<T> find(T value);

    void traverse();

    T getMax();

    T getMin();

    boolean isEmpty();

    int nodesCount();
}
