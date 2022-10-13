import java.util.NoSuchElementException;

public class linkedlist<K> {

    private class Node {
        K data;
        Node next = null;

        Node(K data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int nodeCount = 0;

    public int size() {
        return this.nodeCount;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node curr = this.head;
        while (curr != null) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != null) {
                sb.append(",");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    private Node getNodeAt(int idx) {
        if (idx < 0 || idx > this.size())
            throw new NullPointerException();

        Node cur = this.head;
        while (idx-- > 0)
            cur = cur.next;

        return cur;
    }

    private void addFirstNode(Node node) {
        if (this.isEmpty()) {
            this.tail = node;
            this.head = node;
        } else {
            node.next = this.head;
            this.head = node;
        }

        this.nodeCount++;
    }

    public void addFirst(K data) {
        Node node = new Node(data);
        this.addFirstNode(node);
    }

    private void addLastNode(Node node) {
        if (this.isEmpty()) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.next = node;
            this.tail = node;
        }

        this.nodeCount++;
    }

    public void addLast(K data) {
        Node node = new Node(data);
        this.addLastNode(node);
    }

    private void addAtNode(Node node, int idx) {
        if (idx == 0)
            this.addFirstNode(node);
        else if (idx == this.size())
            this.addLastNode(node);
        else {
            Node prev = this.getNodeAt(idx - 1);
            Node forw = prev.next;
            prev.next = node;
            node.next = forw;
            this.nodeCount++;
        }
    }

    public void addAt(K data, int idx) {
        if (idx < 0 || idx > this.size())
            throw new NullPointerException();

        Node node = new Node(data);
        this.addAtNode(node, idx);
    }

    public K getFirst() {
        if (this.isEmpty())
            throw new NullPointerException();
        return this.head.data;
    }

    public K getLast() {
        if (this.isEmpty())
            throw new NullPointerException();
        return this.tail.data;
    }

    public K getAt(int idx) {
        return this.getNodeAt(idx).data;
    }

    private Node removeFirstNode() {
        Node rn = this.head;

        if (this.size() == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.next;
        }

        rn.next = null;
        this.nodeCount--;
        return rn;
    }

    public K removeFirst() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        return this.removeFirstNode().data;
    }

    private Node removeLastNode() {
        Node rn = this.tail;
        if (this.size() == 1) {
            this.head = null;
            this.tail = null;
        } else {
            Node secondLast = this.getNodeAt(this.size() - 2);
            secondLast.next = null;
            this.tail = secondLast;
        }

        this.nodeCount--;
        return rn;
    }

    public K removeLast() {
        if (this.isEmpty())
            throw new NoSuchElementException();
        return removeLastNode().data;
    }

    private Node removeNodeAt(int idx) {
        if (idx == 0) {
            return this.removeFirstNode();
        } else if (idx == this.size() - 1) {
            return this.removeLastNode();
        } else {
            Node prev = this.getNodeAt(idx - 1);
            Node rn = prev.next;
            prev.next = rn.next;
            rn.next = null;
            this.nodeCount--;
            return rn;
        }
    }

    public K removeAt(int idx) {
        if (idx < 0 || idx > this.size())
            throw new NoSuchElementException();
        return removeNodeAt(idx).data;
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

    public void reverseList() {
        Node newHead = reverseList(this.head);
        this.tail = this.head;
        this.head = newHead;
    }

    public void reverseDataITR__ON2() {
        int si = 0;
        int ei = this.size() - 1;

        while (si < ei) {
            Node node1 = this.getNodeAt(si);
            Node node2 = this.getNodeAt(ei);
            K temp = node1.data;
            node1.data = node2.data;
            node2.data = temp;
            si++;
            ei--;
        }
    }
}