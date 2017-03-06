package main.com.grigorev;

import main.com.grigorev.cube.enums.CornerPosition;
import main.com.grigorev.cube.helpers.Algorithm;
import main.com.grigorev.cube.models.CubieCube;

public class Main {

    public static void main(String[] args) {
        //CubieCube c = new CubieCube(new Algorithm("R2 U R U R' U' R' U' R' U R'"));
        Algorithm a = new Algorithm("R U R' U' R' F R F'");
        Algorithm b = new Algorithm("F R U' R' U' R U R' F'");
        Algorithm ab = new Algorithm(a);
        Algorithm ba = new Algorithm(b);
        ab.add(b);
        ba.add(a);

        CubieCube c = new CubieCube();
        c.apply(a);
        System.out.println(c);
    }
}
