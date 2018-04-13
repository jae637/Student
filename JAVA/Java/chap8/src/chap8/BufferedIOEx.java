package chap8;

import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BufferedIOEx {
	public static void main(String[] args) {
		FileReader fin = null;
		String now = System.getProperty("user.dir");
		int c;
		try {
			fin = new FileReader(now+"\\test2.txt");
			BufferedOutputStream out = new BufferedOutputStream(System.out,5);
			while((c= fin.read())!=-1) {
				out.write(c);
			}
			new Scanner(System.in).nextLine();
			out.flush();
			fin.close();
			out.close();
		}
		catch(IOException e) {
			
		}
	}
}
