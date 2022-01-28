import java.util.LinkedList;
import java.util.PriorityQueue;

public class practice {

    public static void main(String[] args) {
        // int n = 10;
        // int dp[] = new int[n];
        // System.out.println(boardPath(0, n, 6, dp));
        // System.out.println(boardPathDP(0, n, 6));

        int arr[] = { 2, 3, 5, 7 };
        int tar = 10;
        // System.out.println(targetSum(arr, tar, 0));
        // System.out.println(targetSumLoop(arr, tar, 0));
        // System.out.println(targetSumDp(arr, tar));

        // System.out.println(infiniteCombi(arr, tar));
        System.out.println(infinitePermu(arr, tar));
    }

    public static int boardPath(int s, int e, int m, int dp[]) {
        if (s == e) {
            return 1;
        }

        if (dp[s] != 0)
            return dp[s];

        int ways = 0;
        for (int i = 1; i <= m && s + i <= e; i++) {
            ways += boardPath(s + i, e, m, dp);
        }

        return dp[s] = ways;
    }

    public static int boardPathDP(int s, int e, int m) {
        LinkedList<Integer> ll = new LinkedList<>();

        for (int i = e; i >= s; i--) {
            if (i >= e - 1)
                ll.addFirst(1);
            else if (i >= e - m)
                ll.addFirst(ll.getFirst() * 2);
            else {
                int toAdd = ll.getFirst() * 2 - ll.removeLast();
                ll.addFirst(toAdd);
            }
        }

        return ll.getFirst();
    }

    public static boolean targetSum(int arr[], int tar, int i) {

        if (tar == 0)
            return true;

        if (i == arr.length)
            return false;

        boolean res = false;

        res = targetSum(arr, tar, i + 1);
        if (!res && tar - arr[i] >= 0)
            res = targetSum(arr, tar - arr[i], i + 1);

        return res;
    }

    public static boolean targetSumLoop(int arr[], int tar, int i) {

        if (tar == 0)
            return true;

        if (i == arr.length)
            return false;

        for (int idx = i; idx < arr.length; idx++) {
            if (tar - arr[i] >= 0) {
                boolean res = targetSumLoop(arr, tar - arr[i], idx + 1);
                if (res)
                    return res;
            }
        }

        return false;
    }

    public static boolean targetSumDp(int arr[], int tar) {
        boolean dp[][] = new boolean[arr.length + 1][tar + 1];

        dp[0][0] = true;
        for (int i = 1; i <= arr.length; i++) {
            dp[i][0] = true;
            for (int t = arr[i - 1]; t <= tar; t++) {
                // if else condition necessary.. reason above tabulation table
                dp[i][t] = dp[i - 1][t] || dp[i - 1][t - arr[i - 1]];
            }
        }

        return dp[arr.length][tar];
    }

    public static int infiniteCombi(int arr[], int tar) {
        int n = arr.length;
        int dp[] = new int[tar + 1];

        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int t = arr[i]; t <= tar; t++) {
                dp[t] += dp[t - arr[i]];
            }
        }

        return dp[tar];
    }

    public static int infinitePermu(int arr[], int tar) {
        int n = arr.length;
        int dp[] = new int[tar + 1];

        dp[0] = 1;
        for (int t = 0; t <= tar; t++) {
            for (int i = 0; i < n; i++) {
                if (t - arr[i] >= 0) {
                    dp[t] += dp[t - arr[i]];
                }
            }
        }

        return dp[tar];
    }

    // ======================================================================

    // FRACTIONAL KNAPSACK --------------------------

    public class Item {
        int value, weight;

        Item(int x, int y) {
            this.value = x;
            this.weight = y;
        }
    }

    // Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int W, Item arr[], int n) {

        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            return (b.value * 1.0) / b.weight > (a.value * 1.0) / a.weight ? 1
                    : (b.value * 1.0) / b.weight < (a.value * 1.0) / a.weight ? -1 : 0;
        });

        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }

        int w = 0;
        double ansValue = 0;

        while (pq.size() != 0 && w < W) {

            Item rem = pq.remove();
            double valueToBeAdded = 0;

            if (rem.weight > W - w) {
                valueToBeAdded = (W - w) * ((rem.value * 1.0) / rem.weight);
                w = W;
            } else {
                valueToBeAdded = rem.value;
                w += rem.weight;
            }

            ansValue += valueToBeAdded;
        }

        return ansValue;
    }

    //======================================================================

    //  Leetcode 696

    // different fro pep site -------------------
    
    public int countBinarySubstrings(String s) {
        
        int count = 0;
        int c1 = 1;
        int c2 = 0;

        char cur = s.charAt(0);
        
        int i = 1;
        for(i = 1; i < s.length(); i++){
            if(s.charAt(i) == cur){
                c1++;
            } else {
                break;
            }
        }
        
        for(int j = i; j < s.length(); j++){
            if(s.charAt(j) != cur){
                c2++;
                if(c2 <= c1)
                    count++;
            } else {
                cur = s.charAt(j - 1);
                c1 = c2;
                c2 = 1;
                if(c2 <= c1)
                    count++;
            }
        }
        
        return count;
    }
}
