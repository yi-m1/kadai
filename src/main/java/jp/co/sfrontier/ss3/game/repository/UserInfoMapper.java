/**
 * UserInfoMapper.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.sfrontier.ss3.game.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.sfrontier.ss3.game.dto.UserSearchRequest;
import jp.co.sfrontier.ss3.game.dto.UserUpdateRequest;
import jp.co.sfrontier.ss3.game.model.UserInfoModel;

/**
 * ユーザー情報のテーブルアクセスを行う Mapper インタフェース<br>
 * <br>
 */
@Mapper
public interface UserInfoMapper {

	// 全件取得する
	List<UserInfoModel> findAll();

	// 1件取得する
	UserInfoModel findById(Long id);

	// 条件検索する
	List<UserInfoModel> search(UserSearchRequest user);

	// ユーザーの状態のを新する
	int updateStatus(UserUpdateRequest userUpdateRequest);
}
