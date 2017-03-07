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
    public static final int WCA_SCRAMBLE_LENGTH = 25;

    @Override
    public void setSeed(long seed) {
        r = new Random(seed);
    }

    @Override
    public Algorithm generate() {
        Algorithm a = new Algorithm();
        Move prev = null;
        for (int i = 0; i < WCA_SCRAMBLE_LENGTH; i++) {
            Move curr = new Move(MoveType.values()[r.nextInt(6)], MoveDirection.values()[1 + r.nextInt(3)]);
            if (prev != null && prev.type == curr.type) {
                i--;
                continue;
            }
            prev = curr;
            a.add(curr);
        }
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
