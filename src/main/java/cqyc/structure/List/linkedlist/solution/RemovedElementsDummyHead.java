package cqyc.structure.List.linkedlist.solution;


/**
 * @author cqyc
 * @Description:   1 -> 2 -> 6 -> 6 -> 5
 * 传入6 ，使得返回的链表 1 -> 2 -> 5
 * @date 2021/1/13
 */
public class RemovedElementsDummyHead {

    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        while (prev.next != null) {
            //不用考虑释放内存的代码
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }


}
