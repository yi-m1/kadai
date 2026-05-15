/**
 * UserInfoModel.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.sfrontier.ss3.game.dto.user;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * ユーザー情報を表すモデルクラス。
 */
@Data
public class UserInfoResponse {

	private Long userId;
	private String userName;
	private String mailAddress;
	private String password;
	private Integer status;
	private LocalDateTime createDatetime;
	private LocalDateTime updateDatetime;
	private Integer version;
}
