import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class GT {

    public static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        Node() {

        }

        Node(int data) {
            this.data = data;
        }
    }

    public static Node createTree(int[] arr) {
        Stack<Node> st = new Stack<>();

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] != -1) {
                Node node = new Node(arr[i]);
                st.push(node);
            } else {
                Node node = st.pop();
                st.peek().children.add(node);
            }
        }
        return st.peek();
    }

    public static void display(Node root) {
        System.out.print(root.data + " ->");
        for (Node child : root.children) {
            System.out.print(" " + child.data);
        }
        System.out.println();
        for (Node child : root.children) {
            display(child);
        }
    }

    public static int size(Node root) {
        int sz = 0;

        for (Node child : root.children) {
            sz += size(child);
        }

        return sz + 1;
    }

    public static int height(Node root) {
        int h = -1;

        for (Node child : root.children) {
            h = Math.max(h, height(child));
        }

        return h + 1;
    }

    public static int maximum(Node root) {
        int max = root.data;
        for (Node child : root.children) {
            max = Math.max(max, maximum(child));
        }
        return max;
    }

    public static boolean find(Node root, int ele) {
        boolean res = false;
        if (root.data == ele) {
            return true;
        }
        for (Node child : root.children) {
            res = res || find(child, ele);
        }

        return res;
    }

    public static void eulerTraversal(Node root) {
        System.out.println("Node Pre " + root.data);
        for (Node child : root.children) {
            System.out.println("Edge Pre " + root.data + "--" + child.data);
            eulerTraversal(child);
            System.out.println("Edge Post " + root.data + "--" + child.data);
        }
        System.out.println("Node Post " + root.data);
    }

    public static void levelOrderTraversal(Node root) {
        Queue<Node> que = new LinkedList<>();

        que.add(root);

        while (que.size() != 0) {
            Node node = que.remove();
            System.out.print(node.data + " ");
            for (Node child : node.children)
                que.add(child);
        }
    }

    public static void zigzaglevel(Node root) {
        if (root == null)
            return;

        Stack<Node> ms = new Stack<>();
        int level = 0;
        ms.push(root);
        while (ms.size() != 0) {
            Stack<Node> cs = new Stack<>();
            while (ms.size() != 0) {
                Node rem = ms.pop();

                System.out.print(rem.data + " ");

                if (level % 2 == 0) {
                    for (int i = 0; i < rem.children.size(); i++) {
                        cs.push(rem.children.get(i));
                    }
                } else {
                    for (int i = rem.children.size() - 1; i >= 0; i--) {
                        cs.push(rem.children.get(i));
                    }
                }
            }
            System.out.println();
            level++;
            ms = cs;
        }
    }

    public static void mirrorTree(Node root) {
        Collections.reverse(root.children);
        for (Node child : root.children) {
            mirrorTree(child);
        }
    }

    public static void removeLeaves(Node root) {
        ArrayList<Node> children2 = new ArrayList<>();

        for (Node child : root.children) {
            if (child.children.size() != 0) {
                children2.add(child);
            }
        }

        root.children = children2;

        for (Node child : root.children) {
            removeLeaves(child);
        }
    }

    public static Node getTail(Node root) {
        if (root.children.size() == 0) {
            return root;
        } else {
            return getTail(root.children.get(0));
        }
    }

    public static void lineariaze(Node root) {
        for (Node child : root.children) {
            lineariaze(child);
        }

        while (root.children.size() > 1) {
            Node last = root.children.remove(root.children.size() - 1);
            Node second_last = root.children.get(root.children.size() - 1); // last toh udd chuka
            Node second_last_tail = getTail(second_last);
            second_last_tail.children.add(last);
        }
    }

    // Lineriaze Efficient Approach Only

    public static Node lineariaze_Efficient(Node root) {
        if (root.children.size() == 0)
            return root;
        Node myTail = lineariaze_Efficient(root.children.get(root.children.size() - 1));
        while (root.children.size() > 1) {
            Node last = root.children.remove(root.children.size() - 1);
            Node sLast = root.children.get(root.children.size() - 1);
            Node sLastTail = lineariaze_Efficient(sLast);
            sLastTail.children.add(last);
        }
        return myTail;
    }

    public static ArrayList<Node> nodeToRootPath(Node root, int data) {
        if (root.data == data) {
            ArrayList<Node> basePath = new ArrayList<>();
            basePath.add(root);
            return basePath;
        }
        for (Node child : root.children) {
            ArrayList<Node> smallPath = nodeToRootPath(child, data);
            if (smallPath.size() > 0) {
                smallPath.add(root);
                return smallPath;
            }
        }
        return new ArrayList<>();
    }

    public static int LCA(Node root, int data1, int data2) {
        ArrayList<Node> path1 = nodeToRootPath(root, data1);
        ArrayList<Node> path2 = nodeToRootPath(root, data2);

        if (path1.size() == 0 || path2.size() == 0)
            return -1;

        int i = path1.size() - 1;
        int j = path2.size() - 1;
        while (i >= 0 && j >= 0) {
            if (path1.get(i).data != path2.get(j).data) {
                break;
            }
            i--;
            j--;
        }

        if (i < 0 && j < 0) {
            return path1.get(0).data;
        } else if (i < 0) {
            return path2.get(j).data;
        } else if (j < 0) {
            return path1.get(i).data;
        } else {
            return path1.get(i + 1).data;
        }
    }

    public static Node getNode(Node root, int data) {
        if (root.data == data) {
            return root;
        }

        for (Node child : root.children) {
            Node childAns = getNode(child, data);
            if (childAns != null)
                return childAns;
        }
        return null;
    }

    public static void KDown__(Node root, int k) {
        if (k == 0) {
            System.out.print(root.data + " ");
            return;
        }
        for (Node child : root.children) {
            KDown__(child, k - 1);
        }
    }

    public static void KDown_for_KAway(Node root, int k, Node pnode) {
        if (k < 0) {
            return; // IMP as k - kd in kAway2
        }
        if (k == 0) {
            System.out.print(root.data + " ");
            return;
        }
        for (Node child : root.children) {
            if (child != pnode) {
                KDown_for_KAway(child, k - 1, pnode);
            }
        }
    }

    public static void K_away1(Node root, int data, int k) {
        ArrayList<Node> path = nodeToRootPath(root, data);
        if (path.size() == 0)
            return;

        int i = 0;
        while (k >= 0 && i < path.size()) {
            KDown_for_KAway(path.get(i), k, i == 0 ? null : path.get(i - 1));
            i++;
            k--;
        }
    }

    // returns kd;
    public static int kAway2(Node root, int data, int k) {
        if (root.data == data) {
            KDown_for_KAway(root, k, null);
            return 1;
        }
        for (int i = 0; i < root.children.size(); i++) {
            int kd = kAway2(root.children.get(i), data, k);
            if (kd > 0) {
                KDown_for_KAway(root, k - kd, root.children.get(i));
                return kd + 1;
            }
        }
        return -1;
    }

    public static int maxTime = 0;

    public static void burnTree_forTime(Node root, Node burnt, int time) {
        if (time > maxTime)
            maxTime = time;
        for (Node child : root.children) {
            if (child != burnt) {
                burnTree_forTime(child, burnt, time + 1);
            }
        }
    }

    public static int timeToBurnTree(Node root, int fireNode) {
        if (root.data == fireNode) {
            burnTree_forTime(root, null, 0);
            return 1;
        }
        for (Node child : root.children) {
            int fd = timeToBurnTree(child, fireNode);
            if (fd != -1) {
                burnTree_forTime(root, child, fd);
                return fd + 1;
            }
        }
        return -1;
    }

    public static ArrayList<ArrayList<Integer>> burningOrder;

    public static void burnTree_forOrder(Node root, Node burnt, int time) {
        if (time >= burningOrder.size()) {
            burningOrder.add(new ArrayList<>());
        }
        burningOrder.get(time).add(root.data);
        for (Node child : root.children) {
            if (child != burnt) {
                burnTree_forOrder(child, burnt, time + 1);
            }
        }
    }

    public static int burningTreeOrder(Node root, int fireNode) {
        if (root.data == fireNode) {
            burnTree_forOrder(root, null, 0);
            return 1;
        }
        for (Node child : root.children) {
            int fd = burningTreeOrder(child, fireNode);
            if (fd != -1) {
                burnTree_forOrder(root, child, fd);
                return fd + 1;
            }
        }
        return -1;
    }

    public static void rootToEveryLeafSum(Node root, int sum) {
        if (root.children.size() == 0) {
            System.out.println(sum + root.data);
            return;
        }
        for (Node child : root.children) {
            rootToEveryLeafSum(child, sum + root.data);
        }
    }

    public static int Max_rootLeavesSum(Node root) {
        if (root.children.size() == 0) {
            return root.data;
        }

        int sum = Integer.MIN_VALUE;
        for (Node child : root.children) {
            sum = Math.max(sum, Max_rootLeavesSum(child));
        }

        return sum + root.data;
    }

    public static int rootToNodePathSum(Node root, int data) {
        if (root.data == data) {
            return data;
        }

        for (Node child : root.children) {
            int ans = rootToNodePathSum(child, data);
            if (ans != 0) {
                return ans + root.data;
            }
        }
        return 0;
    }

    public static int nodeToNodeSum(Node root, int d1, int d2) {
        ArrayList<Node> p1 = nodeToRootPath(root, d1);
        ArrayList<Node> p2 = nodeToRootPath(root, d2);

        if (p1.size() == 0 || p2.size() == 0) {
            return 0;
        }

        int sum = 0;

        int i = 0;
        int j = 0;
        while (i < p1.size() && j < p2.size()) {
            if (p1.get(i).data == p2.get(i).data) {
                break;
            }
            sum += p1.get(i).data + p2.get(j).data;
            i++;
            j++;
        }
        if (i < p1.size() && j < p2.size()) {
            sum += p1.get(i).data;
        }

        while (i < p1.size() - 1) {
            sum += p1.get(i).data;
            i++;
        }

        while (j < p2.size() - 1) {
            sum += p2.get(j).data;
            j++;
        }

        return sum;
    }

    public static int NodeToNodeDistance(Node root, int data1, int data2) {
        ArrayList<Node> path1 = nodeToRootPath(root, data1);
        ArrayList<Node> path2 = nodeToRootPath(root, data2);

        if (path1.size() == 0 || path2.size() == 0) {
            return -1;
        }

        int i = path1.size() - 1;
        int j = path2.size() - 1;

        while (i >= 0 && j >= 0 && path1.get(i).data == path2.get(j).data) {
            i--;
            j--;
        }
        i++;
        j++;

        return i + j;
    }

    public static int maxDia = 0;

    public static int calcDia_returnDeepestHei(Node root) {
        if (root.children.size() == 0) {
            return 0;
        }
        if (root.children.size() == 1) { // IMP *************************************
            int ch = calcDia_returnDeepestHei(root.children.get(0)) + 1;
            maxDia = Math.max(maxDia, ch);
            return ch;
        }

        int maxH = 0;
        int sMaxH = 0;
        for (Node child : root.children) {
            int ch = calcDia_returnDeepestHei(child) + 1;
            if (ch >= maxH) {
                sMaxH = maxH;
                maxH = ch;
            } else if (ch > sMaxH) {
                sMaxH = ch;
            }
        }

        maxDia = Math.max(maxDia, maxH + sMaxH);

        return maxH;
    }

    public static int maxSum = 0;

    public static int maxLeafToLeafSum(Node root) {
        if (root.children.size() == 0) {
            return root.data;
        }
        if (root.children.size() == 1) {
            return maxLeafToLeafSum(root.children.get(0)) + root.data;
        }

        int mSum = 0;
        int smSum = 0;
        for (Node child : root.children) {
            int cs = maxLeafToLeafSum(child);
            if (cs >= mSum) {
                smSum = mSum;
                mSum = cs;
            } else if (cs > smSum) {
                cs = smSum;
            }
        }

        maxSum = Math.max(maxSum, mSum + root.data + smSum);

        return mSum;
    }

    public static int maxNtoNsum = Integer.MIN_VALUE;

    public static int maxNodeToNodeSum(Node root) {
        if (root.children.size() == 0) {
            maxNtoNsum = Math.max(maxNtoNsum, root.data);
            return root.data;
        }
        if (root.children.size() == 1) {
            int cs = maxNodeToNodeSum(root.children.get(0));
            maxNtoNsum = Math.max(maxNtoNsum, cs + root.data);
            return Math.max(cs, Math.max(cs + root.data, root.data));
        }

        int ms = 0;
        int sms = 0;
        for (Node child : root.children) {
            int cs = maxNodeToNodeSum(child);
            if (cs >= ms) {
                sms = ms;
                ms = cs;
            } else if (cs > sms) {
                sms = cs;
            }
        }

        maxNtoNsum = Math.max(ms + root.data + sms, Math.max(ms + root.data, root.data + sms));

        return Math.max(root.data, Math.max(ms + root.data, root.data + sms));
    }

    public static boolean areTreeSimilar(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size()) {
            return false;
        }
        for (int i = 0; i < n1.children.size(); i++) {
            if (!areTreeSimilar(n1.children.get(i), n2.children.get(i)))
                return false;
        }

        return true;
    }

    public static boolean areMirrorInShape(Node r1, Node r2) {
        if (r1.children.size() != r2.children.size())
            return false;
        int i = 0;
        int j = r1.children.size() - 1;
        while (i < r1.children.size()) {
            if (!areMirrorInShape(r1.children.get(i), r2.children.get(j)))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public static boolean areMirror(Node n1, Node n2) {
        if (n1.data != n2.data || n1.children.size() != n2.children.size()) {
            return false;
        }

        for (int i = 0; i < n1.children.size(); i++) {
            int j = n2.children.size() - 1 - i;
            if (!areMirror(n1.children.get(i), n2.children.get(j)))
                return false;
        }
        return true;
    }

    public static boolean isTreeMirror(Node r1, Node r2) {
        if (r1.children.size() != r2.children.size() || r1.data != r2.data) {
            return false;
        }
        int i = 0;
        int j = r1.children.size() - 1;
        while (i <= j) {
            if (!isTreeMirror(r1.children.get(i), r2.children.get(j)))
                return false;
        }
        return true;
    }

    static int size;
    static int height;
    static int max;
    static int min;

    public static void multisolver(Node root, int depth) {
        size++;
        height = Math.max(height, depth);
        max = Math.max(max, root.data);
        min = Math.min(min, root.data);
        for (Node child : root.children) {
            multisolver(child, depth + 1);
        }
    }

    static Node pred = null;
    static Node succ = null;
    static int state = 0;

    public static void findPredSucc(Node root, int data, Node prev) {

        if (state == 1) {
            succ = root;
            state = 2;
            return;
        }
        if (root.data == data) {
            pred = prev;
            state = 1;
        }

        prev = root;
        for (Node child : root.children) {
            findPredSucc(child, data, prev);
            prev = child;
            if (state == 2)
                return;
        }
    }

    static int ceil = Integer.MAX_VALUE;
    static int floor = Integer.MIN_VALUE;

    public static void findCeilFloor(Node root, int data) {
        if (root.data < data && root.data > floor) {
            floor = root.data;
        }
        if (root.data > data && root.data < ceil) {
            ceil = root.data;
        }
        for (Node child : root.children) {
            findCeilFloor(child, data);
        }
    }

    public static class Tpair {
        Node node;
        int state;

        Tpair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void IterativeTraversal(Node root) {
        Stack<Tpair> st = new Stack<>();

        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();

        st.add(new Tpair(root, -1));

        while (st.size() != 0) {
            Tpair rp = st.peek();
            if (rp.state == -1) {
                pre.add(rp.node.data);
                rp.state++;
            } else if (rp.state == rp.node.children.size()) {
                post.add(rp.node.data);
                st.pop();
            } else {
                st.push(new Tpair(rp.node.children.get(rp.state), -1));
                rp.state++;
            }
        }

        System.out.print("Pre : ");
        for (int ele : pre)
            System.out.print(ele + " ");

        System.out.println();

        System.out.print("Post : ");
        for (int ele : post)
            System.out.print(ele + " ");
    }

    public static void main(String[] args) {
        int arr[] = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, -1, -1 };

        Node root = createTree(arr);
        // display(root);
        // System.out.println(size(root));
        // System.out.println(height(root));
        // System.out.println(maximum(root));

        // System.out.println(" 80 : " + find(root,80));
        // System.out.println(" 85 : " + find(root,85));

        // eulerTraversal(root);

        // levelOrderTraversal(root);

        // zigzaglevel(root);

        // mirrorTree(root);

        // lineariaze(root);

        // lineariaze_Efficient(root);

        // for (Node node : nodeToRootPath(root, 110)) {
        // System.out.print(node.data + " ");
        // }

        // System.out.println(LCA(root, 110, 70));

        // int data = 30;
        // int k = 2;
        // Node dataNode = getNode(root, 30);
        // if (dataNode != null) {
        // KDown__(dataNode, k);
        // }

        // K_away1(root, 30, 2);

        // kAway2(root, 30, 2);

        // timeToBurnTree(root, 110);
        // System.out.println(maxTime);

        // burningOrder = new ArrayList<>();
        // burningTreeOrder(root, 30);
        // for(ArrayList<Integer> order : burningOrder) {
        // System.out.println(order);
        // }

        // rootToEveryLeafSum(root, 0);

        // Max_rootLeavesSum(root);

        // System.out.println(rootToNodePathSum(root, 80));

        // System.out.println(nodeToNodeSum(root, 50, 110));

        // calcDia_returnDeepestHei(root);
        // System.out.println(maxDia);

        // maxLeafToLeafSum(root);
        // System.out.println(maxSum);

        // findPredSucc(root, 80, null);
        // System.out.println("Pred : " + pred.data + ", Succ : " + succ.data);

        // findCeilFloor(root, 40);
        // System.out.println("Floor : " + floor + ", Ceil : " + ceil);

        int k = 4;
        int factor = Integer.MAX_VALUE;
        while (k-- > 0) {
            findCeilFloor(root, factor);
            factor = floor;
            floor = Integer.MIN_VALUE;
        }
        System.out.println(factor);

        // display(root);

    }

}
