package main.com.grigorev.generators;

import main.com.grigorev.cube.enums.MoveDirection;
import main.com.grigorev.cube.enums.MoveType;
import main.com.grigorev.cube.helpers.Algorithm;
import main.com.grigorev.cube.helpers.Move;

import java.util.Random;

/**
 * Created by Odmin on 05.03.2017.
 */
public class WcaScrambler implements IGenerator {

    private Random r = new Random();
    private long seed;
    public static final int WCA_SCRAMBLE_LENGTH = 25;

    public WcaScrambler() {
        seed = r.nextLong();
        r.setSeed(seed);
    }

    public WcaScrambler(long seed) {
        this.seed = seed;
        r.setSeed(seed);
    }

    @Override
    public void setSeed(long seed) {
        this.seed = seed;
        r.setSeed(seed);
    }

    @Override
    public long getSeed() {
        return seed;
    }

    @Override
    public Algorithm generate() {
        Algorithm a = new Algorithm();
        while (a.length() < WCA_SCRAMBLE_LENGTH)
            a.add(new Move(MoveType.values()[r.nextInt(6)], MoveDirection.values()[1 + r.nextInt(3)]));
        return a;
    }

    @Override
    public Algorithm[] generate(int count) throws IllegalArgumentException {
        if (count < 1)
            throw new IllegalArgumentException("Count must be positive integer!");
        Algorithm[] arr = new Algorithm[count];
        for (int i = 0; i < count; i++)
            arr[i] = generate();
        return arr;
    }
}
