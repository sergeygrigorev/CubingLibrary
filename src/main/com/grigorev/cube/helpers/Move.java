package main.com.grigorev.cube.helpers;

import main.com.grigorev.cube.enums.MoveDirection;
import main.com.grigorev.cube.enums.MoveType;

/**
 * Represents move on cube (face + direction)
 */
public class Move {

    public final MoveType type;
    public final MoveDirection direction;

    public Move(String move) throws IllegalArgumentException {
        if (move.length() == 0)
            throw new IllegalArgumentException("Empty move.");

        if (move.charAt(move.length() - 1) == '\'') {
            direction = MoveDirection.CounterClockWise;
            move = move.substring(0, move.length() - 1);
        } else if (move.charAt(move.length() - 1) == '2') {
            direction = MoveDirection.HalfTurn;
            move = move.substring(0, move.length() - 1);
        } else direction = MoveDirection.ClockWise;

        for (MoveType t: MoveType.values()){
            if (t.toString().equals(move)){
                type = t;
                return;
            }
        }

        throw new IllegalArgumentException("Invalid move.");
    }

    public Move(MoveType moveType, MoveDirection moveDirection) {
        type = moveType;
        direction = moveDirection;
    }

    public boolean isNull() {
        return direction == MoveDirection.None;
    }

    @Override
    public String toString() {
        if (direction == MoveDirection.None)
            return "";
        String res = type.toString();
        if (direction == MoveDirection.HalfTurn)
            res += "2";
        if (direction == MoveDirection.CounterClockWise)
            res += "'";
        return res;
    }

    public Move add(Move other) throws IllegalArgumentException {
        if (type != other.type)
            throw new IllegalArgumentException("Moves must be of one type.");
        return new Move(type, MoveDirection.values()[(direction.ordinal() + other.direction.ordinal()) % 4]);
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Move))
            return false;
        Move o = (Move) other;
        return type == o.type && direction == o.direction;
    }

    public static MoveType[] getSimpleRotations() {
        return new MoveType[]{
                MoveType.U,
                MoveType.D,
                MoveType.R,
                MoveType.L,
                MoveType.F,
                MoveType.B,
        };
    }
}
