import java.util.Arrays;

public class heapSort {

    public static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void downHeapify(int arr[], int pi, int n) {
        int lci = (pi * 2) + 1;
        int rci = (pi * 2) + 2;

        int maxIdx = pi;

        if (lci < n && arr[lci] > arr[maxIdx])
            maxIdx = lci;

        if (rci < n && arr[rci] > arr[maxIdx])
            maxIdx = rci;

        if (pi != maxIdx) {
            swap(arr, pi, maxIdx);
            downHeapify(arr, maxIdx, n);
        }
    }

    public static void main(String[] args) {
        int arr[] = { 10, 1, 2, 3, 4, 100, 6, 5, 4, 8, 7, 9 };

        int n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            downHeapify(arr, i, n);
        }

        int idx = n - 1;
        while (idx > 0) {
            swap(arr, 0, idx);
            downHeapify(arr, 0, idx);
            idx--;
        }

        System.out.println(Arrays.toString(arr));
    }
}
