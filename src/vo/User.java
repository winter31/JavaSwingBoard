package vo;

import java.io.Serializable;

public class User implements Serializable {
	private String id;
	private String password;
	/**
	 * @param id
	 * @param password
	 */
	public User(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return " ID : " + id + ", PassWord : " + password;
	}
	
}
