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
	
	private ArrayList<User> list = new ArrayList<User>();
	
	public void join(User user) {
		list.add(user);
	}
	
	public void leave(User user) {
		list.remove(user);
	}
	
	public ArrayList<User> getList() {
		return this.list;
	}

	@Override
	public String toString() {
		return "UserManager [list=" + list + "]";
	}

	
	
}