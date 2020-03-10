package com.lukasiewicz.fdraw;

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
		JToolBar toolBar = new JToolBar("CoolToolBar(tm)", JToolBar.VERTICAL);
		JButton button = null;
	
	    //first button
	    button = makeButton("LineButton", DRAW_LINE, "Draw a fine line", "Line");
	    toolBar.add(button);
	
	    //second button
	    button = makeButton("RectangleButton", DRAW_RECTANGLE, "Draw a spectacular rectangle", "Rectangle");
	    toolBar.add(button);
	}
	
    protected JButton makeButton(String imageName, String actionCommand, String toolTipText, String altText) {
		//Look for the image.
		String imgLocation = "src/main/resources/images/" + imageName + ".png";
		URL imageURL = MyToolBar.class.getResource(imgLocation);
		
		//Create and initialize the button.
		JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.addActionListener(this);
		
		if (imageURL != null) {                      //image found
		button.setIcon(new ImageIcon(imageURL, altText));
		} else {                                     //no image found
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
