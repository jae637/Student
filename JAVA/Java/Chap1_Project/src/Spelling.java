import java.util.*;
import java.io.*;
public class Spelling {
	public static void main (String[] argv)
	{
		int alp=0;
		System.out.print("���ĺ��� �Է��ϼ��� :");
		InputStreamReader rd = new InputStreamReader(System.in);
		
		try {
			while(true) {
				alp=rd.read();
				if (alp==-1)
					break;
				if ('a'<=alp||alp<='z')
				{
					alp-=32;
					System.out.println("�Է��Ͻ� ���ĺ��� (��/��) �������� : "+(char)alp+"�Դϴ�");
				}
				else if ('A'<=alp || alp <='Z')
				{
					alp+=32;
					System.out.println("�Է��Ͻ� ���ĺ��� (��/��) �������� : "+(char)alp+"�Դϴ�");
				}
				else
				{
					System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է��� �ּ���");
				}
			}
			
		}
		catch(IOException e) {
			System.out.println("�Է� ���� �߻�");
		}
	}
}
