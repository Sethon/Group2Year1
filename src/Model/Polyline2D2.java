package Model;

import java.util.ArrayList;


public class Polyline2D2 implements Polyline<Point2D2> {
	
	private ArrayList<Point2D2> vertices;
	private ArrayList<Edge> edges;
	
	public Polyline2D2(ArrayList<Point2D2> vertices) {
		this.vertices = vertices;
		makeEdges();
	}
	
	public Polyline2D2() {
		vertices = new ArrayList<Point2D2>();
/*		for(int i = 0; i < DEF_VNUM; i++) {
			vertices.add(new Point2D2());
		}
		makeEdges();
*/	}
	
	public Point2D2 getVertex(int i) {
		return vertices.get(i);
	}
	
	public ArrayList<Point2D2> vertices() {
		return vertices;
	}
	
	public void makeEdges() {
		edges = new ArrayList<>();
		for (int i = 0; i < vertices.size()-1; i++) {
			Point2D2 a = vertices.get(i).clone(); Point2D2 b = vertices.get(i+1).clone();
			Edge e;
			if (a.getX() < b.getX()) {
				e = new Edge(a,b);
				a.setNextEdge(e);
				b.setPrevEdge(e);
			} else {
				e = new Edge(b,a);
				a.setPrevEdge(e);
				b.setNextEdge(e);
			}
			edges.add(e);
		}
	}
	
	public ArrayList<Edge> edges() {
		return edges;
	}
	
	public void addPoint(double x, double y) {
		vertices.add(new Point2D2(x, y));
	}
	
	public void addPoint(Point2D2 p) {
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
