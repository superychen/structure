package cqyc.structure.Stack.Solution;

import java.util.Stack;

/**
 * @author cqyc
 * @Description: 匹配正确或者错误的括号---使用栈来进行解决
 * @date 2021/1/5
 */
public class BracketsSolution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                //弹出的元素是否匹配
                if (pop == '(' && c != ')') {
                    return false;
                }
                if (pop == '[' && c != ']') {
                    return false;
                }
                if (pop == '{' && c != '}') {
                    return false;
                }
            }
        }
        //最后判断栈顶是否还有元素
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        BracketsSolution solution = new BracketsSolution();
        String str = "(({))";
        System.out.println(solution.isValid(str));
    }
}
