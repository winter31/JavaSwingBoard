package sever;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	private ServerSocket ssoc;
	
	
	public ServerMain() {
		try {
			
			ssoc = new ServerSocket(8888);
			System.out.println("Sever Strat");
			
			while(true){
				Socket soc = ssoc.accept();
				System.out.println("Who is Connected : "+soc.getInetAddress() +"The Port : " + soc.getPort());
				ServerThread sth = new ServerThread(soc);
				sth.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ServerMain();
	}
}
