/**
 * UserUpdateRequest.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.sfrontier.ss3.game.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * ユーザー情報更新リクエストデータ(status更新用)
 */
@Data
public class UserUpdateRequest implements Serializable{

	/**
	 * ユーザーID
	 */
	@NotNull
	private Long userId;

	/**
	 * ユーザー状態（1:有効 / 0:無効）
	 */
	@NotNull
	private Integer status;

	/**
	 * ロック用バージョン
	 */
	@NotNull
	private Integer version;


}
