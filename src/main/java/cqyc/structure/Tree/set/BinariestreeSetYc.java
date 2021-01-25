package cqyc.structure.Tree.set;

import cqyc.structure.Tree.binariestree.BinariesTree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/23
 */
public class BinariestreeSetYc<T extends Comparable<T>> implements SetYc<T> {

    private BinariesTree<T> bst;

    public BinariestreeSetYc() {
        bst = new BinariesTree<T>();
    }

    @Override
    public void add(T t) {
        bst.add(t);
    }

    @Override
    public void remove(T t) {
        bst.remove(t);
    }

    @Override
    public boolean contains(T t) {
        return bst.contains(t)  ;
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    public static void main(String[] args) {
//        System.out.println("pride and Prejudice");
//        ArrayList<String> words = new ArrayList<String>();
//        FileOperation.readFile("D:\\cqyc\\java\\structure\\src\\pride-and-prejudice.txt", words);
//        System.out.println("total words: " + words.size());
//
//        BinariestreeSetYc<String> set = new BinariestreeSetYc<String>();
//        for (String word : words) {
//            set.add(word);
//        }
//        System.out.println("total different words: " + set.getSize());
        String filename = "D:\\cqyc\\java\\structure\\src\\pride-and-prejudice.txt";
        BinariestreeSetYc<String> binariestreeSetYc = new BinariestreeSetYc<String>();
        double time1 = testSet(binariestreeSetYc, filename);
        System.out.println("BST set: " + time1 + "s");

        System.out.println();

        LinkedListSetYc<String> setYc = new LinkedListSetYc<String>();
        double time2 = testSet(setYc, filename);
        System.out.println("Linked list set: " + time2 + "s");
    }

    /**
     * 测试二分搜索树集成的集合，对比链表集成的结合，作比较
     */
    public static double testSet(SetYc<String> set, String fileName) {
        long startTime = System.nanoTime();

        System.out.println(fileName);
        ArrayList<String> words = new ArrayList<String>();
        if (FileOperation.readFile(fileName, words)) {
            System.out.println("total words: " + words.size());
            for (String word : words) {
                set.add(word);
            }
            System.out.println("total different words: " + set.getSize());
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }
}
