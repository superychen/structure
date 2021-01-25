package cqyc.structure.Tree.set;


import cqyc.structure.List.linkedlist.LinkedListYcTwo;

import java.util.ArrayList;

/**
 * @author cqyc
 * @Description:
 * @date 2021/1/23
 */
public class LinkedListSetYc<T> implements SetYc<T> {

    private LinkedListYcTwo<T> linkedListYcTwo;

    public LinkedListSetYc() {
        linkedListYcTwo = new LinkedListYcTwo<T>();
    }
    @Override
    public void add(T t) {
        if (!linkedListYcTwo.contains(t)) {
            linkedListYcTwo.addFirst(t);
        }
    }

    @Override
    public void remove(T t) {
        linkedListYcTwo.removeElement(t);
    }

    @Override
    public boolean contains(T t) {
        return linkedListYcTwo.contains(t);
    }

    @Override
    public int getSize() {
        return linkedListYcTwo.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedListYcTwo.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("pride and Prejudice");
        ArrayList<String> words = new ArrayList<String>();
        FileOperation.readFile("D:\\cqyc\\java\\structure\\src\\pride-and-prejudice.txt", words);
        System.out.println("total words: " + words.size());

        LinkedListSetYc<String> set = new LinkedListSetYc<String>();
        for (String word : words) {
            set.add(word);
        }
        System.out.println("total different words: " + set.getSize());

        System.out.println("a tale of two cities");
        ArrayList<String> words1 = new ArrayList<String>();
        FileOperation.readFile("D:\\cqyc\\java\\structure\\src\\a-tale-of-two-cities.txt", words1);
        System.out.println("total words1: " + words1.size());

        LinkedListSetYc<String> set1 = new LinkedListSetYc<String>();
        for (String word : words) {
            set1.add(word);
        }
        System.out.println("total different words: " + set1.getSize());
    }
}
