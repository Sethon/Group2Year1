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
	
	public void balance(){
		boolean balanced = false;
		//Checks if unbalanced
		
	}
	
	public void rotateLeft(BinaryTreeNode<Double> n){    //Rotate the chosen node to the the left
		if((n.getParent()).isRoot()) {                   //Check if the parent is the root
			BinaryTreeNode<Double> m = n.getLeftChild(); //Make a new treenode with the left part of the chosen node.
			n.setLeftChild(n.getParent());               //n's left child is his old parent.
			(n.getLeftChild()).setRightChild(m);         //n's new left child needs to have the new treenode as his right child.
			(n.getLeftChild()).setParent(n);             //n's new left child will have n as parent.
		}
		else{
			BinaryTreeNode<Double> m = n.getLeftChild();                         //Make a new treenode with the left part of the chosen node.
			n.setLeftChild(n.getParent());										 //n's left child is his old parent.
			(n.getLeftChild()).setRightChild(m);                                 //n's new left child needs to have the new treenode as his right child.
			if(((n.getParent()).getParent()).getLeftChild() == n.getParent())    //Parent of parent (soon to be parent of n) needs n as left child
				((n.getParent()).getParent()).setLeftChild(n);
			else																 //Parent of parent (soon to be parent of n) needs n as right child
				((n.getParent()).getParent()).setRightChild(n);
			(n.getLeftChild()).setParent(n);									 //n's new left child will have n as parent.
		}
	}
	
	public void rotateRight(BinaryTreeNode<Double> n){   //Rotate the chosen node to the the right
		if((n.getParent()).isRoot()) {                   //Check if the parent is the root
			BinaryTreeNode<Double> m = n.getRightChild(); //Make a new treenode with the right part of the chosen node.
			n.setRightChild(n.getParent());               //n's Right child is his old parent.
			(n.getRightChild()).setLeftChild(m);         //n's new Right child needs to have the new treenode as his Left child.
			(n.getRightChild()).setParent(n);             //n's new Right child will have n as parent.
		}
		else{
			BinaryTreeNode<Double> m = n.getRightChild();                         //Make a new treenode with the Right part of the chosen node.
			n.setRightChild(n.getParent());										 //n's Right child is his old parent.
			(n.getRightChild()).setLeftChild(m);                                 //n's new Right child needs to have the new treenode as his Left child.
			if(((n.getParent()).getParent()).getRightChild() == n.getParent())    //Parent of parent (soon to be parent of n) needs n as Right child
				((n.getParent()).getParent()).setRightChild(n);
			else																 //Parent of parent (soon to be parent of n) needs n as Left child
				((n.getParent()).getParent()).setLeftChild(n);
			(n.getRightChild()).setParent(n);									 //n's new Right child will have n as parent.
		}
		
	}
	
	public Double remove(BinaryTreeNode<Double> n, double e) {
		if (n == null) {
			return null;
		}  else {
			BinaryTreeNode<Double> m = search(e, n);
			Double element = m.getElement();
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
				BinaryTreeNode<Double> s = m.getRightChild();
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
