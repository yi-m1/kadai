package jp.co.sfrontier.ss3.game.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.entity.user.UserInformation;
import jp.co.sfrontier.ss3.game.mapper.UserMapper;

@Service
public class RegisterService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public enum UserCheckResult {
		NOT_FOUND, FOUND;
	}

	/**
	 * ユーザIDとメールアドレスの重複チェックを行う
	 */
	public UserCheckResult checkUserExists(String userName, String mailAddress) {
		if (userMapper.findByUserName(userName) != null || userMapper.findByMailAddress(mailAddress) != null) {
			return UserCheckResult.FOUND;
		}
		return UserCheckResult.NOT_FOUND;
	}

	/**
	 * 新規ユーザを登録する
	 */
	public void register(String userName, String mailAddress, String password) {

		// 平文のパスワードをハッシュ化する
		String encodedPassword = passwordEncoder.encode(password);

		UserInformation user = new UserInformation();
		user.setUserName(userName);
		user.setMailAddress(mailAddress);
		user.setPassword(encodedPassword);
		user.setStatus(1);
		userMapper.addUser(user);
	}
}