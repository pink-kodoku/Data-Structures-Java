package trie;

public class Test {
    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("careful");
        trie.insert("care");
        trie.insert("card");
        trie.insert("cat");

        trie.autocomplete("car");
    }
}
