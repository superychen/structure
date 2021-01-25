package cqyc.structure.Tree.solution;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author cqyc
 * @Description: 关于树的一些小算法
 * @date 2021/1/24
 */
public class TreeSolution {

    public int uniqueMorseRepresentations(String[] words) {
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",
                ".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        Set<String> set = new TreeSet<String>();
        for (String word : words) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                String res = codes[word.charAt(i) - 'a'];
                builder.append(res);
            }
            set.add(builder.toString());
        }
        return set.size();
    }

}
