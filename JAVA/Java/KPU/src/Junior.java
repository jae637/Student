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
		//들어온 첫번째 데이터 만큼 배열을 생성하기 위해 선언
		count = Integer.parseInt(result[0]);
		//좌표들의 X좌표와 Y좌표를 첫 번째 숫자 만큼 생성
		double[] x = new double[count];
		double[] y = new double[count];
		// 파일 데이터를 읽을때 짝수 번째 데이터는 X, 홀수 번째 데이터는 Y좌표로 입력
		for (int i = 1; i < result.length; i++) {
			if (i % 2 == 1) {
				x[i / 2] = Double.valueOf(result[i]);
			} else {
				y[(i / 2) - 1] = Double.valueOf(result[i]);
			}
		}
		//결과값 (선분의 길이들의 합)선언
		double sum=0;
		for(int i=0;i<x.length-1;i++) {
			double xdif=x[i+1]-x[i];
			double ydif=y[i+1]-y[i];
			ydif= Math.pow(ydif,2);
			xdif=Math.pow(xdif,2);
			sum=sum+Math.sqrt(ydif+xdif);
		}
		//결과값 출력
		System.out.println(sum);
	}

}
