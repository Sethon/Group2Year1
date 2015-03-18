package Model;


import java.util.*;

public class Helper2 {
	
	public static double length(Point2D2 p0, Point2D2 p1) {
		return Math.sqrt(Math.pow(p1.getX() - p0.getX(),2) + 
		Math.pow(p1.getY() - p0.getY(),2));
	}
	
	public static void sortByX(ArrayList<Point2D2> points) {
		Collections.sort(points, new XComparator());
	}
	
	public static void removeDuplicatePoints(ArrayList<Point2D2> points) {
		
		Object[] a = points.toArray();
		for (int i = 0; i < a.length-1; i++) {
			if (a[i].equals(a[i+1]))
				a[i] = null;
		}
		points.clear();
		for (int i = 0; i < a.length-1; i++) {
			if (a[i] != null)
				points.add((Point2D2) a[i]);
		}
	}

	static class YComparator implements Comparator<Point2D2> {

		public int compare(Point2D2 a, Point2D2 b) {
			if (a.getY() > b.getY()) {
				return 1; 
			}
			else if (a.getY() == b.getY()) {
				if (a.getX() > b.getX()) {
					return 1;
				}
				else if (a.getX() == b.getX()) {
					return 0;
				} else  {
					return -1;
				}
			}
			else {
				return -1;
			}
		}
	}
	
	static class XComparator implements Comparator<Point2D2> {

		public int compare(Point2D2 a, Point2D2 b) {
			if (a.getX() > b.getX()) {
				return 1;
			}
			else if (a.getX() == b.getX()) {
				if (a.getY() > b.getY()) {
					return 1;
				}
				else if (a.getY() == b.getY()) {
					return 0;
				} else  {
					return -1;
				}
			} else {
				return -1;
			}
		}
	}


	/*static class EdgeComparator implements Comparator<Edge> {

		public int compare(Edge a, Edge b) {
			if (a.getY() > b.getY()) return -1;
			else if (a.getY() == b.getY()) return 0;
			else return 1;
		}
	}*/
	
	public static Point2D2 intersect(Edge edge1, Edge edge2, Point2D2 p, boolean right) {
		if (edge1 == null || edge2 == null) {
			System.out.println("ONE EDGE IS NULL");
			return null;
		}

		Point2D2 p0 = edge1.active();
		Point2D2 p1 = edge1.inactive();
		Point2D2 p2 = edge2.active();
		Point2D2 p3 = edge2.inactive();
		
		//one of segments or both are vertical
		
		if ((p1.getX() != p0.getX()) && (p3.getX() != p2.getX())) {
			
			//a = dy/dx
			double a1 = (p1.getY() - p0.getY())/(p1.getX() - p0.getX());
			double a2 = (p3.getY() - p2.getY())/(p3.getX() - p2.getX());
			double b1 = p1.getY() - a1 * p1.getX();
			double b2 = p3.getY() - a2 * p3.getX();
			
			//segments are parallel
			if (((a1 - a2) == 0) ^ ((b2 - b1) == 0)) {
				return null;
			}
			//segments may overlap
			else if (((a1 - a2) == 0) && ((b2 - b1) == 0)) {
				return null;
			} else {
				
				double xi = (b2 - b1)/(a1 - a2);
				boolean fulfilled;
				if (right) {
					if (xi > p.getX()) {
						fulfilled = true;
					} else {
						fulfilled = false;
					}
				} else {
					if (xi < p.getX()) {
						fulfilled = true;
					} else {
						fulfilled = false;
					}
				}
				boolean isInX1 = (((xi <= p1.getX()) && (xi >= p0.getX())) || ((xi >= p1.getX()) && (xi <= p0.getX())));
				boolean isInX2 = (((xi <= p3.getX()) && (xi >= p2.getX())) || ((xi >= p3.getX()) && (xi <= p2.getX())));
				if (isInX1 && isInX2 && fulfilled) {
					double yi = (a2*xi)+b2;
					System.out.println("Inter here");
						if (a1 >= 0 && a2 <= 0) {
							return new Point2D2(xi, yi, edge1, edge2);
						} else {
							return new Point2D2(xi, yi, edge2, edge1);
						}
				} else {
					return null;
				}
			}
		} else {
			return null;
		}
	}

	public static boolean contains(Polyline2D2 p2d, Point2D2 point){
		Point2D2 left = new Point2D2(-Double.MAX_VALUE, point.getY());
		
		int cnt = 0;
		for (int i = 0; i < p2d.vertices().size(); i++) {
			if (p2d.getVertex(i).getX() == point.getX() && p2d.getVertex(i).getY() == point.getY()) {
				return true;
			}
		}
		while (goesThrough(p2d, point, left)) {
			System.out.println("TROUBLE");
			left = new Point2D2(-Double.MAX_VALUE, point.getY() + Math.random());
		}
		for (int i = 0; i < p2d.vertices().size() - 1; i++) {
			if (areIntersect(p2d.getVertex(i), p2d.getVertex(i + 1), point, left)) {
				System.out.println(i);
				cnt++;
			}
		}
		return (cnt % 2 == 1);
	}
	
	private static boolean goesThrough(Polyline2D2 p2d, Point2D2 point1, Point2D2 point2) {
		double a = (point1.getY() - point1.getY())/(point1.getX() - point1.getX());
		double b = point1.getY() - a * point1.getX();
		for (int i = 0; i < p2d.vertices().size(); i++) {
			if (a*p2d.getVertex(i).getX() + b == p2d.getVertex(i).getY() && 
			((p2d.getVertex(i).getX() < point1.getX() && p2d.getVertex(i).getX() > point2.getX()) || 
			(p2d.getVertex(i).getX() > point1.getX() && p2d.getVertex(i).getX() < point2.getX()))) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean areIntersect(Point2D2 p0, Point2D2 p1, Point2D2 p2, Point2D2 p3) {
		//one of segments or both are vertical
		if ((p1.getX() == p0.getX()) && (p3.getX() != p2.getX())) {
			boolean isInX = (((p0.getX() <= p3.getX()) && (p0.getX() >= p2.getX())) || ((p0.getX() >= p3.getX()) && (p0.getX() <= p2.getX())));
			double a2 = (p3.getY() - p2.getY())/(p3.getX() - p2.getX());
			double b2 = p3.getY() - a2 * p3.getX();
			double yi = a2*p1.getX() + b2;
			boolean isInY = (yi <= p0.getY() && yi >= p1.getY()) || (yi >= p0.getY() && yi <= p1.getY());
			return (isInX && isInY);
		}
		else if ((p1.getX() != p0.getX()) && (p3.getX() == p2.getX())) {
			boolean isInX = (((p2.getX() <= p1.getX()) && (p2.getX() >= p0.getX())) || ((p2.getX() >= p1.getX()) && (p2.getX() <= p0.getX())));
			double a1 = (p1.getY() - p0.getY())/(p1.getX() - p0.getX());
			double b1 = p1.getY() - a1 * p1.getX();
			double yi = a1*p1.getX() + b1;
			boolean isInY = (yi <= p2.getY() && yi >= p3.getY()) || (yi >= p2.getY() && yi <= p3.getY());
			return (isInX && isInY);
		}
		else if ((p1.getX() == p0.getX()) && (p3.getX() == p2.getX())) {
			if ((p0.getY() > p2.getY() && p0.getY() > p3.getY() && p1.getY() > p2.getY() && p1.getY() > p3.getY()) || 
				(p0.getY() < p2.getY() && p0.getY() < p3.getY() && p1.getY() < p2.getY() && p1.getY() < p3.getY())) {
				return false;
			} else {
				return true;
			}
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
}
