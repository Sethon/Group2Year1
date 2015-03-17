package Model;

import java.util.ArrayList;


public class Polyline2D implements Polyline<Point2D> {
	
	private ArrayList<Point2D> vertices;
	private ArrayList<Edge> edges;
	
	public Polyline2D(ArrayList<Point2D> vertices) {
		this.vertices = vertices;
		makeEdges();
	}
	
	public Polyline2D() {
		vertices = new ArrayList<Point2D>();
		for(int i = 0; i < DEF_VNUM; i++) {
			vertices.add(new Point2D());
		}
		makeEdges();
	}
	
	public Point2D getVertex(int i) {
		return vertices.get(i);
	}
	
	public ArrayList<Point2D> vertices() {
		return vertices;
	}
	
	public void makeEdges() {
		edges = new ArrayList<>();
		for (int i = 0; i < vertices.size()-1; i++) {
			Point2D a = vertices.get(i); Point2D b = vertices.get(i+1);
			Edge e;
			if (a.getX() < b.getX()) {
				e = new Edge(a,b);
			} else {
				e = new Edge(b,a);
			}
			a.setEdge(e); b.setEdge(e);
			edges.add(e);
		}
	}
	
	public ArrayList<Edge> edges() {
		return edges;
	}
	
	public void addPoint(double x, double y) {
		vertices.add(new Point2D(x, y));
	}
	
	public void addPoint(Point2D p) {
		vertices.add(p);
	}
	
	//TO BE IMPLEMENTED
	public double length() {
		double length = 0;
		for (int i = 0; i < vertices.size() - 1; i++) {
			length += Math.sqrt(Math.pow(vertices.get(i + 1).getX() - vertices.get(i).getX(),2) + 
			Math.pow(vertices.get(i + 1).getY() - vertices.get(i).getY(),2));
		}
		return length;
	}
	
	public boolean isClosed() {
		return ((vertices.get(0).getX() == vertices.get(vertices.size() - 1).getX()) && 
		(vertices.get(0).getY() == vertices.get(vertices.size() - 1).getY()));
	}
	
}
