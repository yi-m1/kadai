package jp.co.sfrontier.ss3.game.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jp.co.sfrontier.ss3.game.validation.annotation.PasswordMatches;

@PasswordMatches
public class ChangePasswordForm {

	@NotBlank(message = "登録しているユーザ名またはメールアドレスを入力してください。")
	private String userNameOrMailAddress;

	@NotBlank(message = "新しいパスワードを入力してください。")
	@Size(min = 8, max = 32, message = "パスワードは8～32文字で入力してください。")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "パスワードは英大文字・英小文字・数字を含める必要があります。")
	private String newPassword;

	@NotBlank(message = "新しいパスワードを入力してください。")
	@Size(min = 8, max = 32, message = "パスワードは8～32文字で入力してください。")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "パスワードは英大文字・英小文字・数字を含める必要があります。")
	private String confirmPassword;

	public String getUserNameOrMailAddress() {
		return userNameOrMailAddress;
	}

	public void setUserNameOrMailAddress(String userNameOrMailAddress) {
		this.userNameOrMailAddress = userNameOrMailAddress;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
