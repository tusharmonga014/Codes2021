import java.util.HashMap;

public class q5 {

    // GFG Question -
    // https://practice.geeksforgeeks.org/problems/smallest-distant-window3132/1?utm_source=gfg&utm_medium=article&utm_campaign=bottom_sticky_on_article
    public int findSubString(String str) {

        String s = str;
        String t = str;

        HashMap<Character, Integer> omap = new HashMap<>();
        for (char c : t.toCharArray()) {
            omap.put(c, 1);
        }

        int ans = 0;
        int ansi = -1;
        int ansj = -1;

        int mct = 0;
        int dmct = omap.size();
        HashMap<Character, Integer> fmap = new HashMap<>();

        int i = -1;
        int j = -1;

        boolean flag1 = true;
        boolean flag2 = true;

        while (flag1 || flag2) {

            flag1 = false;
            flag2 = false;

            // acquire while matchCount != desired_mct
            while (i < s.length() - 1 && mct < dmct) {
                i++;

                char c = s.charAt(i);

                fmap.put(c, fmap.getOrDefault(c, 0) + 1);

                if (fmap.getOrDefault(c, 0) <= omap.getOrDefault(c, 0))
                    mct++;

                flag1 = true;
            }

            // update ans, relase answer, untill mct does not drop
            while (j < i && mct == dmct) {

                int potential_ans = (i - j);
                if (ans == 0 || potential_ans < ans) {
                    ans = potential_ans;
                    ansi = i;
                    ansj = j;
                }

                j++;

                char c = s.charAt(j);

                fmap.put(c, fmap.get(c) - 1);

                if (fmap.getOrDefault(c, 0) < omap.getOrDefault(c, 0))
                    mct--;

                flag2 = true;
            }
        }

        return ans;
    }
}
