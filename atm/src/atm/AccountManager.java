package atm;

import java.util.ArrayList;

public class AccountManager {
	private AccountManager() {}
	private static AccountManager instance = new AccountManager();
	public static AccountManager getInstance() {
		return instance;
	}
	
	private ArrayList<Account> list = new ArrayList<Account>();;
	
	/*public void join(Account account) {
		list.add(account);
	}
	
	public void leave(Account account) {
		list.remove(account);
	}*/
	
	public ArrayList<Account> getList() {
		return this.list;
	}

	public void setList(ArrayList<Account> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "AccountManager [list=" + list + "]";
	}
	
	public void joinAccount(User user) {
		if(user!=null) {
			int accNumber = getRndCode();
			System.out.print("계좌 비밀번호 ");
			int accPw = Atm.inputNumber();
			
			Account acc = new Account(user.getUserCode(), accNumber, accPw);
			this.list.add(acc);
			
			ArrayList<Account> accs = user.getAccs();
			accs.add(acc);
			user.setAccs(accs);
		}else {
			System.out.println("로그인 필요");
		}
	}
	
	public void leaveAccount(User user) {
		if(user!=null) {
			System.out.print("계좌번호 ");
			int accNumber = Atm.inputNumber();
			Account acc = getAccountByAccNumber(accNumber, user);
			if(acc!=null) {
				System.out.print("계좌 비밀번호 ");
				int accPw = Atm.inputNumber();
				if(acc.getAccPw()==accPw) {
					this.list.remove(acc);
					user.getAccs().remove(acc);
					System.out.println("철회 성공");
				}else {
					System.out.println("철회 실패");
				}
			}else {
				System.out.println("계좌번호 확인");
			}
		}else {
			System.out.println("로그인 필요");
		}
	}
	
	private Account getAccountByAccNumber(int accNumber) {
		if(this.list != null) {
			for(Account acc : this.list) {
				if(acc.getAccNumber() == accNumber) {
					return acc;
				}
			}
		}
		return null;
	}
	
	private Account getAccountByAccNumber(int accNumber, User user) {
		if(user!=null && user.getAccs()!=null) {
			for(Account acc : user.getAccs()) {
				if(acc.getAccNumber() == accNumber) {
					return acc;
				}
			}
		}
		return null;
	}
	
	public void inquiry(User user) {
		if(user!=null) {
			System.out.print("계좌번호 ");
			int accNumber = Atm.inputNumber();
			Account acc = getAccountByAccNumber(accNumber, user);
			if(acc!=null) {
				System.out.print("계좌 비밀번호 ");
				int accPw = Atm.inputNumber();
				if(acc.getAccPw()==accPw) {
					System.out.println("잔고 : " + acc.getMoney() + "원");
				}
			}else {
				System.out.println("계좌번호 확인");
			}
		}else {
			System.out.println("로그인 필요");
		}
	}
	
	private int getRndCode() {
		int code = 0;
		while (true) {
			code = (int)(Math.random()*9000) + 1000;
			boolean dup = false;
			for(Account acc : this.list) {
				if(acc.getAccNumber() == code) {
					dup = true;
				}
			}
			if(!dup) {
				break;
			}
		}
		return code;
	}
	
	public void deposit() {
		System.out.print("계좌번호 ");
		int accNumber = Atm.inputNumber();
		Account acc = getAccountByAccNumber(accNumber);
		System.out.print("금액 ");
		int money = Atm.inputNumber();
		if(acc != null) {
			acc.setMoney(acc.getMoney() + money);			
		}else {
			System.out.println("계좌 없음");
		}
	}
	
	public void withdraw(User user) {
		if(user!=null) {
			System.out.print("계좌번호 ");
			int accNumber = Atm.inputNumber();
			Account acc = getAccountByAccNumber(accNumber, user);
			if(acc!=null) {
				System.out.print("계좌 비밀번호 ");
				int accPw = Atm.inputNumber();
				if(acc.getAccPw()==accPw) {
					System.out.print("금액 ");
					int money = Atm.inputNumber();
					if(acc.getMoney()>=money) {
						acc.setMoney(acc.getMoney() - money);;
					}
					else {
						System.out.println("금액 부족");
					}
				}else {
					System.out.println("비밀번호 틀림");
				}
			}else {
				System.out.println("계좌 없음");
			}
		}else {
			System.out.println("로그인 필요");
		}
	}
	
	public void remit(User user) {
		if(user!=null) {
			System.out.print("계좌번호 ");
			int accNumber = Atm.inputNumber();
			Account acc = getAccountByAccNumber(accNumber, user);
			if(acc!=null) {
				System.out.print("계좌 비밀번호 ");
				int accPw = Atm.inputNumber();
				if(acc.getAccPw()==accPw) {
					System.out.print("이체할 계좌번호 ");
					int remAccNum = Atm.inputNumber();
					Account remAcc = getAccountByAccNumber(remAccNum);
					if(remAcc!=null) {
						System.out.print("금액 ");
						int money = Atm.inputNumber();
						if(acc.getMoney()>=money) {
							acc.setMoney(acc.getMoney() - money);;
							remAcc.setMoney(remAcc.getMoney() + money);
						}
						else {
							System.out.println("금액 부족");
						}
					}else {
						System.out.println("이체할 계좌 없음");
					}
				}else {
					System.out.println("비밀번호 틀림");
				}
			}else {
				System.out.println("계좌 없음");
			}
		}else {
			System.out.println("로그인 필요");
		}
	}
	
	public String getData() {
		String data = "";
		
		if(list.size()>0) {
			for(int i=0;i<list.size();i++) {
				data += list.get(i).getData() + "/";
			}
			data = data.substring(0, data.length()-1);
		}
		
		return data;
	}
}