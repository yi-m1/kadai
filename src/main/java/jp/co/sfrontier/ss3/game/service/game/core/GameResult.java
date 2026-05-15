/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.game.core;

import jp.co.sfrontier.ss3.game.common.ResultCode;

/**
 * ゲーム結果を表すインタフェース<br>
 * <br>
 */
public interface GameResult {
	
	/**
	 * 勝敗結果を取得する
	 */
	ResultCode getResultCode();
	
	/**
	 * 勝者情報を取得する
	 */
	// TODO 勝者情報取得メソッドを追加する
}
