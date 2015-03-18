package Model;


public class Point2D implements Point, Comparable<Object> {
	private double[] coordinates;
	private Edge edge;
	private Edge top;
	private Edge bottom;
	
	public Point2D(double x, double y) {
		coordinates = new double[2];
		coordinates[0] = x;
		coordinates[1] = y;
	}
	public Point2D(double x, double y, Edge e) {
		coordinates = new double[2];
		coordinates[0] = x;
		coordinates[1] = y;
		edge = e;
	}
	public Point2D(double x, double y, Edge t, Edge b) {
		coordinates = new double[2];
		coordinates[0] = x;
		coordinates[1] = y;
		top = t; bottom = b;
	}
	public Point2D() {
		coordinates = new double[2];
		coordinates[0] = Math.pow(-1,Math.round(Math.random())) * (RANGE * Math.random());
		coordinates[1] = Math.pow(-1,Math.round(Math.random())) * (RANGE * Math.random());
	}
	public double[] coordinates() {
		return coordinates;
	}
	public double getX() {
		return coordinates[0];
	}
	public double getY() {
		return coordinates[1];
	}
	
	public String toString() {
		return "[" + coordinates[0] + ", " + coordinates[1] + "]";
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
	
	public boolean equals(Object o) {
		Point2D p2d = (Point2D) o;
		return (p2d.getX() == getX()) && (p2d.getY() == getY());
	}

	public int compareTo(Object o) {
		Point2D p2d = (Point2D) o;
		if (getX() > p2d.getX()) {
			return 1;
		}
		else if (edge() == null)
			return 0;
		else if (this.equals(edge.left()) && p2d.equals(edge.right()))
			return 1;
		else {
			return -1;
		}
	}
	
	public Point2D clone() {
		return new Point2D(coordinates[0], coordinates[1]);
	}
}
