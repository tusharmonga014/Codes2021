import java.util.ArrayList;

public class heap {
    ArrayList<Integer> arr;
    boolean isMax = false;

    heap() {
        this.isMax = false;
        this.arr = new ArrayList<>();
    }

    heap(boolean isMax) {
        this();
        this.isMax = isMax;
    }

    heap(int[] arr, boolean isMax) {
        this(isMax);
        for (int ele : arr) {
            this.arr.add(ele);
        }
        for (int i = this.arr.size() - 1; i >= 0; i--) {
            downheapify(i);
        }
    }

    public int size() {
        return this.arr.size();
    }

    private int compareTo(int x, int y) {
        if (isMax)
            return x - y;
        else
            return y - x;
    }

    private void swap(int i, int j) {
        int ith = this.arr.get(i);
        int jth = this.arr.get(j);

        this.arr.set(i, jth);
        this.arr.set(j, ith);
    }

    private void downheapify(int pi) {
        int lci = (pi * 2) + 1;
        int rci = (pi * 2) + 2;

        int maxIdx = pi;

        if (lci < this.arr.size() && compareTo(this.arr.get(lci), this.arr.get(maxIdx)) > 0)
            maxIdx = lci;

        if (rci < this.arr.size() && compareTo(this.arr.get(rci), this.arr.get(maxIdx)) > 0)
            maxIdx = rci;

        if (pi != maxIdx) {
            swap(pi, maxIdx);
            downheapify(maxIdx);
        }
    }

    private void upheapify(int ci) {
        int pi = (ci - 1) / 2;

        if (pi >= 0 && compareTo(this.arr.get(ci), this.arr.get(pi)) > 0) {
            swap(ci, pi);
            upheapify(pi);
        }
    }

    public void add(int ele) {
        this.arr.add(ele);
        upheapify(this.arr.size() - 1);
    }

    public int peek() {
        if (this.size() == 0) {
            return -1;
        } else {
            return this.arr.get(0);
        }
    }

    public void update(int idx, int ele) {
        if (idx < 0 || idx >= this.arr.size()) {
            return;
        }

        this.arr.set(idx, ele);
        upheapify(idx);
        downheapify(idx); // we call both, but in every case either of them will work
    }

    public int remove() {
        int remEle = this.arr.get(0);

        if (this.arr.size() == 1) {
            arr = new ArrayList<>();
        } else {
            this.arr.set(0, this.arr.remove(this.arr.size() - 1)); // remove last ele and put it on top
            downheapify(0); // call downheapify on top ele now.
        }

        return remEle;
    }

    public String toString() {
        return this.arr.toString();
    }
}
