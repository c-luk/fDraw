package com.lukasiewicz.fdraw;

/**
 * 				fDraw - a simple app for learning/testing purposes
 * @author      Christoph Lukasiewicz <lukasiewicz@gmx.at>
 * @version     0.45
 */

public class FDrawMain {
	
	public static String appversion = "0.45";
	
	public static void main(String[] args) {
	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new MyGUI();
                MyGUI.createAndShowGUI(appversion);
            }
        });
	}
}