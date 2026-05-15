package jp.co.sfrontier.ss3.game.service.game.core;

/**
 * ゲーム共通の進行処理を提供するクラス<br>
 * <br>
 * 具体的なゲーム処理は、本クラスを継承したサービスクラスにて実装する。
 * 
 * @param <T> ゲーム内で使用する行動の型
 * @param <R> ゲーム結果の型
 */
public abstract class AbstractGameService<T extends GameAction, R extends GameResult> {

	private final CpuActionStrategy<T> cpuActionStrategy;

	private final GameRule<T, R> gameRule;

	public AbstractGameService(CpuActionStrategy<T> opponentActionStrategy, GameRule<T, R> gameRule) {
		this.cpuActionStrategy = opponentActionStrategy;
		this.gameRule = gameRule;
	}

	/**
	 * CPU の行動を取得する
	 * 
	 * @return CPU が選択した行動
	 */
	protected T getOpponentAction() {

		return cpuActionStrategy.getAction();
	}

	/**
	 * 2人のプレイヤーの行動から勝敗を判定する
	 * 
	 * @param player1 player1の行動
	 * @param player2 player2の行動
	 * @return 判定結果
	 */
	protected R judge(T player1, T player2) {

		return gameRule.judge(player1, player2);
	}

}