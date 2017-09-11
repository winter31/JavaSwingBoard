package sever;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Board;
import vo.User;

public class ServerThread extends Thread {

	private HWServerManager manager = new HWServerManager();
	private Socket soc;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public ServerThread(Socket soc) {
		this.soc = soc;
		openStreaming();
	}

	@Override
	public void run() {
		try {
			while (soc.isConnected()) {
				Object[] oa = (Object[]) ois.readObject();
				String command = (String) oa[0];
				Object operate = (Object) oa[1];
			
				switch (command) {
				case "addU":
					System.out.println("Insert IDs and Passwords");
					boolean addUResult1 = manager.userAdd((User) operate);
					oos.writeObject(addUResult1);
					break;
				case "addB":
					System.out.println("Insert Postings");
					boolean addBResult2 = manager.boardAdd((Board) operate);
					oos.writeObject(addBResult2);
					break;
				case "userGetAll":
					System.out.println("UserData(ID,Password) OutPutting");
					HashMap<String, User> hm = manager.getAllData();
					oos.writeObject(hm);
					break;
				case "boardGetAll":
					System.out.println("BoardData OutPutting");
					ArrayList<Board> alb = manager.intoTheList();
					oos.writeObject(alb);
					break;
				case "deleteUser":
					System.out.println("DeleteUser Function");
					boolean DeleteUResult1 = manager.userDelete((String) operate);
					oos.writeObject(DeleteUResult1);
					break;
				case "deleteBoard":
					System.out.println("DeleteBoard Function");
					boolean DeleteBResult2 = manager.boardDelete((int) operate);
					oos.writeObject(DeleteBResult2);
					break;
				case "update":
					System.out.println("update");
					boolean upade = manager.updateBoard((Board) operate);
					oos.writeObject(upade);
					break;
				}
			}
		} catch (Exception e) {
			System.out.print("Cutted Server");
			System.out.println(soc.getInetAddress() +","+ soc.getPort());
		}
		super.run();
	}

	public void openStreaming() {
		try {
			ois = new ObjectInputStream(soc.getInputStream());
			oos = new ObjectOutputStream(soc.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
