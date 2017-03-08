package main.com.grigorev.cube.models;

import main.com.grigorev.cube.enums.*;

import static main.com.grigorev.cube.enums.CornerPosition.*;
import static main.com.grigorev.cube.enums.EdgePosition.*;

import main.com.grigorev.cube.helpers.Algorithm;
import main.com.grigorev.cube.helpers.Corner;
import main.com.grigorev.cube.helpers.Edge;
import main.com.grigorev.cube.helpers.Move;
import main.com.grigorev.generators.IGenerator;

/**
 *
 */
public class CubieCube implements ICube {

    // Constants
    public static final int CORNERS_COUNT = 8;
    public static final int EDGES_COUNT = 12;
    private static final int CORNER_FLIPS = 3;
    private static final int EDGE_FLIPS = 2;

    // Model
    private final Corner[] corners = new Corner[CORNERS_COUNT];
    private final Edge[] edges = new Edge[EDGES_COUNT];

    // Statistics
    private final Algorithm applied = new Algorithm();

    // Moves
    private static CornerPosition[] cpU = {UBR, URF, UFL, ULB, DFR, DLF, DBL, DRB};
    private static byte[] coU = {0, 0, 0, 0, 0, 0, 0, 0};
    private static EdgePosition[] epU = {UB, UR, UF, UL, DR, DF, DL, DB, FR, FL, BL, BR};
    private static byte[] eoU = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static CornerPosition[] cpD = {URF, UFL, ULB, UBR, DLF, DBL, DRB, DFR};
    private static byte[] coD = {0, 0, 0, 0, 0, 0, 0, 0};
    private static EdgePosition[] epD = {UR, UF, UL, UB, DF, DL, DB, DR, FR, FL, BL, BR};
    private static byte[] eoD = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static CornerPosition[] cpR = {DFR, UFL, ULB, URF, DRB, DLF, DBL, UBR};
    private static byte[] coR = {2, 0, 0, 1, 1, 0, 0, 2};
    private static EdgePosition[] epR = {FR, UF, UL, UB, BR, DF, DL, DB, DR, FL, BL, UR};
    private static byte[] eoR = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static CornerPosition[] cpL = {URF, ULB, DBL, UBR, DFR, UFL, DLF, DRB};
    private static byte[] coL = {0, 1, 2, 0, 0, 2, 1, 0};
    private static EdgePosition[] epL = {UR, UF, BL, UB, DR, DF, FL, DB, FR, UL, DL, BR};
    private static byte[] eoL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private static CornerPosition[] cpF = {UFL, DLF, ULB, UBR, URF, DFR, DBL, DRB};
    private static byte[] coF = {1, 2, 0, 0, 2, 1, 0, 0};
    private static EdgePosition[] epF = {UR, FL, UL, UB, DR, FR, DL, DB, UF, DF, BL, BR};
    private static byte[] eoF = {0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0};

    private static CornerPosition[] cpB = {URF, UFL, UBR, DRB, DFR, DLF, ULB, DBL};
    private static byte[] coB = {0, 0, 1, 2, 0, 0, 2, 1};
    private static EdgePosition[] epB = {UR, UF, UL, BR, DR, DF, DL, BL, FR, FL, UB, DB};
    private static byte[] eoB = {0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1};

    private static CubieCube[] moves = new CubieCube[MoveType.values().length];

    static {
        moves[MoveType.U.ordinal()] = new CubieCube(cpU, coU, epU, eoU);
        moves[MoveType.D.ordinal()] = new CubieCube(cpD, coD, epD, eoD);
        moves[MoveType.R.ordinal()] = new CubieCube(cpR, coR, epR, eoR);
        moves[MoveType.L.ordinal()] = new CubieCube(cpL, coL, epL, eoL);
        moves[MoveType.F.ordinal()] = new CubieCube(cpF, coF, epF, eoF);
        moves[MoveType.B.ordinal()] = new CubieCube(cpB, coB, epB, eoB);


    }

    public static CubieCube multiply(CubieCube a, CubieCube b) {
        CubieCube res = new CubieCube(a);
        res.multiply(b);
        return res;
    }

    /**
     * Checks if the current state is valid
     *
     * @return validness of the current state
     */
    private boolean isValid() {
        return true;
    }

    // TODO decide wheither we need to save pieces after setSolvedState
    private void initialize() {
        for (int i = 0; i < CORNERS_COUNT; i++)
            corners[i] = new Corner(CornerPosition.values()[i], CornerFlip.NRM);
        for (int i = 0; i < EDGES_COUNT; i++)
            edges[i] = new Edge(EdgePosition.values()[i], EdgeFlip.NM);
    }

    private void multiply(CubieCube other) {
        Corner[] newCorners = new Corner[CORNERS_COUNT];
        Edge[] newEdges = new Edge[EDGES_COUNT];

        for (CornerPosition position : CornerPosition.values()) {
            int destination = position.ordinal();
            Corner c = other.corners[destination];
            CornerPosition p = c.getPosition();
            int source = p.ordinal();
            CornerFlip f = c.getFlip();
            newCorners[destination] = corners[source];
        }
        for (EdgePosition position : EdgePosition.values()) {
            int destination = position.ordinal();
            Edge c = other.edges[destination];
            EdgePosition p = c.getPosition();
            int source = p.ordinal();
            EdgeFlip f = c.getFlip();
            newEdges[destination] = edges[source];
        }

        for (int i = 0; i < CORNERS_COUNT; i++) {
            corners[i] = newCorners[i];
            corners[i].flip(other.corners[i].getFlip());
        }
        for (int i = 0; i < EDGES_COUNT; i++) {
            edges[i] = newEdges[i];
            edges[i].flip(other.edges[i].getFlip());
        }

        applied.add(other.applied);
    }

    public CubieCube() {
        initialize();
    }

    public CubieCube(CornerPosition[] cp, byte[] co, EdgePosition[] ep, byte[] eo) {
        for (int i = 0; i < CORNERS_COUNT; i++)
            corners[i] = new Corner(cp[i], CornerFlip.values()[co[i]]);
        for (int i = 0; i < EDGES_COUNT; i++)
            edges[i] = new Edge(ep[i], EdgeFlip.values()[eo[i]]);
        if (!isValid())
            throw new IllegalStateException("Illegal cube state given to constructor.");
    }

    public CubieCube(Algorithm scramble) {
        this();
        apply(scramble);
    }

    public CubieCube(IGenerator generator) {
        this(generator.generate());
    }

    public CubieCube(CubieCube c) {
        for (int i = 0; i < CORNERS_COUNT; i++)
            corners[i] = new Corner(c.corners[i].getPosition(), c.corners[i].getFlip());
        for (int i = 0; i < EDGES_COUNT; i++)
            edges[i] = new Edge(c.edges[i].getPosition(), c.edges[i].getFlip());
    }

    @Override
    public void rotate(Move move) {
        applied.add(move);
        CubieCube moveCube = moves[move.type.ordinal()];
        for (int i = 0; i < move.direction.ordinal(); i++)
            multiply(moveCube);
    }

    @Override
    public void apply(Algorithm algo) {
        for (int i = 0; i < algo.length(); i++)
            rotate(algo.get(i));
    }

    @Override
    public boolean isSolved() {
        for (int i = 0; i < CORNERS_COUNT; i++) {
            Corner c = corners[i];
            CornerPosition p = c.getPosition();
            CornerFlip f = c.getFlip();
            if (!c.isAnyPos() && p != CornerPosition.values()[i])
                return false;
            if (!c.isAnyFlip() && f != CornerFlip.NRM)
                return false;
        }

        for (int i = 0; i < EDGES_COUNT; i++) {
            Edge e = edges[i];
            EdgePosition p = e.getPosition();
            EdgeFlip f = e.getFlip();
            if (!e.isAnyPos() && p != EdgePosition.values()[i])
                return false;
            if (!e.isAnyFlip() && f != EdgeFlip.NM)
                return false;
        }

        return true;
    }

    @Override
    public void setSolvedState() {
        initialize();
        applied.clear();
    }

    @Override
    public Corner getCorner(CornerPosition position) {
        return corners[position.ordinal()];
    }


    public Corner getCorner(int position) {
        return corners[position];
    }

    @Override
    public Edge getEdge(EdgePosition position) {
        return edges[position.ordinal()];
    }


    public Edge getEdge(int position) {
        return edges[position];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (CornerPosition c : CornerPosition.values())
            if(!(corners[c.ordinal()].getPosition() == c && corners[c.ordinal()].getFlip() == CornerFlip.NRM))
                s.append(c.toString()).append(" - ").append(corners[c.ordinal()]).append("\n");
        for (EdgePosition e : EdgePosition.values())
            if (!(edges[e.ordinal()].getPosition() == e && edges[e.ordinal()].getFlip() == EdgeFlip.NM))
                s.append(e.toString()).append(" - ").append(edges[e.ordinal()]).append("\n");
        return s.length() > 0 ? s.toString().trim() : "Solved cube";
    }
}
