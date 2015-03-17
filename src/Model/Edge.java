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
	public Point2D left() {
		return left;
	}
	public Point2D right() {
		return right;
	}
	public boolean isLeft(Point2D p) {
		return left.equals(p);
	}
	public boolean isRight(Point2D p) {
		return right.equals(p);
	}
}
