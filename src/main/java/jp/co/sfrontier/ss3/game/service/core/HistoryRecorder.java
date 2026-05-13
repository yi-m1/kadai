/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.core;

import java.sql.SQLException;

import jp.co.sfrontier.ss3.game.value.Player;

/**
 * 対戦履歴保存処理を定義するインターフェース<br>
 * <br>
 */
public interface HistoryRecorder {

	/**
	 * 対戦履歴を保存する
	 * 
	 * @param player1 player1の情報
	 * @param player2 player2の情報
	 * @param battleResult 対戦結果
	 * @throws SQLException DB アクセス時にエラーが発生した場合
	 */
	public void record(Player player1,Player player2,BattleResult battleResult) throws SQLException;
}
