package frc.robot.vision;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.RotatedRect;

import java.util.ArrayList;

import static org.opencv.imgproc.Imgproc.*;

/**
 * A class that can recognize pairs of reflective tape (or green angle paired rectangles,
 * if you prefer) that are of specific dimensions and distance apart in an otherwise
 * dark image.
 */
public class TapePairRecognizer {

    private static final int DISTANCE_APART_MULTIPLIER = 8;
    private static final double DISTANCE_APART_TOLERANCE = .35;

    /**
     * Recognize and return any reflective tape pairs found in an image.
     *
     * @param image an OpenCV color image
     * @return an unordered list of found tape pairs
     */
    public static ArrayList<TapePair> recognize(Mat image) {
        ArrayList<MatOfPoint> pipelineResult = runPipeline(image);
        ArrayList<RotatedRect> rectangles = contoursToRectangles(pipelineResult);
        return findPairs(rectangles);
    }

    private static ArrayList<RotatedRect> contoursToRectangles(ArrayList<MatOfPoint> contours) {
        ArrayList<RotatedRect> rectangles = new ArrayList<>();

        for (var contour : contours) {
            MatOfPoint2f polygon = contourToPolygon(contour);

            // we want to exclude things that don't have (roughly) four sides
            int numberOfSides = polygon.toArray().length;
            if (numberOfSides == 4 || numberOfSides == 5) {
                //convert polygon to rectangle
                RotatedRect rectangle = minAreaRect(polygon);
                rectangles.add(rectangle);
            }
        }
        return rectangles;
    }

    private static MatOfPoint2f contourToPolygon(MatOfPoint contour) {
        MatOfPoint2f itemConverted = new MatOfPoint2f();
        itemConverted.fromList(contour.toList());
        MatOfPoint2f polygon = new MatOfPoint2f();
        double maxPerimeter = arcLength(itemConverted, true);
        double perimeterDifference = maxPerimeter * 0.05;
        approxPolyDP(itemConverted, polygon, perimeterDifference, true);
        return polygon;
    }

    private static ArrayList<MatOfPoint> runPipeline(Mat image) {
        GripPipelineContour pipeline = new GripPipelineContour();
        pipeline.process(image);
        return pipeline.filterContoursOutput();
    }

    private static boolean checkAngle(RotatedRect rectangle, double minAngle, double maxAngle) {
        return rectangle.angle >= minAngle && rectangle.angle <= maxAngle;
    }

    private static boolean leansRight(RotatedRect rectangle) {
        return checkAngle(rectangle, -90, -60);
    }

    private static boolean leansLeft(RotatedRect rectangle) {
        return checkAngle(rectangle, -30, -0);
    }

    private static ArrayList<TapePair> findPairs(ArrayList<RotatedRect> rectangles) {
        ArrayList<TapePair> pairs = new ArrayList<>();
        for (var maybeLeftRectangle : rectangles) {
            for (var maybeRightRectangle : rectangles) {
                if (maybeLeftRectangle != maybeRightRectangle && isPair(maybeLeftRectangle, maybeRightRectangle)) {
                    pairs.add(new TapePair(maybeLeftRectangle, maybeRightRectangle));
                }
            }
        }
        return pairs;
    }

    private static boolean isPair(RotatedRect leftRectangle, RotatedRect rightRectangle) {
        boolean leansRightRect1 = leansRight(leftRectangle);
        boolean leansLeftRect2 = leansLeft(rightRectangle);
        boolean distance = distanceIsExpected(leftRectangle, rightRectangle);
        boolean ordering = leftRectangle.center.x < rightRectangle.center.x;
        return leansRightRect1 && leansLeftRect2 && distance && ordering;
    }

    //Sometimes height is the shortest side
    public static double getShortestSide(RotatedRect rectangle) {
        if (rectangle.size.width < rectangle.size.height) {
            return rectangle.size.width;
        } else {
            return rectangle.size.height;
        }
    }

    public static double getLongestSide(RotatedRect rectangle) {
        if (rectangle.size.width < rectangle.size.height) {
            return rectangle.size.height;
        } else {
            return rectangle.size.width;
        }
    }

    private static boolean distanceIsExpected(RotatedRect rectangle1, RotatedRect rectangle2) {
        //average together the short sides
        double widthSum = getShortestSide(rectangle1) + getShortestSide(rectangle2);
        double widthAverage = widthSum / 2;

        //we expect the target rectangles to be about 8 times the "width" (short side) apart
        double expectedDistance = widthAverage * DISTANCE_APART_MULTIPLIER;
        return isDistanceApartWithinTolerance(rectangle1, rectangle2, expectedDistance);
    }

    private static boolean isDistanceApartWithinTolerance(RotatedRect rectangle1, RotatedRect rectangle2, double distance) {
        double min = (1.0 - DISTANCE_APART_TOLERANCE) * distance;
        double max = (1.0 + DISTANCE_APART_TOLERANCE) * distance;

        double xSquared = Math.pow(rectangle1.center.x - rectangle2.center.x, 2);
        double ySquared = Math.pow(rectangle1.center.y - rectangle2.center.y, 2);
        double actualDistance = Math.sqrt(xSquared + ySquared);

        return actualDistance >= min && actualDistance <= max;
    }

    /**
     * Simple data structure to provide easier access to the left and right
     * pieces of a reflective tape pair.
     */
    public static class TapePair {
        public final RotatedRect left;
        public final RotatedRect right;

        public TapePair(RotatedRect left, RotatedRect right) {
            this.left = left;
            this.right = right;
        }

        public double getWidth() {
          return  (right.center.x - left.center.x) + right.center.x/2 + left.center.x/2;
        }

        public double getCenterX() {
            return (left.center.x + right.center.x) / 2;
        }
    }
}
