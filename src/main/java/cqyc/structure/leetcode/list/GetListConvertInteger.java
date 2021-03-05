package cqyc.structure.leetcode.list;

/**
 * @author cqyc
 * @Description: 1290. 二进制链表转整数
 * @date 2021/3/5
 */
public class GetListConvertInteger {

    /**
     * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
     * <p>
     * 请你返回该链表所表示数字的 十进制值 。
     * 输入：head = [1,0,1]
     * 输出：5
     * 解释：二进制数 (101) 转化为十进制数 (5)
     * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
     * 输出：18880
     */

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    int index = 0;

    public int getDecimalValue(ListNode head) {
        int res = 0;
        ListNode node = head;
        while (head != null) {
            index = index + 1;
            head = head.next;
        }
        head = node;
        while (head != null) {
            int a = 0;
            if (head.val == 1) {
                a = 1;
                for (int i = 0; i < index - 1; i++) {
                    a = a * 2;
                }
            }
            res = res + a;
            head = head.next;
            index = index - 1;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(0);
        listNode.next.next = new ListNode(1);
        GetListConvertInteger getListConvertInteger = new GetListConvertInteger();
        System.out.println(getListConvertInteger.getDecimalValue(listNode));
    }
}
