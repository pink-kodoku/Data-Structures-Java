package trees.redBlackTree;

public class RedBlackTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
    private int nodesCount;

    public void insert(T value) {
        Node<T> newNode = new Node<>(value, null);
        root = insert(root, newNode);
        balance(newNode);
        nodesCount++;
    }

    private void balance(Node<T> node) {
        Node<T> parent = node.parent;
        if (node != root && parent.isRed()) {
            Node<T> grandparent = node.getGrandparent();
            Node<T> uncle = node.getUncle();

            if (uncle != null && uncle.isRed()) {
                recolor(parent, uncle, grandparent);
            } else if (parent.isRightChild()) {
                handleRightHeavy(node, parent, grandparent);
            } else if (parent.isLeftChild()) {
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
        if (newNode.isSmaller(root.value)) {
            root.left = insert(root.left, newNode);
        } else {
            root.right = insert(root.right, newNode);
        }

        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void delete(T value) {
        root = delete(root, value);
        nodesCount--;
    }

    private Node<T> delete(Node<T> root, T value) {
        if (root == null) {
            return null;
        }

        if (root.isSmaller(value)) {
            root.right = delete(root.right, value);
            if (root.right != null) {
                root.right.parent = root;
            }
        } else if (root.isLarger(value)) {
            root.left = delete(root.left, value);
            if (root.left != null) {
                root.left.parent = root;
            }
        } else {
            // has one child or leaf node
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // two children
            root.value = getMax(root.left).value;
            root.left = delete(root.left, root.value);
            if (root.left != null) {
                root.left.parent = root;
            }
        }

        balance(root);

        return root;
    }

    @Override
    public Node<T> find(T value) {
        return null;
    }

    @Override
    public void traverse() {
        traverse(root);
    }

    private void traverse(Node<T> root) {
        if (root == null) {
            return;
        }

        traverse(root.left);
        traverse(root.right);
    }

    @Override
    public T getMax() {
        return getMax(root).value;
    }

    private Node<T> getMax(Node<T> root) {
        if (root.right == null) {
            return root;
        }

        return getMax(root.right);
    }

    @Override
    public T getMin() {
        return getMin(root).value;
    }

    private Node<T> getMin(Node<T> root) {
        if (root.left == null) {
            return root;
        }

        return getMin(root.left);
    }

    @Override
    public int nodesCount() {
        return nodesCount;
    }
}
