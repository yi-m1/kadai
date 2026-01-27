package jp.co.sfrontier.ss3.game.dto;

import lombok.Data;

@Data
public class MatchResultRequest {

	/**
	 * ユーザー名
	 */
	private String userName;

	/**
	 * "じゃんけん"/"あっちむいてほい"（表示用）
	 */
	private String gameTypeName;

	/**
	 * 対戦日時
	 */
	private String executeDatetime;

	/**
	 * 対戦相手
	 */
	private String opponent;

	/**
	 * 勝敗
	 */
	private String result;
}
