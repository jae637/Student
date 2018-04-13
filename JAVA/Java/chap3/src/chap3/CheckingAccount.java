package chap3;

class CheckingAccount extends Account {
	String cardNo;
	
	int pay (String cardeNo, int amount) throws Exception{
		if(!cardNo.equals(this.cardNo)||(balance<amount)) {
			throw new Exception("지불이 불가능 합니다");
		}
		return withdraw(amount);
	}
}