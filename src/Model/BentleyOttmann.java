package Model;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.TreeMap;

public class BentleyOttmann {

	private TreeMap<Point2D, Point2D> xStructure;
	private TreeMap<Point2D, Point2D> yStructure;
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
		xStructure = new TreeMap<>();
		yStructure = new TreeMap<>(new Helper.YComparator());
		//BinaryTreeNode<Point2D> xRoot = xStructure.root();
		for (int i = 0; i < pl1.edges().size(); i++) {
			xStructure.put(pl1.edges().get(i).left(), pl1.edges().get(i).left());
			xStructure.put(pl1.edges().get(i).right(), pl1.edges().get(i).right());
		}
		for (int i = 0; i < pl2.edges().size(); i++) {
			xStructure.put(pl2.edges().get(i).left(), pl2.edges().get(i).left());
			xStructure.put(pl2.edges().get(i).right(), pl2.edges().get(i).right());
		}
		System.out.println(xStructure);
		/*for (int i = 1; i < pl1.edges().size(); i++) {
			xStructure.add(xStructure.root(), pl1.vertices().get(i));
		}
		for (int i = 1; i < pl2.edges().size(); i++) {
			xStructure.add(xStructure.root(), pl1.vertices().get(i));
		}*/
	}
	
	public ArrayList<Point2D> bentley() {
		
		ArrayList<Point2D> intersections = new ArrayList<>();
		
		while (!(xStructure.isEmpty())) {
			
			System.out.println(xStructure + " x");
			System.out.println(yStructure + " y");
			Point2D currentPoint = xStructure.firstKey();
			xStructure.remove(currentPoint);
			//System.out.println(xStructure.size());
			//System.out.println(currentPoint);
			Edge currentEdge = currentPoint.edge(); 
			//BinaryTreeNode<Point2D> yRoot = yStructure.root(); ???
			//Point2D intersection; ???
			if (currentEdge != null) {
				if (currentEdge.isLeft(currentPoint)) {
					System.out.println("inside left :" + currentPoint);
					yStructure.put(currentPoint, currentPoint);
					System.out.println(yStructure + " yLeft");
					Point2D suc = null;
					Point2D pred = null;
					if ((suc = yStructure.higherKey(currentPoint)) != null) {
						Point2D interPoint = Helper.intersect(suc.edge(), currentEdge, currentPoint, true);
						if (interPoint != null) {
							System.out.println("yo");
							xStructure.put(interPoint, interPoint);
						}
					}
					if ((pred = yStructure.lowerKey(currentPoint)) != null) {
						Point2D interPoint = Helper.intersect(pred.edge(), currentEdge, currentPoint, true);
						if (interPoint != null) {
							System.out.println("yo");
							xStructure.put(interPoint, interPoint);
						}
					}
					if (suc != null && pred != null) {
						Point2D interPoint = Helper.intersect(pred.edge(), suc.edge(), currentPoint, true);
						if (interPoint != null) {
							System.out.println("yo");
							xStructure.remove(interPoint);
						}
					}
				} else {
					System.out.println("inside right :" + currentPoint);
					System.out.println(yStructure + " yRight");
					Point2D suc = null;
					Point2D pred = null;
					Edge successor = null;
					Edge predecessor = null;
					if ((suc = yStructure.higherKey(currentPoint)) != null) {
						successor = suc.edge();
					}
					if ((pred = yStructure.lowerKey(currentPoint)) != null) {
						predecessor = pred.edge();
					}
					yStructure.remove(currentPoint);
					if (suc != null && pred != null) {
						Point2D interPoint = Helper.intersect(predecessor, successor, currentPoint, true);
						if (interPoint != null) {
							System.out.println("yo");
							xStructure.put(interPoint, interPoint);
						}
					}
				}
			} else {
				intersections.add(currentPoint);
				System.out.println("inside else");
				Edge top = currentPoint.top();
				Edge bottom = currentPoint.bottom();
				Point2D successorTop = null;
				Point2D predecessorBottom = null;
				if ((successorTop = yStructure.higherKey(top.left()))!= null) {
					Point2D interPoint = Helper.intersect(bottom, successorTop.edge(), currentPoint, true);
					if (interPoint != null) {
						System.out.println("yo");
						xStructure.put(interPoint, interPoint);
					}
				}
				if ((predecessorBottom = yStructure.lowerKey(bottom.left())) != null) {
					Point2D interPoint = Helper.intersect(top, predecessorBottom.edge(), currentPoint, true);
					if (interPoint != null) {
						System.out.println("yo");
						xStructure.put(interPoint, interPoint);
					}
				}
				yStructure.remove(top.left());
				yStructure.remove(bottom.left());
				yStructure.put(bottom.right(), top.right());
				yStructure.put(bottom.right(), bottom.right());
				if (successorTop != null) {
					Point2D interPoint = Helper.intersect(top, successorTop.edge(), currentPoint, true);
					if (interPoint != null) {
						xStructure.remove(interPoint);
					}
				}
				if (predecessorBottom != null) {
					Point2D interPoint = Helper.intersect(bottom, predecessorBottom.edge(), currentPoint, true);
					if (interPoint != null) {
						xStructure.remove(interPoint);
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
