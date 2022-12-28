package trees.binarySearchTree;

public class Test {
    public static void main(String[] args) {
        FamilyAgeProblem tree = new FamilyAgeProblem();

        tree.insert(47, "Adam");
        tree.insert(21, "Kevin");
        tree.insert(55, "Joe");
        tree.insert(68, "Marko");
        tree.insert(34, "Noel");
        tree.insert(20, "Arnold");
        tree.insert(23, "Susan");
        tree.insert(38, "Rose");

        tree.sumOfNode(21);
    }
}
