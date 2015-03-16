package Model;

public class Helper {
	public static double length(Point2D p0, Point2D p1) {
		return Math.sqrt(Math.pow(p1.getX() - p0.getX(),2) + 
		Math.pow(p1.getY() - p0.getY(),2));
	}
}
