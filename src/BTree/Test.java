package BTree;

public class Test {
    public static void main(String[] args) {
        BTree<Integer> bTree = new BTree<Integer>(3);

        bTree.add(7);
        bTree.add(15);
        bTree.add(2);
        bTree.add(88);
        bTree.add(12);
        bTree.add(21);
        bTree.add(43);
        System.out.println(bTree.contains(21));
        System.out.println("");
    }
}
