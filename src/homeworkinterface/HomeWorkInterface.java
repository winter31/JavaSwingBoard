package homeworkinterface;

import java.util.ArrayList;
import java.util.HashMap;

import vo.Board;
import vo.User;

public interface HomeWorkInterface {
	//user���
	public boolean userAdd(User user);
	//board ���
	public boolean boardAdd(Board board);
	//user ����
	public boolean userDelete(String userId);
	//board ����
	public boolean boardDelete(int boardNo);
	//���� ���� ��ü���(list)�� �� ��; ��ü ��� ������ �� �Ϳ��� Text�� ���� list�� ���;
	public ArrayList<Board> intoTheList();
	//board ���� 
	public boolean updateBoard(Board board);
	//Array  ��� ���� �������� (��¥ �˻�����̳�, �˻�, �Խù� �ѹ��� �˻� ���� �ϰԲ�);
	//board �˻�(userid�� �˻��ؼ� ������ userid�� ���� �Խù� ���);
	public ArrayList<Board> samethingsBoardShow(String something);
	//Hashmap ��� ���� ��������(�ʿ���ٰ� ���������� Ȥ�ó� �ؼ� �־���);
	public HashMap<String, User> getAllData();
	//Vo.User �� Board �� SaveFile �� readFile�� ���� �ٸ��� ������༭ �� �żҵ� 4��, ���� 2���� �����Ǿ���.
}
