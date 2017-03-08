package main.com.grigorev.generators;

import main.com.grigorev.cube.helpers.Algorithm;

/**
 * Interface representing scramble generator
 */
public interface IGenerator {
    void setSeed(long seed);

    long getSeed();

    Algorithm generate();

    Algorithm[] generate(int count);
}
