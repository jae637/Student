package jiyoung;

import java.util.Scanner;

interface NewDays {
	public static final String[] days = {"N/A","월요일","화요일","수요일","목요일","금요일","토요일","일요일"};
	void printDays(int i);
} 

public class Interface implements NewDays{
	public Interface(int a) {
		// TODO Auto-generated constructor stub
		System.out.println("생성자 생성");
	}
	@Override
	public void printDays(int i) {
		System.out.println(days[i]);
	}
	public static void main(String[] args) {
		int number;
		Interface inter = new Interface(1);
		System.out.printf("표시할 날짜를 입력하세요(1~7) :");
		Scanner scanner =new Scanner(System.in);
		number=scanner.nextInt();
		if(number<1||number>7)
			System.out.println("잘못된 숫자가 입력되었습니다.");
		else
			inter.printDays(number);
	}
}
