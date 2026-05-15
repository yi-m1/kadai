/**
 * 
 */
package jp.co.sfrontier.ss3.game.repository.core;

import jp.co.sfrontier.ss3.game.entity.history.ResultHistory;

/**
 * 対戦履歴用のリポジトリ共通処理を定義するインタフェース<br>
 * <br>
 */
public interface ResultHistoryRepository {

	/**
	 * 対戦履歴を保存する
	 */
	void save(ResultHistory history);
}
