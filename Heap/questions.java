import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class questions {

    // 215
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int ele : nums) {
            pq.offer(ele);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    // leetcode 703
    class KthLargest {

        PriorityQueue<Integer> pq;
        int k;

        public KthLargest(int k, int[] nums) {
            this.k = k;
            this.pq = new PriorityQueue<>();
            for (int ele : nums) {
                this.pq.offer(ele);
                if (this.pq.size() > this.k) {
                    this.pq.poll();
                }
            }
        }

        public int add(int val) {
            this.pq.offer(val);

            if (this.pq.size() > this.k)
                this.pq.poll();

            return this.pq.peek();
        }
    }

    class Pair implements Comparable<Pair> {
        int val;
        int x;
        int y;

        Pair(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair o) {
            return this.val - o.val;
        }
    }

    // leetcode 378
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i = 0; i < matrix.length; i++) {
            pq.offer(new Pair(matrix[i][0], i, 0));
        }

        while (k-- > 1) {
            Pair rem = pq.poll();
            if (rem.y < matrix[rem.x].length - 1) {
                pq.offer(new Pair(matrix[rem.x][rem.y + 1], rem.x, rem.y + 1));
            }
        }

        return pq.peek().val;
    }

    class sPair implements Comparable<sPair> {
        String s;
        int f;

        sPair(String s, int f) {
            this.s = s;
            this.f = f;
        }

        @Override
        public int compareTo(sPair o) {
            if (this.f < o.f) {
                return -1;
            } else if (this.f > o.f) {
                return 1;
            } else {
                return o.s.compareTo(this.s);
            }
        }
    }

    // leetcode 692
    public List<String> topKFrequent(String[] words, int k) {
        Arrays.sort(words);
        List<String> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<sPair> pq = new PriorityQueue<>();
        for (String s : map.keySet()) {
            pq.add(new sPair(s, map.get(s)));
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while (k-- > 0) {
            ans.add(pq.poll().s);
        }
        Collections.reverse(ans);
        return ans;
    }

    // leetcode 778
    public int swimInWater(int[][] grid) {

        int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> {
            int v1 = grid[a[0]][a[1]];
            int v2 = grid[b[0]][b[1]];

            return v1 - v2;
        });

        pq.add(new Integer[] { 0, 0 });

        int max = Integer.MIN_VALUE;

        boolean vis[][] = new boolean[grid.length][grid[0].length];

        while (pq.size() != 0) {
            Integer[] hei = pq.poll();

            vis[hei[0]][hei[1]] = true;
            max = Math.max(max, grid[hei[0]][hei[1]]);

            if (hei[0] == grid.length - 1 && hei[1] == grid[0].length - 1) {
                return max;
            }

            for (int d = 0; d < dir.length; d++) {
                int x = dir[d][0] + hei[0];
                int y = dir[d][1] + hei[1];

                if (x >= 0 && y >= 0 && x < grid.length && y < grid[x].length && !vis[x][y]) {
                    pq.offer(new Integer[] { x, y });
                }
            }
        }

        return max;
    }

    // leetcode 1642
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        long sum = 0;
        for (int i = 0; i < heights.length - 1; i++) {
            if (heights[i] < heights[i + 1]) {
                int h = heights[i + 1] - heights[i];
                pq.add(h);
            }

            if (pq.size() > ladders) {
                sum += pq.poll();

                if (sum > bricks) {
                    break;
                }
            }
            ans = i + 1;
        }

        return ans;
    }

    class rPair implements Comparable<rPair> {
        int val;
        int i;
        int j;

        rPair(int val, int i, int j) {
            this.val = val;
            this.i = i;
            this.j = j;
        }

        public int compareTo(rPair o) {
            return this.val - o.val;
        }
    }

    // leetcode 632
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<rPair> minH = new PriorityQueue<>();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int ele = nums.get(i).get(0);
            minH.add(new rPair(ele, i, 0));
            max = Math.max(max, ele);
        }

        int range = Integer.MAX_VALUE;
        int ans[] = new int[2];

        while (minH.size() == nums.size()) {
            rPair min = minH.poll();
            int cur_range = max - min.val;
            if (cur_range < range) {
                range = cur_range;
                ans[0] = min.val;
                ans[1] = max;
            }

            if (min.j < nums.get(min.i).size() - 1) {
                int ele = nums.get(min.i).get(min.j + 1);
                minH.offer(new rPair(ele, min.i, min.j + 1));
                max = Math.max(max, ele);
            } else {
                break;
            }
        }

        return ans;
    }

}
