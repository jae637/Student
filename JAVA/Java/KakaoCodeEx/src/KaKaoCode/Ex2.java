package KaKaoCode;

import java.util.*;

//A, C, F, J, M, N, R, T ���� ����ġ, ��, ���ε�, ������, ����, �׿�, ���̾�, Ʃ��

public class Ex2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String input;
		String[] input2 = null;
		int count;

		input = in.nextLine();
		try {
		if (Integer.parseInt(input) < 101 && Integer.parseInt(input) > 0)
			count = Integer.parseInt(input);
		else
			count=0;
		}
		catch(Exception e){
			count = 101;
		}
		if (count == 0)
			System.out.println("������ ������ϴ�.");
		else if (count==101)
			System.out.println("Error 500");
		else {
			input2= new String[count];
			for (int i = 0; i < count; i++) {
				input2[i]=in.nextLine();
				input2[i].
			}
		}
		judge(input2);/*
		for (int i=0 ; i<count;i++) {
			//input2[i].charAt(0);
			judge(input2);
		}*/
	}
	
	public static void judge(String[] line) {
		int count = line.length;
		//System.out.println(""+count);
		for (int i=0 ;i<count;i++) {
			String character1=line[i].charAt(0);
			String character2=line[i].charAt(2);
			
		}
		
	}
}
