public class client {
    public static void main(String[] args) {
        linkedlist<Integer> ll = new linkedlist<>();
        for (int i = 1; i < 10; i++) {
            ll.addFirst(i);
        }
        // ll.reverseList();
        // System.out.println(ll.getAt(5));
        ll.reverseDataITR_ON();
        System.out.println(ll);
    }
}
