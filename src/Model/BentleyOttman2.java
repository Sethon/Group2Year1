package Model;

import java.util.*;

import com.sun.javafx.collections.MappingChange.Map;

public class BentleyOttmann2 {

	private TreeMap<Point2D2, Point2D2> xStructure;
	private TreeMap<Point2D2, Point2D2> yStructure;
	
	public BentleyOttmann2(Polyline2D2 pl1, Polyline2D2 pl2) {
		
		xStructure = new TreeMap<>(new Helper2.XComparator());
		yStructure = new TreeMap<>(new Helper2.YComparator());

		
		
		for (int i = 0; i < pl1.vertices().size(); i++) {
			xStructure.put(pl1.vertices().get(i), pl1.vertices().get(i));
			System.out.println(pl1.vertices().get(i));
		}
		for (int i = 0; i < pl2.vertices().size(); i++) {
			xStructure.put(pl2.vertices().get(i), pl2.vertices().get(i));
		}
		System.out.println(xStructure);
	}
	
	public ArrayList<Point2D2> bentley() {
		
		ArrayList<Point2D2> intersections = new ArrayList<>();
		
		
		while (!(xStructure.isEmpty())) {			
			Point2D2 currentPoint = xStructure.firstKey();
			
			System.out.println("i am at: " + currentPoint);
			System.out.println(xStructure + " x");
			
			Edge currentEdge = null;
			boolean inter = false;
			boolean left = false;
			boolean specCase = false;
			
			
			if (currentPoint.prevEdge() == null && currentPoint.nextEdge() == null) {
				inter = true;
				xStructure.remove(currentPoint);
			} else {
				if (currentPoint.prevEdge() != null && currentPoint.nextEdge() == null) {
					xStructure.remove(currentPoint);
					currentEdge = currentPoint.prevEdge();
				}
				else if (currentPoint.prevEdge() == null && currentPoint.nextEdge() != null) {
					left = true;
					xStructure.remove(currentPoint);
					currentEdge = currentPoint.nextEdge();
				} else {
					specCase = true;
					currentEdge = currentPoint.prevEdge();
					currentPoint.setPrevEdge(null);
				}
				currentEdge = currentPoint.nextEdge();
			}
			
			if (!(inter)) {
				if (left){
					yStructure.put(currentPoint, currentPoint);
					Point2D2 suc = null;
					Point2D2 pred = null;
					if ((suc = yStructure.higherKey(currentPoint)) != null) {
						Point2D2 interPoint = Helper2.intersect(suc.nextEdge(), currentEdge, currentPoint, true);
						if (interPoint != null) {
							xStructure.put(interPoint, interPoint);
						}
					}
					if ((pred = yStructure.lowerKey(currentPoint)) != null) {
						Point2D2 interPoint = Helper2.intersect(pred.nextEdge(), currentEdge, currentPoint, true);
						if (interPoint != null) {
							xStructure.put(interPoint, interPoint);
						}
					}
					if (suc != null && pred != null) {
						Point2D2 interPoint = Helper2.intersect(pred.nextEdge(), suc.nextEdge(), currentPoint, true);
						if (interPoint != null) {
							xStructure.remove(interPoint);
						}
					}
				} else {
					if (specCase) {
						System.out.println("SPECIAL CASE!!!");
						Point2D2 suc = null;
						Point2D2 pred = null;
						suc = yStructure.higherKey(currentPoint);
						pred = yStructure.lowerKey(currentPoint);
						if (suc != null && pred != null) {
							Point2D2 interPoint = Helper2.intersect(suc.nextEdge(), pred.nextEdge(), currentPoint, true);
							if (interPoint != null) {
								xStructure.put(interPoint, interPoint);
							}
						}
						xStructure.remove(currentPoint);
						currentEdge = currentPoint.nextEdge();
						System.out.println(currentPoint.nextEdge());
						xStructure.put(currentPoint, currentPoint);
						Point2D2 suc1 = null;
						Point2D2 pred1 = null;
						suc1 = yStructure.higherKey(currentPoint);
						pred1 = yStructure.lowerKey(currentPoint);
						System.out.println(suc1);
						System.out.println(suc1);
						if (suc1 != null) {
							Point2D2 interPoint = Helper2.intersect(suc1.nextEdge(), currentEdge, currentPoint, true);
							if (interPoint != null) {
								xStructure.put(interPoint, interPoint);
							}
							Point2D2 interPoint2 = Helper2.intersect(suc1.prevEdge(), currentEdge, currentPoint, true);
							if (interPoint2 != null) {
								xStructure.put(interPoint2, interPoint2);
							}
						}
						if (pred1 != null) {
							Point2D2 interPoint = Helper2.intersect(pred1.nextEdge(), currentEdge, currentPoint, true);
							if (interPoint != null) {
								xStructure.put(interPoint, interPoint);
							}
							Point2D2 interPoint2 = Helper2.intersect(pred1.prevEdge(), currentEdge, currentPoint, true);
							if (interPoint2 != null) {
								xStructure.put(interPoint2, interPoint2);
							}
						}
						if (suc1 != null && pred1 != null) {
							Point2D2 interPoint = Helper2.intersect(pred1.nextEdge(), suc1.nextEdge(), currentPoint, true);
							if (interPoint != null) {
								xStructure.remove(interPoint);
							}
							
						}
						
						xStructure.remove(currentPoint);
					} else {
						//CAN TAKE A SHORTCUT HERE
						Point2D2 suc = null;
						Point2D2 pred = null;
						suc = yStructure.higherKey(currentPoint);
						pred = yStructure.lowerKey(currentPoint);
						yStructure.remove(currentPoint);
						if (suc != null && pred != null) {
							Point2D2 interPoint = Helper2.intersect(suc.nextEdge(), pred.nextEdge(), currentPoint, true);
							if (interPoint != null) {
								xStructure.put(interPoint, interPoint);
							}
						}
					}
				}
			} else {
				//Point2D2 intersection;
				intersections.add(currentPoint);
				
				System.out.println("inside inter");
				
				Edge top = currentPoint.top();
				Edge bottom = currentPoint.bottom();
				Point2D2 successorTop = yStructure.higherKey(top.left());
				Point2D2 predecessorBottom = yStructure.lowerKey(bottom.left());
				
				if (successorTop != null) {
					Point2D2 interPoint = Helper2.intersect(bottom, successorTop.nextEdge(), currentPoint, true);
					if (interPoint != null) {
						System.out.println("yo");
						xStructure.put(interPoint, interPoint);
					}
				}
				if (predecessorBottom != null) {
					Point2D2 interPoint = Helper2.intersect(top, predecessorBottom.nextEdge(), currentPoint, true);
					if (interPoint != null) {
						System.out.println("yo");
						xStructure.put(interPoint, interPoint);
					}
				}
				
				yStructure.remove(top.left());
				yStructure.remove(bottom.left());
				yStructure.put(top.right(), top.right());
				yStructure.put(bottom.right(), bottom.right());
				
				if (successorTop != null) {
					Point2D2 interPoint = Helper2.intersect(top, successorTop.nextEdge(), currentPoint, true);
					if (interPoint != null) {
						xStructure.remove(interPoint);
					}
				}
				if (predecessorBottom != null) {
					Point2D2 interPoint = Helper2.intersect(bottom, predecessorBottom.nextEdge(), currentPoint, true);
					if (interPoint != null) {
						xStructure.remove(interPoint);
					}
				}
			}
		
			
		System.out.println(yStructure + " y");
		System.out.println();
		System.out.println();
		System.out.println();
		}
		
		return intersections;	
	}
}
