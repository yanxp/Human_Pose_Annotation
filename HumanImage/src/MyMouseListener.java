import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;


public class MyMouseListener implements MouseListener{
	private String label_folder;
	private String fname;
	private Label flabel;
	private int count=0;
	Point start,end;
	private  String[] annoName = {
			// the body joints
			"head", "neck", "torso", "left_shoulder", "left_elbow",
			"left_hand/wrist", "right_shoulder", "right_elbow",
			"right_hand/wrist", "left_hip", "left_knee", "left_foot/ankle",
			"right_hip", "right_knee", "right_foot/ankle" ,""};
   public MyMouseListener(String label_folder,String fname,Label FLable,int count) {
	// TODO Auto-generated constructor stub
	   this.label_folder=label_folder;
	   this.fname=fname;
	   this.flabel=FLable;
	   this.count=count;
	flabel.setText(annoName[count]);
	
}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub 

	    MyCanvas mc=(MyCanvas) e.getSource();
	    Graphics mGraphics=mc.getGraphics();
		if (count<15) {
			System.out.println(count);
			flabel.setText(annoName[count+1]);
		    int x=e.getX();
		    int y=e.getY();
		    String s="当前鼠标坐标:"+x+','+y;
		    System.out.println("x:"+x+"y:"+y);
		    mGraphics.setColor(Color.red);
		    mGraphics.fillOval(x,y,10,10);
		    FileSave fS=new FileSave(label_folder);
		    fS.saveFile(fname, x, y,annoName[count]);
		    count++;
		}
		  else {
			mGraphics.dispose();
		}   	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		start=e.getPoint();
		System.out.println("开始点坐标："+start.x+" "+start.y);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		end=e.getPoint();
		System.out.println("结束点坐标："+end.x+" "+end.y);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
