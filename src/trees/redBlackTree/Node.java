package trees.redBlackTree;

class Node<T extends Comparable<T>> {
    public T value;
    public Node<T> left;
    public Node<T> right;
    public Node<T> parent;
    public Color color = Color.RED;

    public Node(T value, Node<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public boolean isRed() {
        return color == Color.RED;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public void makeRed() {
        color = Color.RED;
    }

    public void makeBlack() {
        color = Color.BLACK;
    }

    public void flipColor() {
        color = color == Color.RED ? Color.BLACK : Color.RED;
    }

    public boolean isLeftChild() {
        return this == parent.left;
    }

    public boolean isRightChild() {
        return this == parent.right;
    }

    public Node<T> getGrandparent() {
        return parent.parent;
    }

    public Node<T> getUncle() {
        return isLeftChild() ? getGrandparent().right : getGrandparent().left;
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
}
