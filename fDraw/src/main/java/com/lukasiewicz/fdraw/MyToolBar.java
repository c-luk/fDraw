package com.lukasiewicz.fdraw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class MyToolBar extends JPanel implements ActionListener {
	
    static final private String DRAW_LINE = "Line";
    static final private String DRAW_RECTANGLE = "Rectangle";
    
	public MyToolBar() {
		super(new BorderLayout());
		
		JToolBar toolBar = new JToolBar("CoolToolBar(tm)", JToolBar.VERTICAL);
		toolBar.setFloatable(false);
		toolBar.setRollover(true);
		
		JButton lineButton = null;
		JButton rectangleButton = null;
		
	    lineButton = makeButton("LineButton", DRAW_LINE, "Draw a fine line", "Line");
	    toolBar.add(lineButton);
	    
        toolBar.addSeparator();
	    
	    rectangleButton = makeButton("RectangleButton", DRAW_RECTANGLE, "Draw a spectacular rectangle", "Rectangle");
	    toolBar.add(rectangleButton);
	    
        setPreferredSize(new Dimension(450, 130));
        add(toolBar, BorderLayout.PAGE_START);
	}
	
    protected JButton makeButton(String imageName, String actionCommand, String toolTipText, String altText) {
    	String imgLocation =  "/images/" + imageName + ".png";
		URL imageURL = this.getClass().getResource(imgLocation);
		
		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.setSize(32,32);
		button.addActionListener(this);
		
		if (imageURL != null) {
			button.setIcon(new ImageIcon(imageURL, altText));
		} else {
			button.setText(altText);
			System.err.println("Resource not found: " + imgLocation);
		}
		
		return button;
    }

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();

        // Handle each button.
        if (DRAW_LINE.equals(cmd)) { 
        	// User wants to draw a line
        	
        } else if (DRAW_RECTANGLE.equals(cmd)) { 
        	// Users wants to draw a rectangle
        	
        }

	}	
}
