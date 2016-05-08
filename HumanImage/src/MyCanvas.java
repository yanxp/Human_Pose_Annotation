import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import java.util.Iterator;
public class MyCanvas extends Canvas implements ComponentListener{
  private BufferedImage bi;
  private ArrayList<Point> points = new ArrayList<Point>();
  private Image im;
  private int image_width;
  private int image_height;
  public void setImage(BufferedImage bi){
    this.bi = bi;
    this.zoom();
  }	
  public void paint(Graphics g){
	  // g.drawImage(im,(this.getWidth()-image_width)/2,(this.getHeight()-image_height)/2,this);
      g.drawImage(im,0,0,image_width,image_height,this);
	  Iterator<Point> i = points.iterator();
     while(i.hasNext())
    {
     Point p = (Point)i.next();
     g.setColor(Color.red);
     g.fillOval(p.x,p.y,10,10);
    }
  }  
  public void componentResized(ComponentEvent e){
  /*  if(bi != null){
      System.out.println("resize!!");
      this.zoom();
      this.repaint();
    }*/
  }
  public void componentMoved(ComponentEvent e){}
  public void componentShown(ComponentEvent e){}
  public void componentHidden(ComponentEvent e){}
  public void zoom(){
    if(bi == null)
      return;
    int screen_width = this.getWidth();
    int screen_height = this.getHeight();
    double screen_proportion = 1.0 * screen_height / screen_width;
    System.out.println("screen: w "+screen_width+" ,h "+screen_height+" ,p0 "+screen_proportion);
   image_width = bi.getWidth(this);
   image_height = bi.getHeight(this);
  /*  image_width=1920;
    image_height=1080;*/
    double image_proportion = 1.0 * image_height / image_width;
    System.out.println("image: w "+image_width+" ,h "+image_height+" ,p1 "+image_proportion);
  /* if(image_proportion > screen_proportion){
      image_height = screen_height;
      image_width = (int)(image_height / image_proportion);  
      System.out.println(" p1>p0 w= "+image_width);
    }else{
      image_width = screen_width;
      image_height = (int)(image_width * image_proportion);  
      System.out.println(" p0>p1 h= "+image_height);
    } */
    im = bi.getScaledInstance(image_width,image_height,Image.SCALE_SMOOTH);
  }
 
  public void addPoint(Point p)
  {
   points.add(p);
  }
}