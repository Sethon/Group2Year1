package Model;


public class Edge {
	
	private Point2D left;
	private Point2D right;
	private String label;
	
	public Edge(Point2D l, Point2D r) {
		left = l;
		right = r;
	}
	public Edge(Point2D l, Point2D r, String s) {
		left = l;
		right = r;
		label = s;
	}
	
	public void setLeft(Point2D p) {
		left = p;
	}
	public void setRight(Point2D p) {
		right = p;
	}
	public Point2D getLeft() {
		return left;
	}
	public Point2D getRight() {
		return right;
	}
}
