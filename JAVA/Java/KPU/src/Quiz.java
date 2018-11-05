import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Quiz {
	//방향지정 상수
	final private static int EAST = 1;
	final private static int WEST = 2;
	final private static int SOUTH = 3;
	final private static int NORTH = 4;

	public static void main(String args[]) {
		//차량의 좌표 변수 선언
		int x;
		int y;
		//차량의 보고있는 방향 변수 선언
		int direct;
		//열어야할 텍스트 파일 이름 변수 선언
		String filename= "move.txt";
		//텍스트 파일 안의 내용을 저장할 스트링 변수 선언
		String data = "";
		
		//파일 불러오는 과정
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
		//초기 차량 위치와 바라보고 있는 방향 설정
		x = 0;
		y = 0;
		direct = 4;
		
		//입력받은 명령어를 나누는 과정
		String[] command = data.split(" ");
		
		//명령어 수만큼 반복문 실행
		for (int i = 0; i < command.length; i++) {
			// 방향 전환 명령어 수행 
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
			
			//차량이 바라보고 있는 방향에 따른 전진 명령어 수행
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