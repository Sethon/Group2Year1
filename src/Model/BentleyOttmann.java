package Model;

import java.util.ArrayList;
import java.util.TreeMap;

public class BentleyOttmann {

	private ArrayList<Point2D> xStructure;
	private TreeMap<Edge, Edge> yStructure;
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
		xStructure = new ArrayList<>();
		yStructure = new TreeMap<>();
		//BinaryTreeNode<Point2D> xRoot = xStructure.root();
		System.out.println(pl1.edges() + " buh");
		System.out.println(pl2.edges()+ " b");
		for (int i = 0; i < pl1.edges().size(); i++) {
			xStructure.add(pl1.edges().get(i).active());
			xStructure.add(pl1.edges().get(i).inactive());
		}
		System.out.println(xStructure);

		for (int i = 0; i < pl2.edges().size(); i++) {
			xStructure.add(pl2.edges().get(i).active());
			xStructure.add(pl2.edges().get(i).inactive());
		}
		System.out.println(xStructure + "\n");
		/*for (int i = 1; i < pl1.edges().size(); i++) {
			xStructure.add(xStructure.root(), pl1.vertices().get(i));
		}
		for (int i = 1; i < pl2.edges().size(); i++) {
			xStructure.add(xStructure.root(), pl1.vertices().get(i));
		}*/
	}
	
	public ArrayList<Point2D> bentley() {
		
		ArrayList<Point2D> intersections = new ArrayList<>();
		Helper.sortByX(xStructure);
		
		while (!(xStructure.isEmpty())) {
			System.out.println("x " + xStructure);

			Helper.sortByX(xStructure);
			Point2D currentPoint = xStructure.get(0);
			xStructure.remove(currentPoint);
			Edge currentEdge = currentPoint.edge(); 
			//Point2D intersection; 
			if (currentEdge != null) {
				if (currentEdge.isActive(currentPoint)) {
					System.out.println("inside left :" + currentPoint);
					yStructure.put(currentEdge, currentEdge);
					System.out.println(yStructure + " yLeft");
					Edge suc = null;
					Edge pred = null;
					if ((suc = yStructure.higherKey(currentEdge)) != null) {
						Point2D interPoint = Helper.intersect(suc, currentEdge, currentPoint, true);
						if (interPoint != null) {
							System.out.println("yo");
							xStructure.add(interPoint);
						}
					}
					if ((pred = yStructure.lowerKey(currentEdge)) != null) {
						Point2D interPoint = Helper.intersect(pred, currentEdge, currentPoint, true);
						if (interPoint != null) {
							System.out.println("yo");
							xStructure.add(interPoint);
						}
					}
					if (suc != null && pred != null) {
						Point2D interPoint = Helper.intersect(pred, suc, currentPoint, true);
						if (interPoint != null) {
							System.out.println("yo");
							xStructure.remove(interPoint);
						}
					}
				} else {
					System.out.println("inside right :" + currentPoint);
					System.out.println(yStructure + " yRight");
					Edge successor = yStructure.higherKey(currentEdge);
					Edge predecessor = yStructure.lowerKey(currentEdge);
					yStructure.remove(currentEdge);
					if (successor != null && predecessor != null) {
						Point2D interPoint = Helper.intersect(predecessor, successor, currentPoint, true);
						if (interPoint != null) {
							System.out.println("yo");
							xStructure.add(interPoint);
						}
					}
				}
			} else {
				Point2D intersection;
				intersections.add(currentPoint);
				System.out.println("inside else");
				Edge top = currentPoint.top();
				Edge bottom = currentPoint.bottom();
				Edge topSuc = yStructure.higherKey(top);
				Edge bottomPred = yStructure.lowerKey(bottom);
//				yStructure.remove(top); yStructure.remove(bottom);
//				top.swapActive(); bottom.swapActive();
//				yStructure.put(top, top); yStructure.put(bottom, bottom);
				
				if ((intersection = Helper.intersect(top, topSuc, currentPoint, true)) != null)
					xStructure.remove(intersection); 
				if ((intersection = Helper.intersect(bottom, topSuc, currentPoint, true)) != null)
					xStructure.add(intersection); 
				if ((intersection = Helper.intersect(top, bottomPred, currentPoint, true)) != null)
					xStructure.add(intersection);
				if ((intersection = Helper.intersect(bottom, bottomPred, currentPoint, true)) != null)
					xStructure.remove(intersection);
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
				yStructure.remove(yRoot, top.active());
				yStructure.remove(yRoot, bottom.active());
				yStructure.add(yRoot, top.inactive());
				yStructure.add(yRoot, bottom.inactive());
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
	}
}
