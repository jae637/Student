package ch3_2;

class CheckingAccount extends Account {
	String cardNo;
	
	//���� Ŭ������ �����ڸ� ���� Ŭ�������� �ִ� ���
	public CheckingAccount(String accountNo, String ownerName, int balance, String cardNo) {
		super(accountNo,ownerName,balance);
		this.cardNo =cardNo;
	}
	int pay (String cardeNo, int amount) throws Exception{
		if(!cardNo.equals(this.cardNo)||(balance<amount)) {
			throw new Exception("������ �Ұ��� �մϴ�");
		}
		return withdraw(amount);
	}
}