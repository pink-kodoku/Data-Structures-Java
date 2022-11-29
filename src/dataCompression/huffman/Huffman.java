package dataCompression.huffman;

public class Huffman {
    private class Node implements Comparable<Node> {
        private final int frequency;
        private Node left;
        private Node right;

        public Node(Node left, Node right) {
            this.frequency = left.frequency + right.frequency;
            this.left = left;
            this.right = right;
        }

        public Node(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequency, o.frequency);
        }
    }

    private class Leaf extends Node {
        private final char character;

        public Leaf(char character, int frequency) {
            super(frequency);
            this.character = character;
        }
    }
}
