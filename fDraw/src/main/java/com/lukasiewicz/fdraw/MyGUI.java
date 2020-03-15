package com.lukasiewicz.fdraw;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class MyGUI extends JFrame implements ActionListener {

	static String labelText = "Draw colored rectangles! Red at first, then green and blue at last. Repeat.";
	static JLabel label = new JLabel(labelText);
	static MyPaintPanel paintArea = new MyPaintPanel();
	
	public static void createAndShowGUI(String appversion) {

		// Window setup
		
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim_min = new Dimension(800,600);
		JFrame guiWindow = new JFrame();
		guiWindow.setMinimumSize(dim_min);
		guiWindow.setTitle("fDraw " + appversion);
		guiWindow.setSize(dim_min);
		
		// JMenu setup
		
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem saveItem;
		JMenuItem exitItem;
		JMenuItem aboutItem;
		menuBar = new JMenuBar();
	
		// File menu
		
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription("File menu");
		menuBar.add(menu);
	
		// File menu items
		
			//	Save
		saveItem = new JMenuItem("Save (not implemented yet)", KeyEvent.VK_S);
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveItem.getAccessibleContext().setAccessibleDescription("Saves your wonderful drawing.");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
			// Save Method yet to be implemented...
			}
		});
		menu.add(saveItem);
		
		// Separator between menu items
		
		menu.addSeparator();
		
			//	Exit
		exitItem = new JMenuItem("Exit", KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK));
		exitItem.getAccessibleContext().setAccessibleDescription("Exits the application.");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		menu.add(exitItem);
		
		// Help menu
		menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription("Help menu");
		menuBar.add(menu);
		
		// Help menu items
		
		//	About
		aboutItem = new JMenuItem("About", KeyEvent.VK_A);
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
		aboutItem.getAccessibleContext().setAccessibleDescription("Displays the about text.");
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// Displays about window
				createAndShowAboutWindow(guiWindow, "About fDraw " + appversion);
			}
		});
		menu.add(aboutItem);
	
		// Set operation for closing window
		
		guiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Set JMenu to guiWindow
		
		guiWindow.setJMenuBar(menuBar);

		// Setup SpringLayout and add PaintArea and ToolBar
		
		Container cPane = guiWindow.getContentPane();
		SpringLayout layout = new SpringLayout();
		cPane.setLayout(layout);
		
		cPane.add(label);
		
		MyToolBar toolBar = new MyToolBar();
		cPane.add(toolBar);
		
		paintArea.setSize(dim_min);
		cPane.add(paintArea);
		
		layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, cPane);
		layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, cPane);
		
		layout.putConstraint(SpringLayout.WEST, toolBar, 5, SpringLayout.WEST, cPane);
		layout.putConstraint(SpringLayout.NORTH, toolBar, 40, SpringLayout.NORTH, cPane);
		layout.putConstraint(SpringLayout.EAST, toolBar, -5, SpringLayout.WEST, paintArea);
		layout.putConstraint(SpringLayout.SOUTH, toolBar, -5, SpringLayout.SOUTH, cPane);
		
		layout.putConstraint(SpringLayout.WEST, paintArea, 70, SpringLayout.WEST, cPane);
		layout.putConstraint(SpringLayout.NORTH, paintArea, 30, SpringLayout.NORTH, cPane);

		layout.putConstraint(SpringLayout.EAST, cPane, 5, SpringLayout.EAST, paintArea);
		layout.putConstraint(SpringLayout.SOUTH, cPane, 5, SpringLayout.SOUTH, paintArea);
		
		// Display guiWindow
		
		guiWindow.pack();
		guiWindow.setLocationRelativeTo(null);
		guiWindow.setVisible(true);
	}

	private static void createAndShowAboutWindow(JFrame parentFrame, String title) {
		
		final JDialog aboutDialog = new JDialog(parentFrame, title, Dialog.ModalityType.DOCUMENT_MODAL);
        
		// Setup Container, JLabel and JTextArea
		
		Container cPane = aboutDialog.getContentPane();
		SpringLayout layout = new SpringLayout();
		cPane.setLayout(layout);
		
		JLabel label = new JLabel("About");
		cPane.add(label);
		
		JTextArea aboutText = new JTextArea();
        aboutText.setEditable(false);
        
        aboutText.append("Welcome to the about page."+"\n\n");
        aboutText.append("Here you will learn more about this app."+"\n");
        aboutText.append("...once there is something to learn about."+"\n\n");
        aboutText.append("actually, it's a rather simple app with only four buttons..."+"\n");
        aboutText.append("you'll figure it out all by yourself. i'm sure of it."+"\n\n");
        aboutText.append("good luck.");
        
		JScrollPane scrollPane = new JScrollPane(aboutText);
        cPane.add(scrollPane);

        // Setup Spring Layout
        
		layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, cPane);
		layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, cPane);
		
		layout.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, cPane);
		layout.putConstraint(SpringLayout.EAST, scrollPane, -5, SpringLayout.EAST, cPane);
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 35, SpringLayout.NORTH, cPane);
		layout.putConstraint(SpringLayout.SOUTH, scrollPane, -5, SpringLayout.SOUTH, cPane);
		
		// Display aboutDialog

		aboutDialog.pack();
		aboutDialog.setLocationRelativeTo(parentFrame);
		aboutDialog.setMinimumSize(new Dimension(320,250));
		aboutDialog.setVisible(true);
	}
	
	// Method for changing the label text
	
	public static void setLabelText (String labelText) {
		label.setText(labelText);
	}
	
    // Repaint painting area 
    public static void repaintWindow() {
		paintArea.repaint();
    }
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
