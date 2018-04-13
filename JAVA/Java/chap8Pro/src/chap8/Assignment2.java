package chap8;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Assignment2 {
	public static void main(String argc[]) {
		String now = System.getProperty("user.dir");
		ArrayList<String> a = new ArrayList<String>();
		LinkedList<String> b = new LinkedList<String>();
		File file = new File(now + "\\input.txt");
		FileReader fin = null;
		try {
			fin = new FileReader(file);
			BufferedReader in = new BufferedReader(fin);
			String str;
			while ((str = in.readLine()) != null) {
				a.add(str);
				b.add(str);
			}
			fin.close();
		} catch (FileNotFoundException e) {
			System.out.println("������ ���� ����");
		} catch (IOException e) {
			System.out.println("����� ����");
		}

		System.out.println("�Է� ���� : input.txt");
		System.out.println("");
		System.out.println("ArrayList ��� ���");
		for (int i = 0; i < a.size(); i++) {
			String name = a.get(i);
			System.out.print(name + "->");
		}
		System.out.println("");
		System.out.println("");
		System.out.println("LinkedList ��� ��� (����)");

		int c = b.size();
		for (int i = 0; i < c; i++) {
			String name = b.pollLast();
			System.out.print(name + "->");
		}
	}
}
