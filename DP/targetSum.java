import java.util.Arrays;

public class targetSum {
    public static void main(String args[]) {

        int arr[] = { 2, 3, 5, 7 };

        int tar = 10;

        int dp[] = new int[tar + 1];

        // System.out.println(targetSumSubset(arr, tar));

        // System.out.println(infinitePermutation(arr, tar));
        Arrays.fill(dp, -1);
        // System.out.println(infinitePermutationDP(arr, tar, dp));
        System.out.println(infinitePermutationTAB(arr, tar));
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
        Arrays.fill(dp, -1);

        for (int t = 0; t < tar + 1; t++) {
            if (t == 0) {
                dp[t] = 1;
                continue;
            }
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (t - arr[i] >= 0) {
                    count += dp[t - arr[i]];
                }
            }
            dp[t] = count;
        }
        return dp[tar];
    }

}