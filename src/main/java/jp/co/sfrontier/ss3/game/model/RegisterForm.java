package jp.co.sfrontier.ss3.game.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterForm {

	@NotBlank(message = "メールアドレスを入力してください。")
	@Email(message = "メールアドレスの形式が正しくありません。")
	private String mailAddress;

	@NotBlank(message = "ユーザ名を入力してください。")
	@Size(max = 100, message = "ユーザ名は100文字以内で入力してください。")
	private String userName;

	@NotBlank(message = "パスワードを入力してください。")
	@Size(min = 8, max = 32, message = "パスワードは8～32文字で入力してください。")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "パスワードは英大文字・英小文字・数字を含める必要があります。")
	private String password;

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
