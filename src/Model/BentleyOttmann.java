package Model;

import java.util.ArrayList;

public class BentleyOttmann {

	private YStructure yStructure;
	private XStructure xStructure;
	private Polyline2D[] lines;
	private ArrayList<Edge> edges;
	private ArrayList<Point2D> vertices;
	
	public BentleyOttmann(Polyline2D pl1, Polyline2D pl2) {
		lines = new Polyline2D[2];
		lines[0] = pl1; lines[1] = pl2;
		vertices = pl1.vertices();
		vertices.addAll(pl2.vertices());
		edges = pl1.edges();
		edges.addAll(pl2.edges());
		Helper.sortByX(vertices);
		xStructure = new XStructure(vertices.get(0));
		BinaryTreeNode<Point2D> xRoot = xStructure.root();
		for (int i = 1; i < vertices.size(); i++) {
			xStructure.add(xRoot, vertices.get(i));
		}
	}
	
	public ArrayList<Point2D> bentley() {
		
		yStructure = null;
		ArrayList<Point2D> intersections = new ArrayList<>();
		
		while (!xStructure.isEmpty()) {
			
			Point2D currentPoint = xStructure.removeMin();
			Edge currentEdge = currentPoint.edge(); 
			BinaryTreeNode<Point2D> yRoot = yStructure.root();
			Point2D intersection;
			
			if (currentEdge != null && currentEdge.isLeft(currentPoint)) { 
				if (yStructure == null)
					yStructure = new YStructure(currentPoint);
				else
					yStructure.add(yRoot, currentPoint);  
				Edge successor = yStructure.successor(currentEdge); 
				Edge predecessor = yStructure.predecessor(currentEdge); 
				if (successor != null) {
					if ((intersection = checkIntersection(currentEdge, successor)) != null) 
						xStructure.add(xStructure.root(), intersection);
				}
				if (predecessor != null) {
					if ((intersection = checkIntersection(currentEdge, predecessor)) != null)
						xStructure.add(xStructure.root(), intersection);
				}
				if ((intersection = checkIntersection(successor, predecessor)) != null)
					xStructure.remove(xStructure.root(), intersection); 
				
			} else if (currentEdge != null && currentEdge.isRight(currentPoint)) { 
				Edge successor = yStructure.successor(currentPoint); 
				Edge predecessor = yStructure.predecessor(currentPoint); 
				yStructure.remove(yRoot, currentPoint); 
				checkIntersection(successor, predecessor); 
				
			} else { 
				intersections.add(currentPoint); 
				Edge top = currentPoint.top();
				Edge bottom = currentPoint.bottom(); 
				Edge topTop = yStructure.successor(top); 
				Edge bottomBottom = yStructure.predecessor(bottom); 
				yStructure.remove(yRoot, top.left());
				yStructure.remove(yRoot, bottom.left());
				yStructure.add(yRoot, top.right());
				yStructure.add(yRoot, bottom.right());
				if ((intersection = checkIntersection(top, topTop)) != null)
					xStructure.remove(xStructure.root(), intersection); 
				if ((intersection = checkIntersection(bottom, topTop)) != null)
					xStructure.add(xStructure.root(), intersection); 
				if ((intersection = checkIntersection(top, bottomBottom)) != null)
					xStructure.add(xStructure.root, intersection);
				if ((intersection = checkIntersection(bottom, bottomBottom)) != null)
					xStructure.remove(xStructure.root(), intersection);
			}
		}
		return intersections;
	}
}
