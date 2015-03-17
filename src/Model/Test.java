package Model;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		Point2D p1 = new Point2D(1,1);
		Point2D p2 = new Point2D(4,5);
		Point2D p3 = new Point2D(3,4);
		Point2D p4 = new Point2D(3,2);                 
		YStructure ystr = new  YStructure(p1);
		ystr.add(ystr.root(), p2);
		ystr.add(ystr.root(), p3);
		ystr.add(ystr.root(), p4);
		System.out.println(ystr.successor(p2));
		inOrder(ystr.root());
	
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
