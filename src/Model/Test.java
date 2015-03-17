package Model;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(3,5);
		Point2D p3 = new Point2D(5,3);
		Point2D p4 = new Point2D(8,7);                 
		Point2D p5 = new Point2D(2,8);
		Point2D p6 = new Point2D(1,9);
		Point2D p7 = new Point2D(1,5);
		Point2D p8 = new Point2D(2,5);
		Point2D p9 = new Point2D(1,1);
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		points.add(p6);
		points.add(p7);
		points.add(p8);
		points.add(p9);
		Polyline2D p2d = new Polyline2D(points);
		Point2D point = new Point2D(10,10);
		if(Helper.contains(p2d, point))
			System.out.println("It contains the point");
		else System.out.println("It does not contain the point.");
	}
	
	public static <E> void inOrder(BinaryTreeNode<E> v) {
		if (v != null) {
			if (v.isInternal()) {
				inOrder(v.leftChild());
			}
//			System.out.print(v.element() + " ");
			if (v.isInternal()) {
				inOrder(v.rightChild());
			}
		}
	}
}
