import java.util.*;
import java.io.*;
public class Spelling {
	public static void main (String[] argv)
	{
		int alp=0;
		System.out.print("알파벳을 입력하세요 :");
		InputStreamReader rd = new InputStreamReader(System.in);
		
		try {
			while(true) {
				alp=rd.read();
				if (alp==-1)
					break;
				if ('a'<=alp||alp<='z')
				{
					alp-=32;
					System.out.println("입력하신 알파벳의 (대/소) 문자형은 : "+(char)alp+"입니다");
				}
				else if ('A'<=alp || alp <='Z')
				{
					alp+=32;
					System.out.println("입력하신 알파벳의 (대/소) 문자형은 : "+(char)alp+"입니다");
				}
				else
				{
					System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요");
				}
			}
			
		}
		catch(IOException e) {
			System.out.println("입력 에러 발생");
		}
	}
}
