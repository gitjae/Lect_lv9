package atm;

public class User {
	private String id;
	private String pw;
	private String name;
	private Account account;
	
	public User(String id, String pw, String name, Account account) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.account = account;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}
	
	public Account getAccount() {
		return this.account;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", pw=" + pw + ", name=" + name + ", account=" + account + "]";
	}
	
	
}