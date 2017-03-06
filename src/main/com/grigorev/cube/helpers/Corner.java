package main.com.grigorev.cube.helpers;

import main.com.grigorev.cube.enums.CornerFlip;
import main.com.grigorev.cube.enums.CornerPosition;

/**
 * Created by Odmin on 05.03.2017.
 */
public class Corner {
    private final CornerPosition position;
    private CornerFlip flip;
    private boolean anyPos = false;
    private boolean anyFlip = false;

    public Corner(CornerPosition position, CornerFlip flip) {
        this.position = position;
        this.flip = flip;
    }

    public CornerPosition getPosition(){
        return position;
    }

    public CornerFlip getFlip(){
        return flip;
    }

    public void flip(CornerFlip f) {
        flip = CornerFlip.values()[(flip.ordinal() + f.ordinal()) % 3];
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
        if (!(other instanceof Corner))
            return false;
        Corner o = (Corner) other;
        return position == o.position && flip == o.flip;
    }
}
