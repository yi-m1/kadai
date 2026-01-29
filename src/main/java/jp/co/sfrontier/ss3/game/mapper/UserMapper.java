package jp.co.sfrontier.ss3.game.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import jp.co.sfrontier.ss3.game.entity.UserInformationTbl;

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
	UserInformationTbl findByUserName(@Param("userName") String userName);

	/**
	 * メールアドレスで検索するインターフェース
	 *
	 * @param mailAddress
	 * @return
	 */
	UserInformationTbl findByMailAddress(@Param("mailAddress") String mailAddress);

	/**
	 * ユーザ名またはメールアドレスで検索するインターフェース
	 *
	 * @param userNameOrMailAddress
	 * @return
	 */
	UserInformationTbl findByUserNameOrMailAddress(@Param("userNameOrMailAddress") String userNameOrMailAddress);

	/**
	 * 新規ユーザを追加するインターフェース
	 *
	 * @param user
	 */
	void addUser(UserInformationTbl user);

	/**
	 * パスワードを変更するインターフェース
	 *
	 * @param user
	 */
	void updatePassword(UserInformationTbl user);

}
