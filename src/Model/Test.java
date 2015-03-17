package Model;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(new Point2D(5.0, 0.0));
		
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(5,0);
		Point2D p3 = new Point2D(5,5);
		Point2D p4 = new Point2D(3,5);
		Point2D p5 = new Point2D(2,1);
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		Polyline2D p2d = new Polyline2D(points);
		Point2D point = new Point2D(4,4);
		if(Helper.contains(p2d, point))
			System.out.println("It contains the point");
		//System.out.println("ROOT: " + bst.root());
		bst.add(new Point2D(4.0, 7.0), bst.root());
		//System.out.println("ROOT: " + bst.root());
		//bst.add(new Point2D(7.0, 9.0), bst.root());
		bst.add(new Point2D(6.0, 1.0), bst.root());
		//System.out.println("ROOT: " + bst.root());
		bst.add(new Point2D(3.0, 100.0), bst.root());
		//System.out.println("ROOT: " + bst.root());
		bst.add(new Point2D(4.5, -5.0), bst.root());
		bst.add(new Point2D(5.5, 0.9), bst.root());
		bst.add(new Point2D(7.0, 0.9), bst.root());
		//System.out.println(bst.isBalanced(bst.root()));
		//System.out.println(bst.root().getRightChild());
		inOrder(bst.root());
		//bst.remove(bst.root(), new Point2D(5.0, 0.0));
		System.out.println(bst.isBalanced(bst.root()));
		//System.out.println("ROOT: " + bst.root());
		bst.remove(bst.root(), new Point2D(7.0, 0.9));
		bst.remove(bst.root(), new Point2D(5.5, 0.9));
		bst.remove(bst.root(), new Point2D(6.0, 1.0));
		//bst.remove(bst.root(), new Point2D(4.0, 100.0));
		inOrder(bst.root());
		for (int i = 0; i < 100; i++) {
			bst.add(new Point2D(Math.random()*1000, 0.9), bst.root());
		}
		
		System.out.println();
		inOrder(bst.root());
		System.out.println(bst.isBalanced(bst.root()));
		System.out.println(bst.size());
	}
	
	public static <E> void inOrder(BinaryTreeNode<E> v) {
		if (v != null) {
			if (v.isInternal()) {
				inOrder(v.getLeftChild());
			}
			System.out.print(v.getElement() + " ");
			if (v.isInternal()) {
				inOrder(v.getRightChild());
			}
		}
	}
}
