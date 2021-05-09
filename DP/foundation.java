import java.util.Arrays;

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
        int n = 10;
        int arr[] = { 3, 3, 0, 2, 1, 2, 4, 2, 0, 0 };
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1); // V.V.IMP
        dp[n] = 0;
        // int ans = minMoveVarJump(arr, 0);
        int ans = minMoveVarJumpDP(arr, dp);
        if (ans != -1) {
            System.out.println(ans);
        } else {
            System.out.println("null");
        }
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

}