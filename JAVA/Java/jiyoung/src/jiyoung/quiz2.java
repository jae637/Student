package jiyoung;

class Multi{
	int multi(int a,int b)
	{
		return a*b;
	}
	void multiPrint(int a, int b)
	{
		System.out.println(a*b);
	}
	void multiX(int a) {
		for (int i=1;i<10;i++) {
			System.out.print(a+"X"+i+"=");
			multiPrint(a,i);
		}
	}
}

public class quiz2 {
	public static void main(String[] args) {
		Multi m = new Multi();
		for(int i=1;i<10;i++)
			m.multiX(i);
	}
}
