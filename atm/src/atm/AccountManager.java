package atm;

import java.util.ArrayList;

public class AccountManager {
	private AccountManager() {}
	private static AccountManager instance = new AccountManager();
	public static AccountManager getInstance() {
		return instance;
	}
	
	private ArrayList<Account> list = new ArrayList<Account>();;
	
	public void join(Account account) {
		list.add(account);
	}
	
	public void leave(Account account) {
		list.remove(account);
	}
	
	public ArrayList<Account> getList() {
		return this.list;
	}

	@Override
	public String toString() {
		return "AccountManager [list=" + list + "]";
	}
	
	
	
}