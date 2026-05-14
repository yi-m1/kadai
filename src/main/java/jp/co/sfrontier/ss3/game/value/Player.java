package jp.co.sfrontier.ss3.game.value;

import java.io.Serializable;

import jp.co.sfrontier.ss3.game.service.core.GameAction;

/**
 * プレイヤーの情報を保持するクラス<br>
 * <br>
 */
public class Player<T extends GameAction> implements Serializable {

	private static final long serialVersionUID = 1L;

	/** ユーザーID */
	private final int userId;

	/**  プレイヤーの行動 */
	private final T action;

	public Player(int userId, T action) {
		this.userId = userId;
		this.action = action;
	}

	public int getUserId() {
		return userId;
	}

	public T getAction() {
		return action;
	}

}
