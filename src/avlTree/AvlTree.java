package avlTree;

public class AvlTree<T extends Comparable<T>> implements Tree<T> {
    private Node<T> root;
    private int nodesCount;

    public void insert(T value) {
        root = insert(root, new Node<>(value, null));
        nodesCount++;
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

        setHeight(root);
        return balance(root);
    }

    @Override
    public Node<T> find(T value) {
        return find(root, value);
    }

    private Node<T> find(Node<T> root, T value) {
        if (root == null) {
            return null;
        }
        if (root.isEqual(value)) {
            return root;
        }

        return find(root.isSmaller(value) ? root.right : root.left, value);
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
        System.out.println(root.value);
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
