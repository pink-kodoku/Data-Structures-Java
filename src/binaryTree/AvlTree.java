package binaryTree;

public class AvlTree<T extends Comparable<T>> {
    private class Node<E> {
        private E value;
        private int height;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;

        public Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
              "value=" + value +
              '}';
        }
    }

    private Node<T> root;
    private int size;

    public void insert(T value) {
        Node<T> newNode = new Node<>(value, null);

        if (isEmpty()) {
            initialize(newNode);
        } else {
            root = insert(root, newNode);
        }

        size++;
    }

    private Node<T> insert(Node<T> root, Node<T> newNode) {
        if (root == null) {
            return newNode;
        }

        newNode.parent = root;
        if (isSmaller(newNode.value, root.value)) {
            root.left = insert(root.left, newNode);
        } else {
            root.right = insert(root.right, newNode);
        }

        setHeight(root);

        return balance(root);
    }

    private Node<T> balance(Node<T> node) {
        if (isLeftHeavy(node)) {
            if (balanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        } else if (isRightHeavy(node)) {
            if (balanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }

        return node;
    }

    private Node<T> rightRotate(Node<T> node) {
        Node<T> newRoot = node.left;

        node.left = newRoot.right;
        newRoot.right = node;

        newRoot.parent = null;
        node.parent = newRoot;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private Node<T> leftRotate(Node<T> node) {
        Node<T> newRoot = node.right;

        node.right = newRoot.left;
        newRoot.left = node;

        newRoot.parent = null;
        node.parent = newRoot;

        setHeight(root);
        setHeight(newRoot);

        return newRoot;
    }

    private boolean isRightHeavy(Node<T> node) {
        return balanceFactor(node) < -1;
    }

    private boolean isLeftHeavy(Node<T> node) {
        return balanceFactor(node) > 1;
    }

    private int balanceFactor(Node<T> node) {
        return height(node.left) - height(node.right);
    }

    private void setHeight(Node<T> node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int height(Node<T> node) {
        return node == null ? -1 : node.height;
    }

    private boolean isSmaller(T first, T second) {
        return first.compareTo(second) < 0;
    }

    private void initialize(Node<T> node) {
        root = node;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }
}
