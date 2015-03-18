package Model;

import java.util.*;

public class Test2 {
	
	public static void main(String[] args) {
		Point2D2 p1 = new Point2D2(0,6);
		Point2D2 p2 = new Point2D2(2,2);
		Point2D2 p3 = new Point2D2(3,6);
		Point2D2 p4 = new Point2D2(9,2);
		/*Point2D2 p5 = new Point2D2(8,5);
		Point2D2 p6 = new Point2D2(10,3);
		Point2D2 p7 = new Point2D2(12,5);*/
		ArrayList<Point2D2> line1 = new ArrayList<Point2D2>();
		line1.add(p1);
		line1.add(p2);
		line1.add(p3);
		line1.add(p4);
		/*line1.add(p5);
		line1.add(p6);
		line1.add(p7);*/

		
		Point2D2 p8 = new Point2D2(0,4);
		Point2D2 p9 = new Point2D2(8,4);
		Point2D2 p10 = new Point2D2(9,0); 
		/*Point2D2 p11 = new Point2D2(6,5);
		Point2D2 p12 = new Point2D2(8,3);
		Point2D2 p13 = new Point2D2(10,5); 
		Point2D2 p14 = new Point2D2(12,3);*/


		ArrayList<Point2D2> line2 = new ArrayList<Point2D2>();
		line2.add(p8);
		line2.add(p9);
		line2.add(p10);
		/*line2.add(p11);
		line2.add(p12);
		line2.add(p13);
		line2.add(p14);*/

		Polyline2D2 pl1 = new Polyline2D2(line1);
		Polyline2D2 pl2 = new Polyline2D2(line2);
		BentleyOttmann2 bo = new BentleyOttmann2(pl1, pl2);
		//inOrder(bo.xStructure.root());
		ArrayList<Point2D2> inters = bo.bentley();
		System.out.println(inters);
//		XStructure xstr = new XStructure(p3);
//		xstr.add(xstr.root(), p1);
//		xstr.add(xstr.root(), p2);
//		xstr.add(xstr.root(), p3);
//		xstr.add(xstr.root(), p4);
//		xstr.add(xstr.root(), p5);
//		xstr.add(xstr.root(), p6);
//		xstr.add(xstr.root(), p7);
//		System.out.println();
//		System.out.println(xstr.size());
//		inOrder(xstr.root());
//		xstr.removeMin();
//		System.out.println();
//		System.out.println(xstr.size());
//		inOrder(xstr.root());
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
