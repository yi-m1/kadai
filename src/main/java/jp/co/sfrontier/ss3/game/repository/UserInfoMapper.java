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

@Mapper
public interface UserInfoMapper {

	List<UserInfoModel> findAll();

	UserInfoModel findById(Long id);

	List<UserInfoModel> search(UserSearchRequest user);

	int updateStatus(UserUpdateRequest userUpdateRequest);
}
