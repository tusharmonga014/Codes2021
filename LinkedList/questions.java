
public class questions {
    public class Node {
        int val;
        int data;
        Node next = null;
        Node random;

        Node(int val) {
            this.val = val;
            this.data = val;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private Node reverseList(Node node) {
        Node prev = null;
        Node curr = node;
        Node forw = null;

        while (curr != null) {
            forw = curr.next;
            curr.next = prev;
            prev = curr;

            curr = forw; // IMP ********
        }

        return prev;
    }

    public void reversevalITR_ON(Node head) {
        if (head == null)
            return;
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;
        Node thead1 = head;
        Node thead2 = mid;
        thead2 = reverseList(thead2);

        Node cur1 = thead1;
        Node cur2 = thead2;
        while (cur2 != null) {
            int temp = cur1.val;
            cur1.val = cur2.val;
            cur2.val = temp;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        thead2 = reverseList(thead2);
    }

    public ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode cur = head;
        ListNode forw = null;

        while (cur != null) {
            forw = cur.next;
            cur.next = prev;
            prev = cur;
            cur = forw;
        }

        return prev;
    }

    public ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode mid = getMid(head);
        ListNode head2 = reverse(mid);
        ListNode t1 = head;
        ListNode t2 = head2;
        boolean isPal = true;
        while (t2 != null) {
            if (t1.val != t2.val) {
                isPal = false;
                break;
            }
            t1 = t1.next;
            t2 = t2.next;
        }
        reverse(head2);
        return isPal;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;

        while (n-- > 0) {
            fast = fast.next;
        }

        if (fast == null) {
            ListNode newHead = head.next;
            head.next = null;
            return newHead;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode forw = slow.next;
        slow.next = slow.next.next;
        forw.next = null;
        return head;
    }

    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        ListNode head2 = mid.next;
        mid.next = null;
        head2 = reverse(head2);

        ListNode cur1 = head, cur2 = head2;
        while (cur2 != null) {
            ListNode f1 = cur1.next;
            ListNode f2 = cur2.next;
            cur1.next = cur2;
            cur2.next = f1;
            cur1 = f1;
            cur2 = f2;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        ListNode head = new ListNode(-1);
        ListNode prev = head;

        ListNode cur1 = list1;
        ListNode cur2 = list2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                prev.next = cur1;
                prev = cur1;
                cur1 = cur1.next;
            } else {
                prev.next = cur2;
                prev = cur2;
                cur2 = cur2.next;
            }
        }

        if (cur1 != null) {
            prev.next = cur1;
        }

        if (cur2 != null) {
            prev.next = cur2;
        }

        return head.next;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return head;

        ListNode c1 = head;
        ListNode nhead = head.next;
        ListNode c2 = nhead;

        while (c1.next != null && c2.next != null) {
            c1.next = c1.next.next;
            c2.next = c1.next.next;
            c1 = c1.next;
            c2 = c2.next;
        }

        c1.next = nhead;
        return head;
    }

    Node oddEven_ByVALUES(int N, Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node odd = null;
        Node even = null;
        Node oddh = null;
        Node evenh = null;

        Node temp = head;
        while (temp != null) {
            if (temp.val % 2 == 0) {
                if (evenh == null) {
                    evenh = even = temp;
                } else {
                    even.next = temp;
                    even = even.next;
                }
            } else {
                if (oddh == null) {
                    oddh = odd = temp;
                } else {
                    odd.next = temp;
                    odd = odd.next;
                }
            }
            Node forw = temp.next;
            temp.next = null;
            temp = forw;
        }

        if (evenh == null)
            return oddh;
        else
            even.next = oddh;
        return evenh;
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;

        return mergeTwoLists(sortList(head), sortList(head2));
    }

    public ListNode mergeKLists_(ListNode[] lists, int si, int ei) {
        if (si == ei) {
            return lists[si];
        } else if (si == ei - 1) {
            return mergeTwoLists(lists[si], lists[ei]);
        } else {
            int mid = (si + ei) / 2;
            return mergeTwoLists(mergeKLists_(lists, si, mid), mergeKLists_(lists, mid + 1, ei));
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast)
                break;
        }
        if (fast == null || fast.next == null)
            return null;
        ListNode entry = head;
        while (slow != entry) {
            slow = slow.next;
            entry = entry.next;
            if (slow == entry)
                return slow;
        }
        return head;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        } else if (headA.next == null && headB.next == null) {
            if (headA == headB)
                return headA;
            else
                return null;
        } else {
            ListNode cur = headA;
            while (cur.next != null) {
                cur = cur.next;
            }
            cur.next = headA;
            ListNode ip = detectCycle(headB);
            cur.next = null;
            return ip;
        }
    }

    public int length(ListNode head) {
        ListNode cur = head;
        int count = 1;
        while (cur.next != null) {
            cur = cur.next;
            count++;
        }
        return count;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1)
            return head;

        int len = length(head);

        ListNode h = null;
        ListNode t = null;

        if (k > len) {
            return head;
        }

        ListNode cur = head;

        while (len >= k) {
            ListNode tt = cur;
            ListNode forw = null;
            ListNode prev = t;

            int tempk = k;

            while (tempk-- > 0) {
                forw = cur.next;
                cur.next = prev;
                prev = cur;
                cur = forw;
            }

            len -= k;

            if (h == null) {
                h = prev;
                t = tt;
            }
            t.next = prev;
            t = tt;
        }
        t.next = cur;
        return h;
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right)
            return head;

        int idx = 1;
        ListNode cur = head;

        ListNode oprev = null;
        while (idx < left) {
            oprev = cur;
            cur = cur.next;
            idx++;
        }

        ListNode prev = null;
        ListNode forw = null;

        while (idx <= right) {
            forw = cur.next;
            cur.next = prev;
            prev = cur;
            cur = forw;
            idx++;
        }

        if (left == 1) {
            head.next = forw;
            return prev;
        } else {
            ListNode curTail = oprev.next;
            oprev.next = prev;
            curTail.next = forw;
            return head;
        }
    }

    public void unfold(ListNode head) {

        if (head == null || head.next == null || head.next.next == null)
            return;

        ListNode cur1 = head;
        ListNode head2 = head.next;
        ListNode cur2 = head2;

        while (cur1.next != null && cur2.next != null) {
            cur1.next = cur2.next;
            cur2.next = cur1.next.next;

            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        head2 = reverse(head2);
        cur1.next = head2;
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;

            cur = cur.next.next;
        }

        cur = head;
        while (cur != null) {
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = cur.next.next;
        }

        Node head2 = null;
        Node tail2 = null;

        cur = head;
        while (cur != null) {
            Node cur2 = cur.next;
            cur.next = cur.next.next;

            if (head2 == null) {
                head2 = cur2;
                tail2 = cur2;
            } else {
                tail2.next = cur2;
                tail2 = tail2.next;
            }

            cur = cur.next;
        }

        return head2;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode ans = dummy;

        int carry = 0;
        while (carry > 0 || l1 != null || l2 != null) {
            int sum = carry + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            int toAdd = sum % 10;
            carry = sum / 10;
            ListNode addNode = new ListNode(toAdd);
            ans.next = addNode;
            ans = ans.next;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        return dummy.next;
    }

    public ListNode multiplyWithOneDigit(ListNode l1, int digit) {
        ListNode dummy = new ListNode(-1);
        ListNode ansItr = dummy;
        int carry = 0;

        while (l1 != null || carry > 0) {
            int mul = ((l1 == null ? 0 : l1.val) * digit) + carry;
            int toAdd = mul % 10;
            carry = mul / 10;

            ansItr.next = new ListNode(toAdd);
            ansItr = ansItr.next;

            l1 = l1 == null ? null : l1.next;
        }

        return dummy.next;
    }

    public ListNode multiplyTwoLL(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode dummy = new ListNode(-1);
        ListNode ansItr = dummy;

        l1 = reverse(l1);
        l2 = reverse(l2);

        while (l2 != null) {
            ListNode mul = multiplyWithOneDigit(l1, l2.val);
            ansItr.next = addTwoNumbers(ansItr.next, mul);

            ansItr = ansItr.next;
            l2 = l2.next;
        }

        return reverse(dummy.next);
    }

    public int length(Node head) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        return count;
    }

    public Node reverse(Node head) {
        Node prev = null;
        Node cur = head;
        Node forw = null;

        while (cur != null) {
            forw = cur.next;
            cur.next = prev;
            prev = cur;
            cur = forw;
        }

        return prev;
    }

    public Node subLinkedList(Node l1, Node l2) {
        // remove leading zeroes in lists
        while (l1 != null && l1.next != null && l1.data == 0)
            l1 = l1.next;
        while (l2 != null && l2.next != null && l2.data == 0)
            l2 = l2.next;

        // find lengths of lists
        int len1 = length(l1);
        int len2 = length(l2);

        if (len1 < len2) {
            Node temp = l1;
            l1 = l2;
            l2 = temp;
        }

        if (len1 == len2) {
            Node c1 = l1;
            Node c2 = l2;

            // if lengths are equal, travel and swap if l2 > l1
            while (c1 != null) {
                if (c1.data < c2.data) {
                    Node temp = l1;
                    l1 = l2;
                    l2 = temp;
                    break;
                } else if (c1.data > c2.data) {
                    break;
                } else {
                    c1 = c1.next;
                    c2 = c2.next;
                }
            }
        }

        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        l1 = reverse(l1);
        l2 = reverse(l2);

        Node dummy = new Node(-1);
        Node ansItr = dummy;

        int borrow = 0;
        while (l1 != null) {
            int diff = l1.data - (l2 == null ? 0 : l2.data) + borrow;

            if (diff < 0) {
                borrow = -1;
                diff += 10;
            } else {
                borrow = 0;
            }

            ansItr.next = new Node(diff);
            ansItr = ansItr.next;

            l1 = l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        Node nhead = reverse(dummy.next);

        // remove leading zeroes from ans (except for last zero if all are zeroes)
        while (nhead != null && nhead.next != null && nhead.data == 0) {
            nhead = nhead.next;
        }

        return nhead;
    }

    public ListNode segregateOnLastIndex(ListNode head) {

        if (head == null)
            return null;

        ListNode last = head;
        while (last.next != null) {
            last = last.next;
        }

        ListNode smallerThanLast = new ListNode(-1);
        ListNode si = smallerThanLast;
        ListNode greaterThanLast = new ListNode(-1);
        ListNode gi = greaterThanLast;

        ListNode cur = head;

        while (cur != null) {
            if (cur.val <= last.val) {
                si.next = cur;
                si = si.next;
            } else {
                gi.next = cur;
                gi = gi.next;
            }
            ListNode forw = cur.next;
            cur.next = null;
            cur = forw;
        }

        si.next = greaterThanLast.next;

        return smallerThanLast.next;
    }

    public ListNode segregate(ListNode head, int idx) {
        if (head == null || head.next == null)
            return head;

        ListNode pivot = head;
        while (idx-- > 0) {
            pivot = pivot.next;
        }

        ListNode smallerThanLast = new ListNode(-1);
        ListNode si = smallerThanLast;
        ListNode greaterThanLast = new ListNode(-1);
        ListNode gi = greaterThanLast;

        ListNode cur = head;

        while (cur != null) {
            if (cur != pivot) {
                if (cur.val <= pivot.val) {
                    si.next = cur;
                    si = si.next;
                } else {
                    gi.next = cur;
                    gi = gi.next;
                }
            }

            ListNode forw = cur.next;
            cur.next = null;
            cur = forw;
        }

        si.next = pivot;
        pivot.next = greaterThanLast.next;

        return smallerThanLast.next;
    }


    // -------------------------------------
    //   QUICK SORT ------------------------
    //  ---------------------------------------

    public Node[] segregate(Node head) {
        if (head == null || head.next == null)
            return new Node[] { null, head, null };

        Node sLL = new Node(-1);
        Node gLL = new Node(-1);

        Node si = sLL;
        Node gi = gLL;

        Node pivot = head;
        while (pivot.next != null)
            pivot = pivot.next;

        Node cur = head;
        while (cur != null) {
            if (cur != pivot) {
                if (cur.data <= pivot.data) {
                    si.next = cur;
                    si = si.next;
                } else {
                    gi.next = cur;
                    gi = gi.next;
                }
            }

            Node forw = cur.next;
            cur.next = null;
            cur = forw;
        }

        return new Node[] { sLL.next, pivot, gLL.next };
    }

    public Node[] joinLists(Node[] left, Node pivot, Node[] right) {
        Node head, tail;

        if (left[0] == null && right[0] == null) {
            head = pivot;
            tail = pivot;
        } else if (left[0] == null) {
            head = pivot;
            head.next = right[0];
            tail = right[1];
        } else if (right[0] == null) {
            head = left[0];
            left[1].next = pivot;
            tail = pivot;
        } else {
            head = left[0];
            left[1].next = pivot;
            pivot.next = right[0];
            tail = right[1];
        }

        return new Node[] { head, tail };
    }

    public Node[] quickSort_(Node node) {
        if (node == null)
            return new Node[2];

        Node nodes[] = segregate(node);
        Node[] left = quickSort_(nodes[0]);
        Node[] right = quickSort_(nodes[2]);

        return joinLists(left, nodes[1], right);
    }

    public Node quickSort(Node node) {
        return quickSort_(node)[0];
    }

    // QUICK SORT END --------------------------------

    

}