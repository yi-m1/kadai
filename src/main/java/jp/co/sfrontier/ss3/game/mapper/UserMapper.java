package jp.co.sfrontier.ss3.game.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sfrontier.ss3.game.entity.user.UserInformation;

/**
 * user_information_tbl の Mapper
 */
@Mapper
public interface UserMapper {

	/**
	 * ユーザ名で検索するインターフェース
	 *
	 * @param userName
	 * @return
	 */
	UserInformation findByUserName(@Param("userName") String userName);

	/**
	 * メールアドレスで検索するインターフェース
	 *
	 * @param mailAddress
	 * @return
	 */
	UserInformation findByMailAddress(@Param("mailAddress") String mailAddress);

	/**
	 * ユーザ名またはメールアドレスで検索するインターフェース
	 *
	 * @param userNameOrMailAddress
	 * @return
	 */
	UserInformation findByUserNameOrMailAddress(@Param("userNameOrMailAddress") String userNameOrMailAddress);

	/**
	 * 新規ユーザを追加するインターフェース
	 *
	 * @param user
	 */
	void addUser(UserInformation user);

	/**
	 * パスワードを変更するインターフェース
	 *
	 * @param user
	 */
	void updatePassword(UserInformation user);

}
