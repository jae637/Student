package chap8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Assignment {

	public static void listDirectroy(File dir) {
		System.out.println("-----" + dir.getPath() + "�� ���긮��Ʈ �Դϴ�.-----");

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
				System.out.print("\t ����ũ��:" + f.length());
				System.out.printf("\t ������ �ð� : %tb %td %ta %tT\n", t, t, t, t);

				writer.write(f.getName());
				writer.write("\t����ũ�� : " + f.length());
				writer.write("\t������ �ð� : "+ DDD.format(f.lastModified()) + "\r\n" );
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("����� ����");
		}
	}

	public static void main(String[] args) {
		//���� ���丮 ���� �κ�
		String local = System.getProperty("user.dir");
		File f1 = new File(local);
		
		// tmp ���丮 ���� �κ�
		File f2 = new File(local + "\\tmp");
		if (!f2.exists()) {
			f2.mkdir();
		}
		
		listDirectroy(f1);
	}
}
