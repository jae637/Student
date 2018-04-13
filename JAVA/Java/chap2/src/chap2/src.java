package chap2;

import java.util.*;

public class src {
	public static void main(String argc[])
	{
		Scanner in = new Scanner(System.in);
		int intArray[]= new int[5];
		double sum = 0;

	
	System.out.println("평균을 구하기 위해서 5개의 숫자를 입력하세요");
	
	for(int i=0;i<intArray.length;i++) {
		intArray[i]= in.nextInt();
	}
	
	for (int i=0; i<intArray.length;i++)
	{
		sum+=intArray[i];
	}
	
	System.out.print("배열 원소의 평균은" + sum/intArray.length+"입니다.");

	}
}
