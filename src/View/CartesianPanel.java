package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Model.Point2D;
import Model.Polyline2D;


public class CartesianPanel extends JPanel {
	
	private static final long 	serialVersionUID = 3803801936174545349L;
	
	private static final Color 	BG_COLOUR 		 = Color.WHITE;
	private static final Color	GRID_COLOUR		 = Color.GRAY;
	private static final Color	COORD_COLOUR	 = Color.RED;
	private static final Color	LINE_COLOUR	 	 = Color.BLUE;
	private static final Color	TEXT_COLOUR	 	 = Color.BLACK;
	
	private static final int	DEF_SCALE		 = 32;
	private static final int	MAGN_FACTOR		 = 2;
	private static final int	MOVE_FACTOR		 = 8;
	
	private int 					scale;
	private ArrayList<Polyline2D> 	lines;
	private int						x0;
	private int						y0;
	private int						currX;
	private int						currY;
	private boolean					modificationMode;
	
	public CartesianPanel() {
		scale = DEF_SCALE;
		x0 = Integer.MAX_VALUE;
		y0 = Integer.MAX_VALUE;
		currX = (int) (MouseInfo.getPointerInfo().getLocation().getX());
		currY = (int) (MouseInfo.getPointerInfo().getLocation().getY());
		modificationMode = false;
		lines = new ArrayList<Polyline2D>();
		setBackground(BG_COLOUR);
		addMouseWheelListener(new WheelListener());
		addKeyListener(new KListener());
		addMouseListener(new ClickListener());
		addMouseMotionListener(new MovementListener());
	}

	public void paintComponent(Graphics g) {
	    int width = getWidth();
	    int height = getHeight();
	    if (x0 == Integer.MAX_VALUE && y0 == Integer.MAX_VALUE) {
	    	x0 = (width/(2 * scale)) * scale;
	    	y0 = (height/(2 * scale)) * scale;
	    }
	    
	    super.paintComponent(g);
	    
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setColor(GRID_COLOUR);

	    for(int i = 0; i <= width; i += scale) {
	    	if (i == x0) {
	    		g2.setColor(COORD_COLOUR);
	    		g2.setStroke(new BasicStroke(3));
	    		g2.drawLine(i, 0, i, height);
	    		g2.setColor(GRID_COLOUR);
	    		g2.setStroke(new BasicStroke(1));
	    	} else {
	    		g2.drawLine(i, 0, i, height);
	    	}
	    }
	    
	    for(int i = 0; i <= height; i += scale) {
	    	if (i == y0) {
	    		g2.setColor(COORD_COLOUR);
	    		g2.setStroke(new BasicStroke(3));
	    		g2.drawLine(0, i, width, i);
	    		g2.setColor(GRID_COLOUR);
	    		g2.setStroke(new BasicStroke(1));
	    	} else {
	    		g2.drawLine(0, i, width, i);
	    	}
	    }
	    
	    g2.setColor(TEXT_COLOUR);
	    g2.setFont(new Font("Courier", Font.PLAIN, 16));
	    g2.drawString("0", x0 + 2, y0 - 2);
	    g2.drawString("(" + ((double) (currX - x0))/((double) scale) + ", " + ((double) (-currY + y0))/((double) scale) + ")", 20, 20);
	    g2.setFont(new Font("Courier", Font.PLAIN, 32));
	    g2.drawString("Y", x0 + 16, 24);
	    g2.drawString("X", width - 24, y0 - 16);
	    g2.setColor(GRID_COLOUR);
	    
	    g2.setColor(LINE_COLOUR);
	    g2.setStroke(new BasicStroke(2));
	    for (Polyline2D pl : lines) {
	    	drawPolyline(pl, g2);
	    }
	    g2.setColor(GRID_COLOUR);
		g2.setStroke(new BasicStroke(1));
	}
	
	public void addLine(Polyline2D pl) {
		lines.add(pl);
		repaint();
	}
	
	public void clear() {
		while (!(lines.isEmpty())) {
			lines.remove(lines.size() - 1);
		}
		modificationMode = false;
		repaint();
	}
	
	public void removeLine(int i) {
		lines.remove(i);
		repaint();
	}
	
	private void setScale(int scale) {
		this.scale = scale;
		repaint();
	}
	
	private void drawPolyline(Polyline2D pl, Graphics2D g2) {
		if (pl.getVertices().size() == 1) {
			g2.fillRect((int) Math.round(pl.getVertices().get(0).getCoordinates()[0]*scale + x0 - scale/4),
					(int) Math.round(-pl.getVertices().get(0).getCoordinates()[1]*scale + y0 - scale/4), 
					(int) (scale/2), (int) (scale/2));
		} else {
			for (int i = 0; i < pl.getVertices().size() - 1; i++) {
				g2.drawLine((int) (x0 + pl.getVertices().get(i).getCoordinates()[0]*scale),
				(int) (y0 - pl.getVertices().get(i).getCoordinates()[1]*scale),
				(int) (pl.getVertices().get(i + 1).getCoordinates()[0]*scale + x0),
				(int) (-pl.getVertices().get(i + 1).getCoordinates()[1]*scale + y0));
				
				g2.fillRect((int) Math.round(pl.getVertices().get(i).getCoordinates()[0]*scale + x0 - scale/4),
				(int) Math.round(-pl.getVertices().get(i).getCoordinates()[1]*scale + y0 - scale/4), 
				(int) (scale/2), (int) (scale/2));
				g2.fillRect((int) Math.round(pl.getVertices().get(i + 1).getCoordinates()[0]*scale + x0 - scale/4),
				(int) Math.round(-pl.getVertices().get(i + 1).getCoordinates()[1]*scale + y0 - scale/4), 
				(int) (scale/2), (int) (scale/2));
			}
		}
	}
	
	private void translateOrigin(int dX, int dY) {
		x0 += dX;
		y0 += dY;
		repaint();
	}
	
	private class MovementListener implements MouseMotionListener {
		public void mouseDragged(MouseEvent e) {
			
		}

		public void mouseMoved(MouseEvent e) {
			currX = e.getX();
			currY = e.getY();
			repaint();
		}
		
	}
	
	private class WheelListener implements MouseWheelListener {
		public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() < 0) {
				if (scale*MAGN_FACTOR < 64) {
					setScale(scale*MAGN_FACTOR);
				}
			} else {
				if (scale/MAGN_FACTOR >= 4) {
					setScale(scale/MAGN_FACTOR);
				}
			}
		}
	}
	
	private class ClickListener extends MouseAdapter {
		public void mouseReleased(MouseEvent e) {
			if (modificationMode) {
				lines.get(lines.size() - 1).addPoint(((double) (e.getX() - x0))/((double) scale), ((double) (-e.getY() + y0))/((double) scale));
				repaint();
			} else {
				modificationMode = true;
				ArrayList<Point2D> tmp = new ArrayList<Point2D>();
				tmp.add(new Point2D(((double) (e.getX() - x0))/((double) scale), ((double) (-e.getY() + y0))/((double) scale)));
				lines.add(new Polyline2D(tmp));
				repaint();
			}
		}
	}
	
	private class KListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
			
		}
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				translateOrigin(0, -MOVE_FACTOR*scale);
			}
			else if (e.getKeyCode() == KeyEvent.VK_UP) {
				translateOrigin(0, MOVE_FACTOR*scale);
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				translateOrigin(-MOVE_FACTOR*scale, 0);
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				translateOrigin(MOVE_FACTOR*scale, 0);
			}
		}
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_C) {
				clear();
			}
			else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				modificationMode = false;
			}
			else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				if (modificationMode && lines.get(lines.size() - 1).getVertices().size() > 1) {
					lines.get(lines.size() - 1).addPoint(lines.get(lines.size() - 1).getVertex(0));
					modificationMode = false;
					repaint();
				}
			}
		}
	}
}
