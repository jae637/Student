import java.util.ArrayList;

public class Newbie {
	final private static int noodle = 5000;
	final private static int pork = 4000;
	final private static int cuprice = 3000;
	final private static int ramun = 2000;
	
	public static void main(String args[]) {
		//요셉이가 가지고 있는 돈
		int charge=50000;
		//경우의 수 표시 선언
		int count=1;
		//출력할 문자열 배열리스트 생성
		ArrayList<String> result=new ArrayList<String>();
		
		//4가지 음식을 가격순 대로 반복함
		for(int i=1;i<11;i++) {
			//가지고 있는 돈에서 갯수만큼의 가격을 뺌
			charge=charge-(i*noodle);
			for( int j=1;j<13;j++) {
				//가지고 있는 돈에서 갯수만큼의 가격을 뺌
				charge=charge-(j*pork);
				//해물야끼우동과 돈까스만 계산했을때 잔액이 모자를 경우
				if(charge<0) {
					charge=charge+(j*pork);
					break;
				}
				for(int k=1;k<17;k++) {
					charge=charge-(k*cuprice);
					//해물야끼우동과 돈까스와 컵밥만 계산했을때 잔액이 모자를 경우
					if(charge<0) {
						charge=charge+(k*cuprice);
						break;
					}
					for(int l=1;l<26;l++) {
						charge=charge-(l*ramun);
						//전부다 샀을때  잔액이 모자른 경우
						if(charge<0) {
							charge=charge+(l*ramun);
							break;
						}
						//전부다 샀을때  잔액이 남지 않는 경우
						else if(charge==0) {
							charge=charge+(l*ramun);
							result.add(count+". 해물야끼우동("+i+"개), 컵밥("+k+"개), 라면("+l+"개), 돈까스("+j+"개)");
							count++;
							break;
						}
						//금액 비교후, 금액 사용 전 단계로 롤백
						charge=charge+(l*ramun);
					}
					//금액 비교후, 금액 사용 전 단계로 롤백
					charge=charge+(k*cuprice);
				}
				//금액 비교후, 금액 사용 전 단계로 롤백
				charge=charge+(j*pork);
			}
			//금액 비교후, 금액 사용 전 단계로 롤백
			charge=charge+(i*noodle);
		}
		
		for(int i=0;i<result.size();i++)
			System.out.println(result.get(i));
		
	}
}
