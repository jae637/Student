package KakaoCode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Test2 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		String input;

		input = in.nextLine();
		judge(input);
	}

	public static void judge(String line) {
		String determean = "1234567890_";
		String temp[] = line.split("&&");
		String first[] = new String[temp.length];
		String mark[] = new String[temp.length];
		String second[] = new String[temp.length];
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> sortlist = new ArrayList<String>();
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].split("==").length == 2) {
				String temp2[] = temp[i].split("==");
				first[i] = temp2[0];
				mark[i] = "==";
				second[i] = temp2[1];
			} else if (temp[i].split("!=").length == 2) {
				String temp2[] = temp[i].split("!=");
				first[i] = temp2[0];
				mark[i] = "!=";
				second[i] = temp2[1];
			} else {

			}
		}
		for (int i = 0; i < temp.length; i++) {
			list.add(first[i]);
			list.add(mark[i]);
			list.add(second[i]);
			list.add("&&");
		}
		list.remove(list.size());

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (!sortlist.contains(list.get(j)) || !list.get(j).equals("==") || !list.get(j).equals("!=")) {
					sortlist.add(list.get(j));
					sortlist.add(list.get(j + 1));
					sortlist.add(list.get(j + 2));
					sortlist.add(list.get(j + 3));
				} else {
					// Àü¿¡ ÀüÀÇ ÀÎµ¦½º¶û ºñ±³
					if (sortlist.get(sortlist.indexOf(list.get(j)) + 1).equals("&&")) {
						if (sortlist.get(sortlist.indexOf(list.get(j)) - 1).equals("==")) {
							String cmp = sortlist.get(sortlist.indexOf(list.get(j)) - 2);
							if(cmp==list.get(j)) {
								
							}

						} else {

						}
					} else {

					}
				}
			}
		}

		/*
		 * for (int j = 0; j < temp.length; j++) { int length = -1; for (int i = 0; i <
		 * temp.length * 2; i++) { if (mark[i / 2].equals("==")) { if (i % 2 == 0) { if
		 * (list.contains(first[i / 2])) { if (length == -1 || length > second[i /
		 * 2].length()) {
		 * 
		 * } } } else { if (list.contains(second[i / 2])) { if (length == -1 || length >
		 * first[i / 2].length()) {
		 * 
		 * } } } } else { } } if (length == -1) { for (int k = 0; k < list.size(); k++)
		 * {
		 * 
		 * } } } for (int i = 1; i < temp.length; i++) { if (list.contains(first[i]) &&
		 * mark[i].equals("==")) { if (list.indexOf(first[i]) % 3 == 0) { if
		 * (second[i].length() < list.get(list.indexOf(first[i]) + 2).length()) {
		 * list.remove(list.indexOf(first[i]) + 2); list.add(list.indexOf(first[i]) + 2,
		 * second[i]); } } else if (list.indexOf(first[i]) % 3 == 2) { if
		 * (second[i].length() < list.get(list.indexOf(first[i]) - 2).length()) {
		 * list.remove(list.indexOf(first[i]) - 2); list.add(list.indexOf(first[i]) - 2,
		 * second[i]); } } } else {
		 * 
		 * } }
		 */
	}

}
