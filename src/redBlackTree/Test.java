package redBlackTree;

public class Test {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();

        tree.insert(30);
        tree.insert(20);
        tree.insert(10);


        tree.delete(20);

        System.out.println("");
    }
}
