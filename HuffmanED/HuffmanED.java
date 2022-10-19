import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanED {
    private class Node implements Comparable<Node> {
        String data;
        int freq;
        Node left;
        Node right;

        Node(String data, int freq) {
            this.data = data;
            this.freq = freq;
        }

        Node(String data, int freq, Node left, Node right) {
            this(data, freq);
            this.left = left;
            this.right = right;
        }

        public int compareTo(Node o) {
            return this.freq - o.freq;
        }
    }

    HashMap<String, String> encode;
    HashMap<String, String> decode;

    HuffmanED(String feeder) {
        this.encode = new HashMap<>();
        this.decode = new HashMap<>();
        Node root = huffmanTree(feeder);
        huffmanTraversal(root, "");
    }

    private Node huffmanTree(String feeder) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char ch : feeder.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (char ch : map.keySet()) {
            Node node = new Node(ch + "", map.get(ch));
            pq.offer(node);
        }

        while (pq.size() > 1) {
            Node one = pq.poll();
            Node two = pq.poll();

            pq.offer(new Node("", one.freq + two.freq, one, two));
        }

        Node root = pq.poll();

        return root;
    }

    private void huffmanTraversal(Node root, String binaryCode) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            this.encode.put(root.data, binaryCode);
            this.decode.put(binaryCode, root.data);
        }

        huffmanTraversal(root.left, binaryCode + "0");
        huffmanTraversal(root.right, binaryCode + "1");
    }

    public String encodeString(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int j = i + 1; j <= str.length(); j++) {
            String s = str.substring(i, j);
            if (this.encode.containsKey(s)) {
                sb.append(this.encode.get(s));
                i = j;
            }
        }
        return sb.toString();
    }

    public String decodeString(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (int j = i + 1; j <= str.length(); j++) {
            String s = str.substring(i, j);
            if (this.decode.containsKey(s)) {
                sb.append(this.decode.get(s));
                i = j;
            }
        }
        return sb.toString();
    }
}
