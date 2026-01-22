package jp.co.sfrontier.ss3.game.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.entity.UserInformationTbl;
import jp.co.sfrontier.ss3.game.mapper.UserMapper;

/**
 * パスワード変更を行うサービスクラス
 */
@Service
public class ChangePasswordService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ユーザ名またはメールアドレスに対応するパスワードを変更する
	 *
	 * @param userNameOrMailAddress
	 * @param rawPassword
	 * @return
	 */
	public boolean changePassword(String userNameOrMailAddress, String rawPassword) {

		// ユーザ情報を取得する
		UserInformationTbl user = userMapper.findByUserNameOrMailAddress(userNameOrMailAddress);
		if (user == null) {
			return false;
		}

		// ユーザ情報があれば、パスワードをハッシュ化して更新する
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);

		userMapper.updatePassword(user);

		return true;
	}

}
