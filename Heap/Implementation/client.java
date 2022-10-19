public class client {
    public static void main(String[] args) {
        int arr[] = new int[] { 10, 20, 30, -2, -3, -4, 5, 6, 7, 8, 9, 22, 11, 13, 14 };
        heap pq = new heap(false);
        for (int ele : arr)
            pq.add(ele);
        System.out.println(pq);
        while (pq.size() != 0) {
            System.out.print(pq.remove() + " ");
        }
    }
}
