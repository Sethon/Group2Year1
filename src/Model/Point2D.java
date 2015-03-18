package Model;

import java.util.Comparator;


public class Point2D implements Point {
	
	private double x;
	private double y;
	private Edge edge;
	private Edge top;
	private Edge bottom;
	
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
		edge = null;
	}
	public Point2D(double x, double y, Edge e) {
		this.x = x;
		this.y = y;
		edge = e;
	}
	public Point2D(double x, double y, Edge t, Edge b) {
		this.x = x;
		this.y = y;
		top = t; bottom = b;
	}
	
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
	
	public void setEdge(Edge e) {
		edge = e;
	}
	
	public Edge edge() {
		return edge;
	}
	
	public Edge top() {
		return top;
	}
	
	public Edge bottom() {
		return bottom;
	}
	
//	public boolean equals(Object o) {
//		Point2D p2d = (Point2D) o;
//		return (p2d.getX() == x) && (p2d.getY() == y);
//	}
	
	public Point2D clone() {
		return new Point2D(x,y);
	}
	public double[] coordinates() {
		double[] co = {x,y};
		return co;
	}
	
	static class XComparator implements Comparator<Point2D> {
		
		public int compare(Point2D a, Point2D b) {
			if (a.x > b.x) return 1;
			else if (a.x == b.x) return 0;
			else return -1;
		}
	}
	
	static class YComparator implements Comparator<Point2D> {

		public int compare(Point2D a, Point2D b) {
			if (a.y > b.y) return 1;
			else if (a.equals(b)) return 0;
			else return -1;
		}
	}

}
