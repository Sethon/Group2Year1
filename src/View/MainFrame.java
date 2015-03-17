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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
	
	
	
	public MainFrame(){
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
	
        frame.setTitle("Flatland 1.0");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int maxXSize = (int)screenSize.getWidth();
		int maxYSize = (int)screenSize.getHeight();
        frame.setPreferredSize(screenSize);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
		cartesian = new CartesianPanel();   
               
        frame.add(optionPanel(), BorderLayout.WEST);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.add(cartesian);
        frame.setVisible(true);
        cartesian.requestFocus();
        frame.setFocusable(true);
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
		optionPanel.add(radioButtonPanel(), BorderLayout.SOUTH);
		optionPanel.setFocusable(true);
		return optionPanel;
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
}


 




