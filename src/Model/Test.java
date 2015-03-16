package Model;

public class Test {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(new Point2D(5.0, 0.0));
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
		System.out.println(bst.isBalanced(bst.root()));
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
