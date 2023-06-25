package atm;

import java.util.Scanner;

public class Atm {
	private String brandName;
	
	private UserManager userManager;
	private AccountManager accountManager;
	private FileManager fileManager;
	
	public static Scanner scan;
	
	
	private final int MEMMNG = 1;
	private final int ACCMNG = 2;
	private final int BANKSVC = 3;
	private final int FILEMNG = 4;
	
	private final int JOIN = 1;
	private final int LEAVE = 2;
	private final int LOGIN = 3;
	private final int LOGOUT = 4;
	
	private final int INQUIRY = 3;
	
	private final int DEPOSIT = 1; 
	private final int WITHDRAW = 2;
	private final int REMIT = 3;
	
	private final int SAVE = 1;
	private final int LOAD = 2;
	
	public Atm(String brandName) {
		this.brandName = brandName;
	}
	
	private void init() {
		userManager = UserManager.getInstance();
		accountManager = AccountManager.getInstance();
		fileManager = FileManager.getInstance();
		scan = new Scanner(System.in);
	}
	
	public void run() {
		init();
		while (true) {
			printMenu();
			String data = userManager.toString() + "\n";
			data+= accountManager.toString();
			System.out.println(data);
			//System.out.println(userManager);
			//System.out.println(accountManager);
			int menu = inputNumber();
			if(menu == MEMMNG) {
				printMemMng();
				int memMenu = inputNumber();
				if (memMenu == JOIN) {
					userManager.join();
				}else if (memMenu == LEAVE) {
					userManager.leave();
				}else if (memMenu == LOGIN) {
					userManager.logIn();
				}else if (memMenu == LOGOUT) {
					userManager.logOut();
				}
			}else if (menu == ACCMNG) {
				printAccMng();
				int accMenu = inputNumber();
				if(accMenu==JOIN) {
					accountManager.joinAccount(userManager.getNowUser());
				}else if (accMenu==LEAVE) {
					accountManager.leaveAccount(userManager.getNowUser());
				}else if (accMenu==INQUIRY) {
					accountManager.inquiry(userManager.getNowUser());
				}
			}else if (menu == BANKSVC) {
				printBankSvc();
				int bankMenu = inputNumber();
				if(bankMenu == DEPOSIT) {
					accountManager.deposit();
				}else if (bankMenu == WITHDRAW) {
					accountManager.withdraw(userManager.getNowUser());
				}else if (bankMenu == REMIT) {
					accountManager.remit(userManager.getNowUser());
				}
			}else if (menu == FILEMNG) {
				printFileMng();
				int fileMenu = inputNumber();
				if(fileMenu == SAVE) {
					fileManager.save();
				}else if (fileMenu == LOAD) {
					fileManager.load();
				}
			}
		}
	}
	
	private void printMenu() {
		System.out.println("[1]회원관리");
		System.out.println("[2]계좌관리");
		System.out.println("[3]뱅킹서비스");
		System.out.println("[4]파일처리");
	}
	
	public static int inputNumber() {
		System.out.print("입력 : ");
		return scan.nextInt();
	}
	
	private void printMemMng() {
		System.out.println("[1]가입");
		System.out.println("[2]탈퇴");
		System.out.println("[3]로그인");
		System.out.println("[4]로그아웃");
	}
	
	
	public static String inputString() {
		System.out.print("입력 : ");
		return scan.next();
	}

	private void printAccMng() {
		System.out.println("[1]계약");
		System.out.println("[2]철회");
		System.out.println("[3]조회");
	}
	
	private void printBankSvc() {
		System.out.println("[1]입금");
		System.out.println("[2]출금");
		System.out.println("[3]이체");
	}
	
	private void printFileMng() {
		System.out.println("[1]세이브");
		System.out.println("[2]로드");
	}
}