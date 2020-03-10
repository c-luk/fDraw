package com.lukasiewicz.fdraw;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPaintPanel extends JPanel implements MouseMotionListener {	
	
    private static final int boxSideLength = 64;
    private static final int maxBoxes = 3;
    private Rectangle[] box = new Rectangle[maxBoxes];
    private int currentNumberOfBoxes = 0;
    private int currentBoxIndex = -1;
    boolean dragging = false;
   	    
    public MyPaintPanel() {
    	
    	// create subtile and nice border outline
    	
    	//setBackground(new Color(25,25,25));
    	
    	setBorder(BorderFactory.createLineBorder(Color.black));
     
    	// Mouse events for adding and removing boxes
    	
        addMouseListener(new MouseAdapter() {
            
        	// When mouse pressed, add a box to screen if < maxBoxes
        	
        	@Override
        	public void mousePressed(MouseEvent evt) {
            	int x = evt.getX();
            	int y = evt.getY();
            	dragging = true;
                currentBoxIndex = getBox(x, y);
                if (currentBoxIndex < 0) {             
                	addBox(x, y);
               }
            }
        	
        	@Override
        	public void mouseReleased(MouseEvent evt) {
        		dragging = false;
   		    }
            
        	// When mouse is doubleclicked on a box, remove that box
        	
        	@Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() >= 2) {
                	remove(currentBoxIndex);
                }
            }
        });
        
        addMouseMotionListener(this);
    }
    
    // Mouse event for setting cursor to crosshair when mouseover a box
    
    public void mouseMoved(MouseEvent evt) {
    	int x = evt.getX();
    	int y = evt.getY();
    	if (getBox(x, y) >= 0) {
    		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    	} else {
    		setCursor(Cursor.getDefaultCursor());
    	  }
    }
  
    // Mouse event for dragging a box
    
    public void mouseDragged(MouseEvent evt) {
    	int x = evt.getX();
    	int y = evt.getY();
    	if (currentBoxIndex >= 0) {
    		Graphics graphics = getGraphics();
    		graphics.setXORMode(getBackground());
    		((Graphics2D) graphics).draw(box[currentBoxIndex]);
    		box[currentBoxIndex].x = x;
    		box[currentBoxIndex].y = y;
    		((Graphics2D) graphics).draw(box[currentBoxIndex]);
    		graphics.dispose();
    		repaint();
    	}
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);       
        //g.drawString("Draw (click) up to 3 boxes - drag 'em - doubleclick to remove one!",10,20);
        
        for (int i = 0; i < currentNumberOfBoxes; i++) {
        	((Graphics2D) g).draw(box[i]);
        }
    }

    private int getBox(int x, int y) {
    	for (int i = 0; i < currentNumberOfBoxes; i++) {
    		if (box[i].contains(x, y)) {
        	    return i;
        	}
    	}
        return -1;
    }

    private void addBox(int x, int y) {
    	if (currentNumberOfBoxes < maxBoxes) {
        	box[currentNumberOfBoxes] = new Rectangle(x, y, boxSideLength, boxSideLength);
        	currentBoxIndex = currentNumberOfBoxes;
        	currentNumberOfBoxes++;
        	repaint();
    	}
    }
    
    @Override
    public void remove(int n) {
    	if (n < 0 || n >= currentNumberOfBoxes) {
        	return;
    	}
    	currentNumberOfBoxes--;
    	box[n] = box[currentNumberOfBoxes];
    	if (currentBoxIndex == n) {
        	currentBoxIndex = -1;
    	}
    	repaint();
    }
}
