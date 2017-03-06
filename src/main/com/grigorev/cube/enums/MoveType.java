package main.com.grigorev.cube.enums;

/**
 * All possible cube moves
 */
public enum MoveType {
    U, D, R, L, F, B, // One layer
    Uw, Dw, Rw, Lw, Fw, Bw, // Two layers
    M, S, E, // Middle layers
    x, y, z //  Whole cube
}
