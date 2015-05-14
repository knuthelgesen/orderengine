package no.plasmid.order.usermanagement.im;

public class User {
	
	private final Integer userId;
  private final String userName;
  private final String roleName;

  public User(Integer userId, String userName, String roleName) {
  	this.userId = userId;
    this.userName = userName;
    this.roleName = roleName;
  }

  public Integer getUserId() {
		return userId;
	}

	public String getUserName() {
    return this.userName;
  }

  public String getRoleName() {
    return this.roleName;
  }
  
}