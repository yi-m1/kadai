/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.core;

import jp.co.sfrontier.ss3.game.common.ResultCode;

/**
 * ゲーム固有処理を定義するインタフェース<br>
 * <br>
 */
public interface GameRule<T> {

	ResultCode judge(T player1Action, T player2Action);
}
