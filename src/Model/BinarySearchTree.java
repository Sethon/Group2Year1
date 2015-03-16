package Model;



public class BinarySearchTree implements BinaryTree<Double>{
	private BinaryTreeNode<Double> 	root;
	private int 					size;
	
	public BinarySearchTree(Double e) {
		root = new BinaryTreeNode<Double>(e);
		size = 1;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public BinaryTreeNode<Double> root() {
		return root;
	}
	
	public void add(Double e, BinaryTreeNode<Double> n) {
		if (e >= n.getElement() && n.getRightChild() == null) {
			BinaryTreeNode<Double> n1 = new BinaryTreeNode<Double>(e);
			n.setRightChild(n1);
			n1.setParent(n);
			size++;
		} 
		else if (e < n.getElement() && n.getLeftChild() == null) { 
			BinaryTreeNode<Double> n1 = new BinaryTreeNode<Double>(e);
			n.setLeftChild(n1);
			n1.setParent(n);
			size++;
		}
		else if (e >= n.getElement() && n.getRightChild() != null) {
			add(e, n.getRightChild());
		}
		else if (e < n.getElement() && n.getLeftChild() != null) { 
			add(e, n.getLeftChild());
		}
	}
	
	public BinaryTreeNode<Double> parent(BinaryTreeNode<Double> n) {
		return n.getParent();
	}
	
	public BinaryTreeNode<Double> search(Double e, BinaryTreeNode<Double> n) {
		if (n != null) {
			if ((double) e == (double) n.getElement()) {
				return n;
			} else {
				if (e >= n.getElement()) {
					return search(e, n.getRightChild());
				} else {
					return search(e, n.getLeftChild());
				}
			}
		} else {
			return null;
		}
	}

	public boolean isInternal(BinaryTreeNode<Double> n) {
		return n.isInternal();
	}

	public boolean isExternal(BinaryTreeNode<Double> n) {
		return n.isExternal();
	}

	public boolean isRoot(BinaryTreeNode<Double> n) {
		return n.isRoot();
	}

	public void swapElements(BinaryTreeNode<Double> n, BinaryTreeNode<Double> m) {
		Double tmp = n.getElement();
		n.setElement(m.getElement());
		m.setElement(tmp);
	}

}
