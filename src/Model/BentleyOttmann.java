package Model;

import java.util.ArrayList;

public class BentleyOttmann {

	private YStructure yStructure;
	private XStructure xStructure;
	private Polyline2D[] lines;
	private ArrayList<Edge> edges;
	private ArrayList<Point2D> vertices;
	
	public BentleyOttmann(Polyline2D pl1, Polyline2D pl2) {
		/*lines = new Polyline2D[2];
		lines[0] = pl1; lines[1] = pl2;
		vertices = pl1.vertices();
		vertices.addAll(pl2.vertices());
		edges = pl1.edges();
		edges.addAll(pl2.edges());*/
		//Helper.sortByX(vertices); don't need it
		xStructure = new XStructure(pl1.vertices().get(1));
		//BinaryTreeNode<Point2D> xRoot = xStructure.root();
		for (int i = 1; i < pl1.vertices().size(); i++) {
			xStructure.add(xStructure.root(), pl1.vertices().get(i));
		}
		for (int i = 0; i < pl2.vertices().size(); i++) {
			xStructure.add(xStructure.root(), pl1.vertices().get(i));
		}
		/*for (int i = 1; i < pl1.edges().size(); i++) {
			xStructure.add(xStructure.root(), pl1.vertices().get(i));
		}
		for (int i = 1; i < pl2.edges().size(); i++) {
			xStructure.add(xStructure.root(), pl1.vertices().get(i));
		}*/
	}
	
	public ArrayList<Point2D> bentley() {
		
		yStructure = null;
		ArrayList<Point2D> intersections = new ArrayList<>();
		
		while (!(xStructure.isEmpty())) {
			//System.out.println(xStructure.size());
			Point2D currentPoint = xStructure.removeMin();
			//System.out.println(xStructure.size());
			//System.out.println(currentPoint);
			Edge currentEdge = currentPoint.edge(); 
			//BinaryTreeNode<Point2D> yRoot = yStructure.root(); ???
			//Point2D intersection; ???
			if (currentEdge != null) {
				if (currentEdge.isLeft(currentPoint)) {
					if (yStructure == null) {
						yStructure = new YStructure(currentPoint);
					} else {
						yStructure.add(yStructure.root(), currentPoint);
					}
					Edge successor = yStructure.successor(currentPoint);
					Edge predecessor = yStructure.predecessor(currentPoint);
					if (successor != null) {
						Point2D interPoint = Helper.intersect(successor, currentEdge, currentPoint, true);
						if (interPoint != null) {
							xStructure.add(xStructure.root(), interPoint);
						}
					}
					if (predecessor != null) {
						Point2D interPoint = Helper.intersect(predecessor, currentEdge, currentPoint, true);
						if (interPoint != null) {
							xStructure.add(xStructure.root(), interPoint);
						}
					}
					if (successor != null && successor != null) {
						Point2D interPoint = Helper.intersect(predecessor, successor, currentPoint, true);
						if (interPoint != null) {
							xStructure.remove(xStructure.root(), interPoint);
						}
					}
				} else {
					Edge successor = yStructure.successor(currentPoint);
					Edge predecessor = yStructure.predecessor(currentPoint);
					yStructure.remove(yStructure.root(), currentPoint);
					if (successor != null && successor != null) {
						Point2D interPoint = Helper.intersect(predecessor, successor, currentPoint, true);
						if (interPoint != null) {
							xStructure.add(xStructure.root(), interPoint);
						}
					}
				}
			} else {
				intersections.add(currentPoint);
				System.out.println("BLAAAH");
				Edge top = currentPoint.top();
				Edge bottom = currentPoint.bottom();
				Edge successorTop = yStructure.successor(top.left());
				Edge predecessorBottom = yStructure.predecessor(bottom.left());
				if (successorTop != null) {
					Point2D interPoint = Helper.intersect(bottom, successorTop, currentPoint, true);
					if (interPoint != null) {
						xStructure.add(xStructure.root(), interPoint);
					}
				}
				if (predecessorBottom != null) {
					Point2D interPoint = Helper.intersect(top, predecessorBottom, currentPoint, true);
					if (interPoint != null) {
						xStructure.add(xStructure.root(), interPoint);
					}
				}
				yStructure.remove(yStructure.root(), top.left());
				yStructure.remove(yStructure.root(), bottom.left());
				yStructure.add(yStructure.root(), top.right());
				yStructure.add(yStructure.root(), bottom.right());
				if (successorTop != null) {
					Point2D interPoint = Helper.intersect(top, successorTop, currentPoint, true);
					if (interPoint != null) {
						xStructure.remove(xStructure.root(), interPoint);
					}
				}
				if (predecessorBottom != null) {
					Point2D interPoint = Helper.intersect(bottom, predecessorBottom, currentPoint, true);
					if (interPoint != null) {
						xStructure.remove(xStructure.root(), interPoint);
					}
				}
			}
			/*
			if (currentEdge != null && currentEdge.isLeft(currentPoint)) { 
				if (yStructure == null)
					yStructure = new YStructure(currentPoint);
				else
					yStructure.add(yRoot, currentPoint);  
				Edge successor = yStructure.successor(currentPoint); 
				Edge predecessor = yStructure.predecessor(currentPoint); 
				if (successor != null) {
					if ((intersection = intersect(currentEdge, successor)) != null) 
						xStructure.add(xStructure.root(), intersection);
				}
				if (predecessor != null) {
					if ((intersection = intersect(currentEdge, predecessor)) != null)
						xStructure.add(xStructure.root(), intersection);
				}
				if ((intersection = intersect(successor, predecessor)) != null)
					xStructure.remove(xStructure.root(), intersection); 
				
			} else if (currentEdge != null && currentEdge.isRight(currentPoint)) { 
				Edge successor = yStructure.successor(currentPoint); 
				Edge predecessor = yStructure.predecessor(currentPoint); 
				yStructure.remove(yRoot, currentPoint); 
				intersect(successor, predecessor); 
				
			} else { 
				intersections.add(currentPoint); 
				Edge top = currentPoint.top();
				Edge bottom = currentPoint.bottom(); 
				Edge topTop = yStructure.successor(currentPoint); 
				Edge bottomBottom = yStructure.predecessor(currentPoint); 
				yStructure.remove(yRoot, top.left());
				yStructure.remove(yRoot, bottom.left());
				yStructure.add(yRoot, top.right());
				yStructure.add(yRoot, bottom.right());
				if ((intersection = intersect(top, topTop)) != null)
					xStructure.remove(xStructure.root(), intersection); 
				if ((intersection = intersect(bottom, topTop)) != null)
					xStructure.add(xStructure.root(), intersection); 
				if ((intersection = intersect(top, bottomBottom)) != null)
					xStructure.add(xStructure.root(), intersection);
				if ((intersection = intersect(bottom, bottomBottom)) != null)
					xStructure.remove(xStructure.root(), intersection);
			}
		}
		return intersections;*/
		}
		return intersections;
	
	/* Point2D intersect(Edge edge1, Edge edge2) {
		if (edge1 == null || edge2 == null){
			return null;
		}

		Point2D p0 = edge1.left();
		Point2D p1 = edge1.right();
		Point2D p2 = edge2.left();
		Point2D p3 = edge2.right();
		
		//one of segments or both are vertical
		
		if ((p1.getX() == p0.getX()) && (p3.getX() != p2.getX())) {
			return null;	
		}
		else if ((p1.getX() != p0.getX()) && (p3.getX() == p2.getX())) {
			return null;
		}
		else if ((p1.getX() == p0.getX()) && (p3.getX() == p2.getX())) {
			return null;
		}
		else {
			//a = dy/dx
			double a1 = (p1.getY() - p0.getY())/(p1.getX() - p0.getX());
			double a2 = (p3.getY() - p2.getY())/(p3.getX() - p2.getX());
			double b1 = p1.getY() - a1 * p1.getX();
			double b2 = p3.getY() - a2 * p3.getX();
			
			//segments are parallel
			if (((a1 - a2) == 0) ^ ((b2 - b1) == 0)) {
				return null;
			}
			//segments have a common segment 
			else if (((a1 - a2) == 0) && ((b2 - b1) == 0)) {
				return null;
			} else {
				double xi = (b2 - b1)/(a1 - a2);
				boolean isInX1 = (((xi <= p1.getX()) && (xi >= p0.getX())) || ((xi >= p1.getX()) && (xi <= p0.getX())));
				boolean isInX2 = (((xi <= p3.getX()) && (xi >= p2.getX())) || ((xi >= p3.getX()) && (xi <= p2.getX())));
				if (isInX1 && isInX2) {
					double yi = (a2*xi)+b2;
					if (a1 >= 0 && a2 <= 0) {
						return new Point2D(xi, yi, edge1, edge2);
					} else {
						return new Point2D(xi, yi, edge2, edge1);
					}
				}
			}
		}
		return null;*/
	
	}
}
