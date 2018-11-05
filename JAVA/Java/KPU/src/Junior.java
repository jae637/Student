import java.io.*;
import java.lang.*;

public class Junior {
	public static void main(String args[]) {
		// �ڷ��� ����
		int count;
		// ������� �ؽ�Ʈ ���� �̸� ���� ����
		String filename = "points.txt";
		// �ؽ�Ʈ ���� ���� ������ ������ ��Ʈ�� ���� ����
		String data = "";

		// ���� �ҷ����� ����
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
		//���� ù��° ������ ��ŭ �迭�� �����ϱ� ���� ����
		count = Integer.parseInt(result[0]);
		//��ǥ���� X��ǥ�� Y��ǥ�� ù ��° ���� ��ŭ ����
		double[] x = new double[count];
		double[] y = new double[count];
		// ���� �����͸� ������ ¦�� ��° �����ʹ� X, Ȧ�� ��° �����ʹ� Y��ǥ�� �Է�
		for (int i = 1; i < result.length; i++) {
			if (i % 2 == 1) {
				x[i / 2] = Double.valueOf(result[i]);
			} else {
				y[(i / 2) - 1] = Double.valueOf(result[i]);
			}
		}
		//����� (������ ���̵��� ��)����
		double sum=0;
		for(int i=0;i<x.length-1;i++) {
			double xdif=x[i+1]-x[i];
			double ydif=y[i+1]-y[i];
			ydif= Math.pow(ydif,2);
			xdif=Math.pow(xdif,2);
			sum=sum+Math.sqrt(ydif+xdif);
		}
		//����� ���
		System.out.println(sum);
	}

}
