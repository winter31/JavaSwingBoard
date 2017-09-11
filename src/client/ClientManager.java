package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import homeworkinterface.HomeWorkInterface;
import vo.Board;
import vo.User;

public class ClientManager implements HomeWorkInterface {

	private Socket soc;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public ClientManager() {
		try {
			soc = new Socket("127.0.0.1", 8888);
			System.out.println("to Connect");

			oos = new ObjectOutputStream(soc.getOutputStream());
			ois = new ObjectInputStream(soc.getInputStream());

		} catch (IOException e) {
		}
	}

	@Override
	public boolean userAdd(User user) {
		boolean uResult = false;
		Object[] data = { "addU", user };
		uResult = (Boolean) getAllFiles(data);
		return uResult;
	}

	@Override
	public boolean boardAdd(Board board) {
		boolean uResult = false;
		Object[] data = { "addB", board };
		uResult = (Boolean) getAllFiles(data);
		return uResult;
	}

	@Override
	public boolean userDelete(String userId) {
		boolean userDResult = false;
		Object[] data = { "deleteUser", userId };
		userDResult = (boolean) getAllFiles(data);
		return userDResult;
	}

	@Override
	public boolean boardDelete(int boardNo) {
		boolean boardDResult = false;
		Object[] data = { "deleteBoard", boardNo };
		boardDResult = (boolean) getAllFiles(data);
		return boardDResult;
	}

	@Override
	public ArrayList<Board> intoTheList() {
		ArrayList<Board> result = null;
		Object[] data = { "boardGetAll", null };
		result = (ArrayList<Board>) getAllFiles(data);
		return result;
	}

	@Override
	public boolean updateBoard(Board board) {
		boolean xx = false;
		Object[] data = { "update", board };
		xx = (boolean) getAllFiles(data);
		return xx;
	}

	@Override
	public ArrayList<Board> samethingsBoardShow(String something) { /// 고치야함
		ArrayList<Board> result1 = new ArrayList<Board>();
		String z = something;
		Object[] data = { "boardGetAll", null };
		ArrayList<Board> list1= (ArrayList<Board>) getAllFiles(data);
		for(Board bb1 : list1){
			String x = new Integer(bb1.getNum()).toString();
			if(bb1.getTitle().equals(z)){
				result1.add(bb1);
			}else if(bb1.getuId().equals(z)){
				result1.add(bb1);
			}else if(bb1.getDate().equals(z)){
				result1.add(bb1);
			}else if(x.equals(z)){
				result1.add(bb1);
			}
		}
		return result1;
	}
	
	
	
	public boolean login(String id, String pw){
		boolean result = false;
		Object[] data = {"userGetAll", null};
		HashMap<String, User> hm = (HashMap<String, User>) getAllFiles(data);
			for(User t : hm.values()){
//				System.out.println(t.toString());
				if(t.getId().equals(id) && t.getPassword().equals(pw)){
					result = true;
				}
			}
		return result;
	}
	public boolean delete(String id, String pw){
		boolean result = false;
		Object[] data ={"userGetAll", null};
		HashMap<String, User> hm = (HashMap<String, User>) getAllFiles(data);
		for(User t : hm.values()){
			if(t.getId().equals(id) && t.getPassword().equals(pw)){
				result = true;
			}
		}
	return result;		
	}
	public int countNum(int count){
		int result = 1;
		Object[] data = { "boardGetAll", null };
		ArrayList<Board> comparec = (ArrayList<Board>) getAllFiles(data);
		for (Board k : comparec) {
			if (k.getNum() == count) {
				result++;
			} else {	
				result = k.getNum() + 1;
			}
		}
		return result;
	}
	
	public String passWordCheck(String pw){
		String password = null;
		Object[] data ={"userGetAll", null};
		HashMap<String, User> hm = (HashMap<String, User>) getAllFiles(data);
		for(User t : hm.values()){
			if(t.getPassword().equals(pw)){
				password = t.getPassword();
			}
		}
		return password;
	}
	
	@Override
	public HashMap<String, User> getAllData() {
		HashMap<String, User> result = null;
		Object[] data = { "userGetAll", null };
		result = (HashMap<String, User>) getAllFiles(data);
		return result;
	}

	public Object getAllFiles(Object[] date) {
		Object dataResult = null;
		try {
			oos.writeObject(date);
			dataResult = ois.readObject();

			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataResult;
	}

	public void CloseAll() throws IOException {
		soc.close();
		oos.close();
		ois.close();
	}
}
