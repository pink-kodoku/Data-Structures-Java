import trees.avlTree.AvlTree;


public class Test {
    public static void main(String[] args) {
        AvlTree tree = new AvlTree();

        for (int i = 0; i < 100_000; i++) {
            tree.insert(getRandomNumber(1, 100_000));
        }

        System.out.println("insert");
        tree.insert(86444);


        System.out.println("delete");
        tree.delete(86444);

    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
