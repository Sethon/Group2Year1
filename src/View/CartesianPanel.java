package View;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import Model.Point2D;
import Model.Polyline2D;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;




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
	
	private JPanel polyPanel;
	
	public CartesianPanel() {
		this.polyPanel = new JPanel();
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
	public void setPolyline(JPanel polyline){
		this.polyPanel = polyline;
	}
	
	public JPanel getPolyline(){
		return polylinePanel();
	}
	
	
	private class InfoButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String msg = ("Content:\nContent2:" + ((JButton) e.getSource()).getName());
		    JOptionPane optionPane = new JOptionPane();
		    optionPane.setMessage(msg);
		    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
		    JDialog dialog = optionPane.createDialog(null, "Info");
		    dialog.setVisible(true);
		}	
	}
	
	private class DeleteButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
				lines.remove(Integer.parseInt((((JButton) e.getSource()).getName())));
				polyPanel.remove(Integer.parseInt((((JButton) e.getSource()).getName())));
//System.out.println("Deleted");
				updatePanel();
				polyPanel.repaint();
//System.out.println("Updated");
				repaint();
		}	
	}
	
	private JButton polyButton(int x){
		JButton polyButton = new JButton("Info");
		polyButton.setName("" + x);
		polyButton.addActionListener(new InfoButtonListener());
		return polyButton;
	}
	
	private JButton deleteButton(int x){
		JButton delete = new JButton("Delete");
		delete.addActionListener(new DeleteButtonListener());
		delete.setName("" + x);
		return delete;
	}
	
	private void updatePanel(){
		polyPanel.removeAll();
System.out.println(lines.size());
		for(int x = 0; x < lines.size(); x++){
			JPanel p = new JPanel();
			p.setLayout(new GridLayout(1,3));
			p.add(new JLabel("No. " + (x+1)));
			p.add(polyButton(x));
			p.add(deleteButton(x));
			p.setName("" + x);
			polyPanel.add(p);
			polyPanel.revalidate();
			polyPanel.invalidate();
			polyPanel.repaint();
System.out.println("Printed");
		}	
	}
	
	
	private JPanel polylinePanel(){	
		polyPanel.setPreferredSize(new Dimension(280, 450));
		polyPanel.setBorder(new TitledBorder(new EtchedBorder(), "Polylines"));
//		JScrollPane p = new JScrollPane(polyPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
//	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		p.setPreferredSize(new Dimension(290,500));
		polyPanel.revalidate();
		return polyPanel;
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
		updatePanel();
		polyPanel.repaint();
		modificationMode = false;
		repaint();
	}
	
	public void removeLine(int i) {
		lines.remove(i);
		repaint();
	}
	
	public ArrayList<Polyline2D> getLine(){
		return this.lines;
	}
	
	private void setScale(int scale) {
		this.scale = scale;
		repaint();
	}
	
	private void drawPolyline(Polyline2D pl, Graphics2D g2) {
		if (pl.vertices().size() == 1) {
			g2.fillRect((int) Math.round(pl.vertices().get(0).coordinates()[0]*scale + x0 - scale/4),
					(int) Math.round(-pl.vertices().get(0).coordinates()[1]*scale + y0 - scale/4), 
					(int) (scale/2), (int) (scale/2));
		} else {
			for (int i = 0; i < pl.vertices().size() - 1; i++) {
				g2.drawLine((int) (x0 + pl.vertices().get(i).coordinates()[0]*scale),
				(int) (y0 - pl.vertices().get(i).coordinates()[1]*scale),
				(int) (pl.vertices().get(i + 1).coordinates()[0]*scale + x0),
				(int) (-pl.vertices().get(i + 1).coordinates()[1]*scale + y0));
				
				g2.fillRect((int) Math.round(pl.vertices().get(i).coordinates()[0]*scale + x0 - scale/4),
				(int) Math.round(-pl.vertices().get(i).coordinates()[1]*scale + y0 - scale/4), 
				(int) (scale/2), (int) (scale/2));
				g2.fillRect((int) Math.round(pl.vertices().get(i + 1).coordinates()[0]*scale + x0 - scale/4),
				(int) Math.round(-pl.vertices().get(i + 1).coordinates()[1]*scale + y0 - scale/4), 
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
			if(MainFrame.drawItem.isSelected()){
				if (modificationMode) {
					lines.get(lines.size() - 1).addPoint(((double) (e.getX() - x0))/((double) scale), ((double) (-e.getY() + y0))/((double) scale));
	//System.out.println("Clicking");
					repaint();
				} else {
					modificationMode = true;
					ArrayList<Point2D> tmp = new ArrayList<Point2D>();
					tmp.add(new Point2D(((double) (e.getX() - x0))/((double) scale), ((double) (-e.getY() + y0))/((double) scale)));
					lines.add(new Polyline2D(tmp));
	//System.out.println("Clicking new one");
					requestFocusInWindow();
					repaint();
				}
			}
			else if(MainFrame.dragButton.isSelected()){
				//if(){
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
			else if (e.getKeyCode() == KeyEvent.VK_ENTER){
				updatePanel();
			}
		}
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_C) {
				clear();
				updatePanel();
			}
			else if (e.getKeyCode() == KeyEvent.VK_ENTER) {		
				modificationMode = false;
			}
			else if (e.getKeyCode() == KeyEvent.VK_S) {
//System.out.println("Finish it S" + modificationMode);				
				if (modificationMode && lines.get(lines.size() - 1).vertices().size() > 1) {
					lines.get(lines.size() - 1).addPoint(lines.get(lines.size() - 1).getVertex(0));
					modificationMode = false;
//System.out.println("Finish it");
					updatePanel();
					repaint();
				}
			}
		}
	}
}
