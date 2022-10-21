public class BST {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
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

    public static Node createTree(int arr[], int i, int j) {
        if (i > j) {
            return null;
        }
        int mid = (i + j) / 2;
        Node root = new Node(arr[mid]);
        root.left = createTree(arr, i, mid - 1);
        root.right = createTree(arr, mid + 1, j);
        return root;
    }

    public static void display(Node root) {
        if (root == null)
            return;
        System.out.print(root.left == null ? "." : root.left.data);
        System.out.print("->" + root.data + "<-");
        System.out.println(root.right == null ? "." : root.right.data);
        display(root.left);
        display(root.right);
    }

    public static boolean find_01(Node root, int data) {
        if (root == null)
            return false;

        if (root.data > data) {
            return find_01(root.left, data);
        } else if (root.data < data) {
            return find_01(root.right, data);
        } else {
            return true;
        }
    }

    public static boolean find_02(Node root, int data) {
        while (root != null) {
            if (root.data < data) {
                root = root.right;
            } else if (root.data > data) {
                root = root.left;
            } else {
                return true;
            }
        }
        return false;
    }

    public static Node addNode(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (root.data > data) {
            root.left = addNode(root.left, data);
        } else if (root.data < data) {
            root.right = addNode(root.right, data);
        } else {
            return root;
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                TreeNode node = root.left;
                while (node.right != null) {
                    node = node.right;
                }
                root = deleteNode(root, node.val);
                root.val = node.val;
                return root;
            }
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    public static void main(String[] args) {
        int arr[] = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        Node root = createTree(arr, 0, arr.length - 1);

        // display(root);

        // System.out.println(find_01(root, 45));

        System.out.println(find_02(root, 45));

        // root = addNode(root, 45);

    }
}
