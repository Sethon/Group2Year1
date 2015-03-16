/**
 * 
 */
package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 * @author Bastian Bertram
 *
 */

public class MainFrame {
	private JFrame frame;
	private CartesianPanel cartesian;
	
	private JRadioButton lengthPolyLineButton;
	private JRadioButton areaPolylineButton;
	private JRadioButton bentleyButton;
	private JRadioButton intersectButton;
	
	
	
	public MainFrame(){
		frame = new JFrame();
//		frame.setSize(700, 350);
        frame.setTitle("Polygonal Objects");
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        cartesian = new CartesianPanel();
        
        frame.add(cartesian, BorderLayout.CENTER);       
        frame.add(optionPanel(),  BorderLayout.WEST);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setVisible(true);
       
        cartesian.requestFocus();
	}
	
	
	
	private JPanel optionPanel(){
		JPanel optionPanel = new JPanel();
		optionPanel.setName("Options");
		optionPanel.setPreferredSize(new Dimension(400, 1000));
		optionPanel.add(polylinePanel(), BorderLayout.NORTH);
		optionPanel.add(radioButtonPanel(), BorderLayout.SOUTH);
		return optionPanel;
	}

	
	private JPanel polylinePanel(){
		JPanel polyPanel = new JPanel();
		polyPanel.setName("Polylines");

		JScrollPane scrollFrame = new JScrollPane();

		scrollFrame.setPreferredSize(new Dimension( 900,200));
		polyPanel.add(scrollFrame);
		return polyPanel;
	}
	
	
	private JPanel radioButtonPanel(){
		JPanel panel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		
		panel.setLayout(new GridLayout(4,1));
		panel1.setLayout(new GridLayout(2,1));
//		panel2.setLayout(new GridLyout())
		
		panel1.setSize(new Dimension(200,1000));
		
		intersectButton = new JRadioButton("Check intersection");
		lengthPolyLineButton = new JRadioButton("Calculate length of Polyline");
		areaPolylineButton = new JRadioButton("Calculate area of Polytope");
		bentleyButton = new JRadioButton("Run Bentley-Ottmann algorithm");
		
		ButtonGroup group = new ButtonGroup();
		group.add(intersectButton);
		group.add(lengthPolyLineButton);
		group.add(areaPolylineButton);
		group.add(bentleyButton);
		
		JButton run = new JButton("Run");
		run.addActionListener(new ButtonListener());		
		
		panel.add(intersectButton);
		panel.add(lengthPolyLineButton);
		panel.add(areaPolylineButton);
		panel.add(bentleyButton);
		panel2.add(run);
		
		panel1.add(panel);
		panel1.add(panel2);
		
		return panel1;	
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


 




