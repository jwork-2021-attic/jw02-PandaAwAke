package src;

import java.util.ArrayList;
import java.util.LinkedList;

public class RadixSorter implements Sorter {

    private int[] a;
    private ArrayList<LinkedList<Integer>> buckets;
    static private final int RADIX_NUMBER = 8;

    private String plan = "";

    @Override
    public void load(int[] a) {
        this.a = a;
    }

    @Override
    public void sort() {
        int radix = 1;

        String lastSequence = GetSequenceString();

        for (int i = 0; i < RADIX_NUMBER; i++) { // 总共进行 RADIX_NUMBER 位的排序
            buckets = new ArrayList<LinkedList<Integer>>();
            int index = 0;
            boolean first = true;
            String nowSequence = "";

            for (int j = 0; j < 10; j++)
                buckets.add(new LinkedList<Integer>());
            for (Integer element : a)
                buckets.get(element / radix % 10).offer(element);
            for (int j = 0; j < 10; j++) {
                LinkedList<Integer> q = buckets.get(j);
                for (int element : q) {
                    a[index++] = element;
                    if (first) {
                        first = false;
                        nowSequence += "" + element;
                    } else {
                        nowSequence += " " + element;
                    }
                }
            }

            plan += lastSequence + "<->" + nowSequence + "\n";
            lastSequence = nowSequence;
            radix *= 10;
        }

    }

    @Override
    public String getPlan() {
        return this.plan;
    }

    @Override
    public boolean isSwapBased() {
        return false;
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
