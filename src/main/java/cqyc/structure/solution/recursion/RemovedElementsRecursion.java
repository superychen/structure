package cqyc.structure.solution.recursion;

import cqyc.structure.List.linkedlist.solution.ListNode;

import java.util.List;

/**
 * @author cqyc
 * @Description: 使用递归完成链表删除指定值 1 -> 2 -> 6 -> 6 -> 5
 * 传入6 ，使得返回的链表 1 -> 2 -> 5
 * @date 2021/1/14
 */
public class RemovedElementsRecursion {

    public ListNode removedElementRecursion(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removedElementRecursion(head.next, val);
        // 第二种方式，可以改成三元表达式
        if (head.val == val) {
            return head.next;
        } else {
            return head;
        }

//第一种方式        //获取下一块对应的链表，在这一步已经得到需要递归的数据
//        ListNode res = removedElementRecursion(head.next, val);
//        //然后判断当前的头结点是否需要被删除，如果要就直接返回res,如果不就让head的下一个节点连接获取的链表
//        if (head.val == val) {
//            return res;
//        } else {
//            head.next = res;
//            return head;
//        }
    }

    /**
     * 使用递归完成链表删除指定值 1 -> 2 -> 6 -> 6 -> 5
     * 传入6 ，使得返回的链表 1 -> 2 -> 5  输出日志显示
     */
    public ListNode removedElementRecursionTwo(ListNode head, int val, int depth) {
        String depthString = generateDepthString(depth);

        System.out.print(depthString);
        System.out.println("Call: remove " + val + " in " + head);
        if (head == null) {
            System.out.print(depthString);
            System.out.println("Return: " + head);
            return head;
        }
        ListNode res = removedElementRecursionTwo(head.next, val, depth + 1);
        System.out.print(depthString);
        System.out.println("After remove " + val + ": " + res);

        ListNode ret;
        if (head.val == val) {
            ret = res;
        } else {
            head.next = res;
            ret = head;
        }
        System.out.print(depthString);
        System.out.println("Return: " + ret);
        return ret;
    }

    private String generateDepthString(int depth) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            builder.append("--");
        }
        return builder.toString();
    }

    /**
     * 链表中倒数第k个节点
     * 给定一个链表: 1->2->3->4->5, 和 k = 2.
     * 返回链表 4->5.
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        int depth = 0;
        ListNode node = head;
        while (node != null) {
            depth = depth + 1;
            node = node.next;
        }
        System.out.println(depth);
        return getKthFromEndChild(head, k, depth);
    }

    private ListNode getKthFromEndChild(ListNode head, int k, int depth) {
        if (head == null) {
            return null;
        }
        ListNode res = getKthFromEndChild(head.next, k, depth - 1);
        if (depth == k) {
            return head;
        } else {
            return res;
        }
    }

    /**
     * 输入：[1,2,3,4,5]
     * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
     * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
     * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
     * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
     *
     */
    private int middleNodeSize = 0;
    private int middleInt = 0;
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        middleInt = middleInt + 1;
        ListNode res = middleNode(head.next);
        middleNodeSize = middleNodeSize + 1;

        if ((middleInt / 2) + 1 == middleNodeSize) {
            if (middleInt % 2 == 0) {
                return head.next;
            } else {
                return head;
            }
        } else {
            return res;
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
//        System.out.println(head);
//        ListNode listNode = new RemovedElementsRecursion().removedElementRecursionTwo(head, 6, 0);
//        System.out.println(listNode);
        ListNode kthFromEnd = new RemovedElementsRecursion().middleNode(head);
        System.out.println(kthFromEnd);
    }

}
