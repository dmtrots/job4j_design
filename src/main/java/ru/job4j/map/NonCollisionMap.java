package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }

        int index = indexFor(hash(Objects.hashCode(key)));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash(Objects.hashCode(key)));
        MapEntry<K, V> entry = table[index];
        if (entry != null && Objects.equals(entry.key, key)) {
            result = entry.value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = indexFor(hash(Objects.hashCode(key)));
        MapEntry<K, V> entry = table[index];
        if (entry != null && Objects.equals(entry.key, key)) {
            table[index] = null;
            count--;
            modCount++;
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("Concurrent modification detected");
                }
                while (currentIndex < table.length && table[currentIndex] == null) {
                    currentIndex++;
                }
                return currentIndex < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements");
                }
                return table[currentIndex++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        int newCapacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[newCapacity];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int newHash = hash(Objects.hashCode(entry.key));
                int newIndex = newHash & (newCapacity - 1);
                if (newTable[newIndex] != null) {
                    throw new IllegalStateException("Collision detected during expansion");
                }
                newTable[newIndex] = entry;
            }
        }
        table = newTable;
        capacity = newCapacity;
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        NonCollisionMap<Integer, String> map = new NonCollisionMap<>();
        System.out.println("hash(0) = " + map.hash(0));
        System.out.println("hash(65535) = " + map.hash(65535));
        System.out.println("hash(65536) = " + map.hash(65536));
        System.out.println("map.indexFor(0) = " + map.indexFor(0));
        System.out.println("map.indexFor(7) = " + map.indexFor(7));
        System.out.println("map.indexFor(8) = " + map.indexFor(8));
    }
}
