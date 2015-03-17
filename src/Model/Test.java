package Model;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		System.out.println(Helper.areIntersect(new Point2D(1.0, 5.0), new Point2D(1.0, 0.0), new Point2D(3.0, 0.0), new Point2D(Double.MIN_VALUE, 5.0)));
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
