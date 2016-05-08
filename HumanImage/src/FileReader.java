import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class FileReader {
	private MyCanvas mc;
	private String label_folder;

	public FileReader(MyCanvas mc, String label_folder) {
		// TODO Auto-generated constructor stub
		this.mc = mc;
		this.label_folder = label_folder;
	}

	String result;
	FileInputStream fis = null;
	InputStreamReader isr = null;
	BufferedReader br = null; // 用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
	ArrayList<Point> points = new ArrayList<Point>();

	public void readerFile(String fname) {

		try {
			// label_folder=label_folder.replace("\\", "/");
			// System.out.println(label_folder);
			fis = new FileInputStream(label_folder + "labels.txt");// FileInputStream
			System.out.println(fis);
			// 从文件系统中的某个文件中获取字节
			isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
			br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new
			// InputStreamReader的对象
			result = readAll();
			ShowSpot(fname);
		} catch (FileNotFoundException e) {
			System.out.println("找不到指定文件");
		} catch (IOException e) {
			System.out.println("读取文件失败");
		} finally {
			try {
				br.close();
				isr.close();
				fis.close();
				// 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void ShowSpot(String fname) {

		String[] num = null;
		String[] res = result.split("\n");

		for (int i = 0; i < res.length; i++) {
			num = res[i].split(" ");
			if (num[0].equals(fname)) {
				System.out.println(num[0]);
				System.out.println(fname);
				break;
			}
		}
		System.out.println(num.length - 1);
		for (int i = 1; i < num.length; i += 2) {
			if (i % 2 == 1) {
				points.add(new Point(Integer.parseInt(num[i]), Integer
						.parseInt(num[i + 1])));
			}
		}

		Update();
		points.clear();

	}

	public void Update() {
		// TODO Auto-generated method stub
		Iterator<Point> i = points.iterator();
		Graphics graphics = mc.getGraphics();
		// Color c = g.getColor();
		int j=1;
	    int[] labelnum=new int[30];
		while (i.hasNext()) {
			
			if (j==1||j==2||j==3||j==21) {
				graphics.setColor(Color.BLUE);	
			}else if (j==5||j==6||j==7||j==8||j==22||j==23) {
				graphics.setColor(Color.GREEN);	
			} else if (j==9||j==10||j==11||j==12||j==24||j==25) {
				graphics.setColor(Color.YELLOW);	
			} 
			 else if (j==13||j==14||j==15||j==16) {
					graphics.setColor(Color.pink);	
				} 
			 else if (j==17||j==18||j==19||j==20) {
					graphics.setColor(Color.CYAN);	
				} 
			Point p = (Point) i.next();
			graphics.fillOval(p.x, p.y, 10, 10);
		//	graphics.drawString(j+"", p.x, p.y);
			System.out.println("x->"+p.x+" y->"+p.y);
			j++;
		}

	}

	public String readAll() {
		// TODO Auto-generated method stub
		String str1 = "";
		String line;
		try {
			while ((line = br.readLine()) != null) {
				str1 += line;
				str1 += "\n";
			}
			// System.out.println(str1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str1;
	}
}
