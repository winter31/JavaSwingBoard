package client;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import javazoom.jl.player.Player;

public class Music1 extends Thread {
	Player p = null;
	File f = new File("aaaa.mp3");
//	File f2 = new File("C:/Users/07-22/Desktop/32±â _c¹Ý_±è±æ¼·/Eclipse/BoardLoginhomework");
//	Scanner sc = new Scanner("C:/Users/07-22/Desktop/32±â _c¹Ý_±è±æ¼·/Eclipse/BoardLoginhomework");
	
	FileInputStream fis;
	@Override
	public void run() {
//		if(sc.hasNextLine()){
//			for(int x = 0; x<f1.length; x++){
//				
//			}
//		}
//		int index = 0;
//		File[] f1 = f2.listFiles();
//		for (int q = 0; q < index; q++) {
//			if(sc.hasNextLine()){
//			f1[index] = f1[q]; 
//			index++;
//		}

		while(true){
		try {
			fis = new FileInputStream(f);
			p = new Player(fis);
			System.out.println("Music Start");
			p.play();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		super.run();
		}
	}
}
