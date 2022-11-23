package trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Trie {
    private class Node {
        private char value;
        private boolean isEndOfWord = false;
        private HashMap<Character, Node> children = new HashMap<>();

        public Node(char value) {
            this.value = value;
        }

        private boolean hasChild(char ch) {
            return children.containsKey(ch);
        }

        private void addChild(char ch) {
            children.put(ch, new Node(ch));
        }

        private Node getChild(char ch) {
            return children.get(ch);
        }

        private Node[] getChildren() {
            return children.values().toArray(new Node[0]);
        }

        private boolean hasChildren() {
            return !children.isEmpty();
        }

        private void removeChild(char ch) {
            children.remove(ch);
        }

        @Override
        public String toString() {
            return "Node{" +
              "value=" + value +
              '}';
        }
    }

    private Node root = new Node(' ');

    public void insert(String value) {
        String searchTerm = value.toLowerCase();

        insert(root, searchTerm, 0);
    }

    public void insert(Node current, String value, int index) {
        if (index >= value.length()) {
            current.isEndOfWord = true;
            return;
        }

        if (current == null) {
            return;
        }

        if (!current.hasChild(value.charAt(index))) {
            current.addChild(value.charAt(index));
        }

        insert(current.getChild(value.charAt(index)), value, index + 1);
    }

    public boolean contains(String value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
        return contains(root, value, 0);
    }

    private boolean contains(Node current, String value, int index) {
        if (index >= value.length()) {
            return current.isEndOfWord;
        }

        if (!current.hasChild(value.charAt(index))) {
            return false;
        }

        return contains(current.getChild(value.charAt(index)), value, index + 1);
    }

    public void remove(String value) {
        if (!contains(value)) {
            return;
        }
        remove(root, value, 0);
    }

    private void remove(Node current, String value, int index) {
        if (index == value.length()) {
            current.isEndOfWord = false;
            return;
        }

        char ch = value.charAt(index);
        Node child = current.getChild(ch);

        remove(child, value, index + 1);

        if (!child.isEndOfWord && !child.hasChildren()) {
            current.removeChild(ch);
        }
    }

    public void autocomplete(String prefix) {
        Node lastNode = getLastNode(prefix);
        if (lastNode == null) {
            System.out.println("No suggestions");
        }

        List<String> result = autocomplete(lastNode, prefix, new LinkedList<>());

        System.out.println(result);
    }

    private List<String> autocomplete(Node current, String value, List<String> list) {
        if (current == null) {
            return list;
        }
        if (current.isEndOfWord) {
            list.add(value);
        }

        for (Node node : current.getChildren()) {
            autocomplete(node, value + node.value, list);
        }

        return list;
    }

    private Node getLastNode(String prefix) {
        if (prefix == null) return null;
        Node current = root;

        for (char ch : prefix.toCharArray()) {
            if (current.hasChild(ch)) {
                current = current.getChild(ch);
            } else {
                return null;
            }
        }

        return current;
    }
}
