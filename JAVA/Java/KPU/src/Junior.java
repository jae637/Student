import java.io.*;
import java.lang.*;

public class Junior {
	public static void main(String args[]) {
		// 자료의 갯수
		int count;
		// 열어야할 텍스트 파일 이름 변수 선언
		String filename = "points.txt";
		// 텍스트 파일 안의 내용을 저장할 스트링 변수 선언
		String data = "";

		// 파일 불러오는 과정
		BufferedReader br = null;
		File inFile = new File(filename);
		try {
			br = new BufferedReader(new FileReader(inFile));
			String line;
			while ((line = br.readLine()) != null) {
				data = line + "\n" + data;
			}
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
		}
		String[] result = data.split(" ");
		count = Integer.parseInt(result[0]);
		double[] x = new double[count];
		double[] y = new double[count];
		for (int i = 1; i < result.length; i++) {
			if (i % 2 == 1) {
				x[i / 2] = Double.valueOf(result[i]);
			} else {
				y[(i / 2) - 1] = Double.valueOf(result[i]);
			}
		}
		//결과값 (선분의 길이들의 합)
		double sum=0;
		for(int i=0;i<x.length-1;i++) {
			double xdif=x[i+1]-x[i];
			double ydif=y[i+1]-y[i];
			ydif= Math.pow(ydif,2);
			xdif=Math.pow(xdif,2);
			sum=sum+Math.sqrt(ydif+xdif);
		}
		
		System.out.println(sum);
	}

}
