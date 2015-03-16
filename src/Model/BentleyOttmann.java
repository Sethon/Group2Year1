package Model;

import java.util.ArrayList;

public class BentleyOttmann {

	private BinarySearchTree yStructure;
	private BinarySearchTree xStructure;
	private Polyline2D[] lines;
	private Polyline2D line;
	private ArrayList<Edge> edges;
	private ArrayList<Point2D> vertices;
	private int start = Integer.MIN_VALUE;
	private int end = Integer.MAX_VALUE;
	
	public BentleyOttmann(Polyline2D pl1, Polyline2D pl2) {
		lines = new Polyline2D[2];
		lines[0] = pl1; lines[1] = pl2;
		vertices = pl1.getVertices();
		vertices.addAll(pl2.getVertices());
		edges = pl1.getEdges();
		edges.addAll(pl2.getEdges());
		Helper.sortByX(vertices);
		//Helper.removeDuplicatePoints(vertices);
		yStructure = new BinarySearchTree();
		xStructure = new BinarySearchTree();
		//xStructure.add(vertices);
	}
	
	public ArrayList<Point2D> bentley() {
		
		ArrayList<Point2D> intersections = new ArrayList<>();
		
		while (!xStructure.isEmpty()) {
		
			Point2D min = xStructure.getMin();
			xStructure.removeMin();
			
			if (min.isLeft()) {  //the leftmost active point is the beginning of a line segment
				Edge x = min.edge(); //this segment is now an active segment
				Edge y = yStructure.getSuccessor(x); //if x has a neighboring segment above itself save it in y
				Edge z = yStructure.getPredecessor(x); //if x has a neighboring segment below itself save it in z
				yStructure.add(x);  
				if (y != null)
					checkIntersect(x,y); //if above exists a segment, add their intersection to xStructure (if it exists)
				if (z != null)
					checkIntersect(x,z); //if below exists a segment, add their intersection to xStructure (if it exists)
				(removeIntersect(y,z); //if y and z intersect, remove intersection from 
				
			} else if (min.isRight()) { //the leftmost active point is the end of a line segment 
				Edge y = yStructure.getSuccessor(x); //if x has a neighboring segment above itself save it in y
				Edge z = yStructure.getPredecessor(x); //if x has a neighboring segment below itself save it in z
				yStructure.removeEdge(min); //remove segment of endpoint min
				checkIntersect(y,z); //if neighbors intersect add point to xStructure
				
			} else { //min is an intersection
				intersections.add(min); //an intersection was found, store it
				Edge u = yStructure.getSegmentAbove(min); //get segment that is on top in the yStructure
				Edge v = yStructure.getSegmentBelow(min); //get segment that is lower in the yStructure
				Edge y = yStructure.getSuccessor(u); //if u has a neighboring segment above itself save it in y
				Edge z = yStructure.getPredecessor(v); //if v has a neighboring segment below itself save it in z
				yStructure.swapElements(u, v); //swap u and v (they intersect and therefore change order according to Y
				checkIntersect(u,z); //check if u intersects with z and add to xStructure
				checkIntersect(v,y); //check if v intersects with y and add to xStructure
				removeIntersect(u,y); //remove intersection between u and y if it exists
				removeIntersect(v,z); //remove intersection between v and z if it exists
			}
		}
		return intersections;
	}
	
}
