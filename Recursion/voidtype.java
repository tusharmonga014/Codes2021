import java.util.ArrayList;

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

        // int sr = 0;
        // int sc = 0;
        // int er = 3;
        // int ec = 3;
        // String ans = "";

        // mazepathHVD(sr, sc, er, ec, ans);
        // mazepathMultiMoves(sr, sc, er, ec, ans);

        // boolean board[][] = new boolean[4][4];
        // floodfill(board, sr, sc, er, ec, ans);

        // paintBucket(); // also check if color != new color (or using boolean vis[][])
        // else infinite calls.

        // KnightTOUR();

        // boolean box[] = new boolean[5];
        // int qpsf = 0;
        // int tnq = 4;
        // String ans = "";
        // int n = 4;
        // int m = 4;
        // int idx = 0;
        // boolean board[][] = new boolean[4][4];
        // System.out.println(queen1D_Permutation(box, qpsf, tnq, ans));
        // queen1D_Combination(n, tnq, idx, ans);
        // System.out.println(queen1D_Permutation_Subseq(box, tnq, qpsf, idx, ans));
        // queen1D_Combination_Subseq(n, tnq, idx, ans);
        // queen2D_Permutation(board, tnq, qpsf, ans);
        // queen2D_Combination(n, m, tnq, idx, ans);
        // nQueens_combination(board, tnq, idx, ans);
        // nQueens_permutation(board, tnq, qpsf, ans);
        // int r = 0;
        // nQueensOptimized_01(board, tnq, qpsf, r, ans);
        // nQueensOptimized_02(n, m, r, 0, 0, 0, tnq, ans);
        // nQueensOptimizedPermutation_02(n, m, 0, 0, 0, 0, tnq, qpsf, ans);

        // Sudoku();

        // AbbreviationUsingBacktracking("pep", "", 0, 0);
        // maxScore2
        System.out.println(josephusProblem(8, 3));
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

    public static int color;

    public static void paintBucket() {
        int[][] screen = { { 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0 }, { 1, 0, 0, 1, 1, 0, 1, 1 },
                { 1, 2, 2, 2, 2, 0, 1, 0 }, { 1, 1, 1, 2, 2, 0, 1, 0 }, { 1, 1, 1, 2, 2, 2, 2, 0 },
                { 1, 1, 1, 1, 1, 2, 1, 1 }, { 1, 1, 1, 1, 1, 2, 2, 1 } };

        color = 2;
        int newColor = 3;
        if (color != newColor) // ** V.IMPORTANT **
            paintbucket(screen, 4, 4, 3);

        for (int arr[] : screen) {
            for (int ele : arr) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }

    public static int paintD[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void paintbucket(int[][] image, int sr, int sc, int newColor) {
        image[sr][sc] = newColor;
        for (int i = 0; i < 4; i++) {
            if (sr + d[i][0] >= 0 && sc + d[i][1] >= 0 && sr + d[i][0] < image.length && sc + d[i][1] < image[0].length
                    && image[sr + d[i][0]][sc + d[i][1]] == color) {
                paintbucket(image, sr + d[i][0], sc + d[i][1], newColor);
            }
        }
        return;
    }

    public static void dispBoard(int board[][]) {
        for (int row[] : board) {
            for (int ele : row)
                System.out.print(ele + " ");
            System.out.println();
        }
        System.out.println();
    }

    public static void KnightTOUR() {
        int board[][] = new int[6][6];
        int ksf = 1;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 1;
                knightTour(board, ksf, i, j);
                board[i][j] = 0;
            }
        }
    }

    public static int dKnight[][] = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 },
            { -2, -1 } };

    public static boolean isSafeForKnight(int board[][], int x, int y) {
        if (x >= 0 && y >= 0 && x < board.length && y < board[0].length && board[x][y] == 0)
            return true;
        return false;
    }

    public static void knightTour(int board[][], int ksf, int x, int y) {
        if (ksf == (board.length * board[0].length)) {
            dispBoard(board);
            return;
        }

        for (int i = 0; i < dKnight.length; i++) {
            int nextX = x + dKnight[i][0];
            int nextY = y + dKnight[i][1];
            if (isSafeForKnight(board, nextX, nextY)) {
                board[nextX][nextY] = ksf + 1; // as it stores move no.
                knightTour(board, ksf + 1, nextX, nextY);
                board[nextX][nextY] = 0;
            }
        }
    }

    public static int queen1D_Permutation(boolean[] box, int qpsf, int tnq, String ans) {
        if (tnq == qpsf) {
            if (tnq == qpsf)
                System.out.println(ans);
            /*
             * // see last output line of this function to see its current pattern. if want
             * to print in like manner B1Q1 B2Q2 B3Q3 . . . B3Q3 B4Q4 B5Q5 then take int
             * arr[] and not boolean array, because queen no is inserted in ans while
             * calling, but for peinting in this manner we will have to put that in array,
             * and that wll work as a check also.
             */
            return 1;
        }

        int count = 0;

        for (int i = 0; i < box.length; i++) {
            if (!box[i]) {
                box[i] = true;
                count += queen1D_Permutation(box, qpsf + 1, tnq, ans + "B" + i + "q" + qpsf + " ");
                box[i] = false;
            }
        }

        return count;
    }

    public static void queen1D_Combination(int n, int tnq, int qpsf, int idx, String ans) {
        if (tnq == qpsf) {
            System.out.println(ans);
            return;
        }

        for (int i = idx; i < n; i++) {
            queen1D_Combination(n, tnq, qpsf + 1, i + 1, ans + "B" + i + " ");
        }
    }

    public static int queen1D_Permutation_Subseq(boolean box[], int tnq, int qpsf, int idx, String ans) {
        if (tnq == qpsf || idx == box.length) {
            if (tnq == qpsf) {
                System.out.println(ans);
                return 1;
            }
            return 0;
        }

        int count = 0;
        if (!box[idx]) {
            box[idx] = true;
            count += queen1D_Permutation_Subseq(box, tnq, qpsf + 1, 0, ans + "B" + idx + "q" + qpsf);
            box[idx] = false;
        }
        count += queen1D_Permutation_Subseq(box, tnq, qpsf, idx + 1, ans);
        return count;
    }

    public static void queen1D_Combination_Subseq(int n, int tnq, int idx, String ans) {
        if (tnq == 0 || idx == n) {
            if (tnq == 0)
                System.out.println(ans);
            return;
        }

        queen1D_Combination_Subseq(n, tnq - 1, idx + 1, ans + "B" + idx);
        queen1D_Combination_Subseq(n, tnq - 1, idx + 1, ans);
    }

    public static void queen2D_Permutation(boolean board[][], int tnq, int qpsf, String ans) {
        if (tnq == qpsf) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < board.length * board[0].length; i++) {

            int x = i / board[0].length;
            int y = i % board[0].length;
            if (!board[x][y]) {
                board[x][y] = true;
                queen2D_Permutation(board, tnq, qpsf + 1, ans + "B(" + x + "," + y + ")q" + qpsf + " ");
                board[x][y] = false;
            }
        }
    }

    public static void queen2D_Combination(int n, int m, int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return;
        }

        for (int i = idx; i < n * m; i++) {
            int x = i / m;
            int y = i % m;
            queen2D_Combination(n, m, tnq - 1, i + 1, ans + "B(" + x + "," + y + ")  ");
        }
    }

    public static int dQueen[][] = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
            { 1, -1 } };

    // also for permutation So Checking in All 8 directions
    public static boolean isSafeToPlace(boolean board[][], int i, int j) {
        int n = board.length;
        int m = board[0].length;

        for (int d = 0; d < dQueen.length; d++) {
            for (int r = 1; r < Math.max(n, m); r++) {
                int x = i + (r * dQueen[d][0]);
                int y = j + (r * dQueen[d][1]);
                if (x < 0 || y < 0 || y == m || x == n)
                    break;
                if (board[x][y])
                    return false;
            }
        }
        return true;
    }

    public static void nQueens_combination(boolean board[][], int tnq, int idx, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return;
        }

        for (int i = idx; i < board.length * board[0].length; i++) {
            int x = i / board[0].length;
            int y = i % board[0].length;
            if (isSafeToPlace(board, x, y)) { // if that cell has already queen THIS CONDITION ONLY
                                              // REQUIRED IN PERMUTATION
                board[x][y] = true;
                nQueens_combination(board, tnq - 1, i + 1, ans + "B(" + x + "," + y + ")");
                board[x][y] = false;
            }
        }
    }

    public static void nQueens_permutation(boolean board[][], int tnq, int qpsf, String ans) {
        if (tnq == qpsf) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < board.length * board[0].length; i++) {
            int x = i / board[0].length;
            int y = i % board[0].length;
            if (isSafeToPlace(board, x, y) && !board[x][y]) { // if that cell has already queen THIS CONDITION ONLY
                // REQUIRED IN PERMUTATION
                board[x][y] = true;
                nQueens_permutation(board, tnq, qpsf + 1, ans + "B(" + x + "," + y + ")__Q" + qpsf + "   ");
                board[x][y] = false;
            }
        }
    }

    public static void nQueensCombination_Optimized_01(boolean board[][], int tnq, int r, String ans) {
        if (tnq == 0) {
            System.out.println(ans);
            return;
        }

        for (int c = 0; c < board[0].length; c++) {
            if (isSafeToPlace(board, r, c)) {
                board[r][c] = true;
                nQueensCombination_Optimized_01(board, tnq - 1, r + 1, ans + "B(" + r + "," + c + ") ");
                board[r][c] = false;
            }
        }
    }

    // Above Version not possible for permutations

    // most optimized
    public static void nQueensOptimized_02(int rowSize, int colSize, int r, int col, int diag, int adiag, int tnq,
            String ans) {
        if (tnq == 0 || r == rowSize) {
            if (tnq == 0)
                System.out.println(ans);
            return;
        }

        for (int c = 0; c < colSize; c++)
            if ((col & (1 << c)) == 0 && (diag & (1 << (r + c))) == 0
                    && (adiag & (1 << (Math.max(rowSize, colSize) - 1 + r - c))) == 0) {

                col ^= (1 << c);
                diag ^= (1 << (r + c));
                adiag ^= (1 << (Math.max(rowSize, colSize) - 1 + r - c));

                nQueensOptimized_02(rowSize, colSize, r + 1, col, diag, adiag, tnq - 1,
                        ans + "B(" + r + "," + c + ") ");

                col ^= (1 << c);
                diag ^= (1 << (r + c));
                adiag ^= (1 << (Math.max(rowSize, colSize) - 1 + r - c));

            }
    }

    public static void nQueensOptimizedPermutation_02(int rowSize, int colSize, int row, int col, int diag, int adiag,
            int tnq, int qpsf, String ans) {
        if (tnq == qpsf) {
            System.out.println(ans);
            return;
        }

        for (int i = 0; i < rowSize * colSize; i++) {
            int r = i / colSize;
            int c = i % colSize;
            if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r + c))) == 0
                    && (adiag & (1 << (Math.max(rowSize, colSize) - 1 + r - c))) == 0) {

                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r + c));
                adiag ^= (1 << (Math.max(rowSize, colSize) - 1 + r - c));

                nQueensOptimizedPermutation_02(rowSize, colSize, row, col, diag, adiag, tnq, qpsf + 1,
                        ans + "B(" + r + "," + c + ")_Q" + qpsf + "   ");

                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r + c));
                adiag ^= (1 << (Math.max(rowSize, colSize) - 1 + r - c));

            }
        }
    }

    // ==================================================================================================

    public static void Sudoku() {
        int board[][] = { { 0, 0, 6, 0, 0, 8, 0, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
                { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
                { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

        int row[] = new int[9];
        int col[] = new int[9];
        int mat[][] = new int[3][3];
        ArrayList<Integer> empty = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    empty.add((i * 9) + j);
                } else {
                    row[i] |= (1 << board[i][j]);
                    col[j] |= (1 << board[i][j]);
                    mat[i / 3][j / 3] |= (1 << board[i][j]);
                }
            }
        }

        sudokuSolver(board, row, col, mat, empty, 0);
    }

    public static void displayBoard(int board[][]) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean sudokuSolver(int board[][], int[] row, int[] col, int mat[][], ArrayList<Integer> empty,
            int k) {
        if (k == empty.size()) {
            displayBoard(board);
            return true;
        }

        int r = empty.get(k) / 9;
        int c = empty.get(k) % 9;

        boolean res = false;

        for (int i = 1; i <= 9; i++) {

            int mask = (1 << i);

            if ((row[r] & mask) == 0 && (col[c] & mask) == 0 && (mat[r / 3][c / 3] & mask) == 0) {

                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
                board[r][c] = i;
                res = res || sudokuSolver(board, row, col, mat, empty, k + 1);
                board[r][c] = 0;
                row[r] ^= mask;
                col[c] ^= mask;
                mat[r / 3][c / 3] ^= mask;
            }
        }

        return res;
    }

    // ===============================================================================

    // PEP site LEVEL UP
    // -------------------------------------------------------------

    // ==============================================================================

    public static void AbbreviationUsingBacktracking(String str, String asf, int count, int pos) {
        if (pos == str.length()) {
            if (count > 0)
                System.out.println(asf + count);
            else
                System.out.println(asf);

            return;
        }

        if (count > 0)
            AbbreviationUsingBacktracking(str, asf + count + str.charAt(pos), 0, pos + 1);
        else
            AbbreviationUsingBacktracking(str, asf + str.charAt(pos), 0, pos + 1);

        AbbreviationUsingBacktracking(str, asf, count + 1, pos + 1);

    }

    // use subseq method instead. => easy handing of sc and recans (also with -ve
    // scores).

    // input

    // 4
    // dog cat dad good
    // 9
    // a b c d d d g o o
    // 1 0 9 5 0 0 3 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0 0 0 0 0

    public static int maxScore(String[] words, int[] farr, int[] score, int idx) {

        if (idx == words.length)
            return 0; // if -ve scores return -infinity.

        int recAns = 0; // again -infinity if ive scores are also there.

        for (int id = idx; id < words.length; id++) {
            String word = words[id];
            int i;
            int sc = 0;
            for (i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (farr[ch - 'a'] > 0) {
                    farr[ch - 'a']--;
                    sc += score[ch - 'a'];
                } else
                    break;
            }

            if (i == word.length())
                recAns = Math.max(recAns, sc + maxScore(words, farr, score, id + 1));

            for (int j = 0; j < i; j++) {
                char ch = word.charAt(j);
                farr[ch - 'a']++;
            }

        }
        return recAns;
    }

    public static int maxScore_02(String words[], int farr[], int score[], int idx) {
        if (idx == words.length) {
            return (int) -1e9;
        }

        int sc_no = maxScore_02(words, farr, score, idx + 1);

        String word = words[idx];
        int sc_yes = 0, sc_word = 0;
        int i;
        for (i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (farr[ch - 'a'] > 0) {
                farr[ch - 'a']--;
                sc_word += score[ch - 'a'];
            } else
                break;
        }

        if (i == word.length())
            sc_yes = Math.max(sc_word, sc_word + maxScore_02(words, farr, score, idx + 1));

        for (int j = 0; j < i; j++) {
            char ch = word.charAt(j);
            farr[ch - 'a']++;
        }

        return Math.max(sc_yes, sc_no);
    }

    public static int josephusProblem(int n, int k) {
        if (n == 1)
            return 0;
        int rc_idx = josephusProblem(n - 1, k);
        return (rc_idx + k) % n;
    }

}