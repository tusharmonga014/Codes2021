import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BT {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static int idx = 0;

    public static Node createTree(Integer[] arr) {
        if (arr[idx] == null) {
            idx++;
            return null;
        }

        Node node = new Node(arr[idx]);
        idx++;

        node.left = createTree(arr);
        node.right = createTree(arr);
        return node;
    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.left == null ? "." : node.left.data);
        System.out.print(" -> " + node.data + " <- ");
        System.out.println(node.right == null ? "." : node.right.data);

        display(node.left);
        display(node.right);
    }

    static int maxDia = 0;

    public static int calcDia_returnMaxH(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 0;
        } else if (root.left == null) {
            int rh = calcDia_returnMaxH(root.right) + 1;
            maxDia = Math.max(maxDia, rh);
            return rh;
        } else if (root.right == null) {
            int lh = calcDia_returnMaxH(root.left) + 1;
            maxDia = Math.max(maxDia, lh);
            return lh;
        } else {
            int lh = calcDia_returnMaxH(root.left) + 1;
            int rh = calcDia_returnMaxH(root.right) + 1;
            maxDia = Math.max(maxDia, lh + rh);
            return Math.max(lh, rh);
        }
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        calcDia_returnMaxH(root);
        return maxDia;
    }

    public static int max = Integer.MIN_VALUE;

    public static int calcMax(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            max = Math.max(max, root.val);
            return root.val;
        }

        if (root.left == null) {
            int cs = calcMax(root.right);
            max = Math.max(max, Math.max(root.val, cs + root.val));
            return Math.max(root.val, root.val + cs);
        }

        if (root.right == null) {
            int cs = calcMax(root.left);
            max = Math.max(max, Math.max(root.val, cs + root.val));
            return Math.max(root.val, root.val + cs);
        }

        int ls = calcMax(root.left);
        int rs = calcMax(root.right);
        max = Math.max(max, Math.max(ls + root.val, Math.max(rs + root.val, Math.max(ls + root.val + rs, root.val))));
        return Math.max(root.val, Math.max(root.val + ls, root.val + rs));
    }

    public static int maxPathSum(TreeNode root) {
        calcMax(root);
        return max;
    }

    public static boolean isTreeMirror(Node r1, Node r2) {
        if (r1 == null && r2 == null)
            return true;
        if (r1 == null || r2 == null)
            return false;

        if (r1.data != r2.data || !isTreeMirror(r1.left, r2.right) || !isTreeMirror(r1.right, r2.left))
            return false;

        return true;
    }

    static class Tpair {
        TreeNode node;
        int state;

        Tpair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void preorderTraversal(TreeNode root) {
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        if (root == null)
            return;
        Stack<Tpair> st = new Stack<>();
        st.push(new Tpair(root, -1));
        while (st.size() != 0) {

            Tpair top = st.peek();

            if (top.state == -1) {
                pre.add(top.node.val);
                if (top.node.left != null) {
                    st.push(new Tpair(top.node.left, -1));
                }
                top.state++;
            } else if (top.state == 0) {
                if (top.node.right != null) {
                    st.push(new Tpair(top.node.right, -1));
                }
                in.add(top.node.val);
                top.state++;
            } else {
                post.add(top.node.val);
                st.pop();
            }
        }

        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);
    }

    public static void transformToLeftCloned(Node root) {
        if (root == null)
            return;

        Node myLeft = root.left;
        root.left = new Node(root.data);
        root.left.left = myLeft;

        transformToLeftCloned(root.left.left);
        transformToLeftCloned(root.right);
    }

    public static void transformBack(Node root) {
        if (root == null) {
            return;
        }

        Node rootL = root.left;
        root.left = rootL.left;
        rootL.left = null;

        transformBack(root.left);
        transformBack(root.right);
    }

    static int tilt = 0;

    public static int updateTiltReturnSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ls = updateTiltReturnSum(root.left);
        int rs = updateTiltReturnSum(root.right);

        int myTilt = Math.abs(ls - rs);
        tilt += myTilt;

        return ls + root.val + rs;
    }

    public static int findTilt(TreeNode root) {
        updateTiltReturnSum(root);
        return tilt;
    }

    static class Pair {
        boolean isBST;
        long min;
        long max;

        Pair(boolean isBST, long min, long max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public static Pair isValidBST_(TreeNode root) {
        if (root == null) {
            return new Pair(true, Long.MAX_VALUE, Long.MIN_VALUE);
        }

        Pair left = isValidBST_(root.left);
        if (!(left.isBST && root.val > left.max))
            return new Pair(false, Long.MIN_VALUE, Long.MAX_VALUE);

        Pair right = isValidBST_(root.right);
        if (!(right.isBST && root.val < right.min))
            return new Pair(false, Long.MIN_VALUE, Long.MAX_VALUE);

        long min = Math.min(root.val, Math.min(left.min, right.min));
        long max = Math.max(root.val, Math.max(left.max, right.max));
        Pair myPair = new Pair(true, min, max);

        return myPair;
    }

    public static boolean isValidBST(TreeNode root) {
        return isValidBST_(root).isBST;
    }

    static boolean isBal = true;

    public static int isBalanced_(Node root) {
        if (root == null)
            return -1;

        int lh = isBalanced_(root.left);
        if (!isBal)
            return -2;

        int rh = isBalanced_(root.right);
        if (!isBal)
            return -2;

        if (Math.abs(lh - rh) > 1)
            isBal = false;

        return Math.max(lh, rh) + 1;
    }

    public static boolean isBalanced(Node root) {
        isBalanced_(root);
        return isBal;
    }

    static class Pair2 {
        boolean isBst;
        int min;
        int max;
        int count;

        Pair2(boolean isBst, int min, int max, int count) {
            this.isBst = isBst;
            this.min = min;
            this.max = max;
            this.count = count;
        }
    }

    static Pair2 largestBst_(Node root) {
        if (root == null) {
            return new Pair2(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        Pair2 lp = largestBst_(root.left);
        Pair2 rp = largestBst_(root.right);

        if (lp.isBst && root.data > lp.max && rp.isBst && root.data < rp.min) {
            return new Pair2(true, Math.min(root.data, lp.min), Math.max(root.data, rp.max), lp.count + 1 + rp.count);
        } else {
            return new Pair2(false, Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(lp.count, rp.count));
        }
    }

    static int largestBst(Node root) {
        Pair2 p = largestBst_(root);
        return p.count;
    }

    public static void main(String[] args) {
        Integer arr[] = { 10, 20, 30, 40, null, null, 50, null, null, 60, null, 70, null, null, 80, 90,
                100, 120, null, null, 130, null, null,
                110, null, null, 140, null, null };

        Node root = createTree(arr);

        // display(root);

        // transformToLeftCloned(root);

        // transformBack(root);

        // isBalanced(root);
        // System.out.println(isBal);

        display(root);
    }
}
