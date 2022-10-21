import java.util.*;

public class questions {
    // leetcode 349, 350, 219, 451, 347, 380(*) must see, 381**** (follow up -
    // definitely see code)

    // leetcode 381
    class RandomizedCollection {

        ArrayList<Integer> al;
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        public RandomizedCollection() {
            this.al = new ArrayList<>();
            this.map = new HashMap<>();
        }

        public boolean insert(int val) {
            boolean contains = map.containsKey(val);
            if (!contains) {
                map.put(val, new HashSet<>());
            }
            map.get(val).add(al.size());
            al.add(val);
            return !contains;
        }

        public boolean remove(int val) {
            boolean contains = map.containsKey(val);
            if (!contains)
                return false;
            int loc = map.get(val).iterator().next(); // (it gets the first element, as we are sure at least one
                                                      // exists.)
            map.get(val).remove(loc);

            if (loc < al.size() - 1) { // really IMP condition.. or will fail at insert 1 -> true, remove 1 -> true,
                                       // insert 1 -> exp(true) out(false), as itt added 1 at back of al again.
                int lastOne = al.get(al.size() - 1);
                al.set(loc, lastOne);
                map.get(lastOne).remove(al.size() - 1);
                map.get(lastOne).add(loc);
            }

            al.remove(al.size() - 1);
            if (map.get(val).isEmpty()) {
                map.remove(val);
            }
            return true;
        }

        public int getRandom() {
            int r = (int) (Math.random() * al.size());
            return al.get(r);
        }
    }

    // leetcode 128
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int ele : nums) {
            set.add(ele);
        }

        int range = 0;
        for (int ele : nums) {

            int prev = ele;
            while (set.contains(prev - 1)) {
                set.remove(prev - 1);
                prev--;
            }

            int next = ele;
            while (set.contains(next + 1)) {
                set.remove(next + 1);
                next++;
            }

            int cur_range = next - prev + 1;
            range = Math.max(range, cur_range);
        }

        return range;
    }

    // leetcode 49
    public List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {

            int freq[] = new int[26];
            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (freq[i] > 0) { // ***************** DON'T FORGET THIS *********************
                    char ch = (char) (i + 'a');
                    sb.append(ch + "" + freq[i]);
                }
            }

            String freqStr = sb.toString();
            if (map.containsKey(freqStr)) {
                map.get(freqStr).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(freqStr, list);
            }
        }

        List<List<String>> ans = new ArrayList<>();
        for (String s : map.keySet()) {
            ans.add(map.get(s));
        }

        return ans;
    }

    // leetcode 295 --------------------- SEE CODE ---------
    /*
     * Always maintain pq1 = pq2 OR pq1 = p2 + 1.
     * Equalize function will be different work for them.
     */
    class MedianFinder {
        PriorityQueue<Integer> maxH;
        PriorityQueue<Integer> minH;

        public MedianFinder() {
            maxH = new PriorityQueue<>(Collections.reverseOrder());
            minH = new PriorityQueue<>();
        }

        private void equalizePQs() {

            while (maxH.size() > minH.size() + 1) {
                minH.offer(maxH.poll());
            }

            while (minH.size() > maxH.size()) {
                maxH.offer(minH.poll());
            }

        }

        public void addNum(int num) {

            if (maxH.size() == 0) {
                maxH.offer(num);
            } else if (num <= maxH.peek()) {
                maxH.offer(num);
            } else {
                minH.offer(num);
            }

            equalizePQs();
        }

        public double findMedian() {
            if (minH.size() == maxH.size()) {
                return ((minH.peek() + maxH.peek()) * 1.0) / 2.0;
            } else {
                return maxH.peek();
            }
        }
    }

    //
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int max = 0;

        int csum = 0;

        map.put(csum, -1);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                csum--;
            else
                csum++;

            if (map.containsKey(csum)) {
                int cur = (i - map.get(csum)); // as both pointers are not inclusive, so not + 1
                max = Math.max(max, cur);
            } else {
                map.put(csum, i);
            }
        }

        return max;
    }

    // gfg question
    public int countSubarrWithEqualZeroAndOne(int nums[], int len) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int csum = 0;

        map.put(csum, 1);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                csum--;
            else
                csum++;

            map.put(csum, map.getOrDefault(csum, 0) + 1);
        }

        int ans = 0;
        for (int sum : map.keySet()) { // remember to use map's get value and not the key value itself...
            int n = map.get(sum) - 1; // AND V.V.V.V.V.V IMP Remember to do count - 1, as first time the sum occ 01
                                      // were not equal, even for 0 as we enetered 0 sum intitially.
            ans += (n * (n + 1)) / 2; // Also Its n * (n + 1) / 2 , ==== not n * (n - 1) / 2 ====
        }

        return ans;
    }

}
