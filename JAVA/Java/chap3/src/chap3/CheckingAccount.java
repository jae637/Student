package chap3;

class CheckingAccount extends Account {
	String cardNo;
	
	int pay (String cardeNo, int amount) throws Exception{
		if(!cardNo.equals(this.cardNo)||(balance<amount)) {
			throw new Exception("������ �Ұ��� �մϴ�");
		}
		return withdraw(amount);
	}
}