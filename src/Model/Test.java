package Model;

public class Test {
	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(5.0);
		bst.add(4.0, bst.root());
		bst.add(6.0, bst.root());
		bst.add(7.0, bst.root());
		bst.add(3.0, bst.root());
		bst.add(2.5, bst.root());
		bst.add(10.5, bst.root());
		
		inOrder(bst.root());
		bst.remove(bst.root(), 3.0);
		bst.remove(bst.root(), 2.5);
		bst.remove(bst.root(), 7.0);
		bst.remove(bst.root(), 10.5);
		bst.remove(bst.root(), 6.0);
		bst.remove(bst.root(), 4.0);
		bst.remove(bst.root(), 5.0);
		System.out.println();
		inOrder(bst.root());
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
