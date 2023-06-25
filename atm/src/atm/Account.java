package atm;

public class Account {
	private int userCode;
	private int accNumber;
	private int accPw;
	private int money;
	
	public Account(int userCode, int accNumber, int accPw) {
		this.userCode = userCode;
		this.accNumber = accNumber;
		this.accPw = accPw;
		this.money = 1000;
	}
	public Account(int userCode, int accNumber, int accPw, int money) {
		this.userCode = userCode;
		this.accNumber = accNumber;
		this.accPw = accPw;
		this.money = money;
	}
	
	
	
	public int getUserCode() {
		return userCode;
	}

	public int getAccPw() {
		return accPw;
	}

	public void setAccPw(int accPw) {
		this.accPw = accPw;
	}

	public int getAccNumber() {
		return accNumber;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "Account [userCode=" + userCode + ", accNumber=" + accNumber + ", accPw=" + accPw + ", money=" + money
				+ "]";
	}

	public String getData() {
		String data = "";
		data += "userCode=" + this.userCode + ",";
		data += "accNumber=" + this.accNumber + ",";
		data += "accPw=" + this.accPw + ",";
		data += "money=" + this.money;
		return data;
	}
	
}