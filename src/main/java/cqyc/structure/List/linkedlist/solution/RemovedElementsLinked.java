package cqyc.structure.List.linkedlist.solution;


/**
 * @author cqyc
 * @Description: 1 -> 2 -> 6 -> 6 -> 5
 * 传入6 ，使得返回的链表 1 -> 2 -> 5
 * @date 2021/1/13
 */
public class RemovedElementsLinked {

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }

        }
        return head;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);
        ListNode listNode = new RemovedElementsLinked().removeElements(head, 6);
        System.out.println(listNode);
    }

}
