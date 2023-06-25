package atm;

import java.util.ArrayList;

public class User {
	private String id;
	private String pw;
	private String name;
	private int userCode;
	private ArrayList<Account> accs = new ArrayList<Account>();
	// private Account account;
	
	/*public User(String id, String pw, String name, Account account) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		//this.account = account;
	}*/
	
	public User(int userCode, String id, String pw, String name) {
		this.userCode = userCode;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getPw() {
		return this.pw;
	}

	public int getUserCode() {
		return userCode;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", userCode=" + userCode + ", accs=" + accs + "]";
	}

	public ArrayList<Account> getAccs() {
		return this.accs;
	}

	public void setAccs(ArrayList<Account> accs) {
		this.accs = accs;
	}
	
	/*public Account getAccount() {
		return this.account;
	}*/

	public String getData() {
		String data = "";
		data += "userCode=" + this.userCode + ",";
		data += "id=" + this.id + ",";
		data += "pw=" + this.pw + ",";
		data += "name=" + this.name + ",";
		if(accs.size()>0) {
			for(int i=0;i<accs.size();i++) {
				data+=accs.get(i).getData();
			}
		}
		return data;
	}
	
}