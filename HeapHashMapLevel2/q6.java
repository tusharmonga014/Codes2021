import java.util.HashMap;

public class q6 {

    // leetcode 3
    public int lengthOfLongestSubstring(String s) {

        int ans = 0;

        int i = -1;
        int j = -1;

        boolean f1 = true;
        boolean f2 = true;

        HashMap<Character, Integer> map = new HashMap<>();

        while (f1 || f2) {

            f1 = false;
            f2 = false;

            while (i < s.length() - 1) {
                f1 = true;
                i++;

                char c = s.charAt(i);

                map.put(c, map.getOrDefault(c, 0) + 1);

                if (map.get(c) == 2) {
                    break;
                } else {
                    int len = i - j;
                    ans = Math.max(ans, len);
                }
            }

            while (j < i) {
                f2 = true;
                j++;

                char c = s.charAt(j);

                map.put(c, map.get(c) - 1);

                if (map.getOrDefault(c, 0) == 1) {
                    break;
                }
            }
        }

        return ans;
    }
}
