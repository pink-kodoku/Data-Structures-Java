package trees.ternarySearchTree;

public class TernarySearchTree<T extends Comparable<T>> {
    private class Node {
        private char character;
        private T value;
        private Node leftChild;
        private Node middleChild;
        private Node rightChild;

        public Node(char ch) {
            this.character = ch;
        }

        public Node(char ch, T value) {
            this.character = ch;
            this.value = value;
        }

        private boolean isEndOfWord() {
            return value != null;
        }

        @Override
        public String toString() {
            return "Node{" +
              "character=" + character +
              ", value=" + value +
              '}';
        }
    }

    private Node root;

    public void insert(String word, T value) {
        if (word == null) {
            throw new IllegalArgumentException();
        }

        root = insert(root, word, value, 0);
    }

    private Node insert(Node node, String word, T value, int index) {
        char character = word.charAt(index);
        if (node == null) {
            node = new Node(character);
        }

        if (node.character < character) {
            node.rightChild = insert(node.rightChild, word, value, index);
        } else if (node.character > character) {
            node.leftChild = insert(node.leftChild, word, value, index);
        } else if (word.length() - 1 == index) {
            node.value = value;
        } else if (index < word.length() - 1) {
            node.middleChild = insert(node.middleChild, word, value, index + 1);
        }

        return node;
    }

    public boolean contains(String word) {
        Node node = search(root, word, 0);
        return node != null && node.isEndOfWord();
    }

    private Node search(Node node, String word, int index) {
        char character = word.charAt(index);
        if (node == null) {
            return null;
        }

        if (node.character > character) {
            return search(node.leftChild, word, index);
        } else if (node.character < character) {
            return search(node.rightChild, word, index);
        } else if (word.length() - 1 > index) {
            return search(node.middleChild, word, index + 1);
        }

        return node;
    }

    public T get(String word) {
        Node node = search(root, word, 0);
        return node != null ? node.value : null;
    }

    public void remove(String word) {
        remove(root, word, 0);
    }

    private void remove(Node node, String word, int index) {
        if (node == null) {
            return;
        }
        char character = word.charAt(index);
        if (character < node.character) {
            remove(node.leftChild, word, index);
        } else if (character > node.character) {
            remove(node.rightChild, word, index);
        } else if (index < word.length() - 1) {
            remove(node.middleChild, word, index + 1);
        } else if (index == word.length() - 1 && node.isEndOfWord()) {
            node.value = null;
        }
    }
}
