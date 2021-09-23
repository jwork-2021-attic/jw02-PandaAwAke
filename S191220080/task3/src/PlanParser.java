package src;

import java.util.ArrayList;

public class PlanParser {
    public static String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    // Return a couple of numbers, indicating which two ranks are swapped.
    public static int[] parseSwapStep(String step) {
        String[] couple = step.split("<->");
        assert couple.length > 1;
        int[] result = new int[2];
        result[0] = Integer.parseInt(couple[0]);
        result[1] = Integer.parseInt(couple[1]);
        return result;
    }

    // Return new ranks
    public static ArrayList<ArrayList<Integer>> parseCommonStep(String step) {
        String[] couple = step.split("<->");
        String[] numbersBefore = couple[0].split(" ");
        String[] numbersAfter = couple[1].split(" ");
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>(2);
        result.add(new ArrayList<Integer>(numbersBefore.length));
        result.add(new ArrayList<Integer>(numbersAfter.length));
        for (int i = 0; i < numbersBefore.length; i++) {
            result.get(0).add(Integer.parseInt(numbersBefore[i]));
            result.get(1).add(Integer.parseInt(numbersAfter[i]));
        }
        return result;
    }
}
