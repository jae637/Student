package chap2;

public class src5 {
	public static void main(String[] args)
	{
		int[] iAry = new int[5];
		iAry[0]=0;
		try {
			for(int i=0;i<5;i++) {
			iAry[i+1] = i+1+iAry[i];
			System.out.println("intArray["+i+"]="+iAry[i]);
			}
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("�迭�� �ε��� ������ ������ϴ�.");
		}
	}
}
