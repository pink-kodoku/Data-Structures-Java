package trees.binarySearchTree;

import java.util.HashSet;
import java.util.Set;

public class FamilyAgeProblem {
    private class Node {
        private int age;
        private String name;

        private Node left;
        private Node right;

        public Node(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
              "age=" + age +
              ", name='" + name + '\'' +
              '}';
        }
    }

    private Node root;

    public void insert(int age, String name) {
        root = insert(root, new Node(age, name));
    }

    private Node insert(Node root, Node current) {
        if (root == null) {
            return current;
        }

        if (root.age > current.age) {
            root.left = insert(root.left, current);
        } else if (root.age < current.age) {
            root.right = insert(root.right, current);
        }

        return root;
    }

    public int sumOfNode(int age) {
        if (isEmpty()) {
            throw new IllegalStateException();
        }

        Node node = findNode(age);
        return sumOfNode(0, node);
    }

    private int sumOfNode(int total, Node current) {
        if (current == null) {
            return 0;
        }

        int left = sumOfNode(total, current.left);
        int right = sumOfNode(total, current.right);

        return left + right + current.age + total;
    }

    private Node findNode(int age) {
        return findNode(root, age);
    }

    private Node findNode(Node root, int age) {
        if (root == null) {
            return null;
        }
        if (root.age == age) {
            return root;
        }

        if (root.age < age) {
            return findNode(root.right, age);
        } else {
            return findNode(root.left, age);
        }
    }

    private boolean isEmpty() {
        return root == null;
    }
}
