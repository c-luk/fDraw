package com.lukasiewicz.fDraw;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class MyGUI extends JFrame implements ActionListener {

	public static void createAndShowGUI() {

		//Windowsetup
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		JFrame guiWindow = new JFrame();
		guiWindow.setTitle("fDraw 0.42");
		guiWindow.setSize(640, 480);
		guiWindow.setLocation(dim.width/2-guiWindow.getSize().width/2, dim.height/2-guiWindow.getSize().height/4*3);
		
		//JMenu
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
			public void actionPerformed(ActionEvent e) {
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
			public void actionPerformed(ActionEvent e) {
			// Exit Method
				System.exit(0);
			}
		});
		menu.add(exitItem);		
	
		guiWindow.setJMenuBar(menuBar);
		
		guiWindow.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container cPane = guiWindow.getContentPane();
		cPane.add(new MyPaintPanel());
		guiWindow.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
