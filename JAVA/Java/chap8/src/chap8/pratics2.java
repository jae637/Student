package chap8;

import java.io.FileOutputStream;
import java.io.IOException;

public class pratics2 {
	public static void main(String[] args) {
		byte[] b= {7,51,3,4,-1,24};
		String now = System.getProperty("user.dir");
		
		FileOutputStream fout= null;
		try {
			fout=new FileOutputStream(now+"\\test.out");
			for (int i=0;i<b.length;i++) {
					fout.write(b[i]);
				}
			fout.close();
		}
		catch (IOException e) {
			System.out.println("파일을 저장할수 없습니다.");
			return;
		}
		System.out.println("user.dir\\test.out을 저장했습니다.");
	}
}
