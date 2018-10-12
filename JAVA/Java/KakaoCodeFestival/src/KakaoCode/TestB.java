package KakaoCode;

import java.util.Scanner;
import java.lang.Math;

public class TestB {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String input;
		String input2;
		int avg=0, counter=0;
/*
		input = in.nextLine();*/
		avg=in.nextInt();
		counter= in.nextInt();/*
		avg = Integer.parseInt(input.split("\\s")[0]);
		counter = Integer.parseInt(input.split("\\s")[1]);*/
		if (avg > 0 && avg < 501) {
			if (counter > 0 && counter < avg+1) {
				input2 = in.nextLine();
				judge(input2, avg, counter);
			}
		} else
			return;
	}

	public static void judge(String a, int average, int counter) {
		String stringnumbers[] = a.split("\\s");
		double total = -1;
		int numbers[] = new int[average];
		for (int i = 0; i < average; i++) {
			numbers[i] = Integer.parseInt(stringnumbers[i]);
			if (numbers[i] < 0 || numbers[i] > 1000000)
				return;
		}
		for (int i = 0; i < numbers.length - counter + 1; i++) {
			double avg = 0;
			double sum = 0;
			for (int j = 0; j < counter; j++)
				avg = avg + numbers[i + j];
			avg = avg / counter;
			for (int j = 0; j < counter; j++) {
				sum = sum + ((numbers[i + j] - avg)*(numbers[i + j] - avg));
			}
			sum = sum / counter;
			sum = Math.sqrt(sum);
			if (total == -1 || total > sum) {
				total = sum;
			}
		}
		System.out.print(String.format("%.7f", total));
	}

}
