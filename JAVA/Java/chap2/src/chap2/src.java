package chap2;

import java.util.*;

public class src {
	public static void main(String argc[])
	{
		Scanner in = new Scanner(System.in);
		int intArray[]= new int[5];
		double sum = 0;

	
	System.out.println("����� ���ϱ� ���ؼ� 5���� ���ڸ� �Է��ϼ���");
	
	for(int i=0;i<intArray.length;i++) {
		intArray[i]= in.nextInt();
	}
	
	for (int i=0; i<intArray.length;i++)
	{
		sum+=intArray[i];
	}
	
	System.out.print("�迭 ������ �����" + sum/intArray.length+"�Դϴ�.");

	}
}
