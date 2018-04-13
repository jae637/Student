package ch3_2;

class CheckingAccount extends Account {
	String cardNo;
	
	//상위 클래스의 생산자를 하위 클래스에서 넣는 방법
	public CheckingAccount(String accountNo, String ownerName, int balance, String cardNo) {
		super(accountNo,ownerName,balance);
		this.cardNo =cardNo;
	}
	int pay (String cardeNo, int amount) throws Exception{
		if(!cardNo.equals(this.cardNo)||(balance<amount)) {
			throw new Exception("지불이 불가능 합니다");
		}
		return withdraw(amount);
	}
}