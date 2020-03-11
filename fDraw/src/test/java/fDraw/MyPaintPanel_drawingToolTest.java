package fDraw;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lukasiewicz.fdraw.MyPaintPanel;

public class MyPaintPanel_drawingToolTest {

	@Test
	public void testImageImport() {
		MyPaintPanel testPaintPanel = new MyPaintPanel();

		if (testPaintPanel.drawingtool!=1) {  
			fail("DrawingTool not set");
		}
	}
}
