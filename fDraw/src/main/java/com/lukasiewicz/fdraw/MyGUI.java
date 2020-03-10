package com.lukasiewicz.fdraw;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class MyGUI extends JFrame implements ActionListener {

	public static void createAndShowGUI() {

		//Window setup
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim_min = new Dimension(dim.width/3, dim.height/3);
		Dimension dim_max = new Dimension(dim.width*100/75, dim.height*100/75);
		JFrame guiWindow = new JFrame();
		guiWindow.setMinimumSize(dim_min);
		guiWindow.setMaximumSize(dim_max);
		guiWindow.setTitle("fDraw 0.43");
		guiWindow.setSize(dim.width/2, dim.height/2);
		guiWindow.setLocationRelativeTo(null);
		
		//JMenu setup
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem saveItem;
		JMenuItem exitItem;
		
		menuBar = new JMenuBar();
	
		//File menu
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("File menu");
		menuBar.add(menu);
	
		//File menu items
		
			//	Save
		saveItem = new JMenuItem("Save (not yet implemented)", KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveItem.getAccessibleContext().setAccessibleDescription("Saves your wonderful drawing.");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			// Save Method to be implemented
			}
		});
		menu.add(saveItem);
				
		menu.addSeparator();
		
			//	Exit
		exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
		exitItem.getAccessibleContext().setAccessibleDescription("Exits the application.");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			// Exit Method
				System.exit(0);
			}
		});
		menu.add(exitItem);		
	
		guiWindow.setJMenuBar(menuBar);
	
		// Exit app when window closed
		
		guiWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		
		// Setup SpringLayout
		
		Container cPane = guiWindow.getContentPane();
		SpringLayout layout = new SpringLayout();
		cPane.setLayout(layout);
		
		MyPaintPanel paintArea = new MyPaintPanel();
		paintArea.setSize(dim_min);
		cPane.add(paintArea);
		
		JLabel label = new JLabel("Draw (click) up to 3 boxes - drag 'em - doubleclick to remove one!");
		cPane.add(label);
		
		layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, cPane);
		layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, cPane);
		layout.putConstraint(SpringLayout.WEST, paintArea, 30, SpringLayout.WEST, cPane);
		layout.putConstraint(SpringLayout.NORTH, paintArea, 30, SpringLayout.NORTH, cPane);
		layout.putConstraint(SpringLayout.EAST, cPane, 5, SpringLayout.EAST, paintArea);
		layout.putConstraint(SpringLayout.SOUTH, cPane, 5, SpringLayout.SOUTH, paintArea);

		// Display guiWindow
		
		guiWindow.pack();
		guiWindow.setVisible(true);
	}

	// question: why do i need this here, when i already used it above?
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
