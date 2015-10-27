package no.plasmid.order.usermanagement.im;

public class UserEntity {

	private final Integer userId;
	private final String userName;
	private String hashedPassword;
	private String roleName;
	
	public UserEntity(Integer userId, String userName, String hashedPassword, String roleName) {
		this.userId = userId;
		this.userName = userName;
		this.hashedPassword = hashedPassword;
		this.roleName = roleName;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
