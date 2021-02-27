package cqyc.structure.hash.hashTable;

import cqyc.structure.Tree.set.FileOperation;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author cqyc
 * @Description:
 * @date 2021/2/24
 */
public class HashTableYc<K, V> {

    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private static final int initCapacity = 7;


    private TreeMap<K ,V>[] hashtable;
    private int M;
    private int size;

    public HashTableYc(int M) {
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTableYc() {
        this(initCapacity);
    }

    private int hash(K key) {
        //取绝对值
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            if (size >= upperTol * M) {
                resize(2 * M);
            }
        }
    }

    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size < lowerTol * M && M / 2 > initCapacity) {
                resize(M / 2);
            }
        }
        return ret;
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key: map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashtable = newHashTable;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    public static void main(String[] args) {
        String filename = "D:\\cqyc\\java\\structure\\src\\pride-and-prejudice.txt";
        System.out.println(filename);
        ArrayList<String> words = new ArrayList<String>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words:" + words.size());
            long startTime = System.nanoTime();
            HashTableYc<String, Integer> ht = new HashTableYc<>(131071);
            for (String word : words) {
                if (ht.contains(word)) {
                    ht.set(word, ht.get(word) + 1);
                } else {
                    ht.add(word, 1);
                }
            }
            System.out.println("Total different words: " + ht.getSize());
            System.out.println("Frequency of PRIDE: " + ht.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + ht.get("prejudice"));
            long endTime = System.nanoTime();
            System.out.println((endTime - startTime) / 1000000000.0);
        }
    }

}
