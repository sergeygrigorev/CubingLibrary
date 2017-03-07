package main.com.grigorev;

import main.com.grigorev.cube.enums.CornerPosition;
import main.com.grigorev.cube.enums.CornerSticker;
import main.com.grigorev.cube.enums.EdgeSticker;
import main.com.grigorev.cube.helpers.Algorithm;
import main.com.grigorev.cube.models.CubieCube;
import main.com.grigorev.generators.WcaScrambler;
import main.com.grigorev.solvers.BlindChainSolver;
import main.com.grigorev.writers.BlindSolveComposer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //CubieCube c = new CubieCube(new Algorithm("R2 U R U R' U' R' U' R' U R'"));
        WcaScrambler scr = new WcaScrambler();
        scr.setSeed(123);
        Algorithm a = scr.generate();
        System.out.println(a);
        System.out.println();

        CubieCube c = new CubieCube(a);
        System.out.println(c);
        System.out.println();

        BlindChainSolver bcs = new BlindChainSolver(c);

        BlindSolveComposer comp = new BlindSolveComposer();
        String res = comp.compose(bcs);
        System.out.println(res);
    }
}
