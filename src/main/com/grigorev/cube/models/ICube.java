package main.com.grigorev.cube.models;

import main.com.grigorev.cube.enums.CornerPosition;
import main.com.grigorev.cube.enums.EdgePosition;
import main.com.grigorev.cube.helpers.Algorithm;
import main.com.grigorev.cube.helpers.Corner;
import main.com.grigorev.cube.helpers.Edge;
import main.com.grigorev.cube.helpers.Move;

/**
 * Interface that contains all general cube operations
 */
public interface ICube {
    void rotate(Move move);

    void apply(Algorithm algo);

    boolean isSolved();

    void setSolvedState();

    Corner getCorner(CornerPosition position);

    Edge getEdge(EdgePosition position);



}
