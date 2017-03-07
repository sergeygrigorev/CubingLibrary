package main.com.grigorev.solvers;

import main.com.grigorev.cube.enums.*;
import main.com.grigorev.cube.helpers.Corner;
import main.com.grigorev.cube.helpers.Edge;
import main.com.grigorev.cube.models.CubieCube;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Odmin on 06.03.2017.
 */
public class BlindChainSolver {

    private CornerSticker cornerBuffer = CornerSticker.ULB;
    private CornerPosition cornerBufferPosition = CornerPosition.ULB;
    private EdgeSticker edgeBuffer = EdgeSticker.UF;
    private EdgePosition edgeBufferPosition = EdgePosition.UF;

    private CubieCube cube;

    public BlindChainSolver(CubieCube cube) {
        this.cube = cube;
    }

    private CornerPosition findBadCorner(List<Corner> corners) {
        for (int i = 0; i < CubieCube.CORNERS_COUNT; i++)
            if (!corners.get(i).isAnyPos() && corners.get(i).getPosition().ordinal() != i)
                return CornerPosition.values()[i];
        return null;
    }

    private EdgePosition findBadEdge(List<Edge> edges) {
        for (int i = 0; i < CubieCube.EDGES_COUNT; i++)
            if (!edges.get(i).isAnyPos() && edges.get(i).getPosition().ordinal() != i)
                return EdgePosition.values()[i];
        return null;
    }

    public List<CornerSticker> getCornerChain() {
        CornerPosition bad;
        ArrayList<CornerSticker> res = new ArrayList<>();
        List<Corner> corners = IntStream
                .range(0, CubieCube.CORNERS_COUNT)
                .mapToObj(i -> new Corner(cube.getCorner(i).getPosition(), cube.getCorner(i).getFlip()))
                .collect(Collectors.toList());

        while ((bad = findBadCorner(corners)) != null) {
            if (corners.get(cornerBufferPosition.ordinal()).getPosition() != cornerBufferPosition) {
                bad = corners.get(cornerBufferPosition.ordinal()).getPosition();
                Corner tmp = corners.get(bad.ordinal());
                corners.set(bad.ordinal(), corners.get(cornerBufferPosition.ordinal()));
                corners.set(cornerBufferPosition.ordinal(), tmp);
                int x = CornerSticker.valueOf(bad.name()).ordinal();
                int y = corners.get(bad.ordinal()).getFlip().ordinal();
                x += (3 - y) % 3;
                tmp.flip(CornerFlip.values()[y]);
                res.add(CornerSticker.values()[x]);
            } else {
                Corner tmp = corners.get(bad.ordinal());
                corners.set(bad.ordinal(), corners.get(cornerBufferPosition.ordinal()));
                corners.set(cornerBufferPosition.ordinal(), tmp);
                corners.get(bad.ordinal()).setAnyPos(true);

                res.add(CornerSticker.valueOf(bad.name()));
            }
        }
        return res;
    }

    public List<EdgeSticker> getEdgeChain() {
        EdgePosition bad;
        ArrayList<EdgeSticker> res = new ArrayList<>();
        List<Edge> edges = IntStream
                .range(0, CubieCube.EDGES_COUNT)
                .mapToObj(i -> new Edge(cube.getEdge(i).getPosition(), cube.getEdge(i).getFlip()))
                .collect(Collectors.toList());

        while ((bad = findBadEdge(edges)) != null) {
            if (edges.get(edgeBufferPosition.ordinal()).getPosition() != edgeBufferPosition) {
                bad = edges.get(edgeBufferPosition.ordinal()).getPosition();
                Edge tmp = edges.get(bad.ordinal());
                edges.set(bad.ordinal(), edges.get(edgeBufferPosition.ordinal()));
                edges.set(edgeBufferPosition.ordinal(), tmp);
                int x = EdgeSticker.valueOf(bad.name()).ordinal();
                int y = edges.get(bad.ordinal()).getFlip().ordinal();
                x += y;
                tmp.flip(EdgeFlip.values()[y]);
                res.add(EdgeSticker.values()[x]);
            } else {
                Edge tmp = edges.get(bad.ordinal());
                edges.set(bad.ordinal(), edges.get(edgeBufferPosition.ordinal()));
                edges.set(edgeBufferPosition.ordinal(), tmp);
                edges.get(bad.ordinal()).setAnyPos(true);

                res.add(EdgeSticker.valueOf(bad.name()));
            }
        }
        return res;
    }

    public CornerSticker[] getCornerFlips() {
        ArrayList<CornerSticker> res = new ArrayList<CornerSticker>();
        for (CornerPosition p : CornerPosition.values()) {
            Corner c = cube.get(p);
            if (c.getPosition() == p && c.getFlip() != CornerFlip.NRM)
                res.add(CornerSticker.values()[CornerSticker.valueOf(p.name()).ordinal() + c.getFlip().ordinal()]);
        }
        return res.toArray(res.toArray(new CornerSticker[res.size()]));
    }

    public EdgeSticker[] getEdgeFlips() {
        ArrayList<EdgeSticker> res = new ArrayList<EdgeSticker>();
        for (EdgePosition p : EdgePosition.values()) {
            Edge c = cube.get(p);
            if (c.getPosition() == p && c.getFlip() != EdgeFlip.NM)
                res.add(EdgeSticker.values()[EdgeSticker.valueOf(p.name()).ordinal() + c.getFlip().ordinal()]);
        }
        return res.toArray(res.toArray(new EdgeSticker[res.size()]));
    }
}
