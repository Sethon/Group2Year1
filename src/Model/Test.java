package Model;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		
		
	}
	
	public static <E> void inOrder(BinaryTreeNode<E> v) {
		if (v != null) {
			if (v.isInternal()) {
				inOrder(v.leftChild());
			}
//			System.out.print(v.element() + " ");
			if (v.isInternal()) {
				inOrder(v.rightChild());
			}
		}
	}
}
