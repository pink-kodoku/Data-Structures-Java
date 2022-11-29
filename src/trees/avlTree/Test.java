package trees.avlTree;

public class Test {
    public static void main(String[] args) {
        AvlTree<Integer> avlTree = new AvlTree<>();

        avlTree.insert(10);
        avlTree.insert(20);
        avlTree.insert(30);
        avlTree.insert(12);
        avlTree.insert(5);
        avlTree.insert(7);
        avlTree.insert(3);
        avlTree.insert(1);


        avlTree.delete(12);
        avlTree.traverse();
    }
}
