package jp.co.sfrontier.ss3.game.service.core;

import jp.co.sfrontier.ss3.game.common.ResultCode;

/**
 * ゲーム共通の進行処理を提供するクラス<br>
 * <br>
 */
public abstract class GameService<T> {

	private final OpponentActionStrategy<T> opponentActionStrategy;

	private final GameRule<T> gameRule;

	public GameService(OpponentActionStrategy<T> opponentActionStrategy, GameRule<T> gameRule) {
		this.opponentActionStrategy = opponentActionStrategy;
		this.gameRule = gameRule;
	}

	/**
	 * 相手の行動を取得する
	 */
	protected T getOpponentAction() {

		return opponentActionStrategy.getAction();
	}

	/**
	 * 勝敗を判定する
	 */
	protected ResultCode judge(T player1Action, T player2Action) {

		return gameRule.judge(player1Action, player2Action);
	}

}
