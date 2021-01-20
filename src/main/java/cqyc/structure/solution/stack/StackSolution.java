package cqyc.structure.solution.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author cqyc
 * @Description: 栈相关
 * @date 2021/1/17
 */
public class StackSolution {

    /**
     * 1544. 整理字符串
     * 给你一个由大小写英文字母组成的字符串 s 。
     * <p>
     * 一个整理好的字符串中，两个相邻字符 s[i] 和 s[i+1]，其中 0<= i <= s.length-2 ，要满足如下条件:
     * 若 s[i] 是小写字符，则 s[i+1] 不可以是相同的大写字符。
     * 若 s[i] 是大写字符，则 s[i+1] 不可以是相同的小写字符。
     */
    public String makeGood(String s) {
        if (s == null || "".equals(s)) {
            return "";
        }
        Stack<Character> stack = new Stack<Character>();
        char[] charStrArr = s.toCharArray();
        for (int i = 0; i < charStrArr.length; i++) {
            if (stack.isEmpty()) {
                stack.push(charStrArr[i]);
                continue;
            }
            int pre = stack.peek();
            int cur = charStrArr[i];
            if (pre >= 65 && pre <= 90 && pre + 32 == cur) {
                stack.pop();
            } else if (pre >= 97 && pre - 32 == cur) {
                stack.pop();
            } else {
                stack.push(charStrArr[i]);
            }
        }
        String res = "";
        for (Object o : stack.toArray()) {
            res = res + o;
        }
        return res;
    }

    public static void main(String[] args) {
        StackSolution stackSolution = new StackSolution();
        String str = "abBAcC";
        System.out.println(stackSolution.makeGood(str));
    }

}
