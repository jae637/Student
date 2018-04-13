package ch3_4;

class Account {
	String accountNo;
	String ownerName;
	int balance;
	//생성자 부분
	public Account (String accountNo,String ownerName, int balance) {
		this.accountNo = accountNo;
		this.ownerName = ownerName;
		this.balance = balance;
	}
	
	void deposit (int amount) {
		balance += amount;
	}
	
	int withdraw(int amount) throws Exception {
		if (balance < amount) {
			throw new Exception("잔액이 부족합니다");
		}
		balance -= amount;
		return amount;
	}
}