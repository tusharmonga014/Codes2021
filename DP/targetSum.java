import java.util.Arrays;
import java.util.Comparator;

public class targetSum {
    public static void main(String args[]) {

        int arr[] = { 2, 3, 5, 7 };

        int tar = 10;

        int dp[] = new int[tar + 1];
        // int dp2[][] = new int[arr.length + 1][tar + 1];

        // System.out.println(targetSumSubset(arr, tar));

        // System.out.println(infinitePermutation(arr, tar));
        Arrays.fill(dp, -1);
        // System.out.println(infinitePermutationDP(arr, tar, dp));
        // System.out.println(infinitePermutationTAB(arr, tar));

        // System.out.println(infiniteCombination(arr, tar, 0));
        // for (int i = 0; i < dp2.length; i++)
        // Arrays.fill(dp2[i], -1);
        // System.out.println(infiniteCombination2DP(arr, tar, 0, dp2));
        // System.out.println(infiniteCombination_2DTAB(arr, tar));

        // int weight[] = { 2, 5, 1, 3, 4 };
        // int value[] = { 15, 14, 10, 45, 30 };
        // int bagw = 7;
        // int dp2[][] = new int[weight.length + 1][bagw + 1];
        // for (int i = 0; i < dp2.length; i++)
        // Arrays.fill(dp2[i], Integer.MAX_VALUE);

        // System.out.println(zero_one_knapsack_memo(weight, value, bagw, 0, dp2));
        // System.out.println(zero_one_knapsack_Dp(weight, value, bagw));

        System.out.println(fractionalKnapSack());
    }

    public static boolean targetSumSubset(int[] arr, int tar) {

        boolean dp[][] = new boolean[arr.length + 1][tar + 1];

        for (int i = 0; i < arr.length + 1; i++) {
            for (int j = 0; j < tar + 1; j++) {

                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (j == 0) {
                    dp[i][j] = true;
                } else {
                    boolean notIncludingMe = dp[i - 1][j];
                    boolean includingMe = false;
                    if (j >= arr[i - 1]) {
                        includingMe = dp[i - 1][j - arr[i - 1]];
                    }
                    dp[i][j] = notIncludingMe || includingMe;
                }
            }
        }

        // for(int i=0;i<arr.length+1;i++){
        // for(int j=0;j<tar+1;j++){
        // System.out.print(dp[i][j] + " ");
        // }
        // System.out.println();
        // }

        return dp[arr.length][tar];
    }

    public static int infinitePermutation(int arr[], int tar) {
        if (tar == 0) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                count += infinitePermutation(arr, tar - arr[i]);
            }
        }
        return count;
    }

    public static int infinitePermutationDP(int arr[], int tar, int dp[]) {
        if (tar == 0) {
            return 1;
        }
        if (dp[tar] != -1) {
            return dp[tar];
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (tar - arr[i] >= 0) {
                count += infinitePermutationDP(arr, tar - arr[i], dp);
            }
        }
        return dp[tar] = count;
    }

    public static int infinitePermutationTAB(int arr[], int tar) {

        int dp[] = new int[tar + 1];

        for (int t = 0; t < tar + 1; t++) {
            if (t == 0) {
                dp[t] = 1;
                continue;
            }
            int count = 0;
            for (int ele : arr) {
                if (t - ele >= 0) {
                    count += dp[t - ele];
                }
            }
            dp[t] = count;
        }
        return dp[tar];
    }

    public static int infiniteCombination(int arr[], int tar, int idx) {
        if (tar == 0) {
            return 1;
        }
        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += infiniteCombination(arr, tar - arr[i], i);
        }
        return count;
    }

    // using 2D_DP
    public static int infiniteCombination2DP(int arr[], int tar, int idx, int dp[][]) {
        if (tar == 0) {
            return dp[idx][tar] = 1;
        }

        if (dp[idx][tar] != -1) {
            return dp[idx][tar];
        }

        int count = 0;
        for (int i = idx; i < arr.length; i++) {
            if (tar - arr[i] >= 0)
                count += infiniteCombination2DP(arr, tar - arr[i], i, dp);
        }
        return dp[idx][tar] = count;
    }

    public static int infiniteCombination_2DTAB(int arr[], int Tar) {

        int dp[][] = new int[arr.length][Tar + 1];

        for (int idx = 0; idx < arr.length; idx++) {
            for (int tar = 0; tar < Tar + 1; tar++) {
                if (tar == 0) {
                    dp[idx][tar] = 1;
                    continue;
                }
                int count = 0;
                for (int i = idx; i >= 0; i--) {
                    if (tar - arr[i] >= 0)
                        count += dp[i][tar - arr[i]];
                }
                dp[idx][tar] = count;
            }
        }
        return dp[arr.length - 1][Tar];
    }

    public static int infiniteCombination1DTAB(int arr[], int tar) {

        int dp[] = new int[tar + 1];

        dp[0] = 1;

        for (int i = 0; i < arr.length; i++) {
            for (int t = arr[i]; t < tar + 1; t++) {
                dp[t] += dp[t - arr[i]];
            }
        }

        return dp[tar];
    }

    // -------- LEETCODE 322 -------------------
    public int coinChangeAns(int arr[], int tar) {

        if (tar == 0)
            return 0;

        int dp[] = new int[tar + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        // int minCoins = Integer.MAX_VALUE;

        for (int ele : arr) {
            for (int t = ele; t <= tar; t++) {
                if (dp[t - ele] != Integer.MAX_VALUE) /** IMPORTANT */
                    dp[t] = Math.min(dp[t], dp[t - ele] + 1);
            }
        }

        return dp[tar] == Integer.MAX_VALUE ? -1 : dp[tar];
    }

    public static int zero_one_knapsack(int weight[], int value[], int bagw, int idx) {
        if (idx == weight.length || bagw == 0)
            return 0;

        int notIncludingMe = zero_one_knapsack(weight, value, bagw, idx + 1);
        int includingMe = 0;
        if (bagw - weight[idx] >= 0)
            includingMe = zero_one_knapsack(weight, value, bagw - weight[idx], idx + 1) + value[idx];

        return Math.max(notIncludingMe, includingMe);
    }

    public static int counter = 0;

    public static int zero_one_knapsack_memo(int weight[], int value[], int bagw, int idx, int dp[][]) {
        if (idx == weight.length || bagw == 0)
            return dp[idx][bagw] = 0;

        if (dp[idx][bagw] != Integer.MAX_VALUE) {
            System.out.println("Using dp[" + idx + "][" + bagw + "] = " + dp[idx][bagw]);
            return dp[idx][bagw];
        }

        int notIncludingMe = zero_one_knapsack_memo(weight, value, bagw, idx + 1, dp);

        int includingMe = 0;
        if (bagw - weight[idx] >= 0)
            includingMe = zero_one_knapsack_memo(weight, value, bagw - weight[idx], idx + 1, dp) + value[idx];

        dp[idx][bagw] = Math.max(notIncludingMe, includingMe);
        System.out.println(("Saving dp[" + idx + "][" + bagw + "] = " + dp[idx][bagw]));

        return dp[idx][bagw];
    }

    public static int zero_one_knapsack_Dp(int weight[], int value[], int cap) {

        int dp[][] = new int[weight.length + 1][cap + 1];

        for (int idx = 1; idx < weight.length + 1; idx++) {
            for (int tar = 1; tar < cap + 1; tar++) {

                int notIncludingMe = dp[idx - 1][tar];

                int includingMe = 0;
                if (tar - weight[idx - 1] >= 0) { // IMP ..Wrote wrong two times ..write in this way only.. tar-xyz >= 0
                    includingMe = dp[idx - 1][tar - weight[idx - 1]] + value[idx - 1];
                }

                dp[idx][tar] = Math.max(notIncludingMe, includingMe);
            }
        }

        return dp[weight.length][cap];

    }

    // ----FRACTIONAL KNAPSACK----------------------------

    // ------GREEDY ALGO-----------------------------------
    //

    public static double fractionalKnapSack() {

        int n = 10;
        // int n=20;

        int v[] = new int[n];
        int w[] = new int[n];

        // v = new int[] { 1, 43, 22, 25, 10, 7, 17, 16, 7, 2, 12, 11, 37, 40, 48, 35,
        // 1, 35, 3, 25 };
        // w = new int[] { 8, 5, 5, 2, 7, 3, 2, 4, 5, 6, 10, 3, 9, 6, 10, 4, 7, 7, 10, 3
        // };
        // int cap = 4;

        v = new int[] { 33, 14, 50, 9, 8, 11, 6, 40, 2, 15 };
        w = new int[] { 7, 2, 5, 9, 3, 2, 1, 10, 3, 3 };
        int cap = 5;


        /**
         * Solution begins from here
         * 
         * 
         */

        Ratio r[] = new Ratio[n];

        for (int i = 0; i < n; i++) {
            double rat = (double) ((v[i] * 1.0) / (1.0 * w[i]));
            int wei = w[i];
            int val = v[i];
            r[i] = new Ratio(rat, val, wei);
        }

        Arrays.sort(r, new Comparator<Ratio>() {
            @Override
            public int compare(Ratio o1, Ratio o2) {
                return (o2.ratio > o1.ratio) ? 1 : ((o2.ratio < o1.ratio) ? -1 : 0);
            }
        });

        double ansValue = 0;

        for (int i = 0; i < n && cap > 0; i++) {

            if (cap < r[i].weight) {
                ansValue += (cap * r[i].ratio);
                cap = 0;

            } else {
                ansValue += (r[i].weight * r[i].ratio); // value of complete element
                cap -= r[i].weight;

            }

        }

        return ansValue;
    }

    public static class Ratio {
        double ratio;
        int value;
        int weight;

        public Ratio() {

        }

        public Ratio(double ratio, int value, int weight) {
            this.ratio = ratio;
            this.value = value;
            this.weight = weight;
        }
    }


    //--------------------------------------------------------------------


    
}