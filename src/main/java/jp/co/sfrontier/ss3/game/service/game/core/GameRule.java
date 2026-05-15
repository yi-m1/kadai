package jp.co.sfrontier.ss3.game.service.game.core;

/**
 * ゲーム固有処理を定義するインタフェース<br>
 * <br>
 * ゲームごとのルール実装クラスにて、本インタフェースを実装する。
 * 
 * @param <T> プレイヤーの行動を表す型
 * @param <R> 対戦結果を表す型
 */
public interface GameRule<T extends GameAction, R extends GameResult> {

	/**
	 * 2人のプレイヤーの行動から対戦結果を判定する
	 * 
	 * @param player1 player1の行動
	 * @param player2 player2の行動
	 * @return 対戦結果
	 */
	public R judge(T player1, T player2);

}
