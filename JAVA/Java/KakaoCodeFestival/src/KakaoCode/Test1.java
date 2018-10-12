package KakaoCode;

import java.util.Scanner;

public class Test1 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String input;
		String[] input2 = null;
		int count;

		input = in.nextLine();
		if (Integer.parseInt(input) < 1001 && Integer.parseInt(input) > 0)
			count = Integer.parseInt(input);
		else
			count = 0;
		if (count == 0) {
			System.out.println("¹üÀ§¸¦ ¹þ¾î³µ½À´Ï´Ù.");
			return;
		} else if (count == 1001) {
			System.out.println("Error 500");
			return;
		} else {
			input2 = new String[count];
			for (int i = 0; i < count; i++) {
				input2[i] = in.nextLine();
			}
		}
		judge(input2);
	}

	public static void judge(String[] line) {
		int count = line.length;
		int counting = 0;
		int first[] = new int[count];
		int second[] = new int[count];
		for (int i = 0; i < count; i++) {
			String temp[] = line[i].split("\\s");
			if (Integer.parseInt(temp[0]) > 0 && Integer.parseInt(temp[0]) < 101)
				first[i] = Integer.parseInt(temp[0]);
			else
				first[i] = 0;
			if (Integer.parseInt(temp[1]) > 0 && Integer.parseInt(temp[1]) < 65)
				second[i] = Integer.parseInt(temp[1]);
			else
				second[i] = 0;
			if (first[i] == 0)
				counting = 0;
			else if (first[i] == 1)
				counting = 5000000;
			else if (first[i] < 4)
				counting = 3000000;
			else if (first[i] < 7)
				counting = 2000000;
			else if (first[i] < 11)
				counting = 500000;
			else if (first[i] < 16)
				counting = 300000;
			else if (first[i] < 22)
				counting = 100000;
			else
				counting = 0;

			if (second[i] == 0)
				counting += 0;
			else if (second[i] == 1)
				counting += 5120000;
			else if (second[i] < 4)
				counting += 2560000;
			else if (second[i] < 8)
				counting += 1280000;
			else if (second[i] < 16)
				counting += 640000;
			else if (second[i] < 32)
				counting += 320000;
			else
				counting += 0;

			System.out.println("" + counting);
		}
	}
}
