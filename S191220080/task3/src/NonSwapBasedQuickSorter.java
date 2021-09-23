package src;

import java.util.ArrayList;
import java.util.Random;

public class NonSwapBasedQuickSorter implements Sorter {

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
        return false;
    }

    private void sort(int left, int right) {
        if (left < right) {
            String lastSequence = GetSequenceString();
            String nowSequence = "";

            int i = partition(left, right);

            sort(left, i - 1);
            nowSequence = GetSequenceString();
            plan += lastSequence + "<->" + nowSequence + "\n";
            lastSequence = nowSequence;

            sort(i + 1, right);
            nowSequence = GetSequenceString();
            plan += lastSequence + "<->" + nowSequence + "\n";
        }
    }

    private int partition(int left, int right) {
        int keyIndex = left + random.nextInt(right - left + 1);
        int key = a[keyIndex];
        int keyIndexInBuffer = 0;
        ArrayList<Integer> buffer = new ArrayList<Integer>();
        for (int i = left; i <= right; i++) {
            if (i == keyIndex)
                keyIndexInBuffer = buffer.size();
            if (a[i] <= key)
                buffer.add(a[i]);
        }
        int pos = buffer.size() - 1;
        for (int i = left; i <= right; i++) {
            if (a[i] > key)
                buffer.add(a[i]);
        }
        buffer.set(keyIndexInBuffer, buffer.get(pos));
        buffer.set(pos, key);
        for (int i = left; i <= right; i++) {
            a[i] = buffer.get(i - left);
        }
        return left + pos;
    }

    private String GetSequenceString() {
        String result = "";
        for (int i = 0; i < a.length; i++)
            if (i == 0)
                result += "" + a[i];
            else
                result += " " + a[i];
        return result;
    }

}
