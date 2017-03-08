package tests.cube;

import com.siyeh.ig.junit.AssertEqualsBetweenInconvertibleTypesInspection;
import main.com.grigorev.cube.helpers.Algorithm;
import main.com.grigorev.cube.helpers.Move;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Odmin on 05.03.2017.
 */
public class AlgorithmTest {

    @Test
    public void Test() {
        Algorithm act = new Algorithm("R U R' U' R R U U' F F2 B F' F' F' x' y' y2 Rw2 Rw'");
        Algorithm exp = new Algorithm("R U R' U' R2 F' B F x' y Rw");
        assertEquals("Incorrect optimize algorithm", exp, act);

        act = act.subAlgorithm(2, 4);
        exp = new Algorithm("R' U'");
        assertEquals("Incorrect sub algorithm", exp, act);

        act.add(new Algorithm("F B x"));
        exp = new Algorithm("R' U' F B x");
        assertEquals("Incorrect add algorithm", exp, act);
    }
    @Test
    public void Test2(){
        Algorithm act = new Algorithm("D2 B' U2 R2 L R2 L F' B U R' B2 D' R2 F R' B U2 D R' F2 U D R2 B2");
        Algorithm exp = new Algorithm("D2 B' U2 L2 F' B U R' B2 D' R2 F R' B U2 D R' F2 U D R2 B2");
        assertEquals("Incorrect optimize algorithm", exp, act);
    }
}
