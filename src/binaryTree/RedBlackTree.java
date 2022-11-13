package binaryTree;

public class RedBlackTree<T extends Comparable<T>> {
    private class Node<E> {
        private E value;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;
        private boolean color = true;

        public Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }

        private boolean isRed() {
            return color;
        }

        private boolean isBlack() {
            return !color;
        }

        private void makeRed() {
            color = true;
        }

        private void makeBlack() {
            color = false;
        }

        private void flipColor() {
            color = !color;
        }

        private boolean isLeftChild() {
            return this == parent.left;
        }

        private boolean isRightChild() {
            return this == parent.right;
        }

        private Node<E> getGrandparent() {
            return parent.parent;
        }

        private Node<E> getUncle() {
            return isLeftChild() ? getGrandparent().right : getGrandparent().left;
        }
    }

    private Node<T> root;
    private int size;


    public void insert(T value) {
        Node<T> newNode = new Node<>(value, null);
        if (isEmpty()) {
            System.out.println("initialize");
            initialize(newNode);
        } else {
            System.out.println("insert");
            root = insert(root, newNode);
            balance(newNode);
        }

        size++;
    }

    private void balance(Node<T> node) {
        Node<T> parent = node.parent;
        if (node != root && parent.isRed()) {
            Node<T> grandparent = node.getGrandparent();
            Node<T> uncle = node.getUncle();
            System.out.println("balance");

            if (uncle != null && uncle.isRed()) {
                recolor(parent, uncle, grandparent);
            } else if (parent.isRightChild()) {
                System.out.println("isRightHeavy");
                handleRightHeavy(node, parent, grandparent);
            } else if (parent.isLeftChild()) {
                System.out.println("isLeftHeavy");
                handleLeftHeavy(node, parent, grandparent);
            }
        }

        root.makeBlack();
    }

    private void handleRightHeavy(Node<T> node, Node<T> parent, Node<T> grandparent) {
        if (node.isLeftChild()) {
            rightRotate(parent);
        }

        parent.flipColor();
        grandparent.flipColor();
        leftRotate(grandparent);

        balance(node);
    }

    private void handleLeftHeavy(Node<T> node, Node<T> parent, Node<T> grandparent) {
        if (node.isRightChild()) {
            leftRotate(parent);
        }

        parent.flipColor();
        grandparent.flipColor();
        rightRotate(grandparent);

        balance(node);
    }

    private void rightRotate(Node<T> node) {
        Node<T> leftNode = node.left;
        node.left = leftNode.right;

        if (node.left != null) {
            node.left.parent = node;
        }

        leftNode.right = node;
        leftNode.parent = node.parent;

        updateParentChildren(node, leftNode);
        node.parent = leftNode;
    }

    private void leftRotate(Node<T> node) {
        Node<T> rightNode = node.right;
        node.right = rightNode.left;
        if (node.right != null) {
            node.right.parent = node;
        }

        rightNode.left = node;
        rightNode.parent = node.parent;
        updateParentChildren(node, rightNode);
        node.parent = rightNode;
    }


    private void updateParentChildren(Node<T> node, Node<T> tempNode) {
        if (node.parent == null) {
            root = tempNode;
        } else if (node.isRightChild()) {
            node.parent.right = tempNode;
        } else if (node.isLeftChild()) {
            node.parent.left = tempNode;
        }
    }

    private void recolor(Node<T> parent, Node<T> uncle, Node<T> grandparent) {
        parent.flipColor();
        uncle.flipColor();
        grandparent.flipColor();

        balance(grandparent);
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

    private void initialize(Node<T> node) {
        root = node;
        node.makeBlack();
    }

    private boolean isSmaller(T first, T second) {
        return first.compareTo(second) < 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }
}
