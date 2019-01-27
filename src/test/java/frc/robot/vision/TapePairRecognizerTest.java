package frc.robot.vision;

import org.junit.BeforeClass;
import org.junit.Test;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import static org.junit.Assert.assertEquals;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class TapePairRecognizerTest {

    @BeforeClass
    public static void setUp() {
        try {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        } catch (UnsatisfiedLinkError e){
            System.out.println(e.getMessage());
        }
    }

    private void assertNumberOfTargets(String path, int numberOfTargets) {
        Mat image = imread(path);
        var pairs = TapePairRecognizer.recognize(image);
        assertEquals(numberOfTargets, pairs.size());
    }

    @Test
    public void twoTargetsClose() {
        assertNumberOfTargets("src/test/resources/CargoAngledDark48in.jpg", 2);
    }

    @Test
    public void twoTargetsFar() {
        assertNumberOfTargets("src/test/resources/CargoStraightDark90in.jpg", 2);
    }

    @Test
    public void threeRectanglesOneTarget() {
        assertNumberOfTargets("src/test/resources/three_pieces.jpg", 1);
    }

    @Test
    public void twoAndAHalfRectanglesOneTarget() {
        assertNumberOfTargets("src/test/resources/two_and_a_half.jpg", 1);
    }

    @Test
    public void innerRectanglesNoTarget() {
        assertNumberOfTargets("src/test/resources/inner_two.jpg", 0);
    }
}

