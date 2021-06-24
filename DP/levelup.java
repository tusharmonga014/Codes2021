
public class levelup {

    public static void main(String args[]) {

    }

    public static int LIS(int[] arr) {

        int dp[] = new int[arr.length];

        int max_ = 0;

        for (int i = 0; i < arr.length; i++) {

            int myAns = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && myAns < dp[j])
                    myAns = dp[j];
            }
            dp[i] = myAns + 1;
            max_ = Math.max(max_, dp[i]);
        }

        return max_;
    }

    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1
    public static int MaxSumIncreasingSubsequence(int[] arr) {

        int dp[] = new int[arr.length];

        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {

            int myMaxSum = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    if (dp[j] > myMaxSum)
                        myMaxSum = dp[j];
                }
            }

            if (myMaxSum != Integer.MIN_VALUE)
                dp[i] = myMaxSum + arr[i];
            else
                dp[i] = arr[i];

            if (maxSum < dp[i]) {
                maxSum = dp[i];
            }
        }

        return maxSum;
    }

    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1
    public static int LBS(int[] arr) {

        int lis[] = new int[arr.length];
        int lds[] = new int[arr.length];

        int max = 0;

        for (int i = 0; i < arr.length; i++) {

            int myLis = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && myLis < lis[j])
                    myLis = lis[j];
            }
            lis[i] = myLis + 1;
        }

        for (int i = arr.length - 1; i >= 0; i--) {

            int myLds = 0;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j] && myLds < lds[j])
                    myLds = lds[j];
            }
            lds[i] = myLds + 1;
        }

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, lis[i] + lds[i] - 1);
        }

        return max;
    }

    // https://practice.geeksforgeeks.org/problems/minimum-number-of-deletions-to-make-a-sorted-sequence3248/1
    public static int minDeletionsToMakeArraySorted(int[] arr) {

        int dp[] = new int[arr.length];

        int max_ = 0;

        for (int i = 0; i < arr.length; i++) {

            int myAns = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && myAns < dp[j])
                    myAns = dp[j];
            }
            dp[i] = myAns + 1;
            max_ = Math.max(max_, dp[i]);
        }

        // ONly Change ------------------------------------
        return arr.length - max_;
    }

    // 673
    public static int findNumberOfLIS(int[] arr) {

        int dp[] = new int[arr.length];
        int count[] = new int[arr.length];

        int max_ = 0;
        int maxCount = 0;
        for (int i = 0; i < arr.length; i++) {

            int myAns = 0;
            int mcount = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    if (myAns < dp[j]) {
                        myAns = dp[j];
                        mcount = count[j];
                    } else if (myAns == dp[j])
                        mcount += count[j];
                }
            }
            dp[i] = myAns + 1;
            count[i] = mcount;
            if (max_ < dp[i]) {
                max_ = dp[i];
                maxCount = count[i];
            } else if (max_ == dp[i]) {
                maxCount += count[i];
            }
        }

        return maxCount;
    }

}