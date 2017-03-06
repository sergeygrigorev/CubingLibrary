package main.com.grigorev.generators;

import main.com.grigorev.cube.helpers.Algorithm;

import java.util.Random;

/**
 * Created by Odmin on 05.03.2017.
 */
public class WcaScrambler implements IGenerator {

    private Random r = new Random();


    @Override
    public void setSeed(long seed) {
        r = new Random(seed);
    }

    @Override
    public Algorithm generate() {
        return null;
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
