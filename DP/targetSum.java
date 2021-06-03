import java.util.Arrays;

public class targetSum {
    public static void main(String args[]) {

        int arr[] = { 2, 3, 5, 7 };

        int tar = 10;

        int dp[] = new int[tar + 1];
        int dp2[][] = new int[arr.length + 1][tar + 1];

        // System.out.println(targetSumSubset(arr, tar));

        // System.out.println(infinitePermutation(arr, tar));
        Arrays.fill(dp, -1);
        // System.out.println(infinitePermutationDP(arr, tar, dp));
        // System.out.println(infinitePermutationTAB(arr, tar));

        // System.out.println(infiniteCombination(arr, tar, 0));
        for (int i = 0; i < dp2.length; i++)
            Arrays.fill(dp2[i], -1);
        // System.out.println(infiniteCombination2DP(arr, tar, 0, dp2));
        System.out.println(infiniteCombination_2DTAB(arr, tar));

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

}