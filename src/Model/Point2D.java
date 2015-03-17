package Model;



public class Point2D implements Point, Comparable<Object> {
	private double[] coordinates;
	
	public Point2D(double x, double y) {
		coordinates = new double[2];
		coordinates[0] = x;
		coordinates[1] = y;
	}
	public Point2D() {
		coordinates = new double[2];
		coordinates[0] = Math.pow(-1,Math.round(Math.random())) * (RANGE * Math.random());
		coordinates[1] = Math.pow(-1,Math.round(Math.random())) * (RANGE * Math.random());
	}
	public double[] getCoordinates() {
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
	
	public boolean equals(Object o) {
		Point2D p2d = (Point2D) o;
		return (p2d.getX() == getX()) && (p2d.getY() == getY());
	}
	
	public int compareTo(Object o) {
		Point2D p2d = (Point2D) o;
		if (getX() > p2d.getX()) {
			return 1;
		}
		else if (this.equals(p2d)) {
			return 0;
		} else {
			return -1;
		}
	}
}
