package atm;

import java.util.ArrayList;

public class UserManager {
	// Design Pattern GOF 설계 패턴 중 Singleton(단일 인스턴스)
	
	// 1. 생성자 private로 숨기기
	private UserManager() {}
	// 2. 클래스 내부에서 단일 인스턴스 생성
	private static UserManager instance = new UserManager();
	// 3. 외부에서 단일 인스턴스를 참조 할 수 있는 getter 제공
	public static UserManager getInstance() {
		return instance;
	}
	
	private User nowUser;
	
	public User getNowUser() {
		return nowUser;
	}

	public void setNowUser(User nowUser) {
		this.nowUser = nowUser;
	}
	private ArrayList<User> list = new ArrayList<User>();
	
	/*public void join(User user) {
		list.add(user);
	}
	
	public void leave(User user) {
		list.remove(user);
	}*/
	
	public ArrayList<User> getList() {
		return this.list;
	}

	public void setList(ArrayList<User> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "UserManager [list=" + list + "]";
	}

	
	public void join() {
		System.out.print("id ");
		String id = Atm.inputString();
		if(!dupId(id)) {
			System.out.print("pw : ");
			String pw = Atm.inputString();
			System.out.print("name : ");
			String name = Atm.inputString();
			int userCode = getRndCode();
			
			User user = new User(userCode, id, pw, name);
			
			list.add(user);
			//accountManager.join(acc);
			
			System.out.println("가입 성공");
		}else {
			System.out.println("중복된 아이디");
		}
	}
	
	
	
	private boolean dupId(String id) {
		if(this.list!=null) {
			for(User user : this.list) {
				if(user.getId().equals(id)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int getRndCode() {
		int code = 0;
		while (true) {
			code = (int)(Math.random()*9000) + 1000;
			boolean dup = false;
			for(User user : this.list) {
				if(user.getUserCode() == code) {
					dup = true;
				}
			}
			if(!dup) {
				break;
			}
		}
		return code;
	}
	
	public void leave() {
		if(this.nowUser!=null) {
			if(this.nowUser.getAccs().size()==0) {
				System.out.print("pw : ");
				String pw = Atm.inputString();
				if(this.nowUser.getPw().equals(pw)) {
					list.remove(nowUser);
					// userManager.leave(nowUser);
					// accountManager.leave(nowUser.getAccount());
					System.out.println("탈퇴 성공");
					nowUser = null;
				}
			}else {
				System.out.println("계좌 해지 필요");
			}
		}else {
			System.out.println("로그인 필요");
		}
	}
	
	public void logIn() {
		if(this.nowUser==null) {
			System.out.print("id : ");
			String id = Atm.inputString();
			System.out.print("pw : ");
			String pw = Atm.inputString();
			
			for(User user : this.list) {
				if(user.getId().equals(id) && user.getPw().equals(pw)) {
					this.nowUser = user;
				}
			}
		}else {
			System.out.println("이미 로그인 했습니다.");
		}
	}
	
	public void logOut() {
		if(this.nowUser!=null) {
			this.nowUser=null;
		}else {
			System.out.println("로그인 필요");
		}
	}
	
	public String getData() {
		String data = "";
		if(this.list!=null) {
			
		}
		
		
		
		
		return data;
	}
}