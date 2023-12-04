package udemy.entity;

public class UserEntity {
	private int id;
	private String email;
	private String pass;
	private String fullName;
	private String avar;
	private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getAvar() {
		return avar;
	}
	public void setAvar(String avar) {
		this.avar = avar;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public  String toString() {
		return "Id: " + this.id + " . Full name: " + this.fullName;
	}
	
}
