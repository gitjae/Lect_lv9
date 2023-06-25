package atm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	private FileManager() {}
	private static FileManager instance = new FileManager();
	public static FileManager getInstance() {
		return instance;
	}
	
	String fileName = "atm.txt";
	private File file = new File(fileName);
	private FileWriter fw;
	private FileReader fr;
	private BufferedReader br;
	
	UserManager userManager = UserManager.getInstance();
	AccountManager accountManager = AccountManager.getInstance();
	
	public void save() {
		try {
			fw = new FileWriter(fileName);
			
			String data = "";
			data += userManager.toString() + "\n";
			data += accountManager.toString();
			
			fw.write(data);
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void load() {
		if(file.exists()) {
			try {
				fr = new FileReader(fileName);
				br = new BufferedReader(fr);
				
				String data = "";
				while (br.ready()) {
					data += br.readLine() + "\n";
				}
				if(data!="") {
					data = data.substring(0, data.length()-1);
					ArrayList<User> userlist = new ArrayList<User>();
					ArrayList<Account> acclist = new ArrayList<Account>();
					String[] lines =data.split("\n");
					String[] users = lines[0].split("User ");
					for(int i=1;i<users.length;i++) {
						String id = users[i].split("id=")[1].split(",")[0];
						String pw = users[i].split("pw=")[1].split(",")[0];
						String name = users[i].split("name=")[1].split(",")[0];
						int userCode = Integer.parseInt(users[i].split("userCode=")[1].split(",")[0]);
						User user = new User(userCode, id, pw, name);
						
						userlist.add(user);
					}
					
					String[] accs = lines[1].split("Account ");
					for(int i=1;i<accs.length;i++) {
						int userCode = Integer.parseInt(accs[i].split("userCode=")[1].split(",")[0]);
						int accNumber = Integer.parseInt(accs[i].split("accNumber=")[1].split(",")[0]);
						int accPw = Integer.parseInt(accs[i].split("accPw=")[1].split(",")[0]);
						int money = Integer.parseInt(accs[i].split("money=")[1].split("]")[0]);
			
						Account acc = new Account(userCode, accNumber, accPw, money);
						
						acclist.add(acc);
					}
					
					for(User user : userlist) {
						for(Account acc : acclist) {
							if(user.getUserCode() == acc.getUserCode()) {
								user.getAccs().add(acc);
							}
						}
					}
					
					userManager.setList(userlist);
					accountManager.setList(acclist);
				}
				
				
				
				
				br.close();
				fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("파일 없음");
		}
	}
}