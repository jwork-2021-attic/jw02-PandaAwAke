package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import src.Line.Position;

public class LittleDevil implements Linable {
    private final int r;
    private final int g;
    private final int b;
    private Position position;

    public static LittleDevil[] littleDevils;
    public static final int LITTLEDEVILS = 64;

    static {
        // 随机去重生成
        HashSet<Integer> colors = new HashSet<Integer>();
        Random random = new Random();
        littleDevils = new LittleDevil[LITTLEDEVILS];
        for (int i = 0; i < LITTLEDEVILS; i++) {
            int r, g, b;
            while (true) {
                r = random.nextInt(256);
                g = random.nextInt(256);
                b = random.nextInt(256);
                int value = r * 256 * 256 + g * 256 + b;
                if (!colors.contains(value)) {
                    colors.add(value);
                    break;
                }
            }
            littleDevils[i] = new LittleDevil(r, g, b);
        }
    }

    public static LittleDevil getLittleDevilByRank(int rank) {
        for (LittleDevil littleDevil : littleDevils) {
            if (littleDevil.rank() == rank)
                return littleDevil;
        }
        return null;
    }

    public static void setPositionsByRanks(ArrayList<Integer> ranksBefore, ArrayList<Integer> ranksAfter) {
        for (int i = 0; i < ranksBefore.size(); i++) {
            if (!(ranksBefore.get(i).equals(ranksAfter.get(i)))) {
                int index_swap = -1;
                for (int j = i + 1; j < ranksBefore.size(); j++) {
                    if (ranksBefore.get(j).equals(ranksAfter.get(i))) {
                        index_swap = j;
                        break;
                    }
                }
                assert index_swap > -1;
                getLittleDevilByRank(ranksBefore.get(i))
                        .swapPosition(getLittleDevilByRank(ranksBefore.get(index_swap)));
                int t = ranksBefore.get(i);
                ranksBefore.set(i, ranksBefore.get(index_swap));
                ranksBefore.set(index_swap, t);
            }
        }
    }

    LittleDevil(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int rank() {
        return this.r * 256 * 256 + this.g * 256 + this.b;
    }

    @Override
    public String toString() {
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m " + this.rank() + " \033[0m";
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public void swapPosition(LittleDevil another) {
        Position p = another.position;
        this.position.setLinable(another);
        p.setLinable(this);
    }

    @Override
    public int getValue() {
        return this.rank();
    }

}