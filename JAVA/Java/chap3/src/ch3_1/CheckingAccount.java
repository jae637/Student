package ch3_1;

class CheckingAccount extends Account {
	String cardNo;
	
	//�ڽ��� ������ �θ�� �Ϲ�
	public CheckingAccount(String accountNo, String ownerName, int balance, String cardNo) {
		this.accountNo = accountNo;
		this.ownerName = ownerName;
		this.balance = balance;
		this.cardNo =cardNo;
	}
	int pay (String cardeNo, int amount) throws Exception{
		if(!cardNo.equals(this.cardNo)||(balance<amount)) {
			throw new Exception("������ �Ұ��� �մϴ�");
		}
		return withdraw(amount);
	}
}