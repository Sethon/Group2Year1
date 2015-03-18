/**
 * 
 */
package View;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;

import java.util.ArrayList;
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
	
	private JTextArea field;
	private JTabbedPane tabbedPane;
	private ButtonGroup group;
	
	private JButton run;

	public static JRadioButtonMenuItem drawItem;
	public static JRadioButtonMenuItem dragItem;
	private JRadioButtonMenuItem typeItem;
	private JRadioButtonMenuItem loadItem;
	
	private JRadioButtonMenuItem intersectItem;
	private JRadioButtonMenuItem polylineLengthItem;
	private JRadioButtonMenuItem areaItem;
	private JRadioButtonMenuItem bentleyItem;
	
	
	
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
        
        //The menuBar starts here
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
        intersectItem = new JRadioButtonMenuItem("Intersect");
        polylineLengthItem = new JRadioButtonMenuItem("Length of polyline");
        areaItem = new JRadioButtonMenuItem("area of polytope");
        bentleyItem = new JRadioButtonMenuItem("Bentley-Ottman");
        calculationsMenu.add(intersectItem);
        calculationsMenu.add(polylineLengthItem);
        calculationsMenu.add(areaItem);
        calculationsMenu.add(bentleyItem);
        
        ButtonGroup calculationGroup = new ButtonGroup();
        calculationGroup.add(intersectItem);
        calculationGroup.add(polylineLengthItem);
        calculationGroup.add(areaItem);
        calculationGroup.add(bentleyItem);
        menuBar.add(calculationsMenu);
        intersectItem.addActionListener(new ButtonListener());
        polylineLengthItem.addActionListener(new ButtonListener());
        areaItem.addActionListener(new ButtonListener());
        bentleyItem.addActionListener(new ButtonListener());
        
        frame.setJMenuBar(menuBar);
        //MenuBar ends here
               
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
		optionPanel.setFocusable(true);
		return optionPanel;
	}


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
        	
            if (intersectItem.isSelected()) {
                intersect();
            } else if (polylineLengthItem.isSelected()) {
                length();
            } else if (areaItem.isSelected()) {
            	area();
            } else if (bentleyItem.isSelected()) {
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
//for(int i = 0; i<pol.vertices().size(); i++)
//System.out.println("point "+i+": x= "+ pol.getVertex(i).getX()+"   y= "+pol.getVertex(i).getY());
		String value = "";
		while("".equals(value))
			value = JOptionPane.showInputDialog("How many points does your polytope need?", null);
		int val = Integer.parseInt(value);
//System.out.println(val);
		for(int i = 0; i<val; i++){
			JPanel tempPanel = new JPanel();
			JTextField xField = new JTextField(20);
		    JTextField yField = new JTextField(20);
		    tempPanel.add(new JLabel("x = "));
		    tempPanel.add(xField);
		    tempPanel.add(Box.createHorizontalStrut(15));
		    tempPanel.add(new JLabel("y:"));
		    tempPanel.add(yField);
		    int result = 10;

		    while(xField.getText().equals("") || yField.getText().equals(""))
		    	 result = JOptionPane.showConfirmDialog(null, tempPanel, "Please Enter X and Y Values of point #"+(i+1)+".", JOptionPane.OK_CANCEL_OPTION);
		   
		    if (result == JOptionPane.OK_OPTION) {
		         Point2D point = new Point2D(Double.parseDouble(xField.getText()), Double.parseDouble(yField.getText()));
 //System.out.println("x="+point.getX()+"   y="+point.getY());
		         pol.addPoint(point);
		      }
		    if (result == JOptionPane.CANCEL_OPTION) 
		    	break;
		}
		pol.addPoint(pol.getVertex(0));
//for(int i = 0; i<pol.vertices().size(); i++)
//System.out.println("point "+i+": x= "+ pol.getVertex(i).getX()+"   y= "+pol.getVertex(i).getY());
		cartesian.addLine(pol);
	}
	
	private void loadMethod() throws FileNotFoundException {
		ArrayList<String> array = new ArrayList<String>();
		String data = "dataFile.txt";
		FileReader fr = new FileReader(data);
		BufferedReader bf = new BufferedReader(fr);
		try {
			while(bf.readLine() != null){
				array.add(bf.readLine());
			}
		} catch (IOException e){}
		boolean onlyDigits = true;
		for(int i = 0; i<array.size(); i++)
			if(array.get(i).matches(".*\\d.*"))
			{}
			else
				onlyDigits = false;
		if(onlyDigits)
		{
			Polyline2D pol = new Polyline2D();
			for(int i = 0; i<array.size(); i+=2)
			{
				Point2D point = new Point2D(Integer.parseInt(array.get(i)), Integer.parseInt(array.get(i++)));
				pol.addPoint(point);
			}
			cartesian.addLine(pol);
		}
	}
}


 




