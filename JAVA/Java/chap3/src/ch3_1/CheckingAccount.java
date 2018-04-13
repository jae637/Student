package ch3_1;

class CheckingAccount extends Account {
	String cardNo;
	
	//자식은 생성자 부모는 일반
	public CheckingAccount(String accountNo, String ownerName, int balance, String cardNo) {
		this.accountNo = accountNo;
		this.ownerName = ownerName;
		this.balance = balance;
		this.cardNo =cardNo;
	}
	int pay (String cardeNo, int amount) throws Exception{
		if(!cardNo.equals(this.cardNo)||(balance<amount)) {
			throw new Exception("지불이 불가능 합니다");
		}
		return withdraw(amount);
	}
}