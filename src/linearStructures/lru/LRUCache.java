package linearStructures.lru;

import java.util.HashMap;
import java.util.LinkedList;

public class LRUCache {
    private int capacity;
    private HashMap<Integer, String> data = new HashMap<>();
    private LinkedList<Integer> order = new LinkedList<>();


    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public void put(int key, String value) {
        if (order.size() >= capacity) {
            int keyRemoved = order.removeLast();
            data.remove(keyRemoved);
        }

        order.addFirst(key);
        data.put(key, value);
    }

    public String get(int key) {
        String res = data.get(key);
        if (res != null) {
            order.remove((Object) key);
            order.addFirst(key);
        }

        return res;
    }

    public String remove(int key) {
        String removed = null;
        if (data.containsKey(key)) {
            removed = data.remove(key);
            order.remove(key);
        }

        return removed;
    }
}
