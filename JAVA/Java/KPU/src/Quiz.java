import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Quiz {
	//�������� ���
	final private static int EAST = 1;
	final private static int WEST = 2;
	final private static int SOUTH = 3;
	final private static int NORTH = 4;

	public static void main(String args[]) {
		//������ ��ǥ ���� ����
		int x;
		int y;
		//������ �����ִ� ���� ���� ����
		int direct;
		//������� �ؽ�Ʈ ���� �̸� ���� ����
		String filename= "move.txt";
		//�ؽ�Ʈ ���� ���� ������ ������ ��Ʈ�� ���� ����
		String data = "";
		
		//���� �ҷ����� ����
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
		//�ʱ� ���� ��ġ�� �ٶ󺸰� �ִ� ���� ����
		x = 0;
		y = 0;
		direct = 4;
		
		//�Է¹��� ��ɾ ������ ����
		String[] command = data.split(" ");
		
		//��ɾ� ����ŭ �ݺ��� ����
		for (int i = 0; i < command.length; i++) {
			// ���� ��ȯ ��ɾ� ���� 
			switch (command[i]) {
			case "L":
				if (direct == NORTH)
					direct = WEST;
				else if (direct == SOUTH)
					direct = EAST;
				else if (direct == EAST)
					direct = NORTH;
				else
					direct = SOUTH;
				break;
			case "R":
				if (direct == NORTH)
					direct = EAST;
				else if (direct == SOUTH)
					direct = WEST;
				else if (direct == EAST)
					direct = SOUTH;
				else
					direct = NORTH;
				break;
			default:
				break;
			}
			
			//������ �ٶ󺸰� �ִ� ���⿡ ���� ���� ��ɾ� ����
			switch (direct) {
			case NORTH:
				y = y + 1;
				break;
			case SOUTH:
				y = y - 1;
				break;
			case WEST:
				x = x - 1;
				break;
			case EAST:
				x = x + 1;
				break;
			}
		}
		System.out.println(x + "," + y);
	}

}