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

	private JRadioButton lengthPolyLineButton;
	private JRadioButton areaPolylineButton;
	private JRadioButton bentleyButton;
	private JRadioButton intersectButton;
	
	private JButton useMethod;
	private ButtonGroup methodGroup;
	private JRadioButton drawButton;
	private JRadioButton dragButton;
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
		optionPanel.setLayout(new BorderLayout());
		
		optionPanel.add(cartesian.getPolyline(), BorderLayout.NORTH);
		optionPanel.add(inputMethod(), BorderLayout.CENTER);
		optionPanel.add(radioButtonPanel(), BorderLayout.SOUTH);
		optionPanel.setFocusable(true);
		return optionPanel;
	}

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


	private class MethodListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			if (drawButton.isSelected()) {
				cartesian.requestFocus();
			} else if (dragButton.isSelected()) {
				dragMethod();
			} else if (typeButton.isSelected()) {
				typeMethod();
			} else if (loadButton.isSelected()) {
				loadMethod();
			}
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
		String value = JOptionPane.showInputDialog("How many points does your polytope need?", null);
		int val = Integer.parseInt(value);
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
		         pol.addPoint(point);
		      }
		    if (result == JOptionPane.CANCEL_OPTION) 
		    	break;
		}
//		CartesianPanel.addLine(pol);
	}
	
	private void loadMethod() {
		
	}
}


 




