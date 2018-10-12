package KaKaoCode;

import java.util.*;

//A, C, F, J, M, N, R, T 각각 어피치, 콘, 프로도, 제이지, 무지, 네오, 라이언, 튜브

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
			System.out.println("범위를 벗어났습니다.");
		else if (count==101)
			System.out.println("Error 500");
		else {
			input2= new String[count];
			for (int i = 0; i < count; i++) {
				input2[i]=in.nextLine();
			}
		}
		System.out.println(""+judge(input2));
		/*
		for (int i=0 ; i<count;i++) {
			//input2[i].charAt(0);
			judge(input2);
		}*/
	}
	
	public static int judge(String[] line) {
		int count = line.length;
		int counting=0;
		char character1[] = new char[count];
		char character2[] =  new char[count];
		char judge[] = new char[count];
		int interval[] = new int[count];
		//System.out.println(""+count);
		for (int i=0 ;i<count;i++) {
			character1[i]=line[i].charAt(0);
			character2[i]=line[i].charAt(2);
			judge[i]=line[i].charAt(3);
			interval[i]=(int)line[i].charAt(4)-48;
		}
		String characters = "ACFJMNRT";
		String 
		for (int i=0;i<8;i++) {
			for (int j=0;j<count;j++) {
				if ()
			}
		}
		for (int )
		return counting;
	}
}
