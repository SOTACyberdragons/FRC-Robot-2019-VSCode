package frc.robot.subsystems;

import org.junit.BeforeClass;
import org.junit.Test;
import frc.robot.subsystems.Arm;

import static org.junit.Assert.assertEquals;
import static org.opencv.imgcodecs.Imgcodecs.imread;

public class ArmTest {

    // @BeforeClass
    // public static void setUp() {
    //     try {
    //         System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    //     } catch (UnsatisfiedLinkError e){
    //         System.out.println(e.getMessage());
    //     }
    // }

    private static double result = 0;

    public static void assertGetClosestAngle(double currentAngle, double targetAngle, double closestAngle) {;
        result = Arm.getClosestAngle(currentAngle, targetAngle);
        assertEquals(result, closestAngle);
    }

    @Test
    public void _10To20() {;
        assertGetClosestAngle(10, 20, 20);
        System.out.println("Helooooo!!!@@!@#@@@@" + result);
    }

    @Test
    public void _10To270() {
        assertGetClosestAngle(10, 270, -90);
    }

    @Test
    public void _170to190() {
        assertGetClosestAngle(170, 190, 170);
    }

    @Test
    public void _390to0() {
        assertGetClosestAngle(390, 0, 360);
    }

    @Test
    public void _0to180() {
        assertGetClosestAngle(0, 180, 180);
    }
}
