// import java.util.ArrayList;

public class voidtype {
    public static void main(String[] args) {

        // subseq("abcd", "");

        // keypad("1048", "");

        // removeHi("hhihihihhiihihiihi", "");
        // removeHiExtraHi("hhhhiiihiiihi", "");
        // removeHileaveHit("hhihithihiiihitihhi", "");
        // removeHiLeaveHitExtraHi("hhhiihiihitih", "");

        // compression("abbcccddddaeeeeef", 1, "");

        // permutations("abc", "");
        // permutations_prevDupli("aba", "");

        // wordBreak("ilikeicecreamandmango", "");

        // int arr[] = { 2, 3, 5, 7 };
        // int tar = 10;
        // String ans = "";
        // int idx = 0;
        // boolean vis[] = new boolean[arr.length];
        // infinitePermutation(arr, tar, ans);
        // singlePermutation(arr, tar, vis, ans);
        // infiniteCombinations(arr, tar, idx, ans);
        // singleCombination(arr, tar, idx, ans);
        // infinitePermutation_SubseqMethod(arr, tar, idx, ans);
        // singlePermutation_SubseqMethod(arr, tar, vis, idx, ans);
        // infiniteCombinations_SubseqMEthod(arr, tar, idx, ans);
        // singleCombination_SubseqMethod(arr, tar, idx, ans);

        // int arr1[] = { 10, 20, 30, 40, 50, 60, 70 };
        // combiDivideDiffSetsBasisOfSum(arr1, 0, 0, 0, "", "");

        int sr = 0;
        int sc = 0;
        int er = 3;
        int ec = 3;
        String ans = "";

        // mazepathHVD(sr, sc, er, ec, ans);
        // mazepathMultiMoves(sr, sc, er, ec, ans);

        boolean board[][] = new boolean[4][4];
        floodfill(board, sr, sc, er, ec, ans);
    }

    public static void subseq(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        subseq(str.substring(1), ans);
        subseq(str.substring(1), ans + str.charAt(0));
    }

    public static String[] keys = { "_", "+-/", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz", "*()%",
            "#@$" };

    public static void keypad(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        int num = Integer.parseInt(str.charAt(0) + "");
        for (int i = 0; i < keys[num].length(); i++)
            keypad(str.substring(1), ans + keys[num].charAt(i));

        if (str.length() > 1) {
            num = Integer.parseInt(str.substring(0, 2));
            if (num > 9 && num < keys.length) {
                for (int i = 0; i < keys[num].length(); i++)
                    keypad(str.substring(2), ans + keys[num].charAt(i));
            }
        }
    }

    public static void removeHi(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        if (str.length() > 1 && str.charAt(0) == 'h' && str.charAt(1) == 'i')
            removeHi(str.substring(2), ans);
        else
            removeHi(str.substring(1), ans + str.charAt(0));
    }

    public static void removeHiExtraHi(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        if (str.length() > 1 && str.substring(0, 2).equals("hi"))
            removeHiExtraHi(str.substring(2), ans);
        else {
            if (str.charAt(0) == 'i' && ans.length() > 0 && ans.charAt(ans.length() - 1) == 'h')
                removeHiExtraHi(str.substring(1), ans.substring(0, ans.length() - 1));
            else
                removeHiExtraHi(str.substring(1), ans + str.charAt(0));
        }
    }

    public static void removeHileaveHit(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        if (str.length() > 1 && str.substring(0, 2).equals("hi")) {
            if (str.length() > 2 && str.charAt(2) == 't')
                removeHileaveHit(str.substring(3), ans + "hit");
            else
                removeHileaveHit(str.substring(2), ans);
        } else {
            removeHileaveHit(str.substring(1), ans + str.charAt(0));
        }
    }

    public static void removeHiLeaveHitExtraHi(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        if (str.length() > 1 && str.charAt(0) == 'h' && str.charAt(1) == 'i') {
            if (str.length() > 2 && str.charAt(2) == 't')
                removeHiLeaveHitExtraHi(str.substring(3), ans + "hit");
            else
                removeHiLeaveHitExtraHi(str.substring(2), ans);
        } else {
            if (str.charAt(0) == 'i' && ans.length() > 0 && ans.charAt(ans.length() - 1) == 'h')
                removeHiLeaveHitExtraHi(str.substring(1), ans.substring(0, ans.length() - 1));
            else
                removeHiLeaveHitExtraHi(str.substring(1), ans + str.charAt(0));
        }
    }

    public static void compression(String str, int count, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        if (str.length() > 1 && str.charAt(0) == str.charAt(1)) { // when another character is same.3
            compression(str.substring(1), count + 1, ans);
        } else { // when its last the appearance of a specific string AND ALSO when on last char
                 // in string or whole string was just one char.
            if (count == 1)
                compression(str.substring(1), 1, ans + str.charAt(0));
            else
                compression(str.substring(1), 1, ans + str.charAt(0) + count + "");
        }
    }

    public static void permutations(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            permutations(str.substring(0, i) + str.substring(i + 1, str.length()), ans + str.charAt(i));
        }
    }

    public static void permutations_prevDupli(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }
        int done = 0;
        System.out.println(ans);
        for (int i = 0; i < str.length(); i++) {
            int mask = 1 << (str.charAt(i) - 'a');
            if ((done & mask) == 0) {
                done |= mask;
                permutations_prevDupli(str.substring(0, i) + str.substring(i + 1, str.length()), ans + str.charAt(i));
            }
        }
    }

    public static String dictionary[] = { "i", "like", "sam", "sung", "samsung", "your", "mobile", "name", "mango",
            "ice", "man", "and", "go", "cream", "icecream", "is", "what" };

    public static boolean findInDict(String str) {
        for (int i = 0; i < dictionary.length; i++)
            if (dictionary[i].equals(str))
                return true;
        return false;
    }

    public static void wordBreak(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            if (findInDict(str.substring(0, i + 1))) {
                wordBreak(str.substring(i + 1), ans + str.substring(0, i + 1) + " ");
            }
        }
    }

    public static void infinitePermutation(int arr[], int tar, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                infinitePermutation(arr, tar - arr[i], ans + arr[i]);
            }
        }
    }

    public static void singlePermutation(int arr[], int tar, boolean vis[], String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!vis[i] && tar - arr[i] >= 0) {
                vis[i] = true;
                singlePermutation(arr, tar - arr[i], vis, ans + arr[i]);
                vis[i] = false;
            }
        }
    }

    public static void infiniteCombinations(int arr[], int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                infiniteCombinations(arr, tar - arr[i], i, ans + arr[i]);
        }
    }

    public static void singleCombination(int arr[], int tar, int idx, String ans) {
        if (tar == 0) {
            System.out.println(ans);
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                singleCombination(arr, tar - arr[i], i + 1, ans + arr[i]);
        }
    }

    // =========================================================================

    public static int infinitePermutation_SubseqMethod(int arr[], int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }
        int count = 0;
        if (tar - arr[idx] >= 0)
            count += infinitePermutation_SubseqMethod(arr, tar - arr[idx], 0, ans + arr[idx]);

        count += infinitePermutation_SubseqMethod(arr, tar, idx + 1, ans);

        return count;
    }

    public static void singlePermutation_SubseqMethod(int arr[], int tar, boolean vis[], int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return;
            }
            return;
        }

        if (!vis[idx] && tar - arr[idx] >= 0) {
            vis[idx] = true;
            singlePermutation_SubseqMethod(arr, tar - arr[idx], vis, 0, ans + arr[idx]);
            vis[idx] = false;
        }
        singlePermutation_SubseqMethod(arr, tar, vis, idx + 1, ans);
    }

    public static void infiniteCombinations_SubseqMEthod(int arr[], int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return;
            }
            return;
        }

        if (tar - arr[idx] >= 0)
            infiniteCombinations_SubseqMEthod(arr, tar - arr[idx], idx, ans + arr[idx]);

        infiniteCombinations_SubseqMEthod(arr, tar, idx + 1, ans);
    }

    public static void singleCombination_SubseqMethod(int arr[], int tar, int idx, String ans) {
        if (tar == 0 || idx == arr.length) {
            if (tar == 0) {
                System.out.println(ans);
                return;
            }
            return;
        }

        if (tar - arr[idx] >= 0)
            singleCombination_SubseqMethod(arr, tar - arr[idx], idx + 1, ans + arr[idx]);

        singleCombination_SubseqMethod(arr, tar, idx + 1, ans);
    }

    public static void combiDivideDiffSetsBasisOfSum(int arr[], int idx, int set1, int set2, String ans1, String ans2) {
        if (arr.length == idx) {
            if (set1 == set2)
                System.out.println(ans1 + " = " + ans2);
            return;
        }

        combiDivideDiffSetsBasisOfSum(arr, idx + 1, set1 + arr[idx], set2, ans1 + arr[idx] + " ", ans2);
        combiDivideDiffSetsBasisOfSum(arr, idx + 1, set1, set2 + arr[idx], ans1, ans2 + arr[idx] + " ");
    }

    public static void mazepathHVD(int sr, int sc, int er, int ec, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return;
        }

        if (sc + 1 <= ec)
            mazepathHVD(sr, sc + 1, er, ec, ans + "H");

        if (sr + 1 <= er)
            mazepathHVD(sr + 1, sc, er, ec, ans + "V");

        if (sr + 1 <= er && sc + 1 <= ec)
            mazepathHVD(sr + 1, sc + 1, er, ec, ans + "D");
    }

    public static void mazepathMultiMoves(int sr, int sc, int er, int ec, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return;
        }

        for (int i = 1; i + sc <= ec; i++)
            mazepathMultiMoves(sr, sc + i, er, ec, ans + "H" + i);
        for (int i = 1; i + sr <= er; i++)
            mazepathMultiMoves(sr + i, sc, er, ec, ans + "V" + i);
        for (int i = 1; i + sc <= ec && sr + i <= er; i++)
            mazepathMultiMoves(sr + i, sc + i, er, ec, ans + "D" + i);
    }

    public static int d[][] = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
    public static String dir[] = { "U", "1", "R", "2", "D", "3", "L", "4" };

    public static boolean isSafe(int x, int y, boolean board[][]) {
        if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && !board[x][y])
            return true;
        return false;
    }

    public static void floodfill(boolean board[][], int sr, int sc, int er, int ec, String ans) {
        if (sr == er && sc == ec) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < d.length; i++) {
            int x = sr + d[i][0];
            int y = sc + d[i][1];
            if (isSafe(x, y, board)) {
                board[x][y] = true;
                floodfill(board, x, y, er, ec, ans + dir[i]);
                board[x][y] = false;
            }
        }
    }

}