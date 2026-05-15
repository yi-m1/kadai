/**
 * UserInfoMapper.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.sfrontier.ss3.game.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.sfrontier.ss3.game.dto.user.UserInfoResponse;
import jp.co.sfrontier.ss3.game.dto.user.UserSearchRequest;
import jp.co.sfrontier.ss3.game.dto.user.UserUpdateRequest;

/**
 * ユーザー情報のテーブルアクセスを行う Mapper インタフェース<br>
 * <br>
 */
@Mapper
public interface UserInfoMapper {

	// 全件取得する
	List<UserInfoResponse> findAll();

	// 1件取得する
	UserInfoResponse findById(Long id);

	// 条件検索する
	List<UserInfoResponse> search(UserSearchRequest user);

	// ユーザーの状態のを新する
	int updateStatus(UserUpdateRequest userUpdateRequest);
}
