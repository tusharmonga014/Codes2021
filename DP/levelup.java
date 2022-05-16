import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

public class levelup {

    public static void main(String args[]) {

        // int arr[][] = { { 0, 1, 4, 2, 8, 2 }, { 4, 3, 6, 5, 0, 4 }, { 1, 2, 4, 1, 4,
        // 6 }, { 2, 0, 7, 3, 2, 2 },
        // { 3, 1, 5, 9, 2, 4 }, { 2, 7, 0, 8, 5, 1 } };
        // ArrayList<String> ans = minCostPathMaze(arr);
        // for(String s : ans){
        // System.out.println(s);
        // }

        // System.out.println(distinctSubseqII("abcabc"));

        // int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
        // printAllLIS(arr);

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

    // No of overlapping bridges

    // https://leetcode.com/problems/russian-doll-envelopes/submissions/
    public static class Env implements Comparable<Env> {
        int h;
        int w;

        Env(int h, int w) {
            this.h = h;
            this.w = w;
        }

        public int compareTo(Env o) {
            if (this.h != o.h)
                return this.h - o.h;
            else
                return this.w - o.w;
        }
    }

    public int russianDollEnvelopes(int[][] envelopes) {

        int n = envelopes.length;

        Env envs[] = new Env[n];
        for (int i = 0; i < n; i++) {
            // width -> 0
            // height -> 1
            envs[i] = new Env(envelopes[i][1], envelopes[i][0]);
        }

        Arrays.sort(envs, 0, n);

        int dp[] = new int[n];
        int max = 0;

        for (int i = 0; i < n; i++) {

            int myMax = 0;
            for (int j = 0; j < i; j++) {
                if (envs[i].h != envs[j].h && envs[j].w < envs[i].w) {
                    if (myMax < dp[j])
                        myMax = dp[j];
                }
            }
            dp[i] = myMax + 1;
            if (dp[i] > max) {
                max = dp[i];
            }
        }

        return max;
    }

    public static int minSquares_memo(int n, int dp[]) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (dp[n] != 0) {
            return dp[n];
        }

        int min = n - 1;
        for (int i = 1; i * i <= n; i++) {
            min = Math.min(min, minSquares_memo(n - (i * i), dp));
        }

        return dp[n] = min + 1;
    }

    static int counter = 0;

    public static int minSquares(int n) {

        int[] dp = new int[n + 1];
        // dp[0] = 0;
        // dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            int min = i - 1;
            for (int j = 1; i - (j * j) >= 0; j++) {
                counter++;
                if (dp[i - (j * j)] < min)
                    min = dp[i - (j * j)];
            }
            dp[i] = min + 1;
        }

        return dp[n];
    }

    // https://leetcode.com/problems/minimum-number-of-removals-to-make-mountain-array/
    public int minimumMountainRemovals(int[] arr) {
        int n = arr.length;

        int lis[] = new int[n];
        for (int i = 0; i < n; i++) {
            int myMax = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && lis[j] > myMax) {
                    myMax = lis[j];
                }
            }
            lis[i] = myMax + 1;
        }

        int lds[] = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int myMax = 0;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i] && lds[j] > myMax) {
                    myMax = lds[j];
                }
            }
            lds[i] = myMax + 1;
        }

        int maxLen = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            if (lis[i] > 1 && lds[i] > 1)
                maxLen = Math.max(maxLen, lis[i] + lds[i] - 1);
        }

        return arr.length - maxLen;
    }

    // Fill the dp with -1 for memoization.
    public int longestCommonSubsequence_memo(String s1, String s2, int i, int j, int[][] dp) {

        if (i == s1.length() || j == s2.length())
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        char c1 = s1.charAt(i);
        char c2 = s2.charAt(j);
        // String r1 = s1.substring(i + 1);
        // String r2 = s2.substring(j + 1);

        int ans = 0;

        if (c1 == c2) {

            ans = 1 + longestCommonSubsequence_memo(s1, s2, i + 1, j + 1, dp);

        } else {

            ans = Math.max(longestCommonSubsequence_memo(s1, s2, i, j + 1, dp),
                    longestCommonSubsequence_memo(s1, s2, i + 1, j, dp));

        }

        return dp[i][j] = ans;
    }

    public int longestCommonSubsequence(String s1, String s2) {

        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {

                char c1 = s1.charAt(i - 1);
                char c2 = s2.charAt(j - 1);

                if (c1 == c2)
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);

            }
        }

        return dp[s1.length()][s2.length()];
    }

    public int countPalindromicSubstrings(String s) {

        int n = s.length();

        boolean dp[][] = new boolean[n][n];

        int count = 0;

        for (int gap = 0; gap < n; gap++) {

            for (int i = 0, j = gap; j < n; i++, j++) {

                if (gap == 0) {
                    dp[i][j] = true;
                    count++;
                } else if (gap == 1) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        count++;
                    }
                } else {
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        count++;
                    }
                }

            }

        }

        return count;
    }

    public int longestPalindromicSubstring(String s) {

        boolean dp[][] = new boolean[s.length()][s.length()];

        int maxLen = 0;

        for (int g = 0; g < s.length(); g++) {

            for (int i = 0, j = g; j < s.length(); i++, j++) {

                if (g == 0) {

                    dp[i][j] = true;
                    maxLen = g + 1;

                } else if (g == 1) {

                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        maxLen = g + 1;
                    }

                } else {

                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {

                        dp[i][j] = true;
                        maxLen = g + 1;

                    }

                }

            }

        }

        return maxLen;
    }

    private static class Pair {
        String psf;
        int i;
        int j;

        public Pair(String psf, int i, int j) {
            this.psf = psf;
            this.i = i;
            this.j = j;
        }
    }

    public static ArrayList<String> minCostPathMaze(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;

        int dp[][] = new int[n][m];

        for (int i = n - 1; i >= 0; i--) {

            for (int j = m - 1; j >= 0; j--) {

                if (i == n - 1 && j == m - 1) {
                    dp[i][j] = arr[i][j];
                } else if (i == n - 1) {
                    dp[i][j] = arr[i][j] + dp[i][j + 1];
                } else if (j == m - 1) {
                    dp[i][j] = arr[i][j] + dp[i + 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + arr[i][j];
                }
            }
        }

        System.out.println(dp[0][0]);
        ArrayList<String> ans = new ArrayList<>();

        LinkedList<Pair> que = new LinkedList<>();

        que.addFirst(new Pair("", 0, 0));

        while (que.size() != 0) {

            int size = que.size();
            while (size-- > 0) {

                Pair removed = que.removeFirst();

                if (removed.i == n - 1 && removed.j == m - 1)
                    ans.add(removed.psf);
                else if (removed.i == n - 1) {
                    que.addLast(new Pair(removed.psf + "H", removed.i, removed.j + 1));
                } else if (removed.j == m - 1) {
                    que.addLast(new Pair(removed.psf + "V", removed.i + 1, removed.j));
                } else {
                    if (dp[removed.i + 1][removed.j] < dp[removed.i][removed.j + 1]) {
                        que.addLast(new Pair(removed.psf + "V", removed.i + 1, removed.j));
                    } else if (dp[removed.i + 1][removed.j] > dp[removed.i][removed.j + 1]) {
                        que.addLast(new Pair(removed.psf + "H", removed.i, removed.j + 1));
                    } else {
                        que.addLast(new Pair(removed.psf + "V", removed.i + 1, removed.j));
                        que.addLast(new Pair(removed.psf + "H", removed.i, removed.j + 1));
                    }
                }
            }
        }

        // for(String s : ans){
        // System.out.println(s);
        // }
        return ans;
    }

    public int jumpGame2(int[] arr) {

        int steps = 1;
        int jump = 0;
        int maxReach = 0;

        for (int i = 0; i < arr.length - 1; i++) {

            maxReach = Math.max(maxReach, i + arr[i]);

            if (i >= maxReach) {
                return -1;
            }

            if (i == arr.length - 1)
                return jump;

            steps--;

            if (steps == 0) {
                jump++;
                steps = maxReach - i;
            }
        }

        return jump;

    }

    public static class Pair2 {
        String psf;
        int idx;

        Pair2(String psf, int idx) {
            this.psf = psf;
            this.idx = idx;
        }
    }

    public static void minVarJump_PATH(int arr[]) {

        int n = arr.length;

        Integer dp[] = new Integer[n];

        dp[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {

            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= arr[i] && i + j < n; j++) {
                if (dp[i + j] != null && dp[i + j] < min) {
                    min = dp[i + j];
                }
            }
            if (min != Integer.MAX_VALUE)
                dp[i] = min + 1;
        }

        System.out.println(dp[0]);
        ArrayList<String> ans = new ArrayList<>();

        LinkedList<Pair2> que = new LinkedList<>();

        que.addFirst(new Pair2("0", 0));

        while (que.size() != 0) {

            int size = que.size();
            while (size-- > 0) {

                Pair2 rem = que.removeFirst();

                if (rem.idx == n - 1) {
                    ans.add(rem.psf + " .");
                }

                else {
                    for (int j = 1; j <= arr[rem.idx] && j + rem.idx < n; j++) {
                        if (dp[rem.idx + j] != null && dp[rem.idx + j] == dp[rem.idx] - 1) {
                            que.addLast(new Pair2(rem.psf + " -> " + (rem.idx + j), rem.idx + j));
                        }
                    }
                }

            }
        }

        for (String s : ans) {
            System.out.println(s);
        }

    }

    public static int distinctSubseqII(String s) {

        int[] dp = new int[s.length() + 1];

        int lastocc[] = new int[26];
        Arrays.fill(lastocc, -1);

        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {

            dp[i] = (dp[i - 1] * 2);

            if (lastocc[s.charAt(i - 1) - 'a'] != -1) {
                int prevIdxInString = lastocc[s.charAt(i - 1) - 'a'] - 1;
                dp[i] -= dp[prevIdxInString];
            }

            lastocc[s.charAt(i - 1) - 'a'] = i;
        }

        return dp[s.length()] - 1;
    }

    // leetcode 940
    public int distinctSubseqII_mod(String s) {

        int mod = 1000000007;

        int[] dp = new int[s.length() + 1];
        int lastocc[] = new int[26];
        Arrays.fill(lastocc, -1);

        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {

            dp[i] = (2 * dp[i - 1]) % mod;

            if (lastocc[s.charAt(i - 1) - 'a'] != -1) {
                int prevIdxInString = lastocc[s.charAt(i - 1) - 'a'] - 1;
                dp[i] = (dp[i] - dp[prevIdxInString] + mod) % mod;
            }

            lastocc[s.charAt(i - 1) - 'a'] = i;
        }

        return dp[s.length()] - 1;
    }

    // Space Optimized-------------

    // Space : O(unique chars)

    public static long distinctSubseqII_opti(String s) {

        long[] dp = new long[26];

        // int lastocc[] = new int[26];
        // Arrays.fill(lastocc, -1);

        long prev = 1;
        long curr = 0;
        for (int i = 0; i < s.length(); i++) {

            curr = (prev * 2);

            if (dp[s.charAt(i) - 'a'] != 0) {
                curr -= dp[s.charAt(i) - 'a'];
            }

            dp[s.charAt(i) - 'a'] = prev;

            prev = curr;
        }

        // -1 for empty subsequence
        return curr - 1;
    }

    // leetcode 940
    public int distinctSubseqII_mod_opti(String s) {

        int mod = (int) 1e9 + 7;

        int[] dp = new int[26];

        // int lastocc[] = new int[26];
        // Arrays.fill(lastocc, -1);

        int p = 1;
        int curr = 0;
        for (int i = 0; i < s.length(); i++) {

            curr = (p * 2) % mod;

            if (dp[s.charAt(i) - 'a'] != 0) {
                curr = (curr - dp[s.charAt(i) - 'a'] + mod) % mod;
            }

            dp[s.charAt(i) - 'a'] = p;

            p = curr;
        }

        return curr - 1;
    }

    private static class Pair3 {
        int idx;
        String psf;

        Pair3(String psf, int idx) {
            this.psf = psf;
            this.idx = idx;
        }
    }

    public static void printAllLIS(int[] arr) {

        int n = arr.length;
        int[] dp = new int[n];

        int maxLen = 0;
        for (int i = 0; i < n; i++) {

            int myAns = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] > myAns) {
                    myAns = dp[j];
                }
            }
            dp[i] = myAns + 1;
            if (dp[i] > maxLen) {
                maxLen = dp[i];
            }
        }

        System.out.println(maxLen);

        LinkedList<Pair3> que = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                que.addLast(new Pair3(arr[i] + "", i));
            }
        }

        while (que.size() != 0) {

            int size = que.size();
            while (size-- > 0) {

                Pair3 rem = que.removeFirst();

                if (dp[rem.idx] == 1) {
                    System.out.println(rem.psf);
                }

                for (int j = 0; j < rem.idx; j++) {
                    if (arr[rem.idx] > arr[j] && dp[j] == dp[rem.idx] - 1) {
                        que.addLast(new Pair3(arr[j] + " -> " + rem.psf, j));
                    }
                }

            }
        }

    }

    public static int countPallindromicSubsequences(String s) {

        int[][] dp = new int[s.length()][s.length()];

        for (int gap = 0; gap < s.length(); gap++) {
            for (int i = 0, j = gap; j < s.length(); i++, j++) {
                if (gap == 0) {
                    dp[i][j] = 1;
                } else if (gap == 1) {
                    if (s.charAt(i) == s.charAt(j))
                        dp[i][j] = 3;
                    else
                        dp[i][j] = 2;
                } else {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i][j - 1] + dp[i + 1][j] + 1;
                    } else {
                        dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                    }
                }
            }
        }

        return dp[0][s.length() - 1];
    }

    // Count Vowel Permutations - leetcode 1220

    /*
     * 0 = a 1 = e 2 = i 3 = o 4 = u
     */

    // Recursive memoized
    // -----------------------------------------------------------------------------------
    public int countVowelPermutation_memo(int n) {

        int mod = 1000000007;

        int count = 0;
        int[][] dp = new int[n + 1][5];
        for (int i = 0; i < 5; i++)
            count = (count + countVowelPermutation_memo__(n, 1, i, dp)) % mod;
        return count;

    }

    public int countVowelPermutation_memo__(int n, int i, int ch, int dp[][]) {
        int mod = 1000000007;

        if (i == n) {
            return dp[i][ch] = 1;
        }

        if (dp[i][ch] != 0) {
            return dp[i][ch];
        }

        int count = 0;

        if (ch == 0) {
            count = (count + countVowelPermutation_memo__(n, i + 1, 1, dp)) % mod;
        } else if (ch == 1) {
            count = (count + countVowelPermutation_memo__(n, i + 1, 0, dp)) % mod;
            count = (count + countVowelPermutation_memo__(n, i + 1, 2, dp)) % mod;
        } else if (ch == 2) {
            count = (count + countVowelPermutation_memo__(n, i + 1, 0, dp)) % mod;
            count = (count + countVowelPermutation_memo__(n, i + 1, 1, dp)) % mod;
            count = (count + countVowelPermutation_memo__(n, i + 1, 3, dp)) % mod;
            count = (count + countVowelPermutation_memo__(n, i + 1, 4, dp)) % mod;
        } else if (ch == 3) {
            count = (count + countVowelPermutation_memo__(n, i + 1, 2, dp)) % mod;
            count = (count + countVowelPermutation_memo__(n, i + 1, 4, dp)) % mod;
        } else {
            count = (count + countVowelPermutation_memo__(n, i + 1, 0, dp)) % mod;
        }

        return dp[i][ch] = count;
    }

    // ---------------------------------------------------------------------
    // Tabulation Space : O(1) -- constant**
    // --------------------------------------------------------------------

    public int countVowelPermutation_TAB(int n) {

        int prev[] = new int[5];
        int curr[] = new int[5];

        int mod = (int) 1e9 + 7;

        for (int i = 1; i <= n; i++) {
            for (int ch = 0; ch < curr.length; ch++) {

                if (i == 1) {

                    curr[ch] = 1;

                } else {

                    if (ch == 0) { // a - > e
                        curr[ch] = prev[1];
                    } else if (ch == 1) { // e - > a, i
                        curr[ch] = (prev[0] + prev[2]) % mod;
                    } else if (ch == 2) { // i - > a, e, o, u
                        curr[ch] = (prev[0] + prev[1] + prev[3] + prev[4]) % mod;
                    } else if (ch == 3) { // o - > i, u
                        curr[ch] = (prev[2] + prev[4]) % mod;
                    } else { // u - > a
                        curr[ch] = prev[0];
                    }

                }
                // don't update here!
                // **WRONG prev[ch] = curr[ch]; **WRONG

            }

            for (int ch = 0; ch < curr.length; ch++) {
                prev[ch] = curr[ch];
            }
        }

        int sum = 0;
        for (int i = 0; i < curr.length; i++)
            sum = (sum + curr[i]) % mod;

        return sum;

    }

    public int countVowelPermutation_TAB_mod(int n) {

        int prev[] = new int[5];
        int curr[] = new int[5];

        int mod = (int) 1e9 + 7;

        for (int i = 1; i <= n; i++) {
            for (int ch = 0; ch < curr.length; ch++) {

                if (i == 1) {

                    curr[ch] = 1;

                } else {

                    if (ch == 0) { // a - > e
                        curr[ch] = prev[1];
                    } else if (ch == 1) { // e - > a, i
                        curr[ch] = (prev[0] + prev[2]) % mod;
                    } else if (ch == 2) { // i - > a, e, o, u
                        curr[ch] = (((prev[0] + prev[1]) % mod) + ((prev[3] + prev[4]) % mod)) % mod;
                    } else if (ch == 3) { // o - > i, u
                        curr[ch] = (prev[2] + prev[4]) % mod;
                    } else { // u - > a
                        curr[ch] = prev[0];
                    }

                }

                // not here
                // **WRONG prev[ch] = curr[ch]; **WRONG

            }

            for (int ch = 0; ch < curr.length; ch++) {
                prev[ch] = curr[ch];
            }
        }

        int sum = 0;
        for (int i = 0; i < curr.length; i++)
            sum = (sum + curr[i]) % mod;

        return sum;

    }

    // -------------------------------------------------------------------------

    private static class Pair4 {
        String psf;
        int i;
        int j;

        public Pair4(String psf, int i, int j) {
            this.psf = psf;
            this.i = i;
            this.j = j;
        }
    }

    public static void maxGoldSumAllPath(int[][] arr) {

        int n = arr.length;
        int m = arr[0].length;
        int[][] dp = new int[n][m];

        for (int j = m - 1; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                if (j == m - 1) {
                    dp[i][j] = arr[i][j];
                } else {
                    if (i == 0) {
                        dp[i][j] = arr[i][j] + Math.max(dp[i][j + 1], dp[i + 1][j + 1]);
                    } else if (i == n - 1) {
                        dp[i][j] = arr[i][j] + Math.max(dp[i - 1][j + 1], dp[i][j + 1]);
                    } else {
                        dp[i][j] = arr[i][j] + Math.max(Math.max(dp[i - 1][j + 1], dp[i][j + 1]), dp[i + 1][j + 1]);
                    }
                }
            }
        }

        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i][0] > max)
                max = dp[i][0];
        }

        System.out.println(max);

        LinkedList<Pair4> que = new LinkedList<>();

        for (int i = 0; i < dp.length; i++) {
            if (dp[i][0] == max) {
                que.addLast(new Pair4(i + "", i, 0));
            }
        }

        while (que.size() != 0) {

            int size = que.size();
            while (size-- > 0) {

                Pair4 rem = que.removeFirst();

                if (rem.j == m - 1) {
                    System.out.println(rem.psf);
                } else {

                    if (rem.i == 0) {

                        int myMax = Math.max(dp[rem.i][rem.j + 1], dp[rem.i + 1][rem.j + 1]);

                        if (myMax == dp[rem.i][rem.j + 1])
                            que.addLast(new Pair4(rem.psf + " d2", rem.i, rem.j + 1));
                        if (myMax == dp[rem.i + 1][rem.j + 1])
                            que.addLast(new Pair4(rem.psf + " d3", rem.i + 1, rem.j + 1));

                    } else if (rem.i == n - 1) {

                        int myMax = Math.max(dp[rem.i - 1][rem.j + 1], dp[rem.i][rem.j + 1]);

                        if (myMax == dp[rem.i - 1][rem.j + 1])
                            que.addLast(new Pair4(rem.psf + " d1", rem.i - 1, rem.j + 1));
                        if (myMax == dp[rem.i][rem.j + 1])
                            que.addLast(new Pair4(rem.psf + " d2", rem.i, rem.j + 1));

                    } else {

                        int myMax = Math.max(Math.max(dp[rem.i - 1][rem.j + 1], dp[rem.i][rem.j + 1]),
                                dp[rem.i + 1][rem.j + 1]);

                        if (myMax == dp[rem.i - 1][rem.j + 1])
                            que.addLast(new Pair4(rem.psf + " d1", rem.i - 1, rem.j + 1));
                        if (myMax == dp[rem.i][rem.j + 1])
                            que.addLast(new Pair4(rem.psf + " d2", rem.i, rem.j + 1));
                        if (myMax == dp[rem.i + 1][rem.j + 1])
                            que.addLast(new Pair4(rem.psf + " d3", rem.i + 1, rem.j + 1));

                    }
                }

            }
        }

    }

    public static class Pair5 {
        int i;
        int j;
        String psf;

        public Pair5(String psf, int i, int j) {
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }

    public static void targetSumSubsetAllResults(int[] arr, int tar) {

        boolean[][] dp = new boolean[arr.length + 1][tar + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int t = 0; t <= tar; t++) {
                if (i == 0 && t == 0) {
                    dp[i][t] = true;
                } else if (i == 0) {
                    dp[i][t] = false;
                } else if (t == 0) {
                    dp[i][t] = true;
                } else {
                    int val = arr[i - 1];
                    if (t - val >= 0) {
                        dp[i][t] = dp[i - 1][t] || dp[i - 1][t - val];
                    } else {
                        dp[i][t] = dp[i - 1][t];
                    }
                }
            }
        }

        if (dp[arr.length][tar])
            System.out.println("true");
        else
            System.out.println("false");

        LinkedList<Pair5> que = new LinkedList<>();
        if (dp[dp.length - 1][tar]) {
            que.addFirst(new Pair5("", dp.length - 1, tar));
        }

        while (que.size() != 0) {

            Pair5 rem = que.removeLast();

            if (rem.j == 0) {

                System.out.println(rem.psf);
                continue;

            } else {

                if (rem.j - arr[rem.i - 1] >= 0 && dp[rem.i - 1][rem.j - arr[rem.i - 1]]) {
                    que.addFirst(new Pair5((rem.i - 1) + " " + rem.psf, rem.i - 1, rem.j - arr[rem.i - 1]));
                }

                if (dp[rem.i - 1][rem.j]) {
                    que.addFirst(new Pair5(rem.psf, rem.i - 1, rem.j));
                }
            }
        }

    }

    private static class Pair6 {
        int i;
        int t;
        String psf;

        public Pair6(int i, int t, String psf) {
            this.i = i;
            this.t = t;
            this.psf = psf;
        }
    }

    public static void zero_One_Knapsack_All_Results(int[] values, int[] wts, int cap) {

        int[][] dp = new int[values.length + 1][cap + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int t = 1; t <= cap; t++) {
                if (t - wts[i - 1] >= 0) {
                    dp[i][t] = Math.max(dp[i - 1][t - wts[i - 1]] + values[i - 1], dp[i - 1][t]);
                } else {
                    dp[i][t] = dp[i - 1][t];
                }
            }
        }

        System.out.println(dp[dp.length - 1][cap]);

        LinkedList<Pair6> que = new LinkedList<>();
        que.add(new Pair6(dp.length - 1, cap, ""));

        while (que.size() != 0) {
            Pair6 rem = que.removeFirst();

            if (rem.t == 0 || rem.i == 0) {
                System.out.println(rem.psf);
                continue;
            }

            int i = rem.i;
            int t = rem.t;
            int val = values[rem.i - 1];
            int wt = wts[rem.i - 1];

            if (t - wt >= 0 && dp[i - 1][t - wt] + val >= 0) {
                if (dp[i - 1][t - wt] + val > dp[i - 1][t]) {
                    que.addLast(new Pair6(i - 1, t - wt, (i - 1) + " " + rem.psf));
                } else if (dp[i - 1][t - wt] + val < dp[i - 1][t]) {
                    que.addLast(new Pair6(i - 1, t, rem.psf));
                } else {
                    que.addLast(new Pair6(i - 1, t - wt, (i - 1) + " " + rem.psf));
                    que.addLast(new Pair6(i - 1, t, rem.psf));
                }
            } else {
                que.addLast(new Pair6(i - 1, t, rem.psf));
            }
        }
    }

    public static void zero_One_Knapsack_All_Results__ALTERNATIVE(int[] values, int[] wts, int cap) {

        int[][] dp = new int[values.length + 1][cap + 1];

        for (int i = 1; i < dp.length; i++) {
            for (int t = 1; t <= cap; t++) {
                if (t - wts[i - 1] >= 0) {
                    dp[i][t] = Math.max(dp[i - 1][t - wts[i - 1]] + values[i - 1], dp[i - 1][t]);
                } else {
                    dp[i][t] = dp[i - 1][t];
                }
            }
        }

        System.out.println(dp[dp.length - 1][cap]);

        LinkedList<Pair6> que = new LinkedList<>();
        que.add(new Pair6(dp.length - 1, cap, ""));

        while (que.size() != 0) {
            Pair6 rem = que.removeFirst();

            if (rem.t == 0 || rem.i == 0) {
                System.out.println(rem.psf);
                continue;
            }

            int i = rem.i;
            int t = rem.t;
            int val = values[rem.i - 1];
            int wt = wts[rem.i - 1];

            if (t - wt >= 0) {
                int inc = dp[i - 1][t - wt] + val;
                if (dp[i][t] == inc) {
                    que.addLast(new Pair6(i - 1, t - wt, (i - 1) + " " + rem.psf));
                }
            }

            int exc = dp[i - 1][t];
            if (dp[i][t] == exc) {
                que.addLast(new Pair6(i - 1, t, rem.psf));
            }
        }
    }

    public static int minPalindromicCut(String s) {
        boolean dp[][] = new boolean[s.length()][s.length()];

        for (int g = 0; g < s.length(); g++) {
            for (int i = 0, j = g; j < s.length(); i++, j++) {
                if (g == 0) {
                    dp[i][j] = true;
                } else if (g == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
            }
        }

        int ndp[] = new int[s.length()];
        for (int j = 1; j < s.length(); j++) {
            if (dp[0][j]) {
                ndp[j] = 0;
            } else {
                int min = Integer.MAX_VALUE;
                for (int i = j; i >= 0; i--) {
                    if (dp[i][j]) {
                        // compare only rest of the string as suffix has 0 cuts
                        min = Math.min(min, ndp[i - 1]);
                    }
                }
                // do + 1 for cut betweeen suffix and rest of the string
                ndp[j] = min + 1;
            }
        }

        return ndp[s.length() - 1];
    }

    public static int matrixMultiplication(int N, int arr[]) {
        int dp[][] = new int[N - 1][N - 1];
        for (int g = 0; g < N - 1; g++) {
            for (int i = 0, j = g; j < N - 1; i++, j++) {
                if (g == 0) {
                    dp[i][j] = 0;
                } else if (g == 1) {
                    dp[i][j] = arr[i] * arr[i + 1] * arr[j + 1];
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        int l = dp[i][k];
                        int r = dp[k + 1][j];
                        int m = arr[i] * arr[k + 1] * arr[j + 1];
                        min = Math.min(min, l + r + m);
                    }
                    dp[i][j] = min;
                }
            }
        }
        return dp[0][N - 1 - 1];
    }

    public static int booleanParenthisization(String str1, String str2) {
        int dp[][][] = new int[str1.length()][str1.length()][2];
        // 0 -> count of true
        // 1 -> count of false

        for (int g = 0; g < str1.length(); g++) {
            for (int i = 0, j = g; j < str1.length(); i++, j++) {
                if (g == 0) {
                    char ch = str1.charAt(i);
                    dp[i][j][0] = ch == 'T' ? 1 : 0;
                    dp[i][j][1] = ch == 'F' ? 1 : 0;
                } else {
                    for (int k = i; k < j; k++) {
                        char op = str2.charAt(k);
                        int leftT = dp[i][k][0];
                        int leftF = dp[i][k][1];
                        int rightT = dp[k + 1][j][0];
                        int rightF = dp[k + 1][j][1];
                        if (op == '&') {
                            dp[i][j][0] += leftT * rightT;
                            dp[i][j][1] += (leftT * rightF) + (leftF * rightT) + (leftF * rightF);
                        } else if (op == '|') {
                            dp[i][j][0] += (leftT * rightF) + (leftF * rightT) + (leftT * rightT);
                            dp[i][j][1] += leftF * rightF;
                        } else {
                            dp[i][j][0] += (leftT * rightF) + (leftF * rightT);
                            dp[i][j][1] += (leftT * rightT) + (leftF * rightF);
                        }
                    }
                }
            }
        }
        return dp[0][str1.length() - 1][0];
    }

    private static void optimalbst(int[] keys, int[] frequency, int n) {
        int preSum[] = new int[keys.length];
        preSum[0] = frequency[0];
        for (int i = 1; i < keys.length; i++) {
            preSum[i] += preSum[i - 1] + frequency[i];
        }
        int[][] dp = new int[keys.length][keys.length];
        for (int g = 0; g < keys.length; g++) {
            for (int i = 0, j = g; j < keys.length; i++, j++) {
                if (g == 0) {
                    dp[i][j] = frequency[i];
                } else if (g == 1) {
                    int ith = frequency[i];
                    int jth = frequency[j];
                    dp[i][j] = Math.min(ith + (2 * jth), jth + (2 * ith));
                } else {
                    int fs = preSum[j] - (i == 0 ? 0 : preSum[i - 1]);
                    int min = Integer.MAX_VALUE;
                    for (int k = i; k <= j; k++) {
                        int left = k == i ? 0 : dp[i][k - 1];
                        int right = k == j ? 0 : dp[k + 1][j];
                        int cur = left + right + fs;
                        min = Math.min(min, cur);
                    }
                    dp[i][j] = min;
                }
            }
        }
        System.out.println(dp[0][keys.length - 1]);
    }

    public int burstBalloons(int[] nums) {
        int dp[][] = new int[nums.length][nums.length];
        for (int g = 0; g < nums.length; g++) {
            for (int i = 0, j = g; j < nums.length; i++, j++) {
                if (g == 0) {
                    // i == j
                    int balloonOnLeft = (i == 0 ? 1 : nums[i - 1]);
                    int balloonOnRight = (i == nums.length - 1 ? 1 : nums[i + 1]);
                    dp[i][i] = balloonOnLeft * nums[i] * balloonOnRight;
                } else if (g == 1) {
                    int balloonOnLeft = (i == 0 ? 1 : nums[i - 1]);
                    int balloonOnRight = (j == nums.length - 1 ? 1 : nums[j + 1]);
                    int c1 = (balloonOnLeft * nums[i] * balloonOnRight) + dp[i + 1][j];
                    int c2 = dp[i][j - 1] + (balloonOnLeft * nums[j] * balloonOnRight);
                    dp[i][j] = Math.max(c1, c2);
                } else {
                    int max = Integer.MIN_VALUE;
                    for (int k = i; k <= j; k++) {
                        int balloonOnLeft = (i == 0 ? 1 : nums[i - 1]);
                        int balloonOnRight = (j == nums.length - 1 ? 1 : nums[j + 1]);
                        int leftAns = k == i ? 0 : dp[i][k - 1];
                        int rightAns = k == j ? 0 : dp[k + 1][j];
                        int c = leftAns + (balloonOnLeft * nums[k] * balloonOnRight) + rightAns;
                        max = Math.max(max, c);
                    }
                    dp[i][j] = max;
                }
            }
        }

        return dp[0][nums.length - 1];
    }

}