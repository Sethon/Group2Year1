package Model;

import java.util.Comparator;

public class Point2D implements Point, Comparable<Object> {
	private Edge edgeNext;
	private Edge edgePrev;
	private Edge top;
	private Edge bottom;
	private double x;
	private double y;
	boolean internal;
	
	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
		edgeNext = null;
		edgePrev = null;
	}
	public Point2D(double x, double y, Edge e) {
		this.x = x;
		this.y = y;
		edgeNext = e;
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
	
	public void setNextEdge(Edge e) {
		edgeNext = e;
	}
	
	public Edge nextEdge() {
		return edgeNext;
	}
	
	public void setPrevEdge(Edge e) {
		edgePrev = e;
	}
	
	public Edge prevEdge() {
		return edgePrev;
	}
	
	public Edge top() {
		return top;
	}
	
	public Edge bottom() {
		return bottom;
	}
	
	public boolean isInternal() {
		return internal;
	}
	
	public void setInternal(boolean i) {
		internal = i;
	}
	
//	public boolean equals(Object o) {
//		Point2D p2d = (Point2D) o;
//		return (p2d.getX() == x) && (p2d.getY() == y);
//	}
	
	public Point2D clone() {
		return new Point2D(x,y);
	}

	public int compareTo(Object o) {
		Point2D p2d = (Point2D) o;
		if (getX() > p2d.getX()) {
			return 1;
		}
		/*else if (edge() == null)
			return 0;
		else if (this.equals(edge.left()) && p2d.equals(edge.right()))
			return 1;*/
		else {
			return -1;
		}
	}

	public double[] coordinates() {
		return  new double[] {x,y};
	}

}
