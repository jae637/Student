import java.text.CollationElementIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

class Solution {
	public static void main(String argv[]) {
		String al = "photo.jpg, Warsaw, 2013-09-05 14:08:15\n" + "john.png, London, 2015-06-20 15:13:22\n"
				+ "myFriends.png, Warsaw, 2013-09-05 14:07:13\n" + "Eiffel.jpg, Paris, 2015-07-23 08:03:02\n"
				+ "pisatower.jpg, Paris, 2015-07-22 23:59:59\n" + "BOB.jpg, London, 2015-08-05 00:02:03\n"
				+ "notredame.png, Paris, 2015-09-01 12:00:00\n" + "me.jpg, Warsaw, 2013-09-06 15:40:22\n"
				+ "a.png, Warsaw, 2016-02-13 13:33:50\n" + "b.jpg, Warsaw, 2016-01-02 15:12:22\n"
				+ "c.jpg, Warsaw, 2016-01-02 14:34:30\n" + "d.jpg, Warsaw, 2016-01-02 15:15:01\n"
				+ "e.png, Warsaw, 2016-01-02 09:49:09\n" + "f.png, Warsaw, 2016-01-02 10:55:32\n"
				+ "g.jpg, Warsaw, 2016-02-29 22:13:11";
		System.out.println(solution(al));
	}

	public static String solution(String S) {
		// write your code in Java SE 8
		String[] arStr = S.split("\n");
		String[] temp;
		String country;
		String result = "";
		int number = 0;

		for (int i = 0; i < arStr.length; i++) {
			arStr[i] = arStr[i].substring(arStr[i].indexOf(",") + 2);
		}
		temp = arStr.clone();
		Arrays.sort(temp);
		country = temp[0].split(",")[0];
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].split(",")[0].equals(country)) {
				number++;
			} else {
				country = temp[i].split(",")[0];
				number = 1;
			}

			if (number > 9) {
				temp[i] = temp[i].split(",")[0] + "," + temp[i].split(",")[1] + "," + temp[i].split(",")[0] + number;
			} else {
				temp[i] = temp[i].split(",")[0] + "," + temp[i].split(",")[1] + "," + temp[i].split(",")[0] + "0"
						+ number;
			}
		}

		for (int i = 0; i < arStr.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				if (arStr[i].split(",")[0].equals(temp[j].split(",")[0])
						&& arStr[i].split(",")[1].equals(temp[j].split(",")[1])) {
					arStr[i] = temp[j].split(",")[2];
				}
			}
		}
		String[] ghkrwkd = S.split("\n");
		for (int i = 0; i < arStr.length; i++) {
			result = result + arStr[i] + ghkrwkd[i].split(",")[0].substring(ghkrwkd[i].split(",")[0].indexOf(".")) + "\n";
		}
		return result;
	}
}