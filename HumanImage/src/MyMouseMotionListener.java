import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.RepaintManager;


public class MyMouseMotionListener implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		    MyCanvas mc=(MyCanvas) e.getSource();
		    Graphics mGraphics=mc.getGraphics();
		    int x=e.getX();
		    int y=e.getY();
		    String s="当前鼠标坐标:"+x+','+y;
		    mGraphics.setColor(Color.red);
		    mGraphics.fillOval(x,y,10,10);
		    
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

 

}
