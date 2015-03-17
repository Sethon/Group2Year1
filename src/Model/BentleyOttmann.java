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
		yStructure = new YStructure();
		xStructure = new XStructure();
		xStructure.add(vertices);
	}
	
	public ArrayList<Point2D> bentley() {
		
		ArrayList<Point2D> intersections = new ArrayList<>();
		
		while (!xStructure.isEmpty()) {
			
			BinaryTreeNode yRoot = yStructure.root();
			Point2D currentPoint = xStructure.removeMin();
			Edge currentEdge = currentPoint.edge(); //will be erroneous if point is transitionpoint
			
			if (currentEdge != null && currentEdge.isLeft(currentPoint)) {  //the leftmost active point is the beginning of a line segment
				yStructure.add(currentPoint);  
				Edge successor = yStructure.successor(currentEdge); //if x has a neighboring segment above itself save it in y
				Edge predecessor = yStructure.predecessor(currentEdge); //if x has a neighboring segment below itself save it in z
				if (successor != null)
					xStructure.add(checkIntersection(x,successor)); //if above exists a segment, add their intersection to xStructure (if it exists)
				if (predecessor != null)
					checkIntersection(x,predecessor); //if below exists a segment, add their intersection to xStructure (if it exists)
				removeIntersection(successor,predecessor); //if successor and predecessor intersect, remove intersection from xStructure
				
			} else if (currentEdge != null && currentEdge.isRight(currentPoint)) { //the leftmost active point is the end of a line segment 
				Edge successor = yStructure.successor(currentPoint); //if x has a neighboring segment above itself save it in successor
				Edge predecessor = yStructure.predecessor(currentPoint); //if x has a neighboring segment below itself save it in predecessor
				yStructure.remove(yRoot, currentPoint); //remove segment of endpoint currentPoint
				checkIntersection(successor,predecessor); //if neighbors intersect add point to xStructure
				
			} else { //currentPoint is an intersection
				intersections.add(currentPoint); //an intersection was found, store it
				Edge top = currentPoint.top(); //get segment that is on top in the yStructure
				Edge bottom = currentPoint.bottom(); //get segment that is lower in the yStructure
				Edge topTop = yStructure.successor(top); //if u has a neighboring segment above itself save it in successor
				Edge bottomBottom = yStructure.predecessor(bottom); //if v has a neighboring segment below itself save it in predecessor
				yStructure.remove(yRoot, top.left()); //swap u and v (they intersect and therefore change order according to Y
				yStructure.remove(yRoot, bottom.left());
				yStructure.add(yRoot, top.right());
				yStructure.add(yRoot, bottom.right());
				checkIntersection(top, bottombottom); //check if u intersects with predecessor and add to xStructure
				checkIntersection(bottom, toptop); //check if v intersects with successor and add to xStructure
				removeIntersection(top, toptop); //remove intersection between u and successor if it exists
				removeIntersection(bottom, bottombottom); //remove intersection between v and predecessor if it exists
			}
		}
		return intersections;
	}
}
