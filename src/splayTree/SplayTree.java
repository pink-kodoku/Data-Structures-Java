package splayTree;

public class SplayTree<T extends Comparable<T>> {
    private class Node<E> {
        private E value;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;

        public Node(E value, Node<E> parent) {
            this.value = value;
            this.parent = parent;
        }

        private boolean isLeftChild() {
            return this == parent.left;
        }

        private boolean isRightChild() {
            return this == parent.right;
        }

        private Node<E> getGrandParent() {
            return parent != null ? parent.parent : null;
        }

        @Override
        public String toString() {
            return "Node{" +
              "value=" + value +
              '}';
        }
    }

    private Node<T> root;
    private int nodesCount;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return nodesCount;
    }

    public void insert(T value) {
        Node<T> newNode = new Node<>(value, null);
        root = insert(root, newNode);
        nodesCount++;
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

    public Node<T> find(T value) {
        return find(root, value);
    }

    private Node<T> find(Node<T> root, T value) {
        if (root == null) {
            return null;
        }
        if (isEqual(root.value, value)) {
            splay(root);
            return root;
        }

        return find(isSmaller(value, root.value) ? root.left : root.right, value);
    }

    private void splay(Node<T> node) {
        if (node.parent == root) {
            if (node.isRightChild()) {
                leftRotate(node.parent);
            } else {
                rightRotate(node.parent);
            }
        } else if (node.isRightChild() && node.parent.isRightChild()) {
            leftRotate(node.getGrandParent());
            leftRotate(node.parent);
        } else if (node.isLeftChild() && node.parent.isLeftChild()) {
            rightRotate(node.getGrandParent());
            rightRotate(node.parent);
        } else if (node.isLeftChild() && node.parent.isRightChild()) {
            rightRotate(node.parent);
            leftRotate(node.getGrandParent());
        } else {
            leftRotate(node.parent);
            rightRotate(node.getGrandParent());
        }
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

    private boolean isSmaller(T first, T second) {
        return first.compareTo(second) < 0;
    }

    private boolean isEqual(T first, T second) {
        return first.compareTo(second) == 0;
    }
}
