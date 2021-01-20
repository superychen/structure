package cqyc.structure;

import cqyc.structure.Stack.impl.ArrayStackYc;

/**
 * @author cqyc
 * @Description:
 * @date 2020/12/31
 */
public class Main {
    public static void main(String[] args) {
//        ArrayYcThree<String> arrayYc = new ArrayYcThree<String>(20);
//        for (int i = 0; i < 10; i++) {
//            arrayYc.addLast(String.valueOf(i));
//        }
//        System.out.println(arrayYc.toString());

        ArrayStackYc<Integer> arrayStack = new ArrayStackYc<Integer>();
        for (int i = 0; i < 5; i++) {
            arrayStack.push(i);
            System.out.println(arrayStack);
        }
        arrayStack.pop();
        System.out.println(arrayStack);

    }
}
