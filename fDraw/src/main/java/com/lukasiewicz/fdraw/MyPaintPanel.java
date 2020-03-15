package com.lukasiewicz.fdraw;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MyPaintPanel extends JPanel implements MouseMotionListener {
	
    public static int drawingTool = 1;
	final int toolRectangle = 1;
	final int toolLine = 2;
	final int toolMove = 3;
	public static int currentNumberOfShapes = 0;
    public static int currentShapeIndex = -1;
	
    Point startDrag, endDrag;
    public static ArrayList<Shape> shapes = new ArrayList<Shape>();
   	    
    public MyPaintPanel() {
    	
    	// Setup PainPanel
    	
    	setBorder(BorderFactory.createLineBorder(Color.black));
    	setBackground(new Color(250,250,250));
     
    	// Mouse events
    	
        addMouseListener(new MouseAdapter() {
            
        	@Override
        	public void mousePressed(MouseEvent evt) {
        		
        		int x = evt.getX();
            	int y = evt.getY();
            	
        		if(drawingTool!=toolMove) {
        			startDrag = new Point(evt.getX(), evt.getY());
                    endDrag = startDrag;
                    repaint();	
        		} else if(getShape(x, y) >= 0) {
        			currentShapeIndex = getShape(x, y);
        		}
        	}
        	
        	@Override
        	public void mouseReleased(MouseEvent evt) {
        		
        		switch(drawingTool) {
        		
        		case toolRectangle:
        			Shape r = makeRectangle(startDrag.x, startDrag.y, evt.getX(), evt.getY());
                    shapes.add(r);
                    currentShapeIndex=currentNumberOfShapes;
                    currentNumberOfShapes++;
                    startDrag = null;
                    endDrag = null;
                    repaint();
                    break;
        		
        		case toolLine:
        			Shape l = makeLine(startDrag.x, startDrag.y, evt.getX(), evt.getY());
                    shapes.add(l);
                    currentShapeIndex=currentNumberOfShapes;
                    currentNumberOfShapes++;
                    startDrag = null;
                    endDrag = null;
                    repaint();
                    break;
        		
        		case toolMove:
        			// Move algorithm to be implemented
        			
        			break;
        		
        		default:
        		}
        	}
        });
        
        addMouseMotionListener(this);
    }
    
    // Mouse draggin event   
    
    public void mouseDragged(MouseEvent evt) {
    	
    	int x = evt.getX();
    	int y = evt.getY();
    	
    	switch(drawingTool) {
    	case toolMove:
    		if (currentShapeIndex >= 0) {
    			Graphics graphics = getGraphics();
        		graphics.setXORMode(getBackground());
        		((Graphics2D) graphics).draw(shapes.get(currentShapeIndex));
        		
        		// ==> IMPLEMENT: If Shape is a rectangle or line -- how to? 
        		
        		Rectangle r = new Rectangle(shapes.get(currentShapeIndex).getBounds());
        		r.x=x;
        		r.y=y;
        		shapes.set(currentShapeIndex, r);
        		((Graphics2D) graphics).draw(shapes.get(currentShapeIndex));
        		graphics.dispose();
        		//endDrag = new Point(x, y);
        		repaint();
        	}
    		break;
    	default:
    		endDrag = new Point(x, y);
            repaint();
    	}
    	
    }
    
    // Paint method
    
    @Override
    protected void paintComponent(Graphics g) {
        
    	super.paintComponent(g);
        
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = { Color.RED, Color.GREEN, Color.BLUE};
        int colorIndex = 0;

        ((Graphics2D) g).setStroke(new BasicStroke(2));
        ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        for (Shape s : shapes) {
          ((Graphics2D) g).setPaint(Color.BLACK);
          ((Graphics2D) g).draw(s);
          ((Graphics2D) g).setPaint(colors[(colorIndex++) % 3]);
          ((Graphics2D) g).fill(s);
        }
        
        switch (drawingTool) {
        
        case toolRectangle:
        	if (startDrag != null && endDrag != null) {
                ((Graphics2D) g).setPaint(Color.LIGHT_GRAY);
                Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                ((Graphics2D) g).draw(r);
            }
        	break;
        
        case toolLine:
        	if (startDrag != null && endDrag != null) {
                ((Graphics2D) g).setPaint(Color.LIGHT_GRAY);
                Shape l = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                ((Graphics2D) g).draw(l);
            }
        	break;
        
        case toolMove:
        	// Move algorithm to be implemented
        	break;
        
        default:
        }
    }
    
    // Return rectangle
    
    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
    
    // Return line
    
    private Line2D.Float makeLine(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1, y1, x2, y2);
    }
    
	@Override
	public void mouseMoved(MouseEvent evt) {
		int x = evt.getX();
    	int y = evt.getY();
    	if (getShape(x, y) >= 0) {
    		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    	} else {
    		setCursor(Cursor.getDefaultCursor());
    	  }
	}
	
	private int getShape(int x, int y) {
    	for (int i = 0; i < currentNumberOfShapes; i++) {
    		if (shapes.get(i).contains(x, y)) {
        	    return i;
        	}
    	}
        return -1;
    }
}