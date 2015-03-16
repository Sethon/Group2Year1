package Model;




public class BinaryTreeNode<E> implements Position<E> {
	private E 					element;
	private BinaryTreeNode<E>	parent;
	private BinaryTreeNode<E> 	leftChild;
	private BinaryTreeNode<E> 	rightChild;
	
	public BinaryTreeNode(E e) {
		element = e;
		parent = null;
		leftChild = null;
		rightChild = null;
	}
	
	public BinaryTreeNode(E e, BinaryTreeNode<E> p) {
		element = e;
		parent = p;
		leftChild = null;
		rightChild = null;
		if (p.getLeftChild() == null) {
			p.setLeftChild(this);
		} else {
			p.setRightChild(this);
		}
	}
	
	public BinaryTreeNode(E e, BinaryTreeNode<E> c1, BinaryTreeNode<E> c2) {
		element = e;
		parent = null;
		leftChild = c1;
		rightChild = c2;
		c1.setParent(this);
		c2.setParent(this);
	}
	
	public BinaryTreeNode(E e, BinaryTreeNode<E> p, BinaryTreeNode<E> c1, BinaryTreeNode<E> c2) {
		element = e;
		parent = p;
		leftChild = c1;
		rightChild = c2;
		c1.setParent(this);
		c2.setParent(this);
		if (p.getLeftChild() == null) {
			p.setLeftChild(this);
		} else {
			p.setRightChild(this);
		}
	}
	
	public void setParent(BinaryTreeNode<E> p) {
		parent = p;
	}
	
	public BinaryTreeNode<E> getParent() {
		return parent;
	}
	
	public void setLeftChild(BinaryTreeNode<E> lc) {
		leftChild = lc;
	}
	
	public BinaryTreeNode<E> getLeftChild() {
		return leftChild;
	}
	
	public void setRightChild(BinaryTreeNode<E> rc) {
		rightChild = rc;
	}
	
	public BinaryTreeNode<E> getRightChild() {
		return rightChild;
	}
	
	public BinaryTreeNode<E> getSibling() {
		if (this == parent.getLeftChild()) {
			return parent.getRightChild();
		} else {
			return parent.getLeftChild();
		}
	}
	
	public void setElement(E e) {
		element = e;
	}
	
	public E getElement() {
		return element;
	}
	
	public boolean isInternal() {
		return ((leftChild != null) || (rightChild != null));
	}
	
	public boolean isExternal() {
		return ((leftChild == null) && (rightChild == null));
	}
	
	public int getHeight() {
		if(isExternal()) {
			return 1;
		} else {
			if (leftChild != null && rightChild == null) {
				return (1 + Math.max(leftChild.getHeight(), 0));
			}
			else if (leftChild == null && rightChild != null) {
				return (1 + Math.max(0, rightChild.getHeight()));
			} else {
				return (1 + Math.max(leftChild.getHeight(), rightChild.getHeight()));
			}
		}
	}
	
	public boolean isBalanced() {
		return (((getLeftChild().getHeight() - getRightChild().getHeight()) >= -1) && 
		((getLeftChild().getHeight() - getRightChild().getHeight()) <= 1));
	}
	
	public boolean isRoot() {
		return (parent == null);
	}
}
