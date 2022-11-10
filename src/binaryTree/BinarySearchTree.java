package binaryTree;

public class BinarySearchTree<T extends Comparable<T>> {
    private class Node<E> {
        private E value;
        private Node<E> right;
        private Node<E> left;
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

        return root;
    }

    public void remove(T value) {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        Node<T> node = findNode(root, value);
        if (node == null) {
            throw new IllegalArgumentException("No such element");
        }

        if (isLeaf(node)) {
            removeLeafNode(node, value);
        } else if (hasOneChild(node)) {
            removeNodeWithOneChild(node, value);
        } else {
            Node<T> predecessor = getPredecessor(node.left);
            removeLeafNode(predecessor, predecessor.value);
            node.value = predecessor.value;
        }

        size--;
    }

    private void removeNodeWithOneChild(Node<T> node, T value) {
        Node<T> parent = node.parent;
        if (isLeftChild(node, value)) {
            parent.left = node.left;
        } else {
            parent.right = node.right;
        }
    }

    private void removeLeafNode(Node<T> node, T value) {
        Node<T> parentNode = node.parent;
        if (isLeftChild(parentNode, value)) {
            parentNode.left = null;
        } else {
            parentNode.right = null;
        }
    }

    private Node<T> getPredecessor(Node<T> root) {
        if (isLeaf(root)) {
            return root;
        }

        return getPredecessor(root.right);
    }

    private boolean hasOneChild(Node<T> node) {
        return (node.left == null && node.right != null) || (node.left != null && node.right == null);
    }

    private boolean isLeftChild(Node<T> node, T value) {
        Node<T> parent = node.parent;
        return isEqual(parent.left.value, value);
    }

    private Node<T> findNode(Node<T> root, T value) {
        if (root == null) {
            return null;
        }
        if (isEqual(root.value, value)) {
            return root;
        }

        if (isSmaller(value, root.value)) {
            return findNode(root.left, value);
        } else {
            return findNode(root.right, value);
        }
    }

    public boolean contains(T value) {
        return findNode(root, value) != null;
    }

    public void inOrderTraverse() {
        inOrderTraverse(root);
    }

    private void inOrderTraverse(Node<T> root) {
        if (root == null) {
            return;
        }

        inOrderTraverse(root.left);
        System.out.println(root.value);
        inOrderTraverse(root.right);
    }

    public void preOrderTraverse() {
        preOrderTraverse(root);
    }

    public void preOrderTraverse(Node<T> root) {
        if (root == null) {
            return;
        }

        System.out.println(root.value);
        preOrderTraverse(root.left);
        preOrderTraverse(root.right);
    }

    public void postOrderTraverse() {
        preOrderTraverse(root);
    }

    public void postOrderTraverse(Node<T> root) {
        if (root == null) {
            return;
        }

        postOrderTraverse(root.left);
        postOrderTraverse(root.right);
        System.out.println(root.value);
    }

    public T max() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return max(root).value;
    }

    private Node<T> max(Node<T> root) {
        if (root.right == null) {
            return root;
        }

        return max(root.right);
    }

    public T min() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        return min(root).value;
    }

    private Node<T> min(Node<T> root) {
        if (root.left == null) {
            return root;
        }

        return min(root.left);
    }

    public boolean compareTree(BinarySearchTree<T> other) {
        return compareTree(root, other.root);
    }

    private boolean compareTree(Node<T> first, Node<T> second) {
        if (first == null && second == null) {
            return true;
        }

        if (first != null && second != null) {
            if (isEqual(first.value, second.value)) {
                return compareTree(first.left, second.left)
                  && compareTree(first.right, second.right);
            }
        }

        return false;
    }

    public T kThSmallestValue(int distance) {
        if (isEmpty() || distance > size) {
            throw new IllegalStateException();
        }


        return kThSmallestValue(root, distance).value;
    }

    private Node<T> kThSmallestValue(Node<T> node, int distance) {
        int subtreeSize = treeSize(node.left) + 1;

        if (subtreeSize == distance) {
            return node;
        }

        if (subtreeSize > distance) {
            return kThSmallestValue(node.left, distance);
        }

        return kThSmallestValue(node.right, distance - subtreeSize);
    }

    private int treeSize(Node<T> node) {
        if (node == null) {
            return 0;
        }

        return treeSize(node.left) + treeSize(node.right) + 1;
    }

    private int height(Node<T> node) {
        if (node == null) {
            return -1;
        }

        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public int height() {
        return height(root);
    }

    private boolean isLeaf(Node<T> node) {
        return node.left == null && node.right == null;
    }

    public int size() {
        return size;
    }

    private boolean isEqual(T first, T second) {
        return first.compareTo(second) == 0;
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
}
