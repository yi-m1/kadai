/**
 * UserInfoService.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.sfrontier.ss3.game.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sfrontier.ss3.game.dto.UserSearchRequest;
import jp.co.sfrontier.ss3.game.dto.UserUpdateRequest;
import jp.co.sfrontier.ss3.game.model.UserInfoModel;
import jp.co.sfrontier.ss3.game.repository.UserInfoMapper;


/**
 * ユーザー情報 Service
 */
@Service
public class UserInfoService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	/**
	 * ユーザー情報全件検索
	 */
	public List<UserInfoModel> findAll(){
		return userInfoMapper.findAll();
	}

	/**
	 * ユーザー情報主キー検索
	 */
	public UserInfoModel findById(Long id) {
		return userInfoMapper.findById(id);
	}

	/**
	 * ユーザー情報検索
	 */
	public List<UserInfoModel> search(UserSearchRequest request){
		return userInfoMapper.search(request);
	}

	/**
	 * ユーザー状態更新(statusのみ)
	 * @return 更新成功:true / 失敗;false
	 */
	public boolean updateStatus(UserUpdateRequest request) {
		int count = userInfoMapper.updateStatus(request);
		return count == 1;
	}
}
