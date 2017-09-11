package homeworkinterface;

import java.util.ArrayList;
import java.util.HashMap;

import vo.Board;
import vo.User;

public interface HomeWorkInterface {
	//user등록
	public boolean userAdd(User user);
	//board 등록
	public boolean boardAdd(Board board);
	//user 삭제
	public boolean userDelete(String userId);
	//board 삭제
	public boolean boardDelete(int boardNo);
	//내용 빼고 전체출력(list)에 들어갈 것; 전체 출력 가지고 온 것에서 Text만 빼고 list에 출력;
	public ArrayList<Board> intoTheList();
	//board 수정 
	public boolean updateBoard(Board board);
	//Array  모든 파일 가져오기 (날짜 검색출력이나, 검색, 게시물 넘버로 검색 가능 하게끔);
	//board 검색(userid로 검색해서 같으면 userid와 같은 게시물 출력);
	public ArrayList<Board> samethingsBoardShow(String something);
	//Hashmap 모든 파일 가져오기(필요없다고 생각되지만 혹시나 해서 넣어줌);
	public HashMap<String, User> getAllData();
	//Vo.User 와 Board 의 SaveFile 과 readFile은 각각 다르게 만들어줘서 총 매소드 4개, 파일 2개가 생성되야함.
}
