package Model;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(4,5);
		Point2D p3 = new Point2D(3,4);
		Point2D p4 = new Point2D(3,2);                 
		Edge e1 = new Edge(p1, p2);
		Edge e2 = new Edge(p3, p4);
		System.out.println(Helper.intersect(e1, e2));
	
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
