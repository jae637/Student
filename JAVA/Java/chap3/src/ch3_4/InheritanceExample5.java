package ch3_4;

public class InheritanceExample5 {
	public static void main(String[] args) {
		BonusPointAccount obj = new BonusPointAccount("11-22-333333","Jung han min",0,0);
		
		obj.deposit(100000);
		System.out.println("�ܾ� :"+obj.balance);
		System.out.println("���� ����Ʈ:" +obj.bonusPoint);
	}
}
