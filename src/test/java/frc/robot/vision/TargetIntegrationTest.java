// package frc.robot.vision;

// import org.junit.BeforeClass;
// import org.junit.Test;
// import org.opencv.core.Core;
// import org.opencv.core.Mat;

// import static org.junit.Assert.assertEquals;
// import static org.opencv.imgcodecs.Imgcodecs.imread;

// public class TargetIntegrationTest {

//     @BeforeClass
//     public static void setUp() {
//         try {
//             System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//         } catch (UnsatisfiedLinkError e) {
//             System.out.println(e.getMessage());
//         }
//     }

//     private static void assertDistanceHeadingAndPlane(String imagePath, double distance, double heading, double planeAngle) {
//         Mat image = imread(imagePath);
//         var pairs = TapePairRecognizer.recognize(image);
//         assertEquals(1, pairs.size());
//         var target = new TargetInfo(pairs.get(0));
//         assertEquals(distance, target.distanceToTarget(), 20.0);
//         assertEquals(heading, target.headingOfTarget(), 5.0);
//     }

//     @Test 
//     public void zeroHeading() {
//         assertDistanceHeadingAndPlane("src/test/resources/0heading.jpg", 45, 0, 0);
//     }

//     @Test 
//     public void twentyTwoHeading() {
//         assertDistanceHeadingAndPlane("src/test/resources/22heading.jpg", 45, 22, 0);
//     }

//     @Test
//     public void zeroDegreesHeading() {
//         assertDistanceHeadingAndPlane("src/test/resources/0deg36in.jpg", 36, 7, 0);
//     }

//     @Test
//     public void zeroDegreesHeadingFar() {
//         assertDistanceHeadingAndPlane("src/test/resources/0deg100in.jpg", 100, 0, 0);
//     }

//     @Test
//     public void smallPlaneAngleClose() {
//         assertDistanceHeadingAndPlane("src/test/resources/22deg36in.jpg", 36, 7, 0);
//     }

//     @Test
//     public void smallPlaneAngleFar() {
//         assertDistanceHeadingAndPlane("src/test/resources/22deg100in.jpg", 100, 8, 22);
//     }

//     @Test
//     public void mediumPlaneAngleClose() {
//         assertDistanceHeadingAndPlane("src/test/resources/45deg36in.jpg", 36, 8, 45);
//     }

//     @Test
//     public void mediumPlaneAngleFar() {
//         assertDistanceHeadingAndPlane("src/test/resources/45deg100in.jpg", 100, 0, 45);
//     }

//     @Test
//     public void zeroDegHeading() {
//         assertDistanceHeadingAndPlane("src/test/resources/0heading.jpg", 68, 0, 0);
//     }

//     @Test
//     public void twelveDegreesHeading() {
//         assertDistanceHeadingAndPlane("src/test/resources/12heading.jpg", 70, 12, 0);
//     }

//     @Test
//     public void twentyTwoDegHeading() {
//         assertDistanceHeadingAndPlane("src/test/resources/22heading.jpg", 81, 22, 0);
//     }




// }
