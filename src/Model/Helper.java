package Model;

import java.util.*;

public class Helper {
	public static double length(Point2D p0, Point2D p1) {
		return Math.sqrt(Math.pow(p1.getX() - p0.getX(),2) + 
		Math.pow(p1.getY() - p0.getY(),2));
	}
	
	public static void sortByX(ArrayList<Point2D> points) {
		Collections.sort(points, new XComparator());
	}
	
	public static void removeDuplicatePoints(ArrayList<Point2D> points) {
		
		Object[] a = points.toArray();
		for (int i = 0; i < a.length-1; i++) {
			if (a[i].equals(a[i+1]))
				a[i] = null;
		}
		points.clear();
		for (int i = 0; i < a.length-1; i++) {
			if (a[i] != null)
				points.add((Point2D) a[i]);
		}
	}
	
	static class XComparator implements Comparator<Point2D> {
		
		public int compare(Point2D a, Point2D b) {
			    if (a.getX() > b.getX()) return -1;
			    else if (a.getX() == b.getX()) return 0;
			    else return 1;
			}
	}
}
