package Model;



import java.util.ArrayList;



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
			if (!(isBalanced(root))) {
				balanceAfterAdd(n1);
			}
		} 
		else if (e.getX() < n.getElement().getX() && n.getLeftChild() == null) { 
			BinaryTreeNode<Point2D> n1 = new BinaryTreeNode<Point2D>(e);
			n.setLeftChild(n1);
			n1.setParent(n);
			size++;
			if (!(isBalanced(root))) {
				balanceAfterAdd(n1);
			}
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
	
	private void balanceAfterAdd(BinaryTreeNode<Point2D> n) {
		BinaryTreeNode<Point2D> z = n.getParent();
		ArrayList<BinaryTreeNode<Point2D>> mark = new ArrayList<BinaryTreeNode<Point2D>>();
		mark.add(n);
		
		while (z.isBalanced()) {
			//System.out.println(mark);
			mark.add(z);
			z = z.getParent();
			//System.out.println("UP");
		}
		//System.out.println(z);
		BinaryTreeNode<Point2D> y = mark.get(mark.size() - 1);
		BinaryTreeNode<Point2D> x = mark.get(mark.size() - 2);
		
		if (y == z.getLeftChild() && x == y.getLeftChild()) {
			//System.out.println("LEFT LEFT");
			rotateRight(y);
		}
		else if (y == z.getLeftChild() && x == y.getRightChild()) {
			//System.out.println("LEFT RIGHT");
			rotateLeft(x);
			rotateRight(x);
		}
		else if (y == z.getRightChild() && x == y.getRightChild()) {
			//System.out.println("RIGHT RIGHT");
			rotateLeft(y);
		}
		else if (y == z.getRightChild() && x == y.getLeftChild()) {
			//System.out.println("RIGHT LEFT");
			rotateRight(x);
			rotateLeft(x);
		}
	}
	
	private void balanceAfterRemove(BinaryTreeNode<Point2D> n) {
		BinaryTreeNode<Point2D> z = n;
		
		while (z.isBalanced()) {
			//System.out.println(mark);
			z = z.getParent();
			//System.out.println("UP");
		}
		//System.out.println(z);
		BinaryTreeNode<Point2D> y;
		if (z.getLeftChild() != null && z.getRightChild() != null) {
			if (z.getLeftChild().getHeight() >= z.getRightChild().getHeight()) {
				y = z.getLeftChild();
			} else {
				y = z.getRightChild();
			}
		}
		else if (z.getLeftChild() == null && z.getRightChild() != null) {
			y = z.getRightChild();
		} 
		else {
			y = z.getLeftChild();
		}
		
		BinaryTreeNode<Point2D> x;
		if (y.getLeftChild() != null && y.getRightChild() != null) {
			if (y.getLeftChild().getHeight() >= y.getRightChild().getHeight()) {
				x = y.getLeftChild();
			} else {
				x = y.getRightChild();
			}
		}
		else if (y.getLeftChild() == null && y.getRightChild() != null) {
			x = y.getRightChild();
		} 
		else {
			x = y.getLeftChild();
		}
		
		if (y == z.getLeftChild() && x == y.getLeftChild()) {
			//System.out.println("LEFT LEFT");
			rotateRight(y);
		}
		else if (y == z.getLeftChild() && x == y.getRightChild()) {
			//System.out.println("LEFT RIGHT");
			rotateLeft(x);
			rotateRight(x);
		}
		else if (y == z.getRightChild() && x == y.getRightChild()) {
			//System.out.println("RIGHT RIGHT");
			rotateLeft(y);
		}
		else if (y == z.getRightChild() && x == y.getLeftChild()) {
			//System.out.println("RIGHT LEFT");
			rotateRight(x);
			rotateLeft(x);
		}
		if (!(isBalanced(root))) {
			balanceAfterRemove(z.getParent());
		}
	}
	
	private void rotateRight(BinaryTreeNode<Point2D> n){    //Rotate the chosen node to the the left
		if ((n.getParent()).isRoot()) {                   //Check if the parent is the root
			BinaryTreeNode<Point2D> tmp = n.getRightChild();
			//System.out.println(tmp);
			n.setRightChild(n.getParent());
			(n.getParent()).setParent(n);
			(n.getParent()).setLeftChild(tmp);
			if (tmp != null) {
				tmp.setParent(n.getParent());
			}
			n.setParent(null);
			root = n;
			
			/*BinaryTreeNode<Point2D> m = n.getLeftChild(); //Make a new treenode with the left part of the chosen node.
			n.setLeftChild(n.getParent());               //n's left child is his old parent.
			(n.getLeftChild()).setRightChild(m);         //n's new left child needs to have the new treenode as his right child.
			(n.getLeftChild()).setParent(n);             //n's new left child will have n as parent.
			*/
		}
		else {
			BinaryTreeNode<Point2D> tmp = n.getRightChild();
			n.setRightChild(n.getParent());
			(n.getParent()).setLeftChild(tmp);
			if (tmp != null) {
				tmp.setParent(n.getParent());
			}
			if(((n.getParent()).getParent()).getLeftChild() == n.getParent()) {    //Parent of parent (soon to be parent of n) needs n as left child
				((n.getParent()).getParent()).setLeftChild(n);
			} else {														 //Parent of parent (soon to be parent of n) needs n as right child
				((n.getParent()).getParent()).setRightChild(n);
			}
			BinaryTreeNode<Point2D> tmp2 = n.getParent();
			n.setParent((n.getParent()).getParent());
			tmp2.setParent(n);
			
			
			/*BinaryTreeNode<Point2D> m = n.getLeftChild();                         //Make a new treenode with the left part of the chosen node.
			n.setLeftChild(n.getParent());										 //n's left child is his old parent.
			(n.getLeftChild()).setRightChild(m);                                 //n's new left child needs to have the new treenode as his right child.
			if(((n.getParent()).getParent()).getLeftChild() == n.getParent())    //Parent of parent (soon to be parent of n) needs n as left child
				((n.getParent()).getParent()).setLeftChild(n);
			else																 //Parent of parent (soon to be parent of n) needs n as right child
				((n.getParent()).getParent()).setRightChild(n);
			(n.getLeftChild()).setParent(n);									 //n's new left child will have n as parent.
			*/
		}
	}
	
	private void rotateLeft(BinaryTreeNode<Point2D> n) {   //Rotate the chosen node to the the right
		if((n.getParent()).isRoot()) {                   //Check if the parent is the root
			BinaryTreeNode<Point2D> tmp = n.getLeftChild();
			n.setLeftChild(n.getParent());
			(n.getParent()).setParent(n);
			(n.getParent()).setRightChild(tmp);
			if (tmp != null) {
				tmp.setParent(n.getParent());
			}
			n.setParent(null);
			root = n;
			/*BinaryTreeNode<Point2D> m = n.getRightChild(); //Make a new treenode with the right part of the chosen node.
			n.setRightChild(n.getParent());               //n's Right child is his old parent.
			(n.getRightChild()).setLeftChild(m);         //n's new Right child needs to have the new treenode as his Left child.
			(n.getRightChild()).setParent(n);             //n's new Right child will have n as parent.
			*/
		}
		else {
			BinaryTreeNode<Point2D> tmp = n.getLeftChild();
			n.setLeftChild(n.getParent());
			(n.getParent()).setRightChild(tmp);
			if (tmp != null) {
				tmp.setParent(n.getParent());
			}
			if(((n.getParent()).getParent()).getRightChild() == n.getParent()) {    //Parent of parent (soon to be parent of n) needs n as left child
				((n.getParent()).getParent()).setRightChild(n);
			} else {														 //Parent of parent (soon to be parent of n) needs n as right child
				((n.getParent()).getParent()).setLeftChild(n);
			}
			BinaryTreeNode<Point2D> tmp2 = n.getParent();
			n.setParent((n.getParent()).getParent());
			tmp2.setParent(n);
			
			/*BinaryTreeNode<Point2D> m = n.getRightChild();                         //Make a new treenode with the Right part of the chosen node.
			n.setRightChild(n.getParent());										 //n's Right child is his old parent.
			(n.getRightChild()).setLeftChild(m);                                 //n's new Right child needs to have the new treenode as his Left child.
			if(((n.getParent()).getParent()).getRightChild() == n.getParent())    //Parent of parent (soon to be parent of n) needs n as Right child
				((n.getParent()).getParent()).setRightChild(n);
			else																 //Parent of parent (soon to be parent of n) needs n as Left child
				((n.getParent()).getParent()).setLeftChild(n);
			(n.getRightChild()).setParent(n);									 //n's new Right child will have n as parent.
			*/
		}
		
	}
	
	public Point2D remove(BinaryTreeNode<Point2D> n, Point2D e) {
		if (n == null) {
			return null;
		}  else {
			BinaryTreeNode<Point2D> m = search(e, n);
			if (m == null) {
				return null;
			}
			Point2D element = m.getElement();
			if(size == 1) {
				root = null;
				size--;
				return element;
			}
			BinaryTreeNode<Point2D> actionNode = m.getParent();
			
			if (m.isExternal()) {
				if((m.getParent()).getLeftChild() == m) {
					(m.getParent()).setLeftChild(null);
				} else {
					(m.getParent()).setRightChild(null);
				}
			}
			else if (m.getRightChild() != null && m.getLeftChild() == null) {
				if (!(m.isRoot())) {
					if((m.getParent()).getLeftChild() == m) {
						(m.getParent()).setLeftChild(m.getRightChild());
						(m.getRightChild()).setParent(m.getParent());
					} else {
						(m.getParent()).setRightChild(m.getRightChild());
						(m.getRightChild()).setParent(m.getParent());
					}
				} else {
					(m.getRightChild()).setParent(null);
					root = m.getRightChild();
				}
			}
			else if (m.getRightChild() == null && m.getLeftChild() != null) {
				if (!(m.isRoot())) {
					if((m.getParent()).getLeftChild() == m) {
						(m.getParent()).setLeftChild(m.getLeftChild());
						(m.getLeftChild()).setParent(m.getParent());
					} else {
						(m.getParent()).setRightChild(m.getLeftChild());
						(m.getLeftChild()).setParent(m.getParent());
					}
				} else {
					(m.getLeftChild()).setParent(null);
					root = m.getLeftChild();
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
			if (!(isBalanced(root))) {
				balanceAfterRemove(actionNode);
			}
			return element;
		}
	}
}
