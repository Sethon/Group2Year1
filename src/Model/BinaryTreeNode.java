package Model;

public class BinaryTreeNode<E> implements Position<E> {
	private E 					element;
	private BinaryTreeNode<E>	parent;
	private BinaryTreeNode<E> 	leftChild;
	private BinaryTreeNode<E> 	rightChild;
	private int 				balanceF;
	
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
		if (p.leftChild() == null) {
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
		if (p.leftChild() == null) {
			p.setLeftChild(this);
		} else {
			p.setRightChild(this);
		}
	}
	
	public void setParent(BinaryTreeNode<E> p) {
		parent = p;
	}
	
	public BinaryTreeNode<E> parent() {
		return parent;
	}
	
	public void setLeftChild(BinaryTreeNode<E> lc) {
		leftChild = lc;
	}
	
	public BinaryTreeNode<E> leftChild() {
		return leftChild;
	}
	
	public void setRightChild(BinaryTreeNode<E> rc) {
		rightChild = rc;
	}
	
	public BinaryTreeNode<E> rightChild() {
		return rightChild;
	}
	
	public BinaryTreeNode<E> sibling() {
		if (this == parent.leftChild()) {
			return parent.rightChild();
		} else {
			return parent.leftChild();
		}
	}
	
	public void setElement(E e) {
		element = e;
	}
	
	public E element() {
		return element;
	}
	
	public boolean isInternal() {
		return ((leftChild != null) || (rightChild != null));
	}
	
	public boolean isExternal() {
		return ((leftChild == null) && (rightChild == null));
	}
	
	public int height() {
		if(isExternal()) {
			return 1;
		} else {
			if (leftChild != null && rightChild == null) {
				return (1 + Math.max(leftChild.height(), 0));
			}
			else if (leftChild == null && rightChild != null) {
				return (1 + Math.max(0, rightChild.height()));
			} else {
				return (1 + Math.max(leftChild.height(), rightChild.height()));
			}
		}
	}
	
	public boolean isBalanced() {
		if (isExternal()) {
			return true;
		} else {
			if (leftChild != null && rightChild != null) {
				return (((leftChild.height() - rightChild.height()) >= -1) && 
				((leftChild.height() - rightChild.height()) <= 1));
			}
			else if (leftChild != null && rightChild == null) {
				return (((leftChild.height() >= -1) && 
				((leftChild.height() <= 1))));
			} else {
				return (((rightChild.height()) >= -1) && 
				((rightChild.height()) <= 1));
			}
		}
	}
	
	public void initBalance() {
		if (leftChild != null && rightChild != null) {
			balanceF = leftChild.height() - rightChild.height();
		}
		else if (leftChild == null && rightChild != null) {
			balanceF = 0 - rightChild.height();
		}
		else if (leftChild != null && rightChild != null){
			balanceF = leftChild.height();
		} else {
			balanceF = 0;
		}
	}
	
	public int getBalance() {
		return balanceF;
	}
	
	public boolean isRoot() {
		return (parent == null);
	}
	
	public String toString() {
		return element.toString();
	}
}
