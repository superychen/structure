package cqyc.structure.leetcode.shuzu;

import java.util.Stack;

/**
 * @author cqyc
 * @Description: 1480. 一维数组的动态和
 * @date 2021/3/7
 */
public class RunningSum {

    /**
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     *
     * 请返回 nums 的动态和
     *
     * 示例 1：
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,6,10]
     * 解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。
     *
     */
    public int[] runningSum(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (stack.isEmpty()) {
                stack.push(nums[i]);
                res[i] = nums[i];
                continue;
            }
            Integer popNum = stack.pop();
            res[i] = popNum + nums[i];
            stack.push(res[i]);
        }
        return res;
    }
}
