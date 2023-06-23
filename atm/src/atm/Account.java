package atm;

public class Account {
	private int money;
	
	public Account() {
		this.money = 1000;
	}
	
	public void deposit(int money) {
		this.money += money;
	}
	
	public boolean withdraw(int money) {
		if(this.money>=money) {
			this.money-=money;
			return true;
		}
		else {
			System.out.println("금액 부족");
			return false;
		}
	}
	
	public void remit(int money, Account account) {
		if(withdraw(money)) {
			account.deposit(money);
		}
	}

	@Override
	public String toString() {
		return "[money=" + money + "]";
	}
	
	
}