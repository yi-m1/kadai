/**
 * 
 */
package jp.co.sfrontier.ss3.game.service.game.core;

import jp.co.sfrontier.ss3.game.common.ResultCode;

/**
 *  ゲームルールの共通処理を定義するクラス
 * @param <T> プレイヤーの行動の型
 */
public abstract class AbstractGameRule<T extends GameAction> implements GameRule<T, BattleResult> {

	/**
	 * 勝敗を判定し、対戦結果を生成する
	 */
	@Override
	public BattleResult judge(T player1, T player2) {
		
		ResultCode resultCode = doJudge(player1, player2);
		
		return new BattleResult(resultCode);
	}
	
	protected abstract ResultCode doJudge(T player1,T player2);
	
}
