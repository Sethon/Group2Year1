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
	
	private static boolean areIntersect(Point2D p0, Point2D p1, Point2D p2, Point2D p3) {
		//one of segments or both are vertical
		if ((p1.getX() == p0.getX()) && (p3.getX() != p2.getX())) {
			return (((p0.getX() <= p3.getX()) && (p0.getX() >= p2.getX())) || ((p0.getX() >= p3.getX()) && (p0.getX() <= p2.getX())));
		}
		else if ((p1.getX() != p0.getX()) && (p3.getX() == p2.getX())) {
			 return (((p2.getX() <= p1.getX()) && (p2.getX() >= p0.getX())) || ((p2.getX() >= p1.getX()) && (p2.getX() <= p0.getX())));
		}
		else if ((p1.getX() == p0.getX()) && (p3.getX() == p2.getX())) {
			return (p1.getX() == p3.getX());
		}
		else {
			//a = dy/dx
			double a1 = (p1.getY() - p0.getY())/(p1.getX() - p0.getX());
			double a2 = (p3.getY() - p2.getY())/(p3.getX() - p2.getX());
			double b1 = p1.getY() - a1 * p1.getX();
			double b2 = p3.getY() - a2 * p3.getX();
			
			//segments are parallel
			if (((a1 - a2) == 0) ^ ((b2 - b1) == 0)) {
				return false;
			}
			//segments have a common segment 
			else if (((a1 - a2) == 0) && ((b2 - b1) == 0)) {
				return true;
			} else {
				double xi = (b2 - b1)/(a1 - a2);
				boolean isInX1 = (((xi <= p1.getX()) && (xi >= p0.getX())) || ((xi >= p1.getX()) && (xi <= p0.getX())));
				boolean isInX2 = (((xi <= p3.getX()) && (xi >= p2.getX())) || ((xi >= p3.getX()) && (xi <= p2.getX())));
				return (isInX1 && isInX2);
			}
		}
	}
	
	public static boolean contains(Polyline2D p2d, Point2D point){
		Point2D left = new Point2D((double) Integer.MIN_VALUE, point.getY());
		Point2D right = new Point2D((double) point.getX()+0.1, point.getY());
		
		int counter = 0;
		ArrayList<Point2D> vertices = p2d.getVertices();
		for(int i = 0; i< vertices.size()-1; i++) {
			if(areIntersect((Point2D) vertices.get(i), (Point2D)vertices.get(i%vertices.size()), left, right))
			{
				counter++;
			}
			
			if(areIntersect(left,right,point,point)) {
				if(counter%2==1)
					return true;
			}
		}
		return false;
	}
}
