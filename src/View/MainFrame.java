/**
 * 
 */
package View;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.*;

import Model.Point2D;
import Model.Polyline;
import Model.Polyline2D;
import java.awt.Toolkit;

/**
 * @author Bastian Bertram
 * @author Joshua Scheidt
 *
 */

public class MainFrame {
	private JFrame frame;
	private CartesianPanel cartesian;
	
//	private JPanel polyPanel;
	private JPanel optionPanel;
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	
	private JScrollPane scrollFrame;
	private JTextArea field;
	private JTabbedPane tabbedPane;
	private ButtonGroup group;
	
	private JButton run;

	public static JRadioButtonMenuItem drawItem;
	private JRadioButtonMenuItem dragItem;
	private JRadioButtonMenuItem typeItem;
	private JRadioButtonMenuItem loadItem;
	
	private JRadioButton lengthPolyLineButton;
	private JRadioButton areaPolylineButton;
	private JRadioButton bentleyButton;
	private JRadioButton intersectButton;
	
	private JButton useMethod;
	private ButtonGroup methodGroup;
	public static JRadioButton drawButton;
	public static JRadioButton dragButton;
	private JRadioButton typeButton;
	private JRadioButton loadButton;
	
	
	public MainFrame(){
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
	
        frame.setTitle("Flatland 1.0");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxXSize = (int)screenSize.getWidth();
		int maxYSize = (int)screenSize.getHeight();
        frame.setPreferredSize(screenSize);
        frame.setSize(maxXSize, maxYSize);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cartesian = new CartesianPanel();
        
        JMenuBar menuBar = new JMenuBar();
        JMenu methodsMenu = new JMenu("Methods");
        drawItem = new JRadioButtonMenuItem("Draw");
        dragItem = new JRadioButtonMenuItem("Drag");
        typeItem = new JRadioButtonMenuItem("Type");
        loadItem = new JRadioButtonMenuItem("Load");
        drawItem.setSelected(true);
        ButtonGroup methodsGroup = new ButtonGroup();
        methodsGroup.add(drawItem);
        methodsGroup.add(dragItem);
        methodsGroup.add(typeItem);
        methodsGroup.add(loadItem);
        methodsMenu.add(drawItem);
        methodsMenu.add(dragItem);
        methodsMenu.add(typeItem);
        methodsMenu.add(loadItem);
        menuBar.add(methodsMenu);
        drawItem.addActionListener(new MethodListener());
        dragItem.addActionListener(new MethodListener());
        typeItem.addActionListener(new MethodListener());
        loadItem.addActionListener(new MethodListener());
        
        JMenu calculationsMenu = new JMenu("Calculations");
        JMenuItem intersectItem = new JMenuItem("Intersect");
        JMenuItem polylineLength = new JMenuItem("Length of polyline");
        JMenuItem areaItem = new JMenuItem("area of polytope");
        JMenuItem bentleyItem = new JMenuItem("Bentley-Ottman");
        calculationsMenu.add(intersectItem);
        calculationsMenu.add(polylineLength);
        calculationsMenu.add(areaItem);
        calculationsMenu.add(bentleyItem);
        menuBar.add(calculationsMenu);
        
        frame.setJMenuBar(menuBar);
               
        frame.add(optionPanel(), BorderLayout.WEST);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.add(cartesian);
        frame.setVisible(true);
        frame.setFocusable(true);
        cartesian.requestFocus();
        frame.pack();
	}
	
	
	
	private JPanel optionPanel(){
		tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(new TitledBorder(new EtchedBorder(), "Polylines"));
		optionPanel = new JPanel();	
		optionPanel.setBorder(new TitledBorder(new EtchedBorder(), "Option Panel"));
		optionPanel.setPreferredSize(new Dimension(300, 1000));
		optionPanel.setLayout(new GridLayout(1,1));
		
		optionPanel.add(cartesian.getPolyline());
//		optionPanel.add(inputMethod(), BorderLayout.CENTER);
//		optionPanel.add(radioButtonPanel(), BorderLayout.SOUTH);
		optionPanel.setFocusable(true);
		return optionPanel;
	}
/*
	private JPanel inputMethod(){
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,1));
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Methods"));
		
		drawButton = new JRadioButton("Click coordinates");
		dragButton = new JRadioButton("Drag coordinates");
		typeButton = new JRadioButton("Type coordinates");
		loadButton = new JRadioButton("Load data type");

		drawButton.setSelected(true);
		
		methodGroup = new ButtonGroup();
		methodGroup.add(drawButton);
		methodGroup.add(dragButton);
		methodGroup.add(typeButton);
		methodGroup.add(loadButton);
		useMethod = new JButton("Use chosen method");
		useMethod.addActionListener(new MethodListener());
		
		panel.add(drawButton);
		panel.add(dragButton);
		panel.add(typeButton);
		panel.add(loadButton);
		panel.add(useMethod);
		
		return panel;
	}
	
	private JPanel radioButtonPanel(){
		panel = new JPanel();
		panel.setLayout(new GridLayout(5,1));	
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Calculations"));	
		
		intersectButton = new JRadioButton("Check intersection");
		lengthPolyLineButton = new JRadioButton("Calculate length of Polyline");
		areaPolylineButton = new JRadioButton("Calculate area of Polytope");
		bentleyButton = new JRadioButton("Run Bentley-Ottmann algorithm");		
		
		group = new ButtonGroup();
		group.add(intersectButton);
		group.add(lengthPolyLineButton);
		group.add(areaPolylineButton);
		group.add(bentleyButton);
	
		run = new JButton("Run");
		run.addActionListener(new ButtonListener());		
		
		panel.add(intersectButton);
		panel.add(lengthPolyLineButton);
		panel.add(areaPolylineButton);
		panel.add(bentleyButton);
		panel.add(run);
			
		return panel;	
	}
*/

	private class MethodListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(drawItem.isSelected())
				cartesian.requestFocus();
			else if(dragItem.isSelected())
				dragMethod();
			else if(typeItem.isSelected())
				typeMethod();
			else if(loadItem.isSelected())
				loadMethod();
		}
	}
	
	private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
          
            if (intersectButton.isSelected()) {
                intersect();
            } else if (lengthPolyLineButton.isSelected()) {
                length();
            } else if (areaPolylineButton.isSelected()) {
            	area();
            } else if (bentleyButton.isSelected()) {
                bentley();
            }
            
        }
    }
	
	
	
	private void bentley(){
		
	}
	
	
	
	private void length(){
		
	}
	
	
	
	private void area(){
		
	}
	
	
	
	private void intersect(){
		
	}
	

	private void dragMethod(){
		
	}
	
	private void typeMethod(){
		Polyline2D pol = new Polyline2D();
for(int i = 0; i<pol.vertices().size(); i++)
System.out.println("point "+i+": x= "+ pol.getVertex(i).getX()+"   y= "+pol.getVertex(i).getY());
		String value = JOptionPane.showInputDialog("How many points does your polytope need?", null);
		int val = Integer.parseInt(value);
System.out.println(val);
		for(int i = 0; i<val; i++){
			JPanel tempPanel = new JPanel();
			JTextField xField = new JTextField(20);
		    JTextField yField = new JTextField(20);
		    tempPanel.add(new JLabel("x = "));
		    tempPanel.add(xField);
		    tempPanel.add(Box.createHorizontalStrut(15));
		    tempPanel.add(new JLabel("y:"));
		    tempPanel.add(yField);
		    
		    int result = JOptionPane.showConfirmDialog(null, tempPanel, "Please Enter X and Y Values of point #"+(i+1)+".", JOptionPane.OK_CANCEL_OPTION);
		   
		    if (result == JOptionPane.OK_OPTION) {
		         Point2D point = new Point2D(Double.parseDouble(xField.getText()), Double.parseDouble(yField.getText()));
 System.out.println("x="+point.getX()+"   y="+point.getY());
		         pol.addPoint(point);
		      }
		    if (result == JOptionPane.CANCEL_OPTION) 
		    	break;
		}
		
for(int i = 0; i<pol.vertices().size(); i++)
System.out.println("point "+i+": x= "+ pol.getVertex(i).getX()+"   y= "+pol.getVertex(i).getY());
		cartesian.addLine(pol);
	}
	
	private void loadMethod() {
		
	}
}


 




