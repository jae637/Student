package chap2;

import java.util.*;

public class src2 {
	enum Week {��,ȭ,��,��,��,��,��}
	
	public static void main(String[] args)
	{
		int[] num = {1,2,3,4,5};
		String names[] = {"���","��","�ٳ���","ü��","����","����"};
		int sum=0;
		
		for (int k : num)
		{
			sum +=k;
		}
		
		for (String s : names) {
			System.out.print(s+" ");
		}
		
		System.out.println();
		
		for(Week day : Week.values()) {
			System.out.print(day+"���� ");
		}
		
		System.out.println();
		
	}
}
