package com.lukasiewicz.fdraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class MyToolBar extends JPanel implements ActionListener {
	
    static final private String DRAW_LINE = "Line";
    static final private String DRAW_RECTANGLE = "Rectangle";
    static private JButton rectangleButton = null;
    static private JButton lineButton = null;
    static private Color buttonBackground = new Color(230,230,230);
    static private boolean rectangleButtonPressed = true;
    static private boolean lineButtonPressed = false;
    
	public MyToolBar() {
		
		super(new BorderLayout());
		
		JToolBar toolBar = new JToolBar("CoolToolBar(tm)", JToolBar.VERTICAL);
		toolBar.setFloatable(false);
		toolBar.setRollover(true);
		
	    // Button for adding rectangles
	    
	    rectangleButton = makeButton("RectangleButton", DRAW_RECTANGLE, "Draw a spectacular rectangle", "Rectangle");
	    rectangleButton.setBackground(rectangleButton.getBackground().brighter());
	    toolBar.add(rectangleButton);
		
	    rectangleButton.addMouseListener(new MouseListener() {
	        @Override
	        public void mousePressed(MouseEvent evt) {
	        	rectangleButtonPressed=true;
	        	rectangleButton.setBackground(rectangleButton.getBackground().brighter());
	        	if(lineButtonPressed) {
	        		lineButton.setBackground(buttonBackground);
	        	}
	        }
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	rectangleButtonPressed=true;
	        	rectangleButton.setBackground(rectangleButton.getBackground().brighter());
	        	if(lineButtonPressed) {
	        		lineButton.setBackground(buttonBackground);
	        	}	
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        }
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
	    		setCursor(Cursor.getDefaultCursor());
			}
	    });
	    
		// Give them buttons a little space
	    toolBar.addSeparator(new Dimension(15,10));
	    
		// Button for makin' lines
	    lineButton = makeButton("LineButton", DRAW_LINE, "Draw a fine line", "Line");
	    toolBar.add(lineButton);
	    
	    lineButton.addMouseListener(new MouseListener() {
	        @Override
	        public void mousePressed(MouseEvent evt) {
	        	lineButtonPressed=true;
	        	lineButton.setBackground(lineButton.getBackground().brighter());
	        	if(rectangleButtonPressed) {
	        		rectangleButton.setBackground(buttonBackground);
	        	}
	        }
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	lineButtonPressed=true;
	        	lineButton.setBackground(lineButton.getBackground().brighter());
	        	if(rectangleButtonPressed) {
	        		rectangleButton.setBackground(buttonBackground);
	        	}
	        }
	        @Override
	        public void mouseEntered(MouseEvent e) {
	        	setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        }
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
	    		setCursor(Cursor.getDefaultCursor());
			}
	    });
	    
        add(toolBar, BorderLayout.PAGE_START);
	}
	
    protected JButton makeButton(String imageName, String actionCommand, String toolTipText, String altText) {
    	String imgLocation =  "/" + imageName + ".png";
		URL imageURL = this.getClass().getResource(imgLocation);
		
		final JButton button = new JButton();
		button.setActionCommand(actionCommand);
		button.setToolTipText(toolTipText);
		button.setSize(32,32);
		button.setBackground(buttonBackground);
		button.setFocusPainted(false);
		button.setMargin(new Insets(2, 2, 2, 2));
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
        	MyPaintPanel.drawingTool = 2;
        	MyGUI.setLabelText("You wanna draw a line? Ha! - not yet implemented! Here are the boxes again, buh.");
        } else if (DRAW_RECTANGLE.equals(cmd)) { 
        	// Users wants to draw a rectangle
        	MyPaintPanel.drawingTool = 1;
        	MyGUI.setLabelText("Draw (click) up to 3 boxes - drag 'em - doubleclick to remove one!");
        }

	}	
}
