import java.util.Arrays;
import java.util.ArrayList;

public class foundation {
    public static void main(String[] args) {

        // FIBONACCI-----------------------------------------
        // int n = 6;
        // int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1);
        // // System.out.println(fiboDpMemoized(n, dp));
        // System.out.println(fiboDpTabulation(n, dp));
        // display1D(dp);

        // CLIMB STAIRS----------------------------------------
        // int n = 5;
        // int[] dp = new int[n + 1];
        // // int ans = climbStairsDpMemo(n, dp);
        // int ans = climbStairsDpTab(n, dp);
        // System.out.println(ans);
        // display1D(dp);

        // Climb stairs var jump--------------------------------
        // int n = 10;
        // int arr[] = { 3, 3, 0, 2, 1, 2, 4, 2, 0, 0 };
        // int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1); // will also work with 0
        // int ans = climbStairsVarJump(arr, 0, dp);
        // System.out.println(ans);

        // Min Move Var Jump ---- MOST IMP-----------------------
        // int n = 10;
        // int arr[] = { 3, 3, 0, 2, 1, 2, 4, 2, 0, 0 };
        // int[] dp = new int[n + 1];
        // Arrays.fill(dp, -1); // V.V.IMP
        // dp[n] = 0;
        // // int ans = minMoveVarJump(arr, 0);
        // int ans = minMoveVarJumpDP(arr, dp);
        // if (ans != -1) {
        // System.out.println(ans);
        // } else {
        // System.out.println("null");
        // }

        // MAZEPATH------------------------------------------------
        int n = 4;
        int m = 4;
        int sr = 0;
        int sc = 0;
        int er = n - 1;
        int ec = m - 1;
        int dp[][] = new int[n][m];

        // int ans = mazepath_memo(sr, sc, er, ec, dp);
        // int ans = mazepathDP(sr, sc, er, ec, dp);

        // int ans = mazepath_infiDP(sr, sc, er, ec, dp);

        // ArrayList<String> tdp[][] = new ArrayList[n][m];
        // for (int i = 0; i < n; i++)
        // for (int j = 0; j < m; j++)
        // tdp[i][j] = new ArrayList<>();
        // ArrayList<String> tans = maze(sr, sc, er, ec, tdp);
        // for (String s : tans)
        // System.out.println(s);

        int cost[][] = { { 0, 1, 4, 2 }, { 4, 3, 6, 5 }, { 1, 2, 4, 1 }, { 1, 2, 4, 1 } };
        int ans = minCostMazeTraversal(sr, sc, n - 1, m - 1, cost, dp);

        System.out.println(ans);
    }

    // ===========================================================================================================

    public static void display1D(int[] arr) {
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
        System.out.println();
    }

    public static void display2D(int[][] arr) {
        for (int[] smallArr : arr) {
            for (int ele : smallArr) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // =============================================================================================================

    // -------------FIBONACCI--------------------

    public static int fiboDpMemoized(int n, int[] dp) {
        if (dp[n] != -1)
            return dp[n];
        if (n <= 1)
            return dp[n] = n;
        int ans = fiboDpMemoized(n - 1, dp) + fiboDpMemoized(n - 2, dp);
        return dp[n] = ans;
    }

    public static int fiboDpTabulation(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }
            dp[n] = dp[n - 1] + dp[n - 2];
        }
        return dp[N];
    }

    // ==============================================================================================================

    // --------------------CLIMB STAIRS---------------------

    public static int climbStairsDpMemo(int n, int[] dp) {

        if (n == 0) {
            return 1;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        int count = 0;
        for (int i = 1; i <= 3; i++) {
            if (n - i >= 0)
                count += climbStairsDpMemo(n - i, dp);
        }
        return dp[n] = count;
    }

    public static int climbStairsDpTab(int N, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n == 0)
                dp[n] = 1;

            for (int i = 1; i <= 3; i++) {
                if (n - i >= 0)
                    dp[n] += dp[n - i];
            }
        }

        return dp[N];
    }

    // ========================================================================================================

    // -------------Climb Stairs with variable jumps--------------------

    public static int climbStairsVarJump(int[] arr, int i, int[] dp) {
        if (i == arr.length) {
            return dp[i] = 1;
        }

        if (dp[i] != -1)
            return dp[i];

        int count = 0;

        System.out.println("Hi " + i);
        for (int step = 1; step <= arr[i]; step++) {
            if (i + step <= arr.length)
                count += climbStairsVarJump(arr, i + step, dp);
        }

        return dp[i] = count;
    }

    public static int climbStairsVarJumpDP(int[] arr, int i, int[] dp) {

        if (i == arr.length) {
            return dp[i] = 1;
        }

        if (dp[i] != -1)
            return dp[i]; // will also work with 0

        int count = 0;

        for (int step = 1; step <= arr[i]; step++) {
            if (i + step <= arr.length)
                count += climbStairsVarJump(arr, i + step, dp);
        }

        return dp[i] = count;
    }

    // =================================================================================================

    public static int minMoveVarJump(int arr[], int i) {
        if (i == arr.length) {
            return 0;
        }

        int min_ = (int) 1e9;

        for (int step = 1; step <= arr[i]; step++) {
            min_ = Math.min(min_, minMoveVarJump(arr, i + step));
        }

        if (min_ != (int) 1e9) {
            min_++;
        }

        return min_;
    }

    public static int minMoveVarJumpDP(int arr[], int dp[]) {

        int n = arr.length;

        for (int i = n - 1; i >= 0; i--) {
            int min_ = Integer.MAX_VALUE; // imp
            for (int j = 1; j <= arr[i] && i + j <= n; j++) {
                if (dp[i + j] != -1) // imp
                    min_ = Math.min(min_, dp[i + j]);
            }
            if (min_ != Integer.MAX_VALUE) // imp
                dp[i] = min_ + 1;
        }

        return dp[0];

    }

    // ====================================================================================================

    // -------MAZEPATH------------------------

    public static int mazepath_memo(int sr, int sc, int er, int ec, int dp[][]) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        if (sr + 1 <= er)
            dp[sr][sc] += mazepath_memo(sr + 1, sc, er, ec, dp);

        if (sc + 1 <= ec)
            dp[sr][sc] += mazepath_memo(sr, sc + 1, er, ec, dp);

        if (sr + 1 <= er && sc + 1 <= ec)
            dp[sr][sc] += mazepath_memo(sr + 1, sc + 1, er, ec, dp);

        return dp[sr][sc];
    }

    public static int mazepathDP(int sr, int sc, int er, int ec, int dp[][]) {

        dp[er][ec] = 1; // FORGOT

        for (int i = er; i >= sr; i--) {
            for (int j = ec; j >= sc; j--) {

                if (i + 1 <= er)
                    dp[i][j] += dp[i + 1][j];

                if (j + 1 <= ec)
                    dp[i][j] += dp[i][j + 1];

                if (i + 1 <= er && j + 1 <= ec)
                    dp[i][j] += dp[i + 1][j + 1];
            }
        }

        return dp[sr][sc];
    }

    public static int mazepath_infiDP(int sr, int sc, int er, int ec, int dp[][]) {

        dp[er][ec] = 1;

        for (int i = er; i >= sr; i--) {
            for (int j = ec; j >= sc; j--) {

                for (int jump = 1; jump + i <= er; jump++) {
                    dp[i][j] += dp[i + jump][j];
                }

                for (int jump = 1; jump + j <= ec; jump++) {
                    dp[i][j] += dp[i][j + jump];
                }

                for (int jump = 1; jump + i <= er && jump + j <= ec; jump++) {
                    dp[i][j] += dp[i + jump][j + jump];
                }

            }
        }

        return dp[sr][sc];
    }

    public static ArrayList<String> maze(int sr, int sc, int er, int ec, ArrayList<String> dp[][]) {

        if (sr == er && sc == ec) {
            ArrayList<String> base = new ArrayList<>();
            base.add("");
            return base;
        }

        if (dp[sr][sc].size() != 0) {
            return dp[sr][sc];
        }

        ArrayList<String> smallAns;
        ArrayList<String> myAns = new ArrayList<>();

        System.out.println("[" + sr + ", " + sc + "]");

        if (sr + 1 <= er) {
            smallAns = maze(sr + 1, sc, er, ec, dp);
            for (String s : smallAns) {
                myAns.add(s + "V");
            }
        }

        if (sc + 1 <= er) {
            smallAns = maze(sr, sc + 1, er, ec, dp);
            for (String s : smallAns) {
                myAns.add(s + "H");
            }
        }

        if (sr + 1 <= er && sc + 1 <= ec) {
            smallAns = maze(sr + 1, sc + 1, er, ec, dp);
            for (String s : smallAns) {
                myAns.add(s + "D");
            }
        }

        return dp[sr][sc] = myAns;
    }

    public static int minCostMazeTraversal(int sr, int sc, int er, int ec, int cost[][], int dp[][]) {

        // base case handled in loop (essential to handle there)

        for (int i = er; i >= sr; i--) {
            for (int j = ec; j >= sc; j--) {

                int minCost = Integer.MAX_VALUE;

                if (i + 1 <= er)
                    minCost = Math.min(minCost, dp[i + 1][j]);

                if (j + 1 <= ec)
                    minCost = Math.min(minCost, dp[i][j + 1]);

                if (i + 1 <= er && j + 1 <= ec)
                    minCost = Math.min(minCost, dp[i + 1][j + 1]);

                if (i == er && j == ec) {
                    dp[i][j] = cost[i][j];
                } else {
                    dp[i][j] = minCost + cost[i][j];
                }

            }
        }

        return dp[sr][sc];
    }

}