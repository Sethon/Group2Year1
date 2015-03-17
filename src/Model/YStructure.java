package Model;

import java.util.ArrayList;

public class YStructure implements BinaryTree<Point2D>{
	private BinaryTreeNode<Point2D> 	root;
	private int 						size;
	
	public YStructure(Point2D p) {
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
	
	public void add(BinaryTreeNode<Point2D> n, Point2D e) {
		if (e.getY() >= n.element().getY() && n.rightChild() == null) {
			BinaryTreeNode<Point2D> n1 = new BinaryTreeNode<Point2D>(e);
			n.setRightChild(n1);
			n1.setParent(n);
			size++;
			if (!(isBalanced(root))) {
				balanceAfterAdd(n1);
			}
		} 
		else if (e.getY() < n.element().getY() && n.leftChild() == null) { 
			BinaryTreeNode<Point2D> n1 = new BinaryTreeNode<Point2D>(e);
			n.setLeftChild(n1);
			n1.setParent(n);
			size++;
			if (!(isBalanced(root))) {
				balanceAfterAdd(n1);
			}
		}
		else if (e.getY() >= n.element().getY() && n.rightChild() != null) {
			add(n.rightChild(), e);
		}
		else if (e.getY() < n.element().getY() && n.leftChild() != null) { 
			add(n.leftChild(), e);
		}
	}
	
	public BinaryTreeNode<Point2D> parent(BinaryTreeNode<Point2D> n) {
		return n.parent();
	}
	
	public BinaryTreeNode<Point2D> search(Point2D e, BinaryTreeNode<Point2D> n) {
		if (n != null) {
			if (e.getY() == n.element().getY() && e.getY() == n.element().getY()) {
				return n;
			} else {
				if (e.getY() >= n.element().getY()) {
					return search(e, n.rightChild());
				} else {
					return search(e, n.leftChild());
				}
			}
		} else {
			return null;
		}
	}
	
	//successor - ABOVE
	public Edge successor(Point2D p) {
		BinaryTreeNode<Point2D> currNode = search(p, root);
		
		/*if (currNode.rightChild() != null) {
			return findMin(currNode.rightChild()).edge();
		}
		BinaryTreeNode<Point2D> actionNode = root;
		while (actionNode != null) {
			if (pn.element().getY() < actionNode.element().getY()) {
				successor = actionNode;
				actionNode = actionNode.leftChild();
			} 
			else if (pn.element().getY() > root.element().getY()) {
				actionNode = actionNode.rightChild();
			}
			else
				break;
		}
		if (successor == null) 
			return null;
		return successor.element().edge();*/
		
		if (currNode.rightChild() == null) {
			if (currNode.isRoot()) {
				return null;
			} else {
				while (currNode.parent() != null) {
					if ((currNode.parent()).leftChild() == currNode) {
						return (currNode.parent()).element().edge();
					}
					currNode = currNode.parent();
				}
				return null;
			}
		} else {
			currNode = currNode.rightChild();
			while (currNode.leftChild() != null) {
				currNode = currNode.leftChild();
			}
			return currNode.element().edge();
		}
	}
	
	//predecessor - BELOW
	public Edge predecessor(Point2D p) {
		BinaryTreeNode<Point2D> currNode = search(p, root);
		if (currNode.leftChild() == null) {
			if (currNode.isRoot()) {
				return null;
			} else {
				while (currNode.parent() != null) {
					if ((currNode.parent()).rightChild() == currNode) {
						return (currNode.parent()).element().edge();
					}
					currNode = currNode.parent();
				}
				return null;
			}
		} else {
			currNode = currNode.leftChild();
			while (currNode.rightChild() != null) {
				currNode = currNode.rightChild();
			}
			return currNode.element().edge();
		}
		/*BinaryTreeNode<Point2D> pn = search(p, root);
		if (pn.leftChild() != null) {
			return findMax(pn.leftChild()).edge();
		}
		BinaryTreeNode<Point2D> predecessor = pn.parent();
		
		while (predecessor != null && pn == predecessor.leftChild()) {
			pn = predecessor;
			predecessor = predecessor.parent();
		}
		if (predecessor == null) 
			return null;
		return predecessor.element().edge();*/
		
		
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
	
	/*public Point2D findMin(BinaryTreeNode<Point2D> currNode) {
		if (isEmpty()) {
			return null;
		} else {
			while (currNode.leftChild() != null) {
				currNode = currNode.leftChild();
			}
			return currNode.element(); 
		}
	}
	
	public Point2D findMax(BinaryTreeNode<Point2D> currNode) {
		if (isEmpty()) {
			return null;
		} else {
			while (currNode.rightChild() != null) {
				currNode = currNode.rightChild();
			}
			return currNode.element(); 
		}
	}*/

	public void swapElements(BinaryTreeNode<Point2D> n, BinaryTreeNode<Point2D> m) {
		Point2D tmp = n.element();
		n.setElement(m.element());
		m.setElement(tmp);
	}
	
	public boolean isBalanced(BinaryTreeNode<Point2D> n) {
		if (n != null) {
			if (n.isExternal()) {
				return true;
			} else {
				if(n.isBalanced()) {
					return (isBalanced(n.leftChild()) && isBalanced(n.rightChild()));
				} else {
					return false;
				}
			}
		} else {
			return true;
		}
	}
	
	private void balanceAfterAdd(BinaryTreeNode<Point2D> n) {
		BinaryTreeNode<Point2D> z = n.parent();
		ArrayList<BinaryTreeNode<Point2D>> mark = new ArrayList<BinaryTreeNode<Point2D>>();
		mark.add(n);
		
		while (z.isBalanced()) {
			mark.add(z);
			z = z.parent();
		}
		BinaryTreeNode<Point2D> y = mark.get(mark.size() - 1);
		BinaryTreeNode<Point2D> x = mark.get(mark.size() - 2);
		
		if (y == z.leftChild() && x == y.leftChild()) {
			rotateRight(y);
		}
		else if (y == z.leftChild() && x == y.rightChild()) {
			rotateLeft(x);
			rotateRight(x);
		}
		else if (y == z.rightChild() && x == y.rightChild()) {
			rotateLeft(y);
		}
		else if (y == z.rightChild() && x == y.leftChild()) {
			rotateRight(x);
			rotateLeft(x);
		}
	}
	
	private void balanceAfterRemove(BinaryTreeNode<Point2D> n) {
		BinaryTreeNode<Point2D> z = n;
		
		while (z.isBalanced()) {
			z = z.parent();
		}
		BinaryTreeNode<Point2D> y;
		if (z.leftChild() != null && z.rightChild() != null) {
			if (z.leftChild().height() >= z.rightChild().height()) {
				y = z.leftChild();
			} else {
				y = z.rightChild();
			}
		}
		else if (z.leftChild() == null && z.rightChild() != null) {
			y = z.rightChild();
		} 
		else {
			y = z.leftChild();
		}
		
		BinaryTreeNode<Point2D> x;
		if (y.leftChild() != null && y.rightChild() != null) {
			if (y.leftChild().height() >= y.rightChild().height()) {
				x = y.leftChild();
			} else {
				x = y.rightChild();
			}
		}
		else if (y.leftChild() == null && y.rightChild() != null) {
			x = y.rightChild();
		} 
		else {
			x = y.leftChild();
		}
		
		if (y == z.leftChild() && x == y.leftChild()) {
			rotateRight(y);
		}
		else if (y == z.leftChild() && x == y.rightChild()) {
			rotateLeft(x);
			rotateRight(x);
		}
		else if (y == z.rightChild() && x == y.rightChild()) {
			rotateLeft(y);
		}
		else if (y == z.rightChild() && x == y.leftChild()) {
			rotateRight(x);
			rotateLeft(x);
		}
		if (!(isBalanced(root))) {
			balanceAfterRemove(z.parent());
		}
	}
	
	private void rotateRight(BinaryTreeNode<Point2D> n){ 
		if ((n.parent()).isRoot()) {                   
			BinaryTreeNode<Point2D> tmp = n.rightChild();
			n.setRightChild(n.parent());
			(n.parent()).setParent(n);
			(n.parent()).setLeftChild(tmp);
			if (tmp != null) {
				tmp.setParent(n.parent());
			}
			n.setParent(null);
			root = n;
		}
		else {
			BinaryTreeNode<Point2D> tmp = n.rightChild();
			n.setRightChild(n.parent());
			(n.parent()).setLeftChild(tmp);
			if (tmp != null) {
				tmp.setParent(n.parent());
			}
			if(((n.parent()).parent()).leftChild() == n.parent()) {    //Parent of parent (soon to be parent of n) needs n as left child
				((n.parent()).parent()).setLeftChild(n);
			} else {														 //Parent of parent (soon to be parent of n) needs n as right child
				((n.parent()).parent()).setRightChild(n);
			}
			BinaryTreeNode<Point2D> tmp2 = n.parent();
			n.setParent((n.parent()).parent());
			tmp2.setParent(n);
		}
	}
	
	private void rotateLeft(BinaryTreeNode<Point2D> n) {   //Rotate the chosen node to the the right
		if((n.parent()).isRoot()) {                   //Check if the parent is the root
			BinaryTreeNode<Point2D> tmp = n.leftChild();
			n.setLeftChild(n.parent());
			(n.parent()).setParent(n);
			(n.parent()).setRightChild(tmp);
			if (tmp != null) {
				tmp.setParent(n.parent());
			}
			n.setParent(null);
			root = n;
		}
		else {
			BinaryTreeNode<Point2D> tmp = n.leftChild();
			n.setLeftChild(n.parent());
			(n.parent()).setRightChild(tmp);
			if (tmp != null) {
				tmp.setParent(n.parent());
			}
			if(((n.parent()).parent()).rightChild() == n.parent()) {    //Parent of parent (soon to be parent of n) needs n as left child
				((n.parent()).parent()).setRightChild(n);
			} else {														 //Parent of parent (soon to be parent of n) needs n as right child
				((n.parent()).parent()).setLeftChild(n);
			}
			BinaryTreeNode<Point2D> tmp2 = n.parent();
			n.setParent((n.parent()).parent());
			tmp2.setParent(n);
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
			Point2D element = m.element();
			if(size == 1) {
				root = null;
				size--;
				return element;
			}
			BinaryTreeNode<Point2D> actionNode = m.parent();
			
			if (m.isExternal()) {
				if((m.parent()).leftChild() == m) {
					(m.parent()).setLeftChild(null);
				} else {
					(m.parent()).setRightChild(null);
				}
			}
			else if (m.rightChild() != null && m.leftChild() == null) {
				if (!(m.isRoot())) {
					if((m.parent()).leftChild() == m) {
						(m.parent()).setLeftChild(m.rightChild());
						(m.rightChild()).setParent(m.parent());
					} else {
						(m.parent()).setRightChild(m.rightChild());
						(m.rightChild()).setParent(m.parent());
					}
				} else {
					(m.rightChild()).setParent(null);
					root = m.rightChild();
				}
			}
			else if (m.rightChild() == null && m.leftChild() != null) {
				if (!(m.isRoot())) {
					if((m.parent()).leftChild() == m) {
						(m.parent()).setLeftChild(m.leftChild());
						(m.leftChild()).setParent(m.parent());
					} else {
						(m.parent()).setRightChild(m.leftChild());
						(m.leftChild()).setParent(m.parent());
					}
				} else {
					(m.leftChild()).setParent(null);
					root = m.leftChild();
				}
			}
			
			else if (m.rightChild() != null && m.leftChild() != null) {
				BinaryTreeNode<Point2D> s = m.rightChild();
				if (s.leftChild() == null) {
					s.setLeftChild(m.leftChild());
					(m.leftChild()).setParent(s);
					if(!(m.isRoot())) {
						s.setParent(m.parent());
						if((m.parent()).leftChild() == m) {
							(m.parent()).setLeftChild(s);
						} else {
							(m.parent()).setRightChild(s);
						}
					} else {
						s.setParent(null);
						root = s;
					}
				} else {
					while (s.leftChild() != null) {
						s = s.leftChild();
					}
					s.setLeftChild(m.leftChild());
					(m.leftChild()).setParent(s);
					s.setRightChild(m.leftChild());
					(m.rightChild()).setParent(s);
					(s.parent()).setLeftChild(null);
					if(!(m.isRoot())) {
						s.setParent(m.parent());
						if((m.parent()).leftChild() == m) {
							(m.parent()).setLeftChild(s);
						} else {
							(m.parent()).setRightChild(s);
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
