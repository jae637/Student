package jiyoung;

import java.util.Scanner;

interface NewDays {
	public static final String[] days = {"N/A","������","ȭ����","������","�����","�ݿ���","�����","�Ͽ���"};
	void printDays(int i);
} 

public class Interface implements NewDays{
	public Interface(int a) {
		// TODO Auto-generated constructor stub
		System.out.println("������ ����");
	}
	@Override
	public void printDays(int i) {
		System.out.println(days[i]);
	}
	public static void main(String[] args) {
		int number;
		Interface inter = new Interface(1);
		System.out.printf("ǥ���� ��¥�� �Է��ϼ���(1~7) :");
		Scanner scanner =new Scanner(System.in);
		number=scanner.nextInt();
		if(number<1||number>7)
			System.out.println("�߸��� ���ڰ� �ԷµǾ����ϴ�.");
		else
			inter.printDays(number);
	}
}
