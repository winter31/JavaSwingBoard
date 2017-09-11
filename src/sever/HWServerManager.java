package sever;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import homeworkinterface.HomeWorkInterface;
import vo.Board;
import vo.User;

public class HWServerManager implements HomeWorkInterface {
	private File fu = new File("User.dat");
	private File fb = new File("Board.dat");
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private FileOutputStream fos;
	private FileInputStream fis;
	private ArrayList<Board> boardlist;
	private HashMap<String, User> userlist;

	public HWServerManager() {
		boardlist = new ArrayList<>();
		userlist = new HashMap<String, User>();

		if (fu.exists()) {
			readUser();
		} else {
			userlist = new HashMap<String, User>();
			saveUser();
		}
		if (fb.exists()) {
			readBoard();
		} else {
			boardlist = new ArrayList<>();
			saveBoard();
		}
	}

	@Override
	public boolean userAdd(User user) {
		boolean userresult = false;
		User us = null;
		if (fu.exists()) {
			this.readUser();
			for (User u : userlist.values()) {
				if (u.getId().equals(user.getId())) {
					us = u;
				}
			}
			if (us == null) {
				userlist.put(user.getId(), user);
				saveUser();
				userresult = true;
			}
		}
		return userresult;
	}

	@Override
	public boolean boardAdd(Board board) {
		boolean resultB = false;
		int bd = 0;
		if (fb.exists()) {
			readBoard();
			for (Board b : boardlist) {
				if (b.getNum() == board.getNum()){
					bd = b.getNum();
					}
				}
			if (bd == 0) {
				boardlist.add(board);
				saveBoard();
				resultB = true;
			}
		}
		return resultB;
	}

	@Override
	public boolean userDelete(String userId) {
		boolean result = false;
		if (fu.exists()) {
			readUser();
			for (User u : userlist.values()) {
				if (u.getId().equals(userId)) {
					userlist.remove(u.getId());
					saveUser();
					result = true;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public boolean boardDelete(int boardNo) {
		boolean result = false;
		if (fb.exists()) {
			readBoard();
			for (Board b : boardlist) {
				if (b.getNum()==boardNo) {
					boardlist.remove(b);
					saveBoard();
					result = true;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public ArrayList<Board> intoTheList() {
		ArrayList<Board> showResult = null;
		if (fb.exists()) {
			readBoard();
			showResult = boardlist;
		}
		return showResult;
	}

	@Override // 나중에 작성----------------------
	public boolean updateBoard(Board board) {
		boolean result = false;
		int x = 0;
		if (fb.exists()) {
			readBoard();
			for (Board b : boardlist) {
				if (b.getNum() == board.getNum()) {
					x = boardlist.indexOf(b);
					boardlist.set(x,board);
					saveBoard();
					result = true;
				}
			}
		}
		return result;
	}

	@Override // 필요시 작성
	public ArrayList<Board> samethingsBoardShow(String something) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, User> getAllData() {
		HashMap<String, User> result = null;
		if (fu.exists()) {
			readUser();
			result = userlist;
		}
		return result;
	}

	public void readUser() {
		try {
			fis = new FileInputStream(fu);
			ois = new ObjectInputStream(fis);

			userlist = (HashMap<String, User>) ois.readObject();

			ois.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveUser() {
		try {
			fos = new FileOutputStream(fu);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(userlist);
			
			oos.flush();
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readBoard() {
		try {
			fis = new FileInputStream(fb);
			ois = new ObjectInputStream(fis);

			boardlist = (ArrayList<Board>) ois.readObject();

			ois.close();
			fis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveBoard() {
		try {
			fos = new FileOutputStream(fb);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(boardlist);

			oos.flush();
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
