package KaKaoCode;

import java.util.*;

public class Ex1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String input;
		int count;

		in.nextInt()
		input = in.nextLine();
		try {
		if (Integer.parseInt(input) < 11 && Integer.parseInt(input) > 0)
			count = Integer.parseInt(input);
		else
			count=0;
		}
		catch(Exception e){
			count = 11;
		}
		if (count == 0)
			System.out.println("¹üÀ§¸¦ ¹þ¾î³µ½À´Ï´Ù.");
		else if (count==11)
			System.out.println("Error 500");
		else
			for (int i = 0; i < count; i++)
				System.out.println("Welcome to Kakao Code Festival!");
	}
}
