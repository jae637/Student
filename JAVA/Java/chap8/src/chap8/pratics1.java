package chap8;

import java.io.FileInputStream;
import java.io.IOException;

public class pratics1 {
	public static void main(String[] args) {
		String now = System.getProperty("user.dir");
		byte b[]= new byte[6];
		FileInputStream fn= null;
		try {
			fn=new FileInputStream(now+"\\test.out");
			int n=0,c;
			while((c=fn.read())!=-1)
			{
				b[n] = (byte)c;
				n++;
			}
			System.out.println("user.dir\\test.out���� ���� �迭�� ����մϴ�.");
			for (int i=0;i<b.length;i++) {
				System.out.print(b[i]+" ");
			}
			System.out.println();
			
			fn.close();
		}
		catch(IOException e){
			System.out.println("user.dir\\test.out���� ���� ���߽��ϴ�. ��θ��� üũ�غ�����");
		}
	}
}
