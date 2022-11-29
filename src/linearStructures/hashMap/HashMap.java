package linearStructures.hashMap;

import java.util.LinkedList;

public class HashMap<K extends Comparable<K>, V> {
    private class Entry {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private LinkedList<Entry>[] items = new LinkedList[5];

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        Entry newEntry = new Entry(key, value);
        int index = hash(key);
        if (items[index] == null) {
            items[index] = new LinkedList<>();
        }

        var bucket = items[index];

        for (var entry : bucket) {
            if (equal(entry.key, key)) {
                entry.value = value;
                size++;
                return;
            }
        }

        items[index].addLast(newEntry);
        size++;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        var bucket = items[hash(key)];
        if (bucket == null) {
            return null;
        }

        for (var value : bucket) {
            if (equal(value.key, key)) {
                return value.value;
            }
        }

        return null;
    }

    private boolean contains(K key) {
        if (key == null) {
            return false;
        }

        return get(key) != null;
    }

    private Entry remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        Entry removed = null;
        int index = hash(key);
        var bucket = items[index];

        for (var value : bucket) {
            if (equal(value.key, key)) {
                removed = value;
                bucket.remove(value);
            }
        }

        return removed;
    }

    private boolean equal(K first, K second) {
        return first.compareTo(second) == 0;
    }

    private int hash(K key) {
        return key.hashCode() % items.length;
    }
}
