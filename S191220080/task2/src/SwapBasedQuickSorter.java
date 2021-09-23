package src;

import java.util.Random;

public class SwapBasedQuickSorter implements Sorter {

    private int[] a;
    static private final Random random = new Random();

    private String plan = "";

    @Override
    public void load(int[] a) {
        this.a = a;
    }

    @Override
    public void sort() {
        sort(0, a.length - 1);
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

    @Override
    public boolean isSwapBased() {
        return true;
    }

    private void sort(int left, int right) {
        if (left < right) {
            int i = partition(left, right);
            sort(left, i - 1);
            sort(i + 1, right);
        }
    }

    private int partition(int left, int right) {
        int keyIndex = left + random.nextInt(right - left + 1);
        int key = a[keyIndex];
        swap(left, keyIndex);
        int p = left, q = left + 1;
        for (; q <= right; q++) {
            if (a[q] <= key) {
                p++;
                swap(p, q);
            }
        }
        swap(left, p);
        return p;
    }

    private void swap(int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

}
