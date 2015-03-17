package Model;

import java.util.ArrayList;


public interface Polyline<E> {
	public static final int DEF_VNUM = 5;
	
	public E getVertex(int i);
	public ArrayList<E> vertices();
	public double length();
	public boolean isClosed();
}
