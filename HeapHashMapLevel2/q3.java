import java.util.HashMap;

public class q3 {
    // GFG question - largest subarray with 0 sum.
    int maxLen(int arr[], int n) {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(0, -1);

        int max = 0;

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                int cur = i - map.get(sum);
                max = Math.max(max, cur);
            } else {
                map.put(sum, i);
            }
        }

        return max;
    }
}
