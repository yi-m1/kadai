/**
 * UserSearchRequest.java
 *
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 */

package jp.co.sfrontier.ss3.game.dto;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * ユーザー情報　検索用リクエストデータ
 */
@Data
public class UserSearchRequest implements Serializable{

	/**
	 * ユーザーID
	 */
	private Long id;

	/**
	 * ユーザー名
	 */
	private String name;
}
