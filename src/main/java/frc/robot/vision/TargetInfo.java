package frc.robot.vision;

import frc.robot.vision.TapePairRecognizer.TapePair;

public class TargetInfo {

    private static final double SIZE_AT_36IN = 94.283;
    private static final double SIZE_AT_100IN = 35.746;
    public static final double frameHeight = 427, frameWidth = 640, ANGLE_OF_VIEW_DEG = 60,
        ANGLE_OF_VIEW = ANGLE_OF_VIEW_DEG * Math.PI/180;

    public static final double aspectRatio = frameWidth / frameHeight;


    public static final double FOCAL_LENGTH = (93.0 * 36.0) / 5.5;


    private final TapePairRecognizer.TapePair tapePair;
    private static final double ROBOT_CENTER = 8.0;

    public TargetInfo(TapePairRecognizer.TapePair tapePair) {
        this.tapePair = tapePair;
    }

    public double headingOfPixelRadians(double pixel) {
        double pixelsFromCenter = pixel - frameWidth / 2;
        return Math.atan((pixelsFromCenter * Math.tan(ANGLE_OF_VIEW/2) / (frameWidth/2)));
    }

    public double headingOfTarget(){
        return Math.toDegrees(headingOfPixelRadians(tapePair.getCenterX()));
    }

    public double distanceToTarget() {
        double averageLongestSide = (
                TapePairRecognizer.getLongestSide(tapePair.left) +
                        TapePairRecognizer.getLongestSide(tapePair.right)) / 2.0;

        return (5.5 * FOCAL_LENGTH) / averageLongestSide;
    }

    public double getArea() {
        double leftArea = TapePairRecognizer.getLongestSide(tapePair.left) * 
            TapePairRecognizer.getShortestSide(tapePair.left);
        double rightArea = TapePairRecognizer.getLongestSide(tapePair.right) * 
        TapePairRecognizer.getShortestSide(tapePair.right);
        return leftArea + rightArea;
    }

    public double planeAngle() {
        //inches

       double distanceToLeft = (5.5 * FOCAL_LENGTH) / TapePairRecognizer.getLongestSide(tapePair.left);
       double headingToLeft = headingOfPixelRadians(tapePair.left.center.x);
       double yLeft = distanceToLeft * Math.cos(headingToLeft);
       double xLeft = distanceToLeft * Math.sin(headingToLeft);

       double distanceToRight = (5.5 * FOCAL_LENGTH) /  TapePairRecognizer.getLongestSide(tapePair.right);
       double headingToRight = headingOfPixelRadians(tapePair.right.center.x);
       double yRight = distanceToRight * Math.cos(headingToRight);
       double xRight = distanceToRight * Math.sin(headingToRight);

//        System.out.println(xLeft);
//        System.out.println(yLeft);
//        System.out.println(xRight);
//        System.out.println(yRight);

        if(distanceToLeft < distanceToRight){
            return Math.toDegrees(Math.atan((yRight - yLeft) / (xRight - xLeft)));
        }else{
            return Math.toDegrees(Math.atan((yLeft - yRight) / (xLeft - xRight)));
        }
    }

    @Override
    public String toString() {
        return "distance: "+distanceToTarget()+" heading: "+headingOfTarget()+" planeAngle: "+planeAngle();
    }
}
