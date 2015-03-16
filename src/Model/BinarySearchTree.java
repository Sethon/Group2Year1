package Model;



public class BinarySearchTree implements BinaryTree<Point2D>{
	private BinaryTreeNode<Point2D> 	root;
	private int 						size;
	
	public BinarySearchTree(Point2D p) {
		root = new BinaryTreeNode<Point2D>(p);
		size = 1;
	}
	
	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public BinaryTreeNode<Point2D> root() {
		return root;
	}
	
	public void add(Point2D e, BinaryTreeNode<Point2D> n) {
		if (e.getX() >= n.getElement().getX() && n.getRightChild() == null) {
			BinaryTreeNode<Point2D> n1 = new BinaryTreeNode<Point2D>(e);
			n.setRightChild(n1);
			n1.setParent(n);
			size++;
		} 
		else if (e.getX() < n.getElement().getX() && n.getLeftChild() == null) { 
			BinaryTreeNode<Point2D> n1 = new BinaryTreeNode<Point2D>(e);
			n.setLeftChild(n1);
			n1.setParent(n);
			size++;
		}
		else if (e.getX() >= n.getElement().getX() && n.getRightChild() != null) {
			add(e, n.getRightChild());
		}
		else if (e.getX() < n.getElement().getX() && n.getLeftChild() != null) { 
			add(e, n.getLeftChild());
		}
	}
	
	public BinaryTreeNode<Point2D> parent(BinaryTreeNode<Point2D> n) {
		return n.getParent();
	}
	
	public BinaryTreeNode<Point2D> search(Point2D e, BinaryTreeNode<Point2D> n) {
		if (n != null) {
			if (e.getX() == n.getElement().getX() && e.getY() == n.getElement().getY()) {
				return n;
			} else {
				if (e.getX() >= n.getElement().getX()) {
					return search(e, n.getRightChild());
				} else {
					return search(e, n.getLeftChild());
				}
			}
		} else {
			return null;
		}
	}

	public boolean isInternal(BinaryTreeNode<Point2D> n) {
		return n.isInternal();
	}

	public boolean isExternal(BinaryTreeNode<Point2D> n) {
		return n.isExternal();
	}

	public boolean isRoot(BinaryTreeNode<Point2D> n) {
		return n.isRoot();
	}

	public void swapElements(BinaryTreeNode<Point2D> n, BinaryTreeNode<Point2D> m) {
		Point2D tmp = n.getElement();
		n.setElement(m.getElement());
		m.setElement(tmp);
	}
	
	public boolean isBalanced(BinaryTreeNode<Point2D> n) {
		if (n != null) {
			if (n.isExternal()) {
				return true;
			} else {
				if(n.isBalanced()) {
					return (isBalanced(n.getLeftChild()) && isBalanced(n.getRightChild()));
				} else {
					return false;
				}
			}
		} else {
			return true;
		}
	}
	
	public void balance() {
		
		
	}
	
	public void rotateLeft(BinaryTreeNode<Point2D> n){    //Rotate the chosen node to the the left
		if((n.getParent()).isRoot()) {                   //Check if the parent is the root
			BinaryTreeNode<Point2D> m = n.getLeftChild(); //Make a new treenode with the left part of the chosen node.
			n.setLeftChild(n.getParent());               //n's left child is his old parent.
			(n.getLeftChild()).setRightChild(m);         //n's new left child needs to have the new treenode as his right child.
			(n.getLeftChild()).setParent(n);             //n's new left child will have n as parent.
		}
		else{
			BinaryTreeNode<Point2D> m = n.getLeftChild();                         //Make a new treenode with the left part of the chosen node.
			n.setLeftChild(n.getParent());										 //n's left child is his old parent.
			(n.getLeftChild()).setRightChild(m);                                 //n's new left child needs to have the new treenode as his right child.
			if(((n.getParent()).getParent()).getLeftChild() == n.getParent())    //Parent of parent (soon to be parent of n) needs n as left child
				((n.getParent()).getParent()).setLeftChild(n);
			else																 //Parent of parent (soon to be parent of n) needs n as right child
				((n.getParent()).getParent()).setRightChild(n);
			(n.getLeftChild()).setParent(n);									 //n's new left child will have n as parent.
		}
	}
	
	public void rotateRight(BinaryTreeNode<Point2D> n){   //Rotate the chosen node to the the right
		if((n.getParent()).isRoot()) {                   //Check if the parent is the root
			BinaryTreeNode<Point2D> m = n.getRightChild(); //Make a new treenode with the right part of the chosen node.
			n.setRightChild(n.getParent());               //n's Right child is his old parent.
			(n.getRightChild()).setLeftChild(m);         //n's new Right child needs to have the new treenode as his Left child.
			(n.getRightChild()).setParent(n);             //n's new Right child will have n as parent.
		}
		else{
			BinaryTreeNode<Point2D> m = n.getRightChild();                         //Make a new treenode with the Right part of the chosen node.
			n.setRightChild(n.getParent());										 //n's Right child is his old parent.
			(n.getRightChild()).setLeftChild(m);                                 //n's new Right child needs to have the new treenode as his Left child.
			if(((n.getParent()).getParent()).getRightChild() == n.getParent())    //Parent of parent (soon to be parent of n) needs n as Right child
				((n.getParent()).getParent()).setRightChild(n);
			else																 //Parent of parent (soon to be parent of n) needs n as Left child
				((n.getParent()).getParent()).setLeftChild(n);
			(n.getRightChild()).setParent(n);									 //n's new Right child will have n as parent.
		}
		
	}
	
	public Point2D remove(BinaryTreeNode<Point2D> n, Point2D e) {
		if (n == null) {
			return null;
		}  else {
			BinaryTreeNode<Point2D> m = search(e, n);
			Point2D element = m.getElement();
			if(size == 1) {
				root = null;
				return element;
			}
			if (m.isExternal()) {
				if((m.getParent()).getLeftChild() == m) {
					(m.getParent()).setLeftChild(null);
				} else {
					(m.getParent()).setRightChild(null);
				}
			}
			else if (m.getRightChild() != null && m.getLeftChild() == null) {
				if((m.getParent()).getLeftChild() == m) {
					(m.getParent()).setLeftChild(m.getRightChild());
					(m.getRightChild()).setParent(m.getParent());
				} else {
					(m.getParent()).setRightChild(m.getRightChild());
					(m.getRightChild()).setParent(m.getParent());
				}
			}
			else if (m.getRightChild() == null && m.getLeftChild() != null) {
				if((m.getParent()).getLeftChild() == m) {
					(m.getParent()).setLeftChild(m.getLeftChild());
					(m.getLeftChild()).setParent(m.getParent());
				} else {
					(m.getParent()).setRightChild(m.getLeftChild());
					(m.getLeftChild()).setParent(m.getParent());
				}
			}
			
			else if (m.getRightChild() != null && m.getLeftChild() != null) {
				BinaryTreeNode<Point2D> s = m.getRightChild();
				if (s.getLeftChild() == null) {
					s.setLeftChild(m.getLeftChild());
					(m.getLeftChild()).setParent(s);
					if(!(m.isRoot())) {
						s.setParent(m.getParent());
						if((m.getParent()).getLeftChild() == m) {
							(m.getParent()).setLeftChild(s);
						} else {
							(m.getParent()).setRightChild(s);
						}
					} else {
						s.setParent(null);
						root = s;
					}
				} else {
					while (s.getLeftChild() != null) {
						s = s.getLeftChild();
					}
					s.setLeftChild(m.getLeftChild());
					(m.getLeftChild()).setParent(s);
					s.setRightChild(m.getLeftChild());
					(m.getRightChild()).setParent(s);
					(s.getParent()).setLeftChild(null);
					if(!(m.isRoot())) {
						s.setParent(m.getParent());
						if((m.getParent()).getLeftChild() == m) {
							(m.getParent()).setLeftChild(s);
						} else {
							(m.getParent()).setRightChild(s);
						}
					} else {
						s.setParent(null);
						root = s;
					}
				}
				

			}
			size--;
			m = null;
			return element;
		}
	}
}
