package main.com.grigorev.writers;

import main.com.grigorev.cube.enums.CornerSticker;
import main.com.grigorev.cube.enums.EdgeSticker;
import main.com.grigorev.solvers.BlindChainSolver;

import java.util.List;

/**
 * Created by Odmin on 07.03.2017.
 */
public class BlindSolveComposer {
    public String compose(BlindChainSolver bcs) {
        StringBuilder s = new StringBuilder();

        List<CornerSticker> cp = bcs.getCornerChain();
        List<EdgeSticker> ep = bcs.getEdgeChain();
        CornerSticker[] cf = bcs.getCornerFlips();
        EdgeSticker[] ef = bcs.getEdgeFlips();

        int pair = 0;

        s.append("Corners:\n");
        for (int i = 0; i < cp.size(); i++) {
            s.append(cp.get(i));
            s.append(" ");
            if (i % 2 == 1 && i < cp.size() - 1)
                s.append("| ");
        }
        s.append("\n");

        s.append("Corner flips:\n");
        for (CornerSticker cs : cf) {
            s.append(cs);
            s.append(" ");
        }
        s.append("\n");

        s.append("Edges:\n");
        for (int i = 0; i < ep.size(); i++) {
            s.append(ep.get(i));
            s.append(" ");
            if (i % 2 == 1 && i < ep.size() - 1)
                s.append("| ");
        }
        s.append("\n");

        s.append("Edge flips:\n");
        for (EdgeSticker es : ef) {
            s.append(es);
            s.append(" ");
        }

        return s.toString();
    }
}
