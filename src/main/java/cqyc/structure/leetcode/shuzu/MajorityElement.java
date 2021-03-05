package cqyc.structure.leetcode.shuzu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cqyc
 * @Description: 面试题 17.10. 主要元素
 * @date 2021/3/3
 */
public class MajorityElement {

    /**
     * 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
     * 示例 1：
     * 输入：[1,2,5,9,5,9,5,5,5]
     * 输出：5
     *
     * 示例 2：
     * 输入：[3,2]
     * 输出：-1
     */
    public int majorityElement(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (arr.size() == 0) {
                arr.add(nums[i]);
                continue;
            }
            Integer cur = arr.get(arr.size() - 1);
            if (nums[i] != cur) {
                arr.remove(arr.size() - 1);
            } else {
                arr.add(nums[i]);
            }
        }
        if (arr.size() > 0) {
            int n = 0;
            for (int num : nums) {
                if (num == arr.get(0)) {
                    n ++;
                }
            }
            if (n > nums.length / 2) {
                return arr.get(0);
            }
        }
        return -1;
    }

}
