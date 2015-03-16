package Model;




public interface BinaryTree<E> {
	public int size();
	public boolean isEmpty();
	public BinaryTreeNode<E> root();
	public BinaryTreeNode<E> parent(BinaryTreeNode<E> n);
	public boolean isInternal(BinaryTreeNode<E> n);
	public boolean isExternal(BinaryTreeNode<E> n);
	public boolean isRoot(BinaryTreeNode<E> n);
	public void swapElements(BinaryTreeNode<E> n, BinaryTreeNode<E> m); 
}
