package Model;


public class Edge implements Comparable<Edge> {
	
	private Point2D active;
	private Point2D inactive;
	private String label;
	
	public Edge(Point2D l, Point2D r) {
		active = l;
		inactive = r;
	}
	public Edge(Point2D l, Point2D r, String s) {
		active = l;
		inactive = r;
		setLabel(s);
	}
	
	public void setActive(Point2D p) {
		active = p;
	}
	public void setInactive(Point2D p) {
		inactive = p;
	}
	public Point2D active() {
		return active;
	}
	public Point2D inactive() {
		return inactive;
	}
	
	public void swapActive() {
		Point2D temp = active;
		active = inactive;
		inactive = temp;
	}
	
	public boolean isActive(Point2D p) {
		return active.equals(p);
	}
	public boolean isInactive(Point2D p) {
		return inactive.equals(p);
	}
	public String label() {

		return label;
	}
	public void setLabel(String label) {

		this.label = label;
	}
	public int compareTo(Edge edge) {
		Point2D.YComparator yc = new Point2D.YComparator();
		
		return yc.compare(active, edge.active);
	}
	
	public String toString() {
		return "(" + active.toString() + "-" + inactive.toString() + ")";
	}
}
