package BTree;

import java.util.Arrays;
import java.util.Comparator;

public class BTree<T extends Comparable<T>> {
    private int minKeySize = 1;
    private int minChildrenSize = minKeySize + 1;
    private int maxKeySize = 2 * minKeySize;
    private int maxChildrenSize = maxKeySize + 1;

    private Node<T> root = null;
    private int size = 0;

    public BTree() {
    }

    public BTree(int order) {
        minKeySize = order;
        minChildrenSize = minKeySize + 1;
        maxKeySize = 2 * minKeySize;
        maxChildrenSize = maxKeySize + 1;
    }

    public boolean add(T value) {
        if (root == null) {
            root = new Node<T>(null, maxKeySize, maxChildrenSize);
            root.addKey(value);
        } else {
            Node<T> node = root;
            while (node != null) {
                if (node.numberOfChildren() == 0) {
                    node.addKey(value);
                    if (node.numberOfKeys() <= maxKeySize) {
                        break;
                    }
                    // need to split up
                    split(node);
                    break;
                }

                // Lesser of equal
                T lesser = node.getKey(0);
                if (value.compareTo(lesser) <= 0) {
                    node = node.getChild(0);
                    continue;
                }

                // Greater
                int numberOfKeys = node.numberOfKeys();
                int last = numberOfKeys - 1;
                T greater = node.getKey(last);
                if (value.compareTo(greater) > 0) {
                    node = node.getChild(numberOfKeys);
                    continue;
                }

                // Search internal nodes
                for (int i = 1; i < node.numberOfKeys(); i++) {
                    T prev = node.getKey(i - 1);
                    T next = node.getKey(i);
                    // if value between left and right
                    if (value.compareTo(prev) > 0 && value.compareTo(next) <= 0) {
                        node = node.getChild(i);
                        break;
                    }
                }
            }
        }
        size++;
        return true;
    }

    private void split(Node<T> nodeToSplit) {
        Node<T> node = nodeToSplit;
        int numberOfKeys = node.numberOfKeys();
        int medianIndex = numberOfKeys / 2;
        T medianValue = node.getKey(medianIndex);

        Node<T> left = new Node<T>(null, maxKeySize, maxChildrenSize);
        for (int i = 0; i < medianIndex; i++) {
            left.addKey(node.getKey(i));
        }

        if (node.numberOfChildren() > 0) {
            for (int j = 0; j <= medianIndex; j++) {
                Node<T> c = node.getChild(j);
                left.addChild(c);
            }
        }

        Node<T> right = new Node<T>(null, maxKeySize, maxChildrenSize);
        for (int i = medianIndex + 1; i < numberOfKeys; i++) {
            right.addKey(node.getKey(i));
        }
        if (node.numberOfChildren() > 0) {
            for (int j = medianIndex + 1; j < node.numberOfChildren(); j++) {
                Node<T> c = node.getChild(j);
                right.addChild(c);
            }
        }

        if (node.parent == null) {
            // new root, height of tree is increased
            Node<T> newRoot = new Node<T>(null, maxKeySize, maxChildrenSize);
            newRoot.addKey(medianValue);
            node.parent = newRoot;
            root = newRoot;
            node = root;
            node.addChild(left);
            node.addChild(right);
        } else {
            // move the median value up to the parent
            Node<T> parent = node.parent;
            parent.addKey(medianValue);
            parent.removeChild(node);
            parent.addChild(left);
            parent.addChild(right);

            if (parent.numberOfKeys() > maxKeySize) {
                split(parent);
            }
        }
    }

    public boolean contains(T value) {
        Node<T> node = getNode(value);
        return node != null;
    }

    private Node<T> getNode(T value) {
        Node<T> node = root;
        while (node != null) {
            T lesser = node.getKey(0);
            if (value.compareTo(lesser) < 0) {
                if (node.numberOfChildren() > 0) {
                    node = node.getChild(0);
                } else {
                    node = null;
                }
                continue;
            }

            int numberOfKeys = node.numberOfKeys();
            int last = numberOfKeys - 1;
            T greater = node.getKey(last);

            if (value.compareTo(greater) > 0) {
                if (node.numberOfChildren() > numberOfKeys) {
                    node = node.getChild(numberOfKeys);
                } else {
                    node = null;
                }
                continue;
            }

            // search internal nodes
            for (int i = 0; i < numberOfKeys; i++) {
                T currentValue = node.getKey(i);
                if (currentValue.compareTo(value) == 0) {
                    return node;
                }

                int next = i + 1;
                if (next <= last) {
                    T nextValue = node.getKey(next);
                    if (currentValue.compareTo(value) < 0 && nextValue.compareTo(value) > 0) {
                        if (next < node.numberOfChildren()) {
                            node = node.getChild(next);
                            break;
                        }
                        return null;
                    }
                }
            }
        }

        return null;
    }

    private static class Node<T extends Comparable<T>> {
        private T[] keys = null;
        private int keysSize = 0;
        private Node<T>[] children = null;
        private int childrenSize = 0;
        protected Node<T> parent = null;

        private Comparator<Node<T>> comparator = Comparator.comparing((Node<T> tNode) -> tNode.getKey(0));

        public Node(Node<T> parent, int maxKeySize, int maxChildrenSize) {
            this.parent = parent;
            this.keys = (T[]) new Comparable[maxKeySize + 1];
            this.keysSize = 0;
            this.children = new Node[maxChildrenSize + 1];
            this.childrenSize = 0;
        }

        private T getKey(int index) {
            return keys[index];
        }

        private int indexOf(T value) {
            for (int i = 0; i < keysSize; i++) {
                if (keys[i].equals(value)) return i;
            }
            return -1;
        }

        private void addKey(T value) {
            keys[keysSize++] = value;
            Arrays.sort(keys, 0, keysSize);
        }

        private T removeKey(T value) {
            T removed = null;
            boolean found = false;
            if (keysSize == 0) return null;
            for (int i = 0; i < keysSize; i++) {
                if (keys[i].equals(value)) {
                    found = true;
                    removed = keys[i];
                } else if (found) {
                    keys[i - 1] = keys[i];
                }
            }

            if (found) {
                keysSize--;
                keys[keysSize] = null;
            }

            return removed;
        }

        private T removeKey(int index) {
            if (index >= keysSize) {
                return null;
            }
            T value = keys[index];
            for (int i = index + 1; i < keysSize; i++) {
                keys[i - 1] = keys[i];
            }
            keysSize--;
            keys[keysSize] = null;
            return value;
        }

        private int numberOfKeys() {
            return keysSize;
        }

        private Node<T> getChild(int index) {
            if (index >= childrenSize) {
                return null;
            }

            return children[index];
        }

        private int indexOf(Node<T> child) {
            for (int i = 0; i < childrenSize; i++) {
                if (children[i].equals(child)) {
                    return i;
                }
            }

            return -1;
        }

        private boolean addChild(Node<T> child) {
            child.parent = this;
            children[childrenSize++] = child;
            Arrays.sort(children, 0, childrenSize, comparator);

            return true;
        }

        private boolean removeChild(Node<T> child) {
            boolean found = false;
            if (childrenSize == 0) {
                return found;
            }

            for (int i = 0; i < childrenSize; i++) {
                if (children[i].equals(child)) {
                    found = true;
                } else if (found) {
                    children[i - 1] = children[i];
                }
            }

            if (found) {
                childrenSize--;
                children[childrenSize] = null;
            }

            return found;
        }

        private Node<T> removeChild(int index) {
            if (index >= childrenSize) {
                return null;
            }

            Node<T> value = children[index];
            children[index] = null;
            for (int i = index + 1; i < childrenSize; i++) {
                children[i - 1] = children[i];
            }

            childrenSize--;
            children[childrenSize] = null;
            return value;
        }

        private int numberOfChildren() {
            return childrenSize;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();

            builder.append("keys=[");
            for (int i = 0; i < numberOfKeys(); i++) {
                T value = getKey(i);
                builder.append(value);
                if (i < numberOfKeys() - 1) {
                    builder.append(", ");
                }
            }

            builder.append("]\n");

            if (parent != null) {
                builder.append("parent=[");
                for (int i = 0; i < parent.numberOfKeys(); i++) {
                    T value = parent.getKey(i);
                    builder.append(value);
                    if (i < parent.numberOfKeys() - 1) {
                        builder.append(", ");
                    }
                    builder.append("]\n");
                }
            }

            if (children != null) {
                builder.append("keySize=").append(numberOfKeys()).append(" children=").append(numberOfChildren()).append("\n");
            }

            return builder.toString();
        }
    }
}

