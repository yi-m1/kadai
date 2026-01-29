package jp.co.sfrontier.ss3.game.model;

public class LoginForm {

	private String userNameOrMailAddress;
	private String password;

	public String getUserNameOrMailAddress() {
		return userNameOrMailAddress;
	}

	public void setUserNameOrMailAddress(String userNameOrMailAddress) {
		this.userNameOrMailAddress = userNameOrMailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
