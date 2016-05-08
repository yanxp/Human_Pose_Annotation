import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.*;
import java.io.File;

public class PictureViewer implements ActionListener {
	private ArrayList<Point> points = new ArrayList<Point>();
	private Frame frame;
	private Label fLabel;
	private MyCanvas mc;
	private String fpath;
	private String fname;
	private File[] files;
	private int findex;
	private FileDialog fd_load; 
	private MyFilter filter;
	private Button previous;
	private Button Annotaion;
	private Button next;
	private String filename;
	private FileReader fd;
	private String folder;

	public static void main(String args[]) throws Exception {
		new PictureViewer().init();
	}

	public void init() {
		frame = new Frame("PictureViewer");
		Panel pb = new Panel();
		fLabel=new Label();
		frame.setLayout(null);
		fLabel.setPreferredSize(new Dimension(150, 20));
		fLabel.setFont(new Font("",1,20));//设置字体大小
		fLabel.setForeground(Color.BLUE);//设置字体颜色
		pb.add(fLabel);
		frame.setLayout(new BorderLayout());
		Button select = new Button("Choose Picture");
		previous = new Button("Previous");
		next = new Button("Next");
		Annotaion = new Button("Annotaion");
		select.addActionListener(this);
		previous.addActionListener(this);
		next.addActionListener(this);
		Annotaion.addActionListener(this);
		
		pb.add(select);
		pb.add(previous);
		pb.add(next);
		pb.add(Annotaion);
		
		mc = new MyCanvas();
		mc.setBackground(new Color(200, 210, 230));
		mc.addComponentListener(mc);
		frame.add(pb, "North");
		frame.add(mc, "Center");
		frame.setSize(1920, 1200);
		frame.setLocation(400, 200);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
		this.validateButton();
		filter = new MyFilter();
		fd_load = new FileDialog(frame, "打开文件", FileDialog.LOAD);
		fd_load.setFilenameFilter(filter);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Choose Picture")) {
			fd_load.setVisible(true);
			fpath = fd_load.getDirectory();
			folder = fd_load.getDirectory().substring(0,
					fd_load.getDirectory().length() - 6);
			fname = fd_load.getFile();
		//	System.out.println(fname);
			if ((fpath != null) && (fname != null)) {
				filename = fname;
				this.display(new File(fpath + fname));
				files = new File(fpath).listFiles(filter);
				this.setIndex();
			}
			mc.addMouseListener(new MyMouseListener(folder,filename,fLabel,0));
		mc.addMouseMotionListener(new MyMouseMotionListener());
		} else if (command.equals("Previous")) {
			findex--;
			if (findex < 0)
				findex = 0;
			filename = files[findex].getName();
			this.display(files[findex]);
	
		//	System.out.println(files[findex].getName());
			mc.addMouseListener(new MyMouseListener(folder,filename,fLabel,0));
			mc.addMouseMotionListener(new MyMouseMotionListener());

		} else if (command.equals("Next")) {
			findex++;
			if (findex >= files.length)
				findex = files.length - 1;
			filename = files[findex].getName();
			this.display(files[findex]);
		//	System.out.println(files[findex].getName());
			
			mc.addMouseListener(new MyMouseListener(folder,filename,fLabel,0));
			mc.addMouseMotionListener(new MyMouseMotionListener());

		} else if (command.equals("Annotaion")) {
			//System.out.println("here");
			fd = new FileReader(mc, folder);
			fd.readerFile(filename);
		}
		this.validateButton();
	}

	public void display(File f) {
		try {
			BufferedImage bi = ImageIO.read(f);
			mc.setImage(bi);
			frame.setTitle("PictureViewer - [" + f.getName() + "]");
			/*Timer timer=new Timer();//实例化Timer类   
			timer.schedule(new TimerTask(){   
			public void run(){   
			System.out.println("退出");   
			this.cancel();}},1000);//五百毫秒
			fd = new FileReader(mc, folder);
			fd.readerFile(filename);
			System.out.println(folder+filename);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		mc.repaint();
	}
	public void setIndex() {
		File current = new File(fpath + fname);
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (current.equals(files[i])) {
					findex = i;
				}
			}
		}
	}

	public void validateButton() {
		previous.setEnabled((files != null) && (findex > 0));
		next.setEnabled((files != null) && (findex < (files.length - 1)));
		Annotaion.setEnabled((files != null) );
	}

}