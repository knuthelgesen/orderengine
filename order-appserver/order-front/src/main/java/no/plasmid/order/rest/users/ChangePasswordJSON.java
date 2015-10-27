package no.plasmid.order.rest.users;

public class ChangePasswordJSON {

	private String password;
	private String passwordRepeat;

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordRepeat() {
		return passwordRepeat;
	}
	
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

}
