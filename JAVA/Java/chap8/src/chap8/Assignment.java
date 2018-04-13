package chap8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Assignment {

	public static void listDirectroy(File dir) {
		System.out.println("-----" + dir.getPath() + "의 서브리스트 입니다.-----");

		SimpleDateFormat DDD = new SimpleDateFormat("yyyy/MM/dd");
		File[] subFiles = dir.listFiles();
		File file = null;
		try {
			file = File.createTempFile("tmp", ".txt", new File(dir.getPath() + "\\tmp"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileWriter writer = null;

		try {
			writer = new FileWriter(file,true);
			for (int i = 0; i < subFiles.length; i++) {
				File f = subFiles[i];
				long t = f.lastModified();
				System.out.print(f.getName());
				System.out.print("\t 파일크기:" + f.length());
				System.out.printf("\t 수정한 시간 : %tb %td %ta %tT\n", t, t, t, t);

				writer.write(f.getName());
				writer.write("\t파일크기 : " + f.length());
				writer.write("\t수정한 시간 : "+ DDD.format(f.lastModified()) + "\r\n" );
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("입출력 오류");
		}
	}

	public static void main(String[] args) {
		//현재 디렉토리 설정 부분
		String local = System.getProperty("user.dir");
		File f1 = new File(local);
		
		// tmp 디렉토리 생성 부분
		File f2 = new File(local + "\\tmp");
		if (!f2.exists()) {
			f2.mkdir();
		}
		
		listDirectroy(f1);
	}
}
