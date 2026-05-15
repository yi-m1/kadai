/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.game.core;

import java.sql.SQLException;

import jp.co.sfrontier.ss3.game.common.model.Player;

/**
 * 対戦履歴作成処理を定義するインターフェース<br>
 * <br>
 */
public interface HistoryRecorder <T extends GameAction ,R extends GameResult>{

	/**
	 * 対戦履歴を保存する
	 * 
	 * @param player1 player1の情報
	 * @param player2 player2の情報
	 * @param battleResult 対戦結果
	 * @throws SQLException DB アクセス時にエラーが発生した場合
	 */
	public void record(Player<T> player1,Player<T> player2,R battleResult) ;
}
