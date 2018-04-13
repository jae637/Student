package ch3_3;

public class InheritanceExample4 {
	public static void main (String[] args)
	{
		CreditLineAccount obj = new CreditLineAccount("11-22-333333","Jung han min",10000,20000000);
		
		try {
			int amount = obj.withdraw(50000);
			System.out.println("인출액"+amount);
			System.out.println("잔액"+obj.balance);
			System.out.println("마이너스 한도"+obj.creditLine);
		}catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
