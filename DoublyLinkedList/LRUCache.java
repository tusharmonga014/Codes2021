import java.util.*;

public class LRUCache {

    private class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    int maximumSize;
    int currSize;

    HashMap<Integer, Node> map;

    public LRUCache(int capacity) {
        this.maximumSize = capacity;
        this.currSize = 0;
        head = tail = null;
        map = new HashMap<>();
    }

    private void addFirst(Node node) {
        if (this.currSize == 0) {
            this.head = this.tail = node;
        } else {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
        this.currSize++;
    }

    private void removeTailNode() {
        if (this.currSize == 1) {
            this.head = this.tail = null;
        } else {
            Node prev = this.tail.prev;
            this.tail.prev = null;
            prev.next = null;
            this.tail = prev;
        }
        this.currSize--;
    }

    private void removeHeadNode() {
        if (this.currSize == 0) {
            this.head = this.tail = null;
        } else {
            Node next = this.head.next;
            this.head.next = null;
            next.prev = null;
            this.head = next;
        }
        this.currSize--;
    }

    private void removeNode(Node node) {
        if (node == this.tail) {
            removeTailNode();
        } else if (node == this.head) {
            removeHeadNode();
        } else {
            Node next = node.next;
            Node prev = node.prev;
            next.prev = prev;
            prev.next = next;
            this.currSize--;
        }
    }

    private void makeMostRecent(Node node) {
        removeNode(node);
        addFirst(node);
    }

    public int get(int key) {
        if (!this.map.containsKey(key)) {
            return -1;
        } else {
            Node node = map.get(key);
            makeMostRecent(node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        if (this.map.containsKey(key)) {
            Node node = map.get(key);
            map.remove(key);
            removeNode(node);
        }

        if (this.currSize == this.maximumSize) {
            Node rem = this.tail;
            map.remove(rem.key);
            removeTailNode();
        }

        Node node = new Node(key, value);
        map.put(key, node);
        addFirst(node);
    }
}
