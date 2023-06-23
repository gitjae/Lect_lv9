package atm;

import java.util.Scanner;

public class Atm {
	private String brandName;
	
	private UserManager userManager;
	private AccountManager accountManager;
	private User nowUser;
	
	private Scanner scan;
	
	
	private final int MEMMNG = 1;
	private final int ACCMNG = 2;
	private final int BANKSVC = 3;
	private final int FILEMNG = 4;
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	
	
	public Atm(String brandName) {
		this.brandName = brandName;
	}
	
	private void init() {
		userManager = UserManager.getInstance();
		accountManager = AccountManager.getInstance();
		scan = new Scanner(System.in);
	}
	
	public void run() {
		init();
		while (true) {
			printMenu();
			System.out.println(userManager);
			System.out.println(accountManager);
			int menu = inputNumber();
			if(menu == MEMMNG) {
				printMemMng();
				int memMenu = inputNumber();
				if (memMenu == JOIN) {
					join();
				}else if (memMenu == LEAVE) {
					leave();
				}else if (memMenu == LOGIN) {
					logIn();
				}else if (memMenu == LOGOUT) {
					logOut();
				}
			}else if (menu == ACCMNG) {
				
			}else if (menu == BANKSVC) {
				
			}else if (menu == FILEMNG) {
				
			}
		}
	}
	
	private void printMenu() {
		System.out.println("[1]회원관리");
		System.out.println("[2]계좌관리");
		System.out.println("[3]뱅킹서비스");
		System.out.println("[4]파일처리");
	}
	
	private int inputNumber() {
		System.out.print("입력 : ");
		return scan.nextInt();
	}
	
	private void printMemMng() {
		System.out.println("[1]가입");
		System.out.println("[2]탈퇴");
		System.out.println("[3]로그인");
		System.out.println("[4]로그아웃");
	}
	
	private String inputString() {
		System.out.print("입력 : ");
		return scan.next();
	}
	
	private void join() {
		System.out.print("id ");
		String id = inputString();
		if(!dupId(id)) {
			System.out.print("pw : ");
			String pw = inputString();
			System.out.print("name : ");
			String name = inputString();
			Account acc = new Account();
			
			User user = new User(id, pw, name, acc);
			
			userManager.join(user);
			accountManager.join(acc);
			
			System.out.println("가입 성공");
		}else {
			System.out.println("중복된 아이디");
		}
	}
	
	private boolean dupId(String id) {
		if(userManager.getList()!=null) {
			for(User user : userManager.getList()) {
				if(user.getId().equals(id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void leave() {
		if(nowUser!=null) {
			System.out.print("pw : ");
			String pw = inputString();
			if(nowUser.getPw().equals(pw)) {
				userManager.leave(nowUser);
				accountManager.leave(nowUser.getAccount());
				System.out.println("탈퇴 성공");
				nowUser = null;
			}
		}else {
			System.out.println("로그인 필요");
		}
	}
	
	private void logIn() {
		if(nowUser==null) {
			System.out.print("id : ");
			String id = inputString();
			System.out.print("pw : ");
			String pw = inputString();
			
			for(User user : userManager.getList()) {
				if(user.getId().equals(id) && user.getPw().equals(pw)) {
					this.nowUser = user;
				}
			}
		}else {
			System.out.println("이미 로그인 했습니다.");
		}
	}
	
	private void logOut() {
		if(nowUser!=null) {
			nowUser=null;
		}else {
			System.out.println("로그인 필요");
		}
	}
}