package ch3_1;

class Account {
	String accountNo;
	String ownerName;
	int balance;
	
	void deposit (int amount) {
		balance += amount;
	}
	
	int withdraw(int amount) throws Exception {
		if (balance < amount) {
			throw new Exception("�ܾ��� �����մϴ�");
		}
		balance -= amount;
		return amount;
	}
}