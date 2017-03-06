package main.com.grigorev.cube.helpers;

import main.com.grigorev.cube.enums.MoveDirection;
import main.com.grigorev.cube.enums.MoveType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Odmin on 05.03.2017.
 */

// TODO implements Iterable<T>
public class Algorithm {
    private List<Move> algo = new ArrayList<Move>();


    public Algorithm() {

    }

    public Algorithm(String s) throws IllegalArgumentException {
        String[] parts = s.split(" ");
        algo.addAll(Arrays.stream(parts).map(Move::new).collect(Collectors.toList()));
        optimize();
    }

    public Algorithm(MoveType moveType, MoveDirection moveDirection) {
        algo.add(new Move(moveType, moveDirection));
        optimize();
    }

    public Algorithm(Move move) {
        algo.add(move);
        optimize();
    }

    public Algorithm(List<Move> moves) {
        algo.addAll(moves);
        optimize();
    }

    public Algorithm(Algorithm other) {
        algo.addAll(other.algo);
        optimize();
    }

    private void optimize() {
        for (int i = 0; i < algo.size(); i++) {
            if (algo.get(i).direction == MoveDirection.None) {
                algo.remove(i);
                i--;
            }
        }
        for (int i = 0; i < algo.size() - 1; i++) {
            if (algo.get(i).type != algo.get(i + 1).type)
                continue;
            Move newMove = algo.get(i).add(algo.get(i + 1));
            algo.remove(i);
            algo.remove(i);
            i--;
            if (newMove.isNull()) {
                if (i >= 0)
                    i--;
                continue;
            }
            algo.add(i + 1, newMove);
        }
    }

    public void add(MoveType moveType, MoveDirection moveDirection) {
        algo.add(new Move(moveType, moveDirection));
        optimize();
    }

    public void add(Move move) {
        algo.add(move);
        optimize();
    }

    public void add(Algorithm other) {
        algo.addAll(other.algo);
        optimize();
    }

    public void reverse() {
        int s = algo.size();
        for (int i = 0; i < s / 2; i++) {
            Move tmp = algo.get(i);
            algo.set(i, algo.get(s - i - 1));
            algo.set(s - i - 1, tmp);
        }
        for (int i = 0; i < s; i++) {
            int d = algo.get(i).direction.ordinal();
            MoveType t = algo.get(i).type;
            algo.set(i, new Move(t, MoveDirection.values()[(4 - d) % 4]));
        }
    }

    public Algorithm reversed() {
        Algorithm res = new Algorithm(this);
        res.reverse();
        return res;
    }

    public int length() {
        return algo.size();
    }

    public Move get(int index) {
        return algo.get(index);
    }

    public void clear() {
        algo.clear();
    }

    public Algorithm subAlgorithm(int start, int end) {
        return new Algorithm(algo.subList(start, end));
    }

    @Override
    public String toString() {
        if (algo.size() == 0)
            return "Empty algorythm";
        return String.join(" ", algo.stream().map(Move::toString).collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Algorithm))
            return false;
        Algorithm o = (Algorithm) other;
        if (algo.size() != o.algo.size())
            return false;
        for (int i = 0; i < algo.size(); i++)
            if (!algo.get(i).equals(o.algo.get(i)))
                return false;
        return true;
    }
}
