package linearStructures.lru;

public class Test {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(4);

        lruCache.put(1, "a");
        lruCache.put(2, "b");
        lruCache.put(3, "c");
        lruCache.put(4, "d");

        System.out.println(lruCache.get(4));

        lruCache.put(6, "o");
        System.out.println("");
    }
}
