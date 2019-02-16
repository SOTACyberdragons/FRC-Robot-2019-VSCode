// package frc.robot.vision;

// import org.junit.BeforeClass;
// import org.junit.Test;
// import org.opencv.core.Core;
// import org.opencv.core.Mat;

// import static org.junit.Assert.assertEquals;
// import static org.opencv.imgcodecs.Imgcodecs.imread;

// public class PlaneAngleTest {

//     @BeforeClass
//     public static void setUp() {
//         try {
//             System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//         } catch (UnsatisfiedLinkError e) {
//             System.out.println(e.getMessage());
//         }
//     }

//     private static void assertDistanceHeadingAndPlane(String imagePath, double planeAngle) {
//         Mat image = imread(""+imagePath);
//         var pairs = TapePairRecognizer.recognize(image);
//         assertEquals(1, pairs.size());
//         var target = new TargetInfo(pairs.get(0));
//         assertEquals(planeAngle, target.planeAngle(), 1);
//     }

//     @Test
//     public void side1() {
//         assertDistanceHeadingAndPlane("src/test/resources/sequential_stills/side_1_1.png", 20);
//         assertDistanceHeadingAndPlane("src/test/resources/sequential_stills/side_1_2.png", 14);
//         assertDistanceHeadingAndPlane("src/test/resources/sequential_stills/side_1_3.png", 14);
//         assertDistanceHeadingAndPlane("src/test/resources/sequential_stills/side_1_4.png", 20);
//         assertDistanceHeadingAndPlane("src/test/resources/sequential_stills/side_1_5.png", 8);
//         assertDistanceHeadingAndPlane("src/test/resources/sequential_stills/side_1_6.png", 18);
//         assertDistanceHeadingAndPlane("src/test/resources/sequential_stills/side_2_1.png", -42);
//         assertDistanceHeadingAndPlane("src/test/resources/sequential_stills/side_2_4.png", -54);
//     }
// }
