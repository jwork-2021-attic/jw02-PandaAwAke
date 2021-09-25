package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Scene {

    public static final int LITTLEDEVILS = 64;

    public static void main(String[] args) throws IOException {

        Matrix matrix = new Matrix(8);
        // 随机初始化序列
        Random random = new Random();
        int[] indices = new int[LITTLEDEVILS];
        for (int i = 0; i < LITTLEDEVILS; i++)
            indices[i] = i;
        for (int i = 0; i < LITTLEDEVILS; i++) {
            int i2 = i + random.nextInt(LITTLEDEVILS - i);
            int t = indices[i];
            indices[i] = indices[i2];
            indices[i2] = t;
        }
        for (int i = 0; i < LITTLEDEVILS; i++)
            matrix.put(LittleDevil.littleDevils[i], indices[i]);

        Snake theSnake = Snake.getTheSnake();

        // Sorter sorter = new BubbleSorter();
        // Sorter sorter = new SwapBasedQuickSorter();
        // Sorter sorter = new NonSwapBasedQuickSorter();
        Sorter sorter = new RadixSorter();

        theSnake.setSorter(sorter);

        String log = theSnake.lineUp(matrix);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }

}
