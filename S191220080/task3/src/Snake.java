package src;

import java.util.ArrayList;

public class Snake {

    private static Snake theSnake;
    private Sorter sorter;

    public static Snake getTheSnake() {
        if (theSnake == null) {
            theSnake = new Snake();
        }
        return theSnake;
    }

    private Snake() {

    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public String lineUp(Matrix matrix) {
        StringBuffer log = new StringBuffer();
        if (sorter == null) {
            return null;
        }

        Linable[] linables = matrix.toArray();
        int[] ranks = new int[linables.length];

        for (int i = 0; i < linables.length; i++) {
            ranks[i] = linables[i].getValue();
        }

        sorter.load(ranks);
        sorter.sort();

        String[] sortSteps = PlanParser.parsePlan(sorter.getPlan());

        for (String step : sortSteps) {
            this.execute(step);
            System.out.println(matrix.toString());
            log.append(matrix.toString());
            log.append("\n[frame]\n");
        }
        return log.toString();
    }

    private void execute(String step) {
        if (sorter.isSwapBased()) {
            int[] couple = PlanParser.parseSwapStep(step);
            LittleDevil.getLittleDevilByRank(couple[0]).swapPosition(LittleDevil.getLittleDevilByRank(couple[1]));
        } else {
            ArrayList<ArrayList<Integer>> commonStep = PlanParser.parseCommonStep(step);
            LittleDevil.setPositionsByRanks(commonStep.get(0), commonStep.get(1));
        }
    }

}
