import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;


public class FileSave {
   private static int count=0;
	String result;
	FileInputStream fis = null;
	InputStreamReader isr = null;
	BufferedReader br = null; 
	private String label_folder;
	public FileSave(String label_folder) {
		// TODO Auto-generated constructor stub
		this.label_folder=label_folder;
	}
public  void saveFile(String fname,int x,int y,String joint) {
	// TODO Auto-generated method stub
	 File fe=new File(label_folder+"results");
	 if (!fe.exists()) {
		 fe.mkdir();
	}
	 System.out.println(fe.getAbsolutePath());
	  File  file = new File(fe.getAbsolutePath()+"\\"+fname+".txt");
	  System.out.println(file.getAbsolutePath());
	   try {
		   // if file doesnt exists, then create it
		   if (!file.exists()) {
		    file.createNewFile();
		   }
		   BufferedWriter output;
		   if (count==15||count==0) {
			   output= new BufferedWriter(new FileWriter(file,false)); 
			   readLabel(fname, output);
			   count=0;
		}
		   else {
			    output = new BufferedWriter(new FileWriter(file,true)); 

		      }
		  //  System.out.println("here"+count);
			output.write(joint+"  x:"+x+"　 y:"+y);
			output.newLine();
			   count++;
			output.close();
			  
            
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
private void readLabel(String fname,BufferedWriter output) {
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
                output.write(num[i]+" "+num[i+1]);
                output.newLine();

			}
		}
		 
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
