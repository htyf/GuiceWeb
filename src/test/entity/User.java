package test.entity;

public class User {

	private int id;
	
	private String account;
	
	private int user_id;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String account, int user_id) {
		super();
		this.account = account;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", user_id="
				+ user_id + "]";
	}
	
	
}
