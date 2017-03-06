package main.com.grigorev.cube.helpers;

import main.com.grigorev.cube.enums.EdgeFlip;
import main.com.grigorev.cube.enums.EdgePosition;

/**
 * Created by Odmin on 05.03.2017.
 */
public class Edge {
    private final EdgePosition position;
    private EdgeFlip flip;
    private boolean anyPos = false;
    private boolean anyFlip = false;

    public Edge(EdgePosition position, EdgeFlip flip) {
        this.position = position;
        this.flip = flip;
    }

    public EdgePosition getPosition(){
        return position;
    }

    public EdgeFlip getFlip(){
        return flip;
    }

    public void flip(EdgeFlip f){
        flip = flip == f ? EdgeFlip.NM : EdgeFlip.FL;
    }

    public boolean isAnyPos() {
        return anyPos;
    }

    public void setAnyPos(boolean any) {
        this.anyPos = any;
    }

    public boolean isAnyFlip() {
        return anyFlip;
    }

    public void setAnyFlip(boolean any) {
        this.anyFlip = any;
    }

    @Override
    public String toString() {
        return "[" + position.toString() + " " + flip.toString() + "]";
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Edge))
            return false;
        Edge o = (Edge) other;
        return position == o.position && flip == o.flip;
    }
}
