package ch3_2;

class InheritanceExample2 {
	public static void main(String[] args) {
		CheckingAccount obj = new CheckingAccount("11-22-333333","���ѹ�",0,"1234-1234-1234-1234");
		obj.deposit(100000);

		try {
			int paidAmount = obj.pay("1234-1234-1234-1234", 47000);
			System.out.println("���Ҿ� : " + paidAmount);
			System.out.println("�ܾ� : " + obj.balance );
		}catch (Exception e) {
			String msg = e.getMessage();
			System.out.println(msg);
		}
	}
}
