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
    static final private String DELETE = "Delete";
    static final private String MOVE = "Move";
    static private JButton rectangleButton = null;
    static private JButton lineButton = null;
    static private JButton moveButton = null;
    static private JButton deleteButton = null;
    static private Color buttonBackground = new Color(230,230,230);
    static private boolean rectangleButtonPressed = true;
    static private boolean lineButtonPressed = false;
    static private boolean moveButtonPressed = false;
    
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
	        	} else if(moveButtonPressed) {
	        		moveButton.setBackground(buttonBackground);
	        	}
	        	lineButtonPressed=false;
	        	moveButtonPressed=false;
	        }
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	rectangleButtonPressed=true;
	        	rectangleButton.setBackground(rectangleButton.getBackground().brighter());
	        	if(lineButtonPressed) {
	        		lineButton.setBackground(buttonBackground);
	        	} else if(moveButtonPressed) {
	        		moveButton.setBackground(buttonBackground);
	        	}
	        	lineButtonPressed=false;
	        	moveButtonPressed=false;
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
	        	} else if(moveButtonPressed) {
	        		moveButton.setBackground(buttonBackground);
	        	}
	        	rectangleButtonPressed=false;
	        	moveButtonPressed=false;
	        }
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	lineButtonPressed=true;
	        	lineButton.setBackground(lineButton.getBackground().brighter());
	        	if(rectangleButtonPressed) {
	        		rectangleButton.setBackground(buttonBackground);
	        	} else if(moveButtonPressed) {
	        		moveButton.setBackground(buttonBackground);
	        	}
	        	rectangleButtonPressed=false;
	        	moveButtonPressed=false;
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
	    
	    // Button for moving stuff
	    moveButton = makeButton("MoveButton", MOVE, "Move a shape", "Move");
	    toolBar.add(moveButton);
	    
	    moveButton.addMouseListener(new MouseListener() {
	    	public void mousePressed(MouseEvent evt) {
	        	moveButtonPressed=true;
	        	moveButton.setBackground(moveButton.getBackground().brighter());
	        	if(rectangleButtonPressed) {
	        		rectangleButton.setBackground(buttonBackground);
	        	} else if(lineButtonPressed) {
	        		lineButton.setBackground(buttonBackground);
	        	}
	        	rectangleButtonPressed=false;
	        	lineButtonPressed=false;
	        }
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	moveButtonPressed=true;
	        	moveButton.setBackground(moveButton.getBackground().brighter());
	        	if(rectangleButtonPressed) {
	        		rectangleButton.setBackground(buttonBackground);
	        	} else if(lineButtonPressed) {
	        		lineButton.setBackground(buttonBackground);
	        	}
	        	rectangleButtonPressed=false;
	        	lineButtonPressed=false;
	        }
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
	    });
	    
		// Give them buttons a little space
	    toolBar.addSeparator(new Dimension(15,280));
	    
	    // Button for deleting the stuff
	    deleteButton = makeButton("DeleteButton", DELETE, "Delete my creation", "Delete");
	    toolBar.add(deleteButton);
	    
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

		// Button handling
		
        switch(cmd) {
        
        case DRAW_RECTANGLE:
        	MyPaintPanel.drawingTool = 1;
        	MyGUI.setLabelText("Draw colored rectangles! Red at first, then green and blue at last. Repeat.");
        	break;
        case DRAW_LINE:
        	MyPaintPanel.drawingTool = 2;
        	MyGUI.setLabelText("You wanna draw a line? Ha! - okay, go for it!.");
        	break;
        case MOVE:
        	MyPaintPanel.drawingTool = 3;
        	MyGUI.setLabelText("Move 'em all like there's no tomorrow! (not yet implemented)");
        	break;
        case DELETE:
        	MyPaintPanel.shapes.clear();
        	MyPaintPanel.currentNumberOfShapes = 0;
        	MyPaintPanel.currentShapeIndex = -1;
        	MyGUI.repaintWindow();
        	break;
        default:
        }
	}
}
