package Model;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		Point2D p1 = new Point2D(0,3);
		Point2D p2 = new Point2D(4,0);
		Point2D p3 = new Point2D(9,6);
		Point2D p4 = new Point2D(14,1); 
		ArrayList<Point2D> line1 = new ArrayList<Point2D>();
		line1.add(p1);
		line1.add(p2);
		line1.add(p3);
		line1.add(p4);
		
		Point2D p5 = new Point2D(0,0);
		Point2D p6 = new Point2D(4,4);
		Point2D p7 = new Point2D(15,4); 
		ArrayList<Point2D> line2 = new ArrayList<Point2D>();
		line2.add(p5);
		line2.add(p6);
		line2.add(p7);

		Polyline2D pl1 = new Polyline2D(line1);
		Polyline2D pl2 = new Polyline2D(line2);
		BentleyOttmann bo = new BentleyOttmann(pl1, pl2);
		ArrayList<Point2D> inters = bo.bentley();
		System.out.println(inters);
		/*XStructure xstr = new XStructure(p3);
		xstr.add(xstr.root(), p1);
		xstr.add(xstr.root(), p2);
		//xstr.add(xstr.root(), p3);
		xstr.add(xstr.root(), p4);
		xstr.add(xstr.root(), p5);
		xstr.add(xstr.root(), p6);
		xstr.add(xstr.root(), p7);
		System.out.println();
		System.out.println(xstr.size());
		inOrder(xstr.root());
		xstr.removeMin();
		System.out.println();
		System.out.println(xstr.size());
		inOrder(xstr.root());*/
	}
	
	public static <E> void inOrder(BinaryTreeNode<E> v) {
		if (v != null) {
			if (v.isInternal()) {
				inOrder(v.leftChild());
			}
			System.out.print(v.element() + " ");
			if (v.isInternal()) {
				inOrder(v.rightChild());
			}
		}
	}
}
