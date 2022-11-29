package trees.ternarySearchTree;

public class Test {
    public static void main(String[] args) {
        TernarySearchTree<Integer> ternarySearchTree = new TernarySearchTree<>();

        ternarySearchTree.insert("cat", 5);
        ternarySearchTree.insert("care", 5);

        ternarySearchTree.remove("cat");

        System.out.println(ternarySearchTree.get("cat"));
    }
}
