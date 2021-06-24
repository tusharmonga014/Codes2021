import java.util.Arrays;
import java.util.LinkedList;
import java.util.ArrayList;

public class foundation {
    public static void main(String[] args) {

        // FIBONACCI-----------------------------------------
        int n = 7;
        int[] dp = new int[n + 1];
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
        // int n = 4;
        // int m = 4;
        // int sr = 0;
        // int sc = 0;
        // int er = n - 1;
        // int ec = m - 1;
        // int dp[][] = new int[n][m];

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

        // int cost[][] = { { 0, 1, 4, 2 }, { 4, 3, 6, 5 }, { 1, 2, 4, 1 }, { 1, 2, 4, 1
        // } };
        // int ans = minCostMazeTraversal(sr, sc, n - 1, m - 1, cost, dp);

        // BOARD PATH--------------V.V.V.V.V.V.IMPORTANT---------------
        // int sp = 0;
        // int ep = 10;
        // int K = 6;
        // int ans = boardPath(sp, ep, K);

        // System.out.println(ans);

        /**
         * ***********************************************************
         * 
         * TAGRET SUM SUBSET
         * 
         * ***********************************************************
         */

        // System.out.println(binaryStringNonConsecZero(2, false));
        // int dp0[] = new int[n + 1];
        // int dp1[] = new int[n + 1];
        // System.out.println(binaryStringNonConsecZero(n, false));
        // System.out.println(binaryStringNonConsecZero_memo(n, false, dp0, dp1));
        // System.out.println(binaryStringNonConsecZero_dp(n));

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

    // ==========================================================================================================
    // -------BOARD PATH--------- V.V.V.V.V.V.V IMPORTANT

    public static int boardPath(int sp, int ep, int K) {

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = ep; i >= sp; i--) {

            if (i >= ep - 1) {

                list.addFirst(1);

            } else {

                if (list.size() <= K)
                    list.addFirst(2 * list.getFirst());
                else
                    list.addFirst((2 * list.getFirst()) - list.removeLast());

            }

        }

        return list.getFirst();
    }

    /**
     * **********************************************
     * 
     * 
     * 
     * -------------TARGET SUMSUBSET---------------
     * 
     * 
     * 
     * **********************************************
     */

    public static int binaryStringNonConsecZero(int n, boolean prevZero) {

        if (n == 1) {
            if (prevZero)
                return 1;
            else
                return 2; // 1+1 zero and one
        }

        int count = 0;

        // Adding 0 to the string and passing it.
        if (!prevZero)
            count += binaryStringNonConsecZero(n - 1, true);

        // Adding 1 to the string.
        count += binaryStringNonConsecZero(n - 1, false);

        return count;
    }

    public static int binaryStringNonConsecZero_memo(int n, boolean prevZero, int dp0[], int dp1[]) {

        if (n == 1) {

            if (prevZero) {
                return dp1[n] = 1;
            } else {
                return dp0[n] = 2;
            }

        }

        if (prevZero && dp1[n] != 0) {
            return dp1[n];
        }
        if (!prevZero && dp1[n] != 0) {
            return dp0[n];
        }

        // 0 can only be added if previous was not 0
        if (!prevZero)
            dp0[n] += binaryStringNonConsecZero(n - 1, true);

        // Adding 1 to the string.
        dp1[n] += binaryStringNonConsecZero(n - 1, false);

        return dp0[n] + dp1[n];

    }

    public static int binaryStringNonConsecZero_dp(int n) {

        int countStrEnd0 = 1;
        int countStrEnd1 = 1;

        for (int i = 2; i <= n; i++) {

            int NEWcountStrEnd0 = countStrEnd1;
            int NEWcountStrEnd1 = (countStrEnd0 + countStrEnd1);

            countStrEnd0 = NEWcountStrEnd0;
            countStrEnd1 = NEWcountStrEnd1;
        }

        return countStrEnd0 + countStrEnd1;
    }

    public static void arrangeBuildings(int n) {

        long csp = 1;
        long cbd = 1;

        for (int i = 2; i <= n; i++) {

            long ncbd = csp;
            long ncsp = csp + cbd;

            csp = ncsp;
            cbd = ncbd;
        }

        long ans = cbd + csp;
        ans = ans * ans;

        System.out.println(ans);

    }

    public static int countEncodings(String str) {

        int n = str.length();
        int dp[] = new int[n];

        dp[0] = 1;

        for (int i = 1; i < n; i++) {

            if (str.charAt(i - 1) == '0' && str.charAt(i) == '0') {

                dp[i] = 0;

            } else if (str.charAt(i - 1) == '0' && str.charAt(i) != '0') {

                dp[i] = dp[i - 1];

            } else if (str.charAt(i - 1) != '0' && str.charAt(i) == '0') {

                if (str.charAt(i - 1) == '1' || str.charAt(i) == '2')
                    dp[i] = i == 1 ? 1 : dp[i - 2];

            } else {

                if (Integer.parseInt(str.substring(i - 1, i + 1)) <= 26)
                    dp[i] = dp[i - 1] + ((i == 1) ? 1 : dp[i - 2]);
                else
                    dp[i] = dp[i - 1];

            }

        }

        return dp[n - 1];

    }

    // for pattern a+b+c+
    public static int countabc(String str, int idx) {

        int a = 0;
        int ab = 0;
        int abc = 0;

        for (int i = 0; i < str.length(); i++) {

            char ch = str.charAt(i);

            if (ch == 'a') {
                a = (2 * a) + 1;
            } else if (ch == 'b') {
                ab = (2 * ab) + a;
            } else if (ch == 'c') {
                abc = (2 * abc) + ab;
            }

        }

        return abc;

    }

    public static int maxSumNonAdjacentElementsInArray(int arr[]) {

        int length = arr.length;

        int inc = arr[0];
        int exc = 0;

        for (int loopIterator = 1; loopIterator < length; loopIterator++) {

            int newInc = exc + arr[loopIterator];
            int newExc = Math.max(inc, exc);

            inc = newInc;
            exc = newExc;

        }

        int ans = Math.max(inc, exc);
        return ans;

    }

    public static int paintHouse(int[][] cost) {

        int red = cost[0][0];
        int blue = cost[0][1];
        int green = cost[0][2];

        for (int i = 1; i < cost.length; i++) {

            int currHouseRed = Math.min(blue, green) + cost[i][0];
            int currHouseBlue = Math.min(red, green) + cost[i][1];
            int currHouseGreen = Math.min(red, blue) + cost[i][2];

            red = currHouseRed;
            blue = currHouseBlue;
            green = currHouseGreen;

        }

        int fAns = Math.min(Math.min(red, blue), green);

        return fAns;
    }

    public static int paintHouse_NColors(int[][] arr) {

        int n = arr.length;
        int k = arr[0].length;

        int costs[] = new int[k];

        for (int i = 0; i < k; i++)
            costs[i] = arr[0][i];

        for (int i = 1; i < n; i++) {

            int min1 = Integer.MAX_VALUE;
            int min2 = Integer.MAX_VALUE;
            for (int fmin = 0; fmin < k; fmin++) {
                if (costs[fmin] <= min1) {
                    min2 = min1;
                    min1 = costs[fmin];
                } else if (costs[fmin] <= min2) {
                    min2 = costs[fmin];
                }
            }

            int newCosts[] = new int[k];
            for (int j = 0; j < k; j++) {

                int myValue = arr[i][j];
                int prevValue;

                if (min1 == costs[j])
                    prevValue = min2;
                else
                    prevValue = min1;

                newCosts[j] = myValue + prevValue;
            }

            costs = newCosts;
            System.out.println(costs);
        }

        int min_ = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            min_ = Math.min(costs[i], min_);
        }

        return min_;
    }

    public static int paintHouse_NColors_(int[][] arr) {

        int n = arr.length;
        int k = arr[0].length;

        int costs[] = new int[k];

        for (int i = 0; i < k; i++)
            costs[i] = arr[0][i];

        for (int i = 1; i < n; i++) {

            int min1 = 0;
            int min2 = 1;
            for (int fmin = 1; fmin < k; fmin++) {
                if (costs[fmin] <= costs[min1]) {
                    min2 = min1;
                    min1 = fmin;
                } else if (costs[fmin] < costs[min2]) {
                    min2 = fmin;
                }
            }

            int newCosts[] = new int[k];
            for (int j = 0; j < k; j++) {

                int myValue = arr[i][j];
                int prevValue;

                if (min1 == j)
                    prevValue = costs[min2];
                else
                    prevValue = costs[min1];

                newCosts[j] = myValue + prevValue;
            }

            costs = newCosts;
        }

        int min_ = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            min_ = Math.min(costs[i], min_);
        }

        return min_;
    }

    public static int paintFence(int n, int k) {

        // V IMP for n=1 last2same and last2diff are NOT DEF.. only we know total=k.
        if (n == 1) {
            return k;
        }

        // defining for n=2.
        int last2same = k;
        int last2diff = k * (k - 1);
        int total = last2same + last2diff;

        for (int i = 3; i <= n; i++) {

            int newLast2same = last2diff;
            int newLast2diff = total * (k - 1);

            last2same = newLast2same;
            last2diff = newLast2diff;

            total = last2same + last2diff;
        }

        return total;
    }

    public static int Tiling_2_1(int n) {

        int b2_1 = 1;
        int b2_2 = 2;

        for (int i = 3; i <= n; i++) {
            int curr = b2_1 + b2_2;
            b2_1 = b2_2;
            b2_2 = curr;
        }

        return b2_2;
    }

    public static int Tiling_m_1(int n, int m) {

        int dp[] = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            if (i < m) {

                dp[i] = 1;

            } else if (i == m) {

                dp[i] = 2;

            } else {

                dp[i] = dp[i - 1] + dp[i - m];

            }
        }

        return dp[n];

    }

    public static int friendsPairing(int n) {

        int dp[] = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {

            dp[i] = dp[i - 1] + (i - 1) * dp[i - 2];

        }

        return dp[n];

    }

    public static long partitionKSubset(int n, int k) {

        if (n == 0 || k == 0 || n < k) {
            return 0;
        }

        long dp[][] = new long[k + 1][n + 1];

        for (int set = 1; set <= k; set++) {

            for (int elem = set; elem <= n; elem++) {

                if (set == elem)
                    dp[set][elem] = 1;
                else {

                    long restEleminKteams = (set * dp[set][elem - 1]);
                    long restEleminK_1_teams = dp[set - 1][elem - 1];

                    dp[set][elem] = restEleminKteams + restEleminK_1_teams;

                }
            }
        }

        return dp[k][n];

    }

    public static int BSS_one_transaction_allowed(int arr[]) {

        int n = arr.length;
        int lsf = Integer.MAX_VALUE;
        int overAllProfit = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {

            if (arr[i] < lsf)
                lsf = arr[i];

            int profitIfSoldToday = arr[i] - lsf;
            if (profitIfSoldToday > overAllProfit)
                overAllProfit = profitIfSoldToday;

        }

        return overAllProfit;
    }

    public static int BSS_infinte_transaction(int[] prices) {

        int n = prices.length;

        int bd = 0;
        int sd = 0;
        int profit = 0;

        for (int i = 1; i < n; i++) {

            if (prices[i] < prices[i - 1]) {

                // DIP
                profit += prices[sd] - prices[bd];

                bd = i;
                sd = i;

            } else {

                // RISE OR SAME
                sd++;
            }

        }

        profit += prices[sd] - prices[bd];

        return profit;

    }

    // public static int BSS_infiniteTransaction_fee_RECURSION(int []arr,boolean
    // haveShare, int money, int i, int fee){

    // if(i==arr.length){
    // return money;
    // }

    // int profit = 0;

    // //no need to buy if already having a share or if its the last day
    // if(!haveShare && i!=arr.length-1){
    // profit = Math.max(profit,
    // BSS_infiniteTransaction_fee_RECURSION(arr,true,money - arr[i],i+1, fee));
    // }

    // //can only sell the share if you have one
    // if(haveShare){
    // profit = Math.max(profit,
    // BSS_infiniteTransaction_fee_RECURSION(arr,false,money + arr[i] - fee,i+1,
    // fee));
    // }

    // // neither buy today nor sell
    // profit = Math.max(profit,
    // BSS_infiniteTransaction_fee_RECURSION(arr,haveShare,money,i+1, fee));

    // return profit;
    // }

    public static int BSS_infiniteTransaction_fee(int arr[], int fee) {

        int bsp = -arr[0];
        int ssp = 0;

        for (int i = 1; i < arr.length; i++) {
            if (ssp - arr[i] > bsp)
                bsp = ssp - arr[i];
            else if (bsp + arr[i] - fee > ssp)
                ssp = bsp + arr[i] - fee;
        }

        return ssp;
    }

            

}