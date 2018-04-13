package chap3;




public class InheritanceExample1 {
	public static void main(String[] args) {
		CheckingAccount obj = new CheckingAccount();
		
		obj.accountNo="111-22-333333";
		obj.ownerName="มควันฮ";
		obj.cardNo="1234-1234-1234-1234";
		obj.deposit(1000000);
		
		
		try {
			int paidAmount = obj.pay("1234-1234-1234-1234", 47000);
			System.out.println("ม๖บาพื"+paidAmount);
			System.out.println("ภÜพื"+obj.balance);
		} catch (Exception e) {
			String msg = e.getMessage();
			System.out.println(msg);
		}
	}
}
